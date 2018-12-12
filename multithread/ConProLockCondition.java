package multithread;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Resources {
	private int i;
	private int size;
	private LinkedList<Integer> list;

	private ReentrantLock lock = new ReentrantLock(true); // ����Ϊ��ƽ��
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
				System.out.println("��Դ�ﵽ���ֵ�������ߣ�	" + Thread.currentThread().getName() + "��ʼwait");
				proCondition.await();
				System.out.println("�����ߣ�	" + Thread.currentThread().getName() + "�˳�wait");
			}
			list.addLast(new Integer(++i));
			System.out.println("������" + Thread.currentThread().getName() + " ��������" + i);
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
				System.out.println("��ԴΪ�գ������ߣ�	" + Thread.currentThread().getName() + "��ʼwait");
				conCondition.await();
				System.out.println("�����ߣ�	 " + Thread.currentThread().getName() + "�˳�wait");
			}
			Integer num = list.removeFirst();
			System.out.println("������" + Thread.currentThread().getName() + " ��������" + num);
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
