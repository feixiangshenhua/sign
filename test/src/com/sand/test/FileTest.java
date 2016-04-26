/**
 * 
 */
package com.sand.test;

import java.io.File;

/**
 *
 * desc:
 * @author Administrator
 * @date 2016-2-22 下午2:22:48
 * @version 
 *
 */
public class FileTest {
	public static void main(String[] args) {
		File fnew = new File("D:\\a.jpg");
		fnew.deleteOnExit();
		
	}
}
