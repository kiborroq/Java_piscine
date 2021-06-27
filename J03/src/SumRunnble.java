public class SumRunnble implements Runnable {
	private int lower_i;
	private int upper_i;
	private int [] arr;
	private Object obj;
	private static int sum;

	SumRunnble(int lower_i, int upper_i, int [] arr, Object obj) {
		this.lower_i = lower_i;
		this.upper_i = upper_i;
		this.arr = arr;
		this.obj = obj;
	}

	public static int getSum() {
		return sum;
	}

	public static void setSum(int sum) {
		SumRunnble.sum = sum;
	}

	@Override
	public void run() {
		int sectionSum = 0;
		int i = lower_i;
		while (i <= upper_i) {
			sectionSum += arr[i];
			i++;
		}
		synchronized (obj) {
			sum += sectionSum;
		}
		System.out.println(Thread.currentThread().getName() + ": from " + lower_i + " to " + upper_i + " sum is " + sectionSum);
	}
}
