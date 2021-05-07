public class AppleTest {
	public static void main(String[] args) {
		Thread t1 = new Thread(new Apple("one"));
		Thread t2 = new Thread(new Apple("two"));
		Thread t3 = new Thread(new Apple("three"));
		t1.start();
		t2.start();
		t3.start();
	}
}
