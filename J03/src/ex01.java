public class ex01 {
	public static void main(String[] args) {
		int count = 5;
		Printer printer = new Printer(count);

		Thread egg = new Thread(new EggRunnable(printer));
		Thread hen = new Thread(new HenRunnable(printer));
		egg.start();
		hen.start();

		try {
			egg.join();
			hen.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
