package multithread;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读锁测试
 */

public class ReadAndWriteLockExample1 {
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
		if (lock.readLock().tryLock()) {
			try {
				System.out.println(Thread.currentThread().getName() + "------加读锁成功-----");
				for (int i = 0; i < 10; i++) {
					Thread.sleep(100);
					System.out.println(Thread.currentThread().getName() + "------进行读操作");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				System.out.println(Thread.currentThread().getName() + "释放了读锁");
				lock.readLock().unlock();
			}
		} else {
			System.out.println(Thread.currentThread().getName() + "------加读锁失败-----");
		}
	}

}
