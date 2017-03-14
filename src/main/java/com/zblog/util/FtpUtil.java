package com.zblog.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 * 标题、简要说明. <br>
 * 类详细说明. FTP工具类
 * <p>
 * Copyright: RcPlatform,Inc Copyright (c) 2013-7-29 上午10:36:47
 * <p>
 * Team:RcPlatform Beijing
 * <p>
 * 
 * @author rc.wys
 * @version 1.0.0
 */
public class FtpUtil {

	/**
	 * Method description 文件下载
	 * 
	 * @param srcPath
	 * @param dstPath
	 */
	public static int download(String ftpIp, String srcPath, String dstPath) {
		FTPClient ftpClient = new FTPClient();
		FileOutputStream fos = null;

		try {
			System.err.println(FilesPros.getProper("ftp." + ftpIp).trim());
			ftpClient.connect(FilesPros.getProper("ftp." + ftpIp).trim());
//			ftpClient.connect("198.74.58.13");
			ftpClient.login(FilesPros.getProper("FTP_USER").trim(), FilesPros.getProper("FTP_PASSWD").trim());

			String remoteFileName = srcPath;
			File file2 = new File(dstPath);
			File file3 = file2.getParentFile();
			if ((file3 != null) && !file3.exists()) {
				file3.mkdirs();
			}
			fos = new FileOutputStream(dstPath);

			ftpClient.setBufferSize(1024);
			// 设置文件类型（二进制）
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.retrieveFile(remoteFileName, fos);
			return 0;
		}
		catch (IOException e) {
			e.printStackTrace();
//			throw new RuntimeException("FTP客户端出错！", e);
			System.err.println("FTP客户端出错!" + e);
			return -1;
		}
		finally {
			IOUtils.closeQuietly(fos);
			try {
				ftpClient.disconnect();
			}
			catch (IOException e) {
				e.printStackTrace();
//				throw new RuntimeException("关闭FTP连接发生异常！", e);
				System.err.println("关闭FTP连接发生异常!" + e);
				return -1;
			}
		}
	}

	public static void main(String[] args) {
//		upload();
		FtpUtil.download("pic3.rcplatformhk.net", "/opt/pt_apache-tomcat-6.0.35/webapps/photos/pool/20130722/15/1374507944502_42fecf.null",
		                 "D:/apache-tomcat-6.0.18/webapps/rccms/photos/pic4.rcplatformhk.net/pool/20130710/18/1373453024311_78251/1373453024311_78251.zip");
	}

	/**
	 * Method description 文件上传
	 */
	public static void upload() {
		FTPClient ftpClient = new FTPClient();
		FileInputStream fis = null;

		try {
			ftpClient.connect("192.168.14.117");
			ftpClient.login("admin", "123");

			File srcFile = new File("C:\\new.gif");
			fis = new FileInputStream(srcFile);
			// 设置上传目录
			ftpClient.changeWorkingDirectory("/admin/pic");
			ftpClient.setBufferSize(1024);
			ftpClient.setControlEncoding("GBK");
			// 设置文件类型（二进制）
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.storeFile("3.gif", fis);
		}
		catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("FTP客户端出错！", e);
		}
		finally {
			IOUtils.closeQuietly(fis);
			try {
				ftpClient.disconnect();
			}
			catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("关闭FTP连接发生异常！", e);
			}
		}
	}
}