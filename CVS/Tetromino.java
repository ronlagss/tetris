package model;

import java.awt.Point;
import java.util.ArrayList;
public abstract class Tetromino {
	protected final Cell cell;
	protected final String name;
	protected ArrayList<Point> locations;
	protected ArrayList<Point> [] layout;

	protected Game game;
	public abstract boolean rotate();
	protected int orientation; // 0 up, 1 right, 2 down, 3 left
	protected int length;   //length of the piece when it is initially inserted
	protected int height;   //height of the piece when it is initially inserted
	protected Point origin;
	protected int or;




	@SuppressWarnings("unchecked")
	public Tetromino(Game game, String name, Cell cell) {
		this.name = name;
		this.cell = cell;
		locations = new ArrayList<>();
		this.game = game;
		layout = (ArrayList<Point> [])(new ArrayList[4]);

	}

	public Cell getCell() { return cell;}

	public boolean moveDown() {
		//if index is too large, return false.
		for (int i = 1; i<5; i++) {
			int x = (int)locations.get(i).getX();
			int y = (int)locations.get(i).getY();
			if (x == 21) {
				return false;
			}

		}

		//Now check if the block can be moved. If cannot, reset and return false.
		this.clearCell(locations);
		for(int i = 1; i < 5; i++) {
			if(locations.get(i).getX()+1>=game.getMaxRows() ||
					!game.getBoardCell((int)locations.get(i).getX()+1,
							(int)locations.get(i).getY()).getName().equals(".")) {
				this.colorCell(locations);
				return false;
			}

		}


		//Having passed the test, move the block by one block.
		this.clearCell(locations);

		for(int i = 0; i<5; i++) {
			locations.get(i).translate(1, 0);
		}

		this.colorCell(locations);

		return true;
	}

	public boolean moveLeft() {
		//check if index is too small. If it is, return false.
		for (int i = 1; i<5; i++) {
			int x = (int)locations.get(i).getX();
			int y = (int)locations.get(i).getY();
			if (y == 0) {
				return false;
			}

		}

		//Test if left is empty. If not, reset and return false.
		this.clearCell(locations);
		for (int i = 1; i < 5; i++) {
			int x = (int)locations.get(i).getX();
			int y = (int)locations.get(i).getY();
			if (y <= -1) {
				this.colorCell(locations);
				return false;
			}
			if (!game.getBoardCell(x, y-1).getName().equals(".")) {
				this.colorCell(locations);
				return false;
			}
		}

		//Having passed the test, move the block by one and update the points.
		this.clearCell(locations);
		for(int i = 0; i<5; i++) {
			locations.get(i).translate(0, -1);
		}
		this.colorCell(locations);
		return true;

	}

	/**
	 * This will move the piece to the right by one column
	 * @return true if allowed, false otherwise
	 */
	public boolean moveRight() {
		//Same for move left, but check if index is too large this time.
		//Since the commenting is repetitive, I will not write it for styling purpose.
		for (int i = 1; i<5; i++) {
			int x = (int)locations.get(i).getX();
			int y = (int)locations.get(i).getY();
			if (y == 9) {
				return false;
			}

		}

		this.clearCell(locations);
		for (int i = 1; i < 5; i++) {
			int x = (int)locations.get(i).getX();
			int y = (int)locations.get(i).getY();
			if (y >= 10) {
				this.colorCell(locations);
				return false;
			}
			if (!game.getBoardCell(x, y+1).getName().equals(".")) {
				this.colorCell(locations);
				return false;
			}
		}
		this.clearCell(locations);
		for(int i = 0; i<5; i++) {
			locations.get(i).translate(0, 1);
		}
		this.colorCell(locations);
		return true;
	}

	public boolean fall() {

		//Simply call moveDown until it returns false.

		if (this.moveDown() == true) {
			while (this.moveDown() == true) {
				this.moveDown();
			}
			return true;

		}

		return false;

	}

	public boolean insertAt(Point center) {
		//Check if the tiles are empty. If not, return false.
		for (int i = 1; i<5; i++) {
			int x = (int) locations.get(i).getX();
			int y = (int) locations.get(i).getY();

			if (!game.getBoardCell(x, y).equals(Cell.EMPTY)) {
				return false;
			}
		}

		//Now that we know the tiles are empty, insert the piece.
		for (int i = 1; i<5; i++) {
			int x = (int) locations.get(i).getX();
			int y = (int) locations.get(i).getY();
			game.setBoardCell(x, y, cell);

		}

		return true;
	}

	/*
	 * Set origin and get origin does most of the heavy lifting of this project.
	 * When moving a tetromino piece, you don’t want to go through an array and
	 * change each point. Instead, you keep track of only one position, the
	 * origin and then build everything relative to that origin.
  /*

	/**
	 * This returns the origin of a piece
	 * @return a Point consisting of the coordinates of the Origin square of a piece
	 */
	public final Point getOrigin() {
		//return first element of locations ArrayList.
		if (locations!=null) {
			return locations.get(0);
		}

		return null;
	}

	/**
	 * This sets the Origin square of a piece based around p and builds the piece around that.
	 * This checks to see if the tiles it will occupy are already occupied or not.
	 * Used when moving and rotating pieces around
	 * @param point of where the new top Origin should be
	 * @return true if the point was able to be set, or false if the point could not be set
	 */
	public final boolean setOrigin(Point p) {
		return true;
	}


	protected void clearCell (ArrayList<Point> in) {

		for (int i = 1; i<5; i++) {
			int x = (int) locations.get(i).getX();
			int y = (int) locations.get(i).getY();
			game.setBoardCell(x, y, Cell.EMPTY);
		}
	}

	protected void colorCell (ArrayList<Point> in) {

		for (int i =1; i<5; i++) {
			int x = (int) locations.get(i).getX();
			int y = (int) locations.get(i).getY();

			game.setBoardCell(x, y, cell);
		}

	}

}
