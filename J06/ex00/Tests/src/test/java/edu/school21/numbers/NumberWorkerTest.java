package edu.school21.numbers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class NumberWorkerTest {
	private NumberWorker nw;

	@BeforeEach
	void setUp() {
		nw = new NumberWorker();
	}

	@ParameterizedTest
	@ValueSource(ints = {-1, 0, 1})
	void isPrimeForIncorrectNumbers(int num) {
		assertThrows(IllegalNumberException.class, ()->nw.isPrime(num), num + " is not valid number. Expected numbers greater then 1");
	}

	@ParameterizedTest
	@ValueSource(ints = {2, 3, 7, 97, 199})
	void isPrimeForPrimes(int num) {
		assertTrue(nw.isPrime(num));
	}

	@ParameterizedTest
	@ValueSource(ints = {4, 46, 393, 1000})
	void isPrimeForNotPrimes(int num) {
		assertFalse(nw.isPrime(num));
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/data_fail.csv")
	void digitsSumForFailNumbers(int num, int result) {
		assertThrows(IllegalNumberException.class, ()-> nw.digitsSum(num), num + " is not valid number. Expected positive numbers.");
	}
}
