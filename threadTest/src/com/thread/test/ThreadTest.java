package com.thread.test;


/**
 * 线程学习
 * @author xiaoyun 2015-11-3
 */
public class ThreadTest {
	public static void main(String[] args) {
		// 向 Java 虚拟机返回可用处理器的数目
		System.out.println(Runtime.getRuntime().availableProcessors());
		//start();
	}
	
	public synchronized static void start(){
		new Thread(new Runnable() {
			public void run() {
				while(true){
					try{
						System.out.println("aa");
					}catch(Exception e){
					}
				}
			}
		}).start();
	}
}
