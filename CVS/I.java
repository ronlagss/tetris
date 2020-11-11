package model;
import java.awt.Point;
import java.util.ArrayList;

public class I extends Tetromino {
	/**
	 * Constructor. You may want to modify
	 * @param game used in the call to super constructor
	 */
	public I(Game game) {
		super(game, "I", Cell.CYAN);
		//Reference point
		this.locations.add(new Point(0,5));
		//"Real" points, will be colored. Index is 1-4
		this.locations.add(new Point(0,5));
		this.locations.add(new Point(1,5));
		this.locations.add(new Point(2,5));
		this.locations.add(new Point(3,5));
		this.or = 1;
	}

	/**
	 * rotates the piece counter-clockwise. See above orientation
	 * for reference on which tile to rotate around.
	 */
	@Override
	public boolean rotate() {
		//this method will test orient and return false
		ArrayList<Point> test = this.orient();

		//first, clear the block temporarily.
		this.clearCell(locations);

		//Test if the block can be rotated.
		for (int i = 1; i < 5; i++) {
			int x = (int) test.get(i).getX();
			int y = (int) test.get(i).getY();
			//if index is less than 0, reset and return false.
			if (x < 0 || y < 0) {
				this.colorCell(locations);
				return false;
			}
			//if index is too large, reset and return false.
			if (y >= 10 || x >= 22) {
				this.colorCell(locations);
				return false;
			}
			//if the points are occupied, reset and return false.
			if (!game.getBoardCell(x, y).getName().equals(".")) {
				this.colorCell(locations);
				return false;
			}
		}
		//Now having passed the test, execute rotation.
		this.locations = test;
		this.colorCell(test);
		//update orientation number.
		or++;
		return true;
	}


	//This private method serves to give points of future moves.
	private ArrayList<Point> orient() {


		ArrayList<Point> temp = new ArrayList<Point>();
		int originX = (int)this.locations.get(0).getX();
		int originY = (int)this.locations.get(0).getY();


		int x = originX;
		int y = originY;

		//get orientation number.
		int ori = this.or%2;

		//for each orientation, different points must be added.

		if (ori == 1) {
			temp.add(new Point(x,y));

			temp.add(new Point(x+1,y-2));
			temp.add(new Point(x+1,y-1));
			temp.add(new Point(x+1,y));
			temp.add(new Point(x+1,y+1));

		}
		else {
			temp.add(new Point(x,y-1));

			temp.add(new Point(x,y-1));
			temp.add(new Point(x+1,y-1));
			temp.add(new Point(x+2,y-1));
			temp.add(new Point(x+3,y-1));
		}

		//return future points.
		return temp;


	}



}
