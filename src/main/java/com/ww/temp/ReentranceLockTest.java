package com.ww.temp;

public class ReentranceLockTest {
	public void method1() {
		synchronized (ReentranceLockTest.class) {
			System.out.println("方法1获得ReentrantTest的内置锁运行了");
			method2();
		}
	}

	public void method2() {
		synchronized (ReentranceLockTest.class) {
			System.out.println("方法1里面调用的方法2重入内置锁,也正常运行了");
		}
	}

	public static void main(String[] args) {
		new ReentranceLockTest().method1();
	}
}
