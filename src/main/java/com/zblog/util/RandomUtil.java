package com.zblog.util;

import java.util.Random;

/**
 * added by damon.niu 2013-01-08
 * 生成随机数的工具类
 */
public class RandomUtil {

	public static void main(String[] args) {
		RandomUtil r = new RandomUtil();
		for (int i = 0; i < 10; i++) {
			System.out.println(RandomUtil.NextInt(10000, 99999));
		}
	}

	public static int NextInt(final int min, final int max) {
		Random rand = new Random();
		int tmp = Math.abs(rand.nextInt());
		return tmp % (max - min + 1) + min;
	}

}
