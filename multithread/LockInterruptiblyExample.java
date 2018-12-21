package multithread;

import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptiblyExample {
	private static ReentrantLock lock = new ReentrantLock();

	public static void main(String[] args) throws InterruptedException {
		Thread thread1 = new Thread(() -> {
			try {
				testInterruptibly();
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + "���ж�");
			} finally {
				System.out.println(Thread.currentThread().getName() + "����");
			}
		});

		Thread thread2 = new Thread(() -> {
			try {
				testInterruptibly();
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + "���ж�");
			} finally {
				System.out.println(Thread.currentThread().getName() + "����");
			}
		});

		thread1.start();
		thread2.start();

		Thread.sleep(1000);

		thread2.interrupt();
	}

	private static void testInterruptibly() throws InterruptedException {
		lock.lockInterruptibly();
		try {
			System.out.println(Thread.currentThread().getName() + "��ȡ���ɹ�");
			Thread.sleep(1500);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(Thread.currentThread().getName() + "�ͷ�����");
			lock.unlock();
		}
	}

}
