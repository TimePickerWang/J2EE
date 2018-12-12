package multithread;

public class DeadLock {
	private static String a = "aaa";
	private static String b = "bbb";

	public static void main(String[] args) throws Exception {
		Thread threadA = new Thread(() -> {
			synchronized (DeadLock.a) {
				System.out.println("�ѻ��a��,��Ҫb��");
				for (int i = 0; i < 100; i++) {
					System.out.println("threadA -----" + i);
				}
				synchronized (DeadLock.b) {
					System.out.println("�ѻ��a,b��");
				}
			}
		});

		Thread threadB = new Thread(() -> {
			synchronized (DeadLock.b) {
				System.out.println("�ѻ��b��,��Ҫa��");
				for (int i = 0; i < 100; i++) {

					System.out.println("threadB ----------" + i);
				}
				synchronized (DeadLock.a) {
					System.out.println("�ѻ��a,b��");
				}
			}
		});

		threadA.start();
		threadB.start();
	}
}
