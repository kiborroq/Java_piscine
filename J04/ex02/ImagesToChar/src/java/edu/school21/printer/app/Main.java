package edu.school21.printer.app;

import com.beust.jcommander.JCommander;
import edu.school21.printer.logic.ImagesToCharsPrinter;

public class Main {
	public static void main(String[] args) {
		try {
			ParseArgument pa = new ParseArgument();
			JCommander.newBuilder().addObject(pa).build().parse(args);
			pa.chackArgs(args.length);
			new ImagesToCharsPrinter(pa.getPath(), pa.getWhite(), pa.getBlack()).printImage();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
