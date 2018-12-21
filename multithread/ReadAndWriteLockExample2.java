package multithread;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * д������
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
				System.out.println(Thread.currentThread().getName() + "------��д���ɹ�-----");
				for (int i = 0; i < 10; i++) {
					Thread.sleep(100);
					System.out.println(Thread.currentThread().getName() + "------����д����");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				System.out.println(Thread.currentThread().getName() + "�ͷ���д��");
				lock.writeLock().unlock();
			}
		} else {
			System.out.println(Thread.currentThread().getName() + "------��д��ʧ��-----");
		}
	}

}
