package model;

import java.awt.*;

/*
 * This enumerated type represents a board cell.
 */

public enum Cell {
	  RED(Color.RED, "R"),CYAN(Color.CYAN, "CY"), PURPLE(Color.MAGENTA, "P"), GREEN(Color.GREEN, "G"), BLUE(Color.BLUE, "B"),
	  YELLOW(Color.YELLOW, "Y"), ORANGE(Color.ORANGE, "O"), EMPTY(Color.WHITE, ".");

	private final Color color;
	private final String name;
	private static int totalColors = Cell.values().length;

	private Cell(Color color, String name) {
		this.color = color;
		this.name = name;
	}

	public Color getColor() {
		return color;
	}

	public String getName() {
		return name;
	}

	public static int getTotalColors() {
		return totalColors;
	}
}
