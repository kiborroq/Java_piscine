package edu.school21.printer.app;

public class ParseArgument {
	String path;
	char white;
	char black;

	ParseArgument(String [] args) throws ArgumentException {
		if (args.length != 2)
			throw new ArgumentException("Invalid number of arguments. Expected two arguments: --white=\"\" --black=\"\"");

		this.path = "/resources/it.bmp";

		String [] arg = args[0].split("=");
		if (arg.length != 2 || arg[1].length() != 1 || !arg[0].contentEquals("--white"))
			throw new ArgumentException("Invalid first argument. Expected char for white color: --white=.");
		white = arg[1].charAt(0);

		arg = args[1].split("=");
		if (arg.length != 2 || arg[1].length() != 1 || !arg[0].contentEquals("--black"))
			throw new ArgumentException("Invalid second argument. Expected char for black color: --black=0");
		black = arg[1].charAt(0);
	}

	public String getPath() {
		return path;
	}

	public char getWhite() {
		return white;
	}

	public char getBlack() {
		return black;
	}
}
