package multithread;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 写锁测试
 */

public class ReadAndWriteLockExample2 {
	private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	public static void main(String[] args) throws InterruptedException {
		new Thread(() -> {
			testReadLock();
		}).start();

		new Thread(() -> {
			testReadLock();
		}).start();

	}

	private static void testReadLock() {
		if (lock.writeLock().tryLock()) {
			try {
				System.out.println(Thread.currentThread().getName() + "------加写锁成功-----");
				for (int i = 0; i < 10; i++) {
					Thread.sleep(100);
					System.out.println(Thread.currentThread().getName() + "------进行写操作");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				System.out.println(Thread.currentThread().getName() + "释放了写锁");
				lock.writeLock().unlock();
			}
		} else {
			System.out.println(Thread.currentThread().getName() + "------加写锁失败-----");
		}
	}

}
