public class EggRunnable implements Runnable {
	Printer printer;

	EggRunnable(Printer printer) {
		this.printer = printer;
	}

	@Override
	public void run() {
		printer.printEgg();
	}
}
