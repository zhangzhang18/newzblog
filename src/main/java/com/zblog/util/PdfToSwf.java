package com.zblog.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ray.wang
 * @date 2013-4-24 下午05:52:27
 * @DESC PDF转SWF工具类
 */
public class PdfToSwf {

	private final static int ERROR_FILE_NOT_EXIST = -1;

	private final static int ERROR_WRONG_EXT_NAME = -2;

	private final static int ERROR_HAS_EXCEPTION = -3;

	private final static String PDF2SWF_PATH = PropertyUtil.getProper("PDF2SWF_PATH");

	private final static String LANGDIR = PropertyUtil.getProper("PDF2SWF_LANGDIR");

	private static int count = 0;

	/**
	 * 将pdf文件转化成swf文件
	 * 
	 * @param fileName
	 *            文件的绝对路径(含文件名和扩展名)
	 * @param destPath
	 *            目标路径 (目标文件夹,不含文件名)
	 * @return -1：源文件不存在,-2:格式不正确,-3：发生异常,0:转化成功;1:无法打开文件(Process.exitValue())
	 * @author ray.wang
	 */
	public static int convertPdfToSwf(String fileName, String destPath) {
		if (null != fileName && fileName.length() > 0) {
//          String destName = "";
			String fileExt = "";
			StringBuffer command = new StringBuffer();
			Process pro;
			BufferedReader buffer;
			fileExt = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
			// 创建一个线程池
			int taskSize = 2;
			ExecutorService pool = Executors.newFixedThreadPool(taskSize);
			try {
				File file = new File(fileName);
				if (!file.exists()) {// 判断源文件是否存在
					return ERROR_FILE_NOT_EXIST;
				} else if (!fileExt.equals("pdf")) {// 判断文件是否是pdf格式的文件
					return ERROR_WRONG_EXT_NAME;
				} else {

					// " -Q 300"参数只在linux下有效
//					command.append(PropertyUtil.getProper("PDF2SWF_PATH")).append("pdf2swf").append(" -o ").append(destPath).append(" -T -z -t -f ")
//					        .append(PDF2SWF_PATH.startsWith("/") ? " -Q 300 " : "").append(fileName).append(" -s languagedir=").append(LANGDIR)
//					        .append(" -s poly2bitmap -s drawonlyshapes -s flashversion=9");
					command.append(PropertyUtil.getProper("PDF2SWF_PATH")).append("pdf2swf").append(" -o ").append(destPath).append(" -T -z -t -f ")
					        .append(PDF2SWF_PATH.startsWith("/") ? " -Q 300 " : "").append(fileName).append(" -s languagedir=").append(LANGDIR)
					        .append(" -s flashversion=9");
					System.out.println(command.toString());
					pro = Runtime.getRuntime().exec(command.toString());
					buffer = new BufferedReader(new InputStreamReader(pro.getInputStream()));

					// 一
					// 创建多个有返回值的任务
//					List<Future> list = new ArrayList<Future>();
//					Callable c = new MyCallable("1", pro.getErrorStream(), "Error");
//					// 执行任务并获取Future对象
//					Future f = pool.submit(c);
//					list.add(f);
//					Callable c1 = new MyCallable("2", pro.getInputStream(), "Output");
//					// 执行任务并获取Future对象
//					Future f2 = pool.submit(c1);
//					list.add(f2);
//					// 关闭线程池
//					pool.shutdown();
//					// 获取所有并发任务的运行结果
//					for (Future f1 : list) {
//						// 从Future对象上获取任务的返回值，并输出到控制台
//						System.out.println(">>>" + f1.get().toString());
//						count = Integer.parseInt(f1.get().toString());
//					}

					// 二
//					StreamGobbler errorGobbler = new StreamGobbler(pro.getErrorStream(), "Error", count);
//					StreamGobbler outputGobbler = new StreamGobbler(pro.getInputStream(), "Output", count);
//					errorGobbler.start();
//					outputGobbler.start();

					// 三
					String str = null;
					int count = 0;
					while ((str = buffer.readLine()) != null) {
						if (str.contains(".swf")) {
							count += 1;
						}
					}

					int rtstate = pro.waitFor();
//					return rtstate;
					return count;
				}
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
				return ERROR_HAS_EXCEPTION;
			}
			finally {
				command = null;
				pro = null;
				buffer = null;
			}
		} else {
			return ERROR_FILE_NOT_EXIST;
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println(convertPdfToSwf("E:/caige/test.pdf", "E:\\caige\\test%.swf"));
	}
}
