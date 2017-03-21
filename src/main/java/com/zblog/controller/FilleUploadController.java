package com.zblog.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zblog.util.FileBean;
import com.zblog.util.FilesPros;
import com.zblog.util.ImageUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/common")
public class FilleUploadController {


	private static final Log log = LogFactory
			.getLog(FilleUploadController.class);

	@RequestMapping(value = "/uploadImg.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> uploadAttachment(HttpServletRequest request,
												HttpServletResponse response, FileBean bean, String num, String type)
			throws IOException {
	
		Map<String, Object> map = new HashMap<String, Object>();
		Up(map, bean);
		return map;
	}
 public void Up( Map<String, Object> map,FileBean bean){
	 int status = 0;
	 try {
			/* 文件基础信息 */
			// 生成新文件名称
			bean.setNewImgName(ImageUtil.getNewName(bean));
			// 获取webRoot路径
			String webRootPath = FilesPros.getProper("IMAGE_DIRECTORY");
			// String webRootPath = this.getWebContextPath(request);
			// 获得新文件存储路径
			bean.setNewImgPath(ImageUtil.getNewFilePath(webRootPath, bean));
			// 临时变量用于存储文件存放路径
			bean.setTmpImgPath(bean.getNewImgPath());
			// 设置源文件存储路径
			bean.setOldImgPath(ImageUtil.getOldFilePath(bean));
			System.out.println("------------------------1"
					+ bean.getNewImgPath());
			if (bean.getUploadFile() != null
					&& bean.getUploadFile().getSize() != 0) {
				/* 生成文件及文件夹操作 */
				System.out.println("------------------------2"
						+ bean.getNewImgPath());
				System.out.println("------------------------3"
						+ bean.getOldImgPath());
				System.out.println("------------------------4"
						+ bean.getUploadFile().getOriginalFilename());
				status = ImageUtil.imageCreate(bean.getNewImgPath(),
						bean.getOldImgPath(), bean.getUploadFile());
				System.out.println("status" + status);
				if (status != 0) {
					map.put("success", false);
					map.put("attachmentPath", "");
					map.put("attachmentName", "");
				} else {
					map.put("success", true);
					map.put("attachmentPath", getAttachmentURLPath(bean));
					map.put("attachmentName", bean.getUploadFile()
							.getOriginalFilename());
				}
			}
			map.put("serverpath", getImgURLPath(bean));
			map.put("error", "");
			map.put("msg", "上传成功!");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("error", "上传失败!");
		}
 }
	/**
	 * @DESC 获得文件的URL访问路径
	 * @param bean
	 * @return
	 */
	public String getImgURLPath(FileBean bean) {
		String dir = "zhang";
		System.out.println(bean.getOldImgPath() + "111111111111111");
		return bean.getOldImgPath().substring(
				bean.getOldImgPath().indexOf(dir) - 1);
	}



	/**
	 * @DESC 获得文件的URL访问路径
	 * @param bean
	 * @return
	 */
	public String getAttachmentURLPath(FileBean bean) {
		String dir = bean.getImgSubDirName();
		String tmp = bean.getOldImgPath().substring(0,
				bean.getOldImgPath().lastIndexOf('.'));
		return tmp.substring(tmp.indexOf(dir) - 1);
	}

	public String getAttachmentServerPath(FileBean bean) {
		String url = bean.getImgUrl();
		String dir = bean.getImgSubDirName();
		url = url
				+ bean.getOldImgPath().substring(
						bean.getOldImgPath().indexOf(dir) - 1,
						bean.getOldImgPath().length());
		return url;
	}
}