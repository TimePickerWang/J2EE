package multithread;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Resources {
	private int i;
	private int size;
	private LinkedList<Integer> list;

	private ReentrantLock lock = new ReentrantLock(true); // 设置为公平锁
	Condition conCondition = lock.newCondition();
	Condition proCondition = lock.newCondition();

	public Resources(int size) {
		this.list = new LinkedList<Integer>();
		this.size = size;
		this.i = 0;
	}

	public void produce() {
		lock.lock();
		try {
			while (list.size() == size) {
				System.out.println("资源达到最大值，生产者：	" + Thread.currentThread().getName() + "开始wait");
				proCondition.await();
				System.out.println("生产者：	" + Thread.currentThread().getName() + "退出wait");
			}
			list.addLast(new Integer(++i));
			System.out.println("生产者" + Thread.currentThread().getName() + " 生产数据" + i);
			conCondition.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void consume() {
		lock.lock();
		try {
			while (list.isEmpty()) {
				System.out.println("资源为空，消费者：	" + Thread.currentThread().getName() + "开始wait");
				conCondition.await();
				System.out.println("消费者：	 " + Thread.currentThread().getName() + "退出wait");
			}
			Integer num = list.removeFirst();
			System.out.println("消费者" + Thread.currentThread().getName() + " 消费数据" + num);
			proCondition.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}

class ConsumerTwo implements Runnable {
	Resources resources;

	ConsumerTwo(Resources resources) {
		this.resources = resources;
	}

	public void run() {
		while (true) {
			resources.consume();
		}
	}
}

class ProducerTwo implements Runnable {
	Resources resources;

	ProducerTwo(Resources resources) {
		this.resources = resources;
	}

	public void run() {
		while (true) {
			resources.produce();
		}
	}
}

public class ConProLockCondition {
	public static void main(String[] args) throws Exception {
		int size = 5;
		Resources resources = new Resources(size);

		ProducerTwo poducer = new ProducerTwo(resources);
		ConsumerTwo consumer = new ConsumerTwo(resources);

		new Thread(poducer, "producerA").start();
		new Thread(poducer, "producerB").start();

		new Thread(consumer, "consumerA").start();
		new Thread(consumer, "consumerB").start();
		new Thread(consumer, "consumerC").start();

	}
}
