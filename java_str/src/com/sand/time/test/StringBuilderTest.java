/**
 * 
 */
package com.sand.time.test;

/**
 *
 * @author xiaoyun
 * @date 2016-1-12 下午7:27:53
 * @version v1.0.0
 *
 */
public class StringBuilderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Foo[] fooArr = new Foo[2];
		Foo foo1 = new Foo();
		foo1.setA("aa");
		Foo foo2 = new Foo();
		foo2.setA("aa2");
		fooArr[0] = foo1;
		fooArr[1] = foo2;
		test(fooArr);
		for (Foo foo : fooArr) {
			System.out.println(foo.getA()+","+foo.getB());
		}
	}
	
	public static void test(Foo[] fooArr){
		Foo f1 = fooArr[0];
		f1.setB("bb");
		Foo f2 = fooArr[1];
		f2.setB("bb2");
	}

}
