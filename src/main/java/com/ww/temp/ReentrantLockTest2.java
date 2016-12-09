package com.ww.temp;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest2 {
	private Lock lock = new ReentrantLock();

	public void method1() {
		lock.lock();
		try {
			System.out.println("get ReentrantLock for method 1 ");
			method2();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void method2() {
		lock.lock();
		try {
			System.out.println("get ReentrantLock for method 2");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		ReentrantLockTest2 t = new ReentrantLockTest2();
		t.method1();
	}
}
