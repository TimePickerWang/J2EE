package multithread;

import java.util.concurrent.locks.ReentrantLock;

/*
 * ���Է���lock.tryLock()
 * */

public class TryLockExample1 {
	private static ReentrantLock lock = new ReentrantLock();

	public static void main(String[] args) {
		new Thread(() -> {
			testNoWait();
			System.out.println(Thread.currentThread().getName() + "����");
		}).start();

		new Thread(() -> {
			testNoWait();
			System.out.println(Thread.currentThread().getName() + "����");
		}).start();
	}

	private static void testNoWait() {
		if (lock.tryLock()) {
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
