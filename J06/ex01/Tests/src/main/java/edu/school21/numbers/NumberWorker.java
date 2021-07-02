package edu.school21.numbers;

import edu.school21.exceptions.IllegalNumberException;

public class NumberWorker {
	public boolean isPrime(int number) {
		if (number < 2)
			throw new IllegalNumberException(number + " is not valid number. Expected numbers greater then 1");
		for (int i = number % 2 == 0 ? 2 : 3; i < number; i += 2) {
			if (i != number && number % i == 0)
				return false;
		}
		return true;
	}

	public int digitsSum(int number) {
		if (number < 0)
			throw new IllegalNumberException(number + " is not valid number. Expected positive numbers.");

		int sum = 0;
		while (number > 0) {
			sum += number % 10;
			number /= 10;
		}
		return sum;
	}
}
