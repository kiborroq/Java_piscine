package edu.school21.printer.app;

import edu.school21.printer.logic.ImageToCharArr;

public class Main {
	public static void main(String[] args) {
		try {
			ParseArgument pa = new ParseArgument(args);
			ImageToCharArr itca = new ImageToCharArr(pa.getPath(), pa.getWhite(), pa.getBlack());
			itca.convert();
			char [][] arr = itca.getCharArr();
			printArray(arr, itca.getHeight(), itca.getWidth());
		} catch (Exception e) {
//			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	private static void printArray(char[][] arr, int height, int width) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}
