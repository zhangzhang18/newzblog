package com.zblog.util;

public class Path {

	public static Class<?> getCaller() {
		StackTraceElement stack[] = (new Throwable()).getStackTrace();
		System.out.println("stack length:" + stack.length);
		if (stack.length < 3) {
			return Path.class;
		}
		String className = stack[2].getClassName();
		System.out.println("getCaller class name:" + className);
		try {
			return Class.forName(className);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getCurrentPath() {
		String path = System.getProperty("serviceframe.config.path");

		System.out.println("serviceframe.config.path:" + path);

		if (path == null || path.equalsIgnoreCase("")) {
			try {
				Class<?> caller = Path.getCaller();
				if (caller == null) {
					caller = Path.class;
				}
				path = caller.getProtectionDomain().getCodeSource().getLocation().getPath();

				System.out.println("caller className:" + caller.getName());
			}
			catch (Exception ex) {
				ex.printStackTrace();
				path = Path.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			}

			// path =
			// Path.class.getProtectionDomain().getCodeSource().getLocation().getPath();

			path = path.replaceFirst("file:/", "");
			path = path.replaceAll("!/", "");
			path = path.replaceAll("\\\\", "/");
			path = path.substring(0, path.lastIndexOf("/"));
			if (path.substring(0, 1).equalsIgnoreCase("/")) {
				String osName = System.getProperty("os.name").toLowerCase();
				if (osName.indexOf("window") >= 0) {
					path = path.substring(1);
				}
			}
		}

		System.out.println("utility path getCurrentPath:" + path);
		return path;
	}

	public static String getCurrentPath(Class<?> cls) {
		String path = cls.getProtectionDomain().getCodeSource().getLocation().getPath();
		path = path.replaceFirst("file:/", "");
		path = path.replaceAll("!/", "");
		path = path.substring(0, path.lastIndexOf("/"));
		if (path.substring(0, 1).equalsIgnoreCase("/")) {
			String osName = System.getProperty("os.name").toLowerCase();
			if (osName.indexOf("window") >= 0) {
				path = path.substring(1);
			}
		}
		return path;
	}
}