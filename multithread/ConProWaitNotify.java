package multithread;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Resource {
	private int i;
	private int size;
	private LinkedList<Integer> list;

	public Resource(int size) {
		this.list = new LinkedList<Integer>();
		this.size = size;
		this.i = 0;
	}

	public synchronized void produce() {
		try {
			while (list.size() == size) {
				System.out.println("��Դ�ﵽ���ֵ�������ߣ�	" + Thread.currentThread().getName() + "��ʼwait");
				wait();
				System.out.println("�����ߣ�	" + Thread.currentThread().getName() + "�˳�wait");
			}
			list.addLast(new Integer(++i));
			System.out.println("������" + Thread.currentThread().getName() + " ��������" + i);
			notifyAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void consume() {
		try {
			while (list.isEmpty()) {
				System.out.println("��ԴΪ�գ������ߣ�	" + Thread.currentThread().getName() + "��ʼwait");
				wait();
				System.out.println("�����ߣ�	 " + Thread.currentThread().getName() + "�˳�wait");
			}
			Integer num = list.removeFirst();
			System.out.println("������" + Thread.currentThread().getName() + " ��������" + num);
			notifyAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Consumer implements Runnable {
	Resource resource;

	Consumer(Resource resource) {
		this.resource = resource;
	}

	public void run() {
		while (true) {
			resource.consume();
		}
	}
}

class Producer implements Runnable {
	Resource resource;

	Producer(Resource resource) {
		this.resource = resource;
	}

	public void run() {
		while (true) {
			resource.produce();
		}
	}
}

public class ConProWaitNotify {
	public static void main(String[] args) throws Exception {
		int size = 5;
		Resource resource = new Resource(size);

		Producer producer = new Producer(resource);
		Consumer consumer = new Consumer(resource);

		new Thread(producer, "producerA").start();
		new Thread(producer, "producerB").start();

		new Thread(consumer, "consumerA").start();
		new Thread(consumer, "consumerB").start();
		new Thread(consumer, "consumerC").start();

//		ExecutorService service = Executors.newFixedThreadPool(8); // ����2��������3��������
//		for (int i = 0; i < 2; i++) {
//			service.submit(new Producer(resource));
//		}
//		for (int i = 0; i < 3; i++) {
//			service.submit(new Consumer(resource));
//		}

	}
}
