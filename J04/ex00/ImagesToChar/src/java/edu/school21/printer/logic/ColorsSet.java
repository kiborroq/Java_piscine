package edu.school21.printer.logic;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ColorsSet {
	static Map<String, Color> colors = null;

	static public Color getColor(String color) {
		if (colors == null)
			initColors();
		return colors.get(color);
	}

	static public int getIntColor(String color) {
		if (colors == null)
			initColors();
		return colors.get(color).getRGB();
	}

	static private void initColors() {
		colors = new HashMap<>();

		colors.put("aqua", new Color(000,255,255));
		colors.put("black", new Color(000,000,000));
		colors.put("blue", new Color(000,000,255));
		colors.put("fuchsia", new Color(255,000,255));
		colors.put("gray", new Color(128,128,128));
		colors.put("green", new Color(000,128,000));
		colors.put("lime", new Color(000,255,000));
		colors.put("brown", new Color(128,000,000));
		colors.put("navy", new Color(000,000,128));
		colors.put("olive", new Color(128,128,000));
		colors.put("purple", new Color(128,000,128));
		colors.put("red", new Color(255,000,000));
		colors.put("silver", new Color(192,192,192));
		colors.put("teal", new Color(000,128,128));
		colors.put("white", new Color(255,255,255));
		colors.put("yellow", new Color(255,255,000));
	}

	static public void addColor(String name, int r, int g, int b) {
		colors.put(name, new Color(r, g, b));
	}
}
