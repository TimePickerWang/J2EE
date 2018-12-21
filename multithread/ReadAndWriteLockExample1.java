package multithread;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ��������
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
				System.out.println(Thread.currentThread().getName() + "------�Ӷ����ɹ�-----");
				for (int i = 0; i < 10; i++) {
					Thread.sleep(100);
					System.out.println(Thread.currentThread().getName() + "------���ж�����");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				System.out.println(Thread.currentThread().getName() + "�ͷ��˶���");
				lock.readLock().unlock();
			}
		} else {
			System.out.println(Thread.currentThread().getName() + "------�Ӷ���ʧ��-----");
		}
	}

}
