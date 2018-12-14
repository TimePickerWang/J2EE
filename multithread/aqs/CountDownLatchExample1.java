package multithread.aqs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample1 {
	private static final int threadCount = 200;

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();

		final CountDownLatch countDownLatch = new CountDownLatch(200);

		for (int i = 0; i < threadCount; i++) {
			final int threadNum = i;
			exec.execute(() -> {
				test(threadNum);
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
		System.out.println("finish");
		exec.shutdown();
	}

	private static void test(int threadNum) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		System.out.println(sdf.format(new Date()) + "     " + Thread.currentThread().getName() + "     " + threadNum);
	}

}
