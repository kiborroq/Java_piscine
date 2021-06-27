package edu.school21.printer.logic;

import com.diogonunes.jcdp.color.api.Ansi;

import java.awt.*;
import java.util.HashMap;

public class ColorsSet {
	static final Color black = new Color(0, 0, 0);
	static final Color white = new Color(255, 255, 255);
	static HashMap<String, Ansi.BColor> colors = null;

	static public Ansi.BColor getColor(String color) {
		if (colors == null)
			initColors();
		return colors.get(color);
	}

	static private void initColors() {
		colors = new HashMap<>();

		colors.put("black", Ansi.BColor.BLACK);
		colors.put("red", Ansi.BColor.RED);
		colors.put("green", Ansi.BColor.GREEN);
		colors.put("yellow", Ansi.BColor.YELLOW);
		colors.put("blue", Ansi.BColor.BLUE);
		colors.put("magenta", Ansi.BColor.MAGENTA);
		colors.put("cyan", Ansi.BColor.CYAN);
		colors.put("white", Ansi.BColor.WHITE);
		colors.put("", Ansi.BColor.NONE);
	}

	public static int getBlackRGB() {
		return black.getRGB();
	}

	public static int getWhiteRGB() {
		return white.getRGB();
	}

	public static boolean containColor(String colorName) {
		if (colors == null)
			initColors();
		return colors.containsKey(colorName);
	}
}
