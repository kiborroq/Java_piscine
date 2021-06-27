import static java.lang.Thread.sleep;

public class SomebodyThread implements Runnable {
	private String name;
	private int count;

	SomebodyThread(String name, int count) {
		this.name = name;
		this.count = count;
	}

	@Override
	public void run() {
		while (count-- > 0) {
			System.out.println(name);
		}
	}
}
