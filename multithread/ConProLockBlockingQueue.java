package multithread;

import java.util.concurrent.ArrayBlockingQueue;

class BlockingQueueResources {
	private int i; // 用来计数
	private ArrayBlockingQueue<Integer> queue;

	public BlockingQueueResources(int size) {
		this.i = 0;
		this.queue = new ArrayBlockingQueue<Integer>(size);
	}

	public void produce() {
		try {
			queue.put(new Integer(++i));
			System.out.println("生产者" + Thread.currentThread().getName() + " 生产数据" + i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void consume() {
		try {
			Integer num = queue.take();
			System.out.println("消费者" + Thread.currentThread().getName() + " 消费数据" + num);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class ConsumerThree implements Runnable {
	BlockingQueueResources resources;

	ConsumerThree(BlockingQueueResources resources) {
		this.resources = resources;
	}

	public void run() {
		while (true) {
			resources.consume();
		}
	}
}

class ProducerThree implements Runnable {
	BlockingQueueResources resources;

	ProducerThree(BlockingQueueResources resources) {
		this.resources = resources;
	}

	public void run() {
		while (true) {
			resources.produce();
		}
	}
}

public class ConProLockBlockingQueue {
	public static void main(String[] args) throws Exception {
		int size = 5;
		BlockingQueueResources resources = new BlockingQueueResources(size);

		ProducerThree poducer = new ProducerThree(resources);
		ConsumerThree consumer = new ConsumerThree(resources);

		new Thread(poducer, "producerA").start();
		new Thread(poducer, "producerB").start();

		new Thread(consumer, "consumerA").start();
		new Thread(consumer, "consumerB").start();
		new Thread(consumer, "consumerC").start();

	}
}
