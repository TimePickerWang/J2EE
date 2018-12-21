package multithread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/*
 * ���Է���lock.tryLock(long timeout, TimeUnit unit)
 * */

public class TryLockExample2 {
	private static ReentrantLock lock = new ReentrantLock();

	public static void main(String[] args) {
		new Thread(() -> {
			try {
				testWait();
				System.out.println(Thread.currentThread().getName() + "����");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

		new Thread(() -> {
			try {
				testWait();
				System.out.println(Thread.currentThread().getName() + "����");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}

	private static void testWait() throws InterruptedException {
		if (lock.tryLock(1500, TimeUnit.MILLISECONDS)) { // ��1.5����
			System.out.println(Thread.currentThread().getName() + "��ȡ���ɹ�");
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				System.out.println(Thread.currentThread().getName() + "�ͷ�����");
				lock.unlock();
			}
		} else {
			System.out.println(Thread.currentThread().getName() + "��ȡ��ʧ��");
		}
	}

}
