/**
 * 
 */
package com.sand.test;

/**
 *
 * desc:
 * @author Administrator
 * @date 2016-2-1 下午2:00:16
 * @version 
 *
 */
public class Test {
	public static void main(String[] args) {
		A a1 = new A();
		A a2 = new B();
		B b = new B();
		C c = new C();
		D d = new D();
		System.out.println(a1.show(b)); // A and A
		System.out.println(a2.show(b)); // A and A
		System.out.println(a2.show(a1));
		System.out.println(b.show(d));
		System.out.println(b.show(c));
	}	
}
