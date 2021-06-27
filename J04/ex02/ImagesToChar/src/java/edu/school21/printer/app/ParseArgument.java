package edu.school21.printer.app;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import edu.school21.printer.logic.ColorsSet;

@Parameters(separators = "=")
public class ParseArgument {

	@Parameter(names={"--white", "-w"})
	private String white;

	@Parameter(names={"--black", "-b"})
	private String black;

	private final String path = "/resources/it.bmp";

	public String getPath() {
		return path;
	}

	public String getWhite() {
		return white.toLowerCase();
	}

	public String getBlack() {
		return black.toLowerCase();
	}

	public void chackArgs(int count) throws ArgumentException {
		if (count != 2)
			throw new ArgumentException("Invalid number of arguments. Expected two arguments: --white=\"COLOR\" --black=\"COLOR\"");

		if (!ColorsSet.containColor(white.toLowerCase()) || !ColorsSet.containColor(black.toLowerCase()))
			throw new ArgumentException("Invalid value of argument. Expected one of the next colors: RED, BLACK, WHITE, CYAN, GREEN, YELLOW, BLUE, MAGENTA, ''");
	}
}
