package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageToCharArr {
	private String imagePath;
	private char black;
	private char white;
	private int width;
	private int height;
	private char [][] charArr;
	private BufferedImage image;

	public ImageToCharArr(String imagePath, char white, char black) throws IOException, ImageException {
		this.imagePath = imagePath;
		this.black = black;
		this.white = white;

		File file = new File(imagePath);
		if (!file.exists())
			throw new IOException("File not found!");
		image = ImageIO.read(file);

		this.height = image.getHeight();
		this.width = image.getWidth();

		this.charArr = new char[this.height][this.width];
	}

	public void convert() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (image.getRGB(j, i) == ColorsSet.getIntColor("black"))
					charArr[i][j] = black;
				else
					charArr[i][j] = white;
			}
		}
	}

	public String getImagePath() {
		return imagePath;
	}

	public char getBlack() {
		return black;
	}

	public char getWhite() {
		return white;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public char[][] getCharArr() {
		return charArr;
	}
}
