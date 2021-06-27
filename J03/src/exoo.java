import static java.lang.Thread.sleep;

public class exoo {
	public static void main(String[] args) throws InterruptedException {
		int count = 5;
		Printer printer = new Printer(count);

		Thread egg = new Thread(new EggRunnable(printer));
		Thread hen = new Thread(new HenRunnable(printer));
		egg.start();
		hen.start();

		egg.join();
		hen.join();

		while (count-- > 0)
			System.out.println("Human");
	}
}
