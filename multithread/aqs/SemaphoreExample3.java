package multithread.aqs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreExample3 {
	private static final int threadCount = 20;

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();

		final Semaphore semaphore = new Semaphore(3);

		for (int i = 0; i < threadCount; i++) {
			final int threadNum = i;
			exec.execute(() -> {
				try {
					if (semaphore.tryAcquire()) { // 尝试获取一个许可
						test(threadNum);
						semaphore.release(); // 释放许可
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}
		exec.shutdown();
	}

	private static void test(int threadNum) throws InterruptedException {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		System.out.println(sdf.format(new Date()) + "     " + Thread.currentThread().getName() + "     " + threadNum);
		Thread.sleep(1000);
	}
}
