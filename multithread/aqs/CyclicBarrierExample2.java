package multithread.aqs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierExample2 {
	private static final CyclicBarrier barrier = new CyclicBarrier(5, () -> {
		System.out.println("等待完毕先执行该方法");
	});

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newCachedThreadPool();

		for (int i = 0; i < 10; i++) {
			final int threadNum = i;
			Thread.sleep(1000);
			executor.execute(() -> {
				try {
					race(threadNum);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
		executor.shutdown();
	}

	private static void race(int threadNum) throws InterruptedException, BrokenBarrierException {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Thread.sleep(1000);
		System.out.println(sdf.format(new Date()) + "     " + Thread.currentThread().getName() + "     " + threadNum
				+ "   is ready");
		barrier.await();
		System.out.println(sdf.format(new Date()) + "     " + Thread.currentThread().getName() + "     " + threadNum
				+ "   continue");
	}
}
