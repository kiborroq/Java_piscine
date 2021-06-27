public class HenRunnable implements Runnable {
	Printer printer;

	HenRunnable(Printer printer) {
		this.printer = printer;
	}

	@Override
	public void run() {
		printer.printHen();
	}
}
