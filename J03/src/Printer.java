public class Printer {
	private int count;
	private boolean eggWrite;

	Printer(int count) {
		this.count = count;
		eggWrite = true;
	}

	public void printHen() {
		int henCount = count;

		while (henCount-- > 0){
			synchronized (this) {
				while (eggWrite) {
					try {
						wait();
					} catch (InterruptedException e) { }
				}
				System.out.println("Hen");
				eggWrite = true;
				notify();
			}
		}
	}

	public void printEgg() {
		int eggCount = count;

		while (eggCount-- >0) {
			synchronized (this) {
				while (!eggWrite) {
					try {
						wait();
					} catch (InterruptedException e) { }
				}
				System.out.println("Egg");
				eggWrite = false;
				notify();
			}
		}
	}


//	public void printHen() {
//		int henCount = count;
//
//		while (henCount-- > 0) {
//			System.out.println("Hen");
//		}
//	}

//	public void printEgg() {
//		int eggCount = count;
//
//		while (eggCount-- > 0) {
//			System.out.println("Egg");
//		}
//	}
}
