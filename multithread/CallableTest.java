package multithread;

import java.util.concurrent.FutureTask;

public class CallableTest {

	public static void main(String[] args) {
		FutureTask<Integer> ft = new FutureTask<>(() -> {
			int i = 0;
			for (; i < 100; i++) {
				System.out.println(Thread.currentThread().getName() + " " + i);
			}
			return i;
		});

		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + " ��ѭ������i��ֵ" + i);
			if (i == 20) {
				new Thread(ft, "�з���ֵ���߳�").start();
			}
		}
		try {
			System.out.println("���̵߳ķ���ֵ��" + ft.get());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
 
}
