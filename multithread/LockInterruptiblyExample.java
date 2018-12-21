package multithread;

import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptiblyExample {
	private static ReentrantLock lock = new ReentrantLock();

	public static void main(String[] args) throws InterruptedException {
		Thread thread1 = new Thread(() -> {
			try {
				testInterruptibly();
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + "被中断");
			} finally {
				System.out.println(Thread.currentThread().getName() + "结束");
			}
		});

		Thread thread2 = new Thread(() -> {
			try {
				testInterruptibly();
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + "被中断");
			} finally {
				System.out.println(Thread.currentThread().getName() + "结束");
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
			System.out.println(Thread.currentThread().getName() + "获取锁成功");
			Thread.sleep(1500);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(Thread.currentThread().getName() + "释放了锁");
			lock.unlock();
		}
	}

}
