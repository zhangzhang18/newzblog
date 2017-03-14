package com.zblog.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

class MyCallable implements Callable<Object> {

	private final String taskNum;

	InputStream is;

	String type;

	Integer count;

	MyCallable(String taskNum, InputStream is, String type) {
		this.taskNum = taskNum;
		this.is = is;
		this.type = type;
	}

	public Object call() {
		System.out.println(">>>>>" + taskNum + "任务启动");
		try {
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				if (type.equals("Error"))
					System.err.println(line);
				if (line.contains(".swf"))
					count += 1;
				else
					System.out.println(line);
//		if (line.contains(".swf"))
//			count += 1;
			}
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return count;
	}
}