import java.util.Random;

public class ex02 {
	public static void main(String[] args) {
		int len = 2345;
		int num_threads = 13;

		int [] arr = getRandomArray(len);
		System.out.println("Sum: " + getSum(arr));

		Thread [] threads = initThreads(arr, num_threads);
		joinThreads(threads, num_threads);

		System.out.println("Sum by threads: " + SumRunnble.getSum());
	}

	private static void joinThreads(Thread[] threads, int num_threads) {
		int i = 0;
		while (i < num_threads) {
			try {
				threads[i].join();
			} catch (InterruptedException e) { }
			i++;
		}
	}

	private static Thread[] initThreads(int[] arr, int num_threads) {
		Object obj = new Object();
		int i = 0;
		int num = 0;
		int add = arr.length / num_threads;
		Thread [] threads = new Thread[num_threads];
		while (i < num_threads - 1) {
			threads[i] = new Thread(new SumRunnble(num, num + add - 1, arr, obj));
			threads[i].start();
			num += arr.length / num_threads;
			i++;
		}
		threads[i] = new Thread(new SumRunnble(num, arr.length - 1, arr, obj));
		threads[i].start();
		return threads;
	}

	private static long getSum(int[] arr) {
		long sum = 0;
		for (int i = 0; i < arr.length; i++)
			sum += arr[i];
		return sum;
	}

	private static int[] getRandomArray(int len) {
		int [] arr = new int[len];
		Random rand = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rand.nextInt(1000);
		}
		return arr;
	}
}
