package multithread;

public class DeadLock {
	private static String a = "aaa";
	private static String b = "bbb";

	public static void main(String[] args) throws Exception {
		Thread threadA = new Thread(() -> {
			synchronized (DeadLock.a) {
				System.out.println("已获得a锁,需要b锁");
				for (int i = 0; i < 100; i++) {
					System.out.println("threadA -----" + i);
				}
				synchronized (DeadLock.b) {
					System.out.println("已获得a,b锁");
				}
			}
		});

		Thread threadB = new Thread(() -> {
			synchronized (DeadLock.b) {
				System.out.println("已获得b锁,需要a锁");
				for (int i = 0; i < 100; i++) {

					System.out.println("threadB ----------" + i);
				}
				synchronized (DeadLock.a) {
					System.out.println("已获得a,b锁");
				}
			}
		});

		threadA.start();
		threadB.start();
	}
}
