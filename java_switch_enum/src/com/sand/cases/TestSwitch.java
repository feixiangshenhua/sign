/**
 * 
 */
package com.sand.cases;

import java.util.Random;

/**
 * 
 * switch表达式
 * @author xiaoyun
 * @date 2015-12-29 下午4:48:58
 * @version 1.0.0
 *
 */
public class TestSwitch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testSwitch("FAIL");
	}
	
	private static void testSwitch(String errorCode){
		switch(ErrorCode.getErrorCode(errorCode)){
			case FAIL:
				System.out.println("this is " + ErrorCode.FAIL.getValue());
				break;
			case SUCCESS:
				System.out.println("this is " + ErrorCode.SUCCESS.getValue());
				break;
			case NOAUTH:
				System.out.println("this is " + ErrorCode.NOAUTH);
			default:
				System.out.println("随便了" + new Random().nextInt(10));
		}		
	}

}