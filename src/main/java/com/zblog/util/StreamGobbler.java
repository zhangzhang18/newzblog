package com.zblog.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamGobbler extends Thread {

	InputStream is;

	String type;

	int count;

	StreamGobbler(InputStream is, String type, int count) {
		this.is = is;
		this.type = type;
		this.count = count;
	}

	@Override
	public void run() {
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
//				if (line.contains(".swf"))
//					count += 1;
			}
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
