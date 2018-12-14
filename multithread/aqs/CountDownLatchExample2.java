package multithread.aqs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchExample2 {
	private static final int threadCount = 200;

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();

		final CountDownLatch countDownLatch = new CountDownLatch(200);

		for (int i = 0; i < threadCount; i++) {
			final int threadNum = i;
			exec.execute(() -> {
				try {
					test(threadNum);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					countDownLatch.countDown();
				}
			});
		}
		countDownLatch.await(100, TimeUnit.MILLISECONDS);
		System.out.println("--------------finish--------------");
		exec.shutdown();
	}

	private static void test(int threadNum) throws InterruptedException {
		Thread.sleep(10);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		System.out.println(sdf.format(new Date()) + "     " + Thread.currentThread().getName() + "     " + threadNum);
	}

}
