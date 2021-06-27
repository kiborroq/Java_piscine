package edu.school21.printer.app;

public class ParseArgument {
	String path;
	char white;
	char black;

	ParseArgument(String [] args) throws ArgumentException {
		if (args.length != 3)
			throw new ArgumentException("Invalid number of arguments. Expected three arguments: --path=\"\" --white=\"\" --black=\"\"");

		String [] arg = args[0].split("=");
		if (arg.length != 2 || !arg[0].contentEquals("--path"))
			throw new ArgumentException("Invalid first argument. Expected image path: --path=./it.bmp");
		this.path = arg[1];

		arg = args[1].split("=");
		if (arg.length != 2 || arg[1].length() != 1 || !arg[0].contentEquals("--white"))
			throw new ArgumentException("Invalid first argument. Expected char for white color: --white=.");
		white = arg[1].charAt(0);

		arg = args[2].split("=");
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
