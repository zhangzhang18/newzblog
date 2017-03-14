package com.zblog.util;

import java.io.File;

public class RealImageUtil {

	public static String getImageRealPath(String path) {
		String spBasePath = FilesPros.getProper("SKYPOOL_IMAGE_DIRECTORY");
		int index = path.indexOf('/', 7);
//		System.err.println(spBasePath + path.substring(index));

		// 获取服务器IP地址
		int index_ = path.indexOf("/") + 2;
		int index__ = path.indexOf("/", 7);
		String ftpIp = path.substring(index_, index__);
		if (ftpIp.contains(":")) {
			ftpIp = ftpIp.substring(0, ftpIp.indexOf(':'));
		}
		// 获得服务器上图片绝对路径
		String spRalativePath = path.substring(index);
		String spAbsolutePath = spBasePath + spRalativePath;

		// 去掉扩展名
		int _index = spRalativePath.lastIndexOf('.');
		String tmp = spRalativePath.substring(0, _index);
		StringBuffer spRalativePathNoEt = new StringBuffer("/").append(ftpIp).append(tmp);

		// 获取不带扩展名的文件名
		int __index = tmp.lastIndexOf('/');
		String spRalativeFilenameNoEt = tmp.substring(__index);

//		// 获取带扩展名的文件名
//		String spRalativeFilename = spRalativePath.substring(__index);

		// 获取本服务图片zip存储路径
		StringBuffer imageZipPath = new StringBuffer();
		imageZipPath.append(FilesPros.getProper("IMAGE_DIRECTORY")).append(FilesPros.getProper("IMAGE_SUB_DIRECTORY_NAME"))
		        .append(spRalativePathNoEt).append("/");

		// 获取本服务图片zip文件名
		StringBuffer imageZipFilename = new StringBuffer();
		imageZipFilename.append(FilesPros.getProper("IMAGE_DIRECTORY")).append(FilesPros.getProper("IMAGE_SUB_DIRECTORY_NAME"))
		        .append(spRalativePathNoEt).append("/").append(spRalativeFilenameNoEt).append(Constants.ZIP_EXTENSION);

		// 获取本服务图片存储路径
		StringBuffer imagePath = new StringBuffer();
		imagePath.append(FilesPros.getProper("IMAGE_DIRECTORY")).append(FilesPros.getProper("IMAGE_SUB_DIRECTORY_NAME")).append(spRalativePathNoEt)
		        .append(Constants.PIC_EXTENSION);

		// 判断此图片是否已经存在
		File dir = new File(imagePath.toString());
		if (dir.exists()) {
			// 返回服务器访问路径
//			System.out.println("already exist!");
			return new StringBuffer(FilesPros.getProper("IMAGE_SUB_DIRECTORY_NAME")).append(spRalativePathNoEt).append(Constants.PIC_EXTENSION)
			        .toString();
		}
		// 复制图片至本服务图片存放路径
//		int copyRes = FileOperate.copyFile(spAbsolutePath, imageZipFilename.toString());
//		if (copyRes == -1) {
//			return "";
//		}
		// FTP下载图片文件
		int ftpRes = FtpUtil.download(ftpIp, spAbsolutePath, imageZipFilename.toString());
		if (ftpRes == -1) {
			return "";
		}
		// 解压此图片
		int unzipRes = FileOperate.unzip(imageZipFilename.toString(), imageZipPath.toString());
		if (unzipRes == -1) {
			return "";
		}
		// 重命名文件
		String zipFileName = imageZipPath.toString() + FilesPros.getProper("SKYPOOL_ZIP_IMAGE_FILENAME");
		boolean renameRes = FileOperate.renameFile(zipFileName, imagePath.toString());
		if (!renameRes) {
			return "";
		}
		// 删除原文件
		FileOperate.deleteFiles(imageZipPath.toString(), true);
		// 返回真实路径
//		System.out.println("new create!");
		return new StringBuffer(FilesPros.getProper("IMAGE_SUB_DIRECTORY_NAME")).append(spRalativePathNoEt).append(Constants.PIC_EXTENSION)
		        .toString();
	}

	public static void main(String[] args) {
		System.err.println(RealImageUtil.getImageRealPath("http://192.168.0.86:81/pool/20130717/15/1374480774748_bfc425.null"));
	}
}
