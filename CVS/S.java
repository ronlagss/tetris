package model;
import java.awt.Point;
import java.util.ArrayList;

public class S extends Tetromino{
	/**
	 * Constructor
	 * @param game used in the call to super constructor
	 */

	//center at 0,4
	public S(Game game) {
		super(game, "S", Cell.GREEN);
		this.or = 1;
		this.locations.add(new Point(0,4));

		this.locations.add(new Point(0,4));
		this.locations.add(new Point(0,5));
		this.locations.add(new Point(1,3));
		this.locations.add(new Point(1,4));

	}

	/**
	 * rotates the piece counter-clockwise
	 */
	@Override
	public boolean rotate() {
		ArrayList<Point> test = this.orient();


		this.clearCell(locations);

		for (int i = 1; i < 5; i++) {
			int x = (int) test.get(i).getX();
			int y = (int) test.get(i).getY();
			if (x < 0 || y < 0) {
				this.colorCell(locations);
				return false;
			}
			if (y >= 10 || x >= 22) {
				this.colorCell(locations);
				return false;
			}
			if (!game.getBoardCell(x, y).getName().equals(".")) {
				this.colorCell(locations);
				return false;
			}
		}

		this.locations = test;
		this.colorCell(test);

		or++;
		return true;
	}

	private ArrayList<Point> orient() {

		ArrayList<Point> temp = new ArrayList<Point>();
		int originX = (int)this.locations.get(0).getX();
		int originY = (int)this.locations.get(0).getY();


		int x = originX;
		int y = originY;

		temp.add(this.locations.get(0));
		int ori = this.or%4;

		if (ori == 0 || ori == 2) {
			temp.add(new Point(x,y));
			temp.add(new Point(x,y+1));
			temp.add(new Point(x+1, y-1));
			temp.add(new Point(x+1, y));
		}
		else {
			temp.add(new Point(x-1,y-1));
			temp.add(new Point(x,y-1));
			temp.add(new Point(x,y));
			temp.add(new Point(x+1,y));
		}


		return temp;

	}
}
