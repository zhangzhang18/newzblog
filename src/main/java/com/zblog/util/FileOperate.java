package com.zblog.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FileOperate {

	public static int copyFile(String strSrcPath, String strDstPath) {
		String srcPath, dstPath;
		srcPath = strSrcPath.trim();
		dstPath = strDstPath.trim();
		if (srcPath.charAt(srcPath.length() - 1) == File.separatorChar) {
			srcPath = srcPath.substring(0, srcPath.length() - 1);
		}
		if (dstPath.charAt(dstPath.length() - 1) == File.separatorChar) {
			dstPath = dstPath.substring(0, dstPath.length() - 1);
		}
		if (srcPath.equals("") || dstPath.equals("") || srcPath.equals(dstPath)) {
			System.out.println("Path is error(FileOperate.copyFile())");
			return -1;
		}
		File file1 = new File(srcPath);
		if (!file1.exists() || file1.isDirectory()) {
			System.out.println("Source Path is error(FileOperate.copyFile())");
			return -1;
		}
		File file2 = new File(dstPath);
		File file3 = file2.getParentFile();
		if ((file3 != null) && !file3.exists()) {
			file3.mkdirs();
		}
		try {
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(srcPath), 8192);
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(dstPath), 8192);
			byte[] btData = new byte[1024];
			int size;
			while ((size = in.read(btData)) != -1) {
				out.write(btData, 0, size);
			}
			out.flush();
			in.close();
			out.close();
			return 0;
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return -1;

		}
		catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}// copyFile end

	public static void copyFiles(String strSrcDir, String strDstDir) {
		String srcDir, dstDir;
		srcDir = strSrcDir.trim();
		dstDir = strDstDir.trim();
		if (!srcDir.equals(File.separator)) {
			if (srcDir.charAt(srcDir.length() - 1) == File.separatorChar) {
				srcDir = srcDir.substring(0, srcDir.length() - 1);
			}
		}
		if (!dstDir.equals(File.separator)) {
			if (dstDir.charAt(dstDir.length() - 1) == File.separatorChar) {
				dstDir = dstDir.substring(0, dstDir.length() - 1);
			}
		}
		if (srcDir.equals("") || dstDir.equals("") || srcDir.equals(dstDir)) {
			System.out.println("Path is error(FileOperate.copyFiles())");
			return;
		}
		File file1 = new File(srcDir);
		if (!file1.isDirectory()) {
			System.out.println("Source Path is error(FileOperate.copyFiles())");
			return;
		}
		File file2 = new File(dstDir);
		if (file2.exists()) {
			if (!file2.isDirectory()) {
				System.out.println("Destination Path is error(FileOperate.copyFiles())");
				return;
			}
		} else {
			file2.mkdirs();
		}
		String[] fileNames = file1.list();
		for (int i = 0; i < fileNames.length; i++) {
			File file = new File(file1, fileNames[i]);
			if (file.isDirectory()) {
				FileOperate.copyFiles(srcDir + File.separator + fileNames[i], dstDir + File.separator + fileNames[i]);
			} else {
				FileOperate.copyFile(srcDir + File.separator + fileNames[i], dstDir + File.separator + fileNames[i]);
			}
		}
	}// copyFiles end

	public static boolean deleteFile(String strPath) {
		File file = new File(strPath.trim());
		if (file.exists()) {
			System.out.println("delete file is unsuccessful(FileOperate.deleteFile())");
			return file.delete();
		} else {
			System.out.println("File or Dir isn't existent(FileOperate.deleteFile())");
			return false;
		}
	}// deleteFile end

	public static void deleteFiles(String strDir, boolean deleteDir) {
		String dir = strDir.trim();
		if (dir.charAt(dir.length() - 1) == File.separatorChar) {
			dir = dir.substring(0, dir.length() - 1);
		}
		if (dir.equals("")) {
			System.out.println("Path is error(FileOperate.deleteFiles())");
			return;
		}
		File file = new File(dir);
		if (!file.exists()) {
			System.out.println("file or dir isn't existent(FileOperate.deleteFiles())");
			return;
		}
		if (file.isDirectory()) {
			String[] fileNames = file.list();
			for (int i = 0; i < fileNames.length; i++) {
				File subFile = new File(file, fileNames[i]);
				if (subFile.isDirectory()) {
					FileOperate.deleteFiles(dir + File.separator + fileNames[i], deleteDir);
					if (deleteDir) {
						subFile.delete();
					}
				} else {
					subFile.delete();
				}
			}
			if (deleteDir) {
				file.delete();
			}
		} else {
			file.delete();
		}
	}// deleteFiles end

	public static void moveFile(String strSrcPath, String strDstPath) {
		FileOperate.copyFile(strSrcPath, strDstPath);
		FileOperate.deleteFile(strSrcPath);
	}// moveFile end

	public static void moveFiles(String strSrcDir, String strDstDir) {
		FileOperate.copyFiles(strSrcDir, strDstDir);
		FileOperate.deleteFiles(strSrcDir, true);
	}// moveFiles end

	public static boolean renameFile(String strSrcPath, String strDstPath) {
		String srcPath, dstPath;
		srcPath = strSrcPath.trim();
		dstPath = strDstPath.trim();
		if (srcPath.charAt(srcPath.length() - 1) == File.separatorChar) {
			srcPath = srcPath.substring(0, srcPath.length() - 1);
		}
		if (dstPath.charAt(dstPath.length() - 1) == File.separatorChar) {
			dstPath = dstPath.substring(0, dstPath.length() - 1);
		}
		if (srcPath.equals("") || dstPath.equals("") || srcPath.equals(dstPath)) {
			System.out.println("Path is error(FileOperate.renameFile())");
			return false;
		}
		File file1 = new File(srcPath);
		if (!file1.exists()) {
			System.out.println("Source Path is error(FileOperate.renameFile())");
			return false;
		}
		if (file1.isDirectory()) // dir
		{
			System.out.println("Source Path isn't file(FileOperate.renameFile())");
			return false;
		} else // file
		{
			File file2 = new File(dstPath);
			if (file2.exists()) {
				if (file2.isDirectory()) {
					System.out.println("Destination Path is error(FileOperate.copyFile())");
					return false;
				} else {
					file2.delete();
				}
			} else {
				File file3 = file2.getParentFile();
				if ((file3 != null) && !file3.exists()) {
					file3.mkdirs();
				}
			}
			return file1.renameTo(file2);
		}
	}// function renameFile end

	public static int unzip(String strSrcPath, String strDstPath) {
		String srcPath, dstPath;
		srcPath = strSrcPath.trim();
		dstPath = strDstPath.trim();
		if (srcPath.charAt(srcPath.length() - 1) == File.separatorChar) {
			srcPath = srcPath.substring(0, srcPath.length() - 1);
		}
		if (dstPath.charAt(dstPath.length() - 1) == File.separatorChar) {
			dstPath = dstPath.substring(0, dstPath.length() - 1);
		}
		if (srcPath.equals("") || dstPath.equals("") || srcPath.equals(dstPath)) {
			System.out.println("Path is error(FileOperate.unzip())");
			return -1;
		}
		if (!new File(srcPath).exists()) {
			System.out.println("Source File isn't existent(FileOperate.unzip())");
			return -1;
		}
		if (srcPath.endsWith(".zip") || srcPath.endsWith(".ZIP")) {
			try {
				File myDir = new File(dstPath);
				// 目标目录，解压缩到此目录下
				if (myDir.exists()) {
					if (!myDir.isDirectory()) {
						myDir.delete();
						myDir.mkdirs();
					}
				} else {
					myDir.mkdirs(); // 若此目录不存在，就建立它
				}
				ZipInputStream myZipInput = new ZipInputStream(new BufferedInputStream(new FileInputStream(srcPath)));
				// 套入 ZipInputStream
				ZipEntry myZipEntry = null;
				while ((myZipEntry = myZipInput.getNextEntry()) != null) {
					/*
					 * 读取下一个 ZipEntry，并将串流内的位置 移至该 entry 所指之资料的开头。
					 */
					// ========= 以下是解压缩 =============
					if (myZipEntry.isDirectory()) { // 若这个 entry 指到的是目录
						File theDir = new File(myDir, myZipEntry.getName());
						// 前面冠上解压缩的目标目录
						if (!theDir.exists()) {
							theDir.mkdirs(); // 若此目录不存在，就建立它
						}
						theDir.setLastModified(myZipEntry.getTime());
						myZipInput.closeEntry();
					} else { // 若这个 entry 指到是文件
						File file = new File(myDir, myZipEntry.getName());
						File myPath = file.getParentFile();
						// 此文件的 Path，即它被压缩时的上层目录
						if (myPath != null) {
							// 若此目录不存在，就建立它
							if (!myPath.exists()) {
								myPath.mkdirs();
							}
						}
						BufferedOutputStream myOut = new BufferedOutputStream(new FileOutputStream(file));
						byte[] btBuf = new byte[1024];
						int len;
						while ((len = myZipInput.read(btBuf)) != -1) {
							myOut.write(btBuf, 0, len); // 解压缩读入后，再写出到一般文件
						}
						myOut.close();
						file.setLastModified(myZipEntry.getTime());
						myZipInput.closeEntry();
						// 关闭此 entry，并让串流内指到适当位置，以备下个 entry 的读取。
					}
				}
				myZipInput.close();
				return 0;
			}
			catch (IOException e) {
				e.printStackTrace();
				return -1;
			}
		} else {
			System.out.println("file is error(FileOperate.unzip())");
			return -1;
		}
	}// Function unzip end

	private static void zip(File file, ZipOutputStream zip, String entryName) {
		try {
			if (file.isDirectory()) {
				ZipEntry ze = new ZipEntry(entryName + file.getName() + "/");
				ze.setTime(file.lastModified());
				zip.putNextEntry(ze);
				zip.closeEntry();
				String[] names = file.list();
				for (int i = 0; i < names.length; i++) {
					FileOperate.zip(new File(file, names[i]), zip, entryName + file.getName() + "/");
				}
			} else {
				int len;
				byte[] buf = new byte[1024];
				BufferedInputStream in = new BufferedInputStream(new FileInputStream(file), 1024);
				ZipEntry ze = new ZipEntry(entryName + file.getName());
				ze.setTime(file.lastModified());
				zip.putNextEntry(ze);
				while ((len = in.read(buf)) != -1) {
					zip.write(buf, 0, len);
				}
				zip.flush();
				in.close();
				zip.closeEntry();
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void zip(String strSrcPath, String strDstPath) {
		String srcPath, dstPath;
		srcPath = strSrcPath.trim();
		dstPath = strDstPath.trim();
		if (srcPath.charAt(srcPath.length() - 1) == File.separatorChar) {
			srcPath = srcPath.substring(0, srcPath.length() - 1);
		}
		if (dstPath.charAt(dstPath.length() - 1) == File.separatorChar) {
			dstPath = dstPath.substring(0, dstPath.length() - 1);
		}
		if (srcPath.equals("") || dstPath.equals("") || srcPath.equals(dstPath)) {
			System.out.println("Path is error(FileOperate.zip())");
			return;
		}
		File srcFile = new File(srcPath);
		try {
			if (srcFile.exists()) {
				ZipOutputStream zipOut;
				if (dstPath.endsWith(".zip") || dstPath.endsWith(".ZIP")) {
					zipOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(dstPath)));
					// zipOut = new ZipOutputStream(new
					// FileOutputStream(dstPath));
				} else {
					File file = new File(dstPath);
					if (!file.exists()) {
						file.mkdirs();
					}
					zipOut = new ZipOutputStream(new FileOutputStream(new File(file, srcFile.getName() + ".zip")));
				}
				FileOperate.zip(srcFile, zipOut, "");
				zipOut.close();
			} else {
				System.out.println("file or dir isn't existent(FileOperate.zip())");
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}// class FileOperate end
