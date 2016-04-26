package com.date.test;

import java.util.Date;

/**
 * 时间类型测试类
 * 
 * @author xiaoyun
 */
public class DateTest {
	public static void main(String[] args) {
		try {
			Date time1 = new Date();
			Thread.currentThread().sleep(5000);
			Date time2 = new Date();
			System.out.println((time2.getTime()-time1.getTime())/1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
