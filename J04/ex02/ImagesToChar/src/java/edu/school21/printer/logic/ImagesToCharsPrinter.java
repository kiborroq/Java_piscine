package edu.school21.printer.logic;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImagesToCharsPrinter {
	private String imagePath;
	private Ansi.BColor blackFill;
	private Ansi.BColor whiteFill;
	private int width;
	private int height;
	private BufferedImage image;

	public ImagesToCharsPrinter(String imagePath, String whiteFill, String blackFill) throws IOException {
		this.imagePath = imagePath;
		this.blackFill = ColorsSet.getColor(blackFill);
		this.whiteFill = ColorsSet.getColor(whiteFill);

		InputStream in = this.getClass().getResourceAsStream(this.imagePath);
		image = ImageIO.read(in);

		this.height = image.getHeight();
		this.width = image.getWidth();
	}

	public void printImage() {
		ColoredPrinter cp = new ColoredPrinter();

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (image.getRGB(j, i) == ColorsSet.getBlackRGB())
					cp.print("  ", Ansi.Attribute.NONE, Ansi.FColor.NONE, blackFill);
				else
					cp.print("  ", Ansi.Attribute.NONE, Ansi.FColor.NONE, whiteFill);
			}
			System.out.println();
		}
	}

	public String getImagePath() {
		return imagePath;
	}

	public Ansi.BColor getBlackFill() {
		return blackFill;
	}

	public Ansi.BColor getWhiteFill() {
		return whiteFill;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
