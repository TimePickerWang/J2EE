package multithread;

import java.util.concurrent.locks.ReentrantLock;

/*
 * 测试方法lock.tryLock()
 * */

public class TryLockExample1 {
	private static ReentrantLock lock = new ReentrantLock();

	public static void main(String[] args) {
		new Thread(() -> {
			testNoWait();
			System.out.println(Thread.currentThread().getName() + "结束");
		}).start();

		new Thread(() -> {
			testNoWait();
			System.out.println(Thread.currentThread().getName() + "结束");
		}).start();
	}

	private static void testNoWait() {
		if (lock.tryLock()) {
			System.out.println(Thread.currentThread().getName() + "获取锁成功");
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				System.out.println(Thread.currentThread().getName() + "释放了锁");
				lock.unlock();
			}
		} else {
			System.out.println(Thread.currentThread().getName() + "获取锁失败");
		}
	}

}
