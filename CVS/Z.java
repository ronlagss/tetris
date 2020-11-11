package model;
import java.awt.Point;
import java.util.ArrayList;

public class Z extends Tetromino{
	/**
	 * Constructor
	 */

	public int or;


	public Z(Game game) {
		super(game, "Z", Cell.RED);

		this.locations.add(new Point(0,5));

		this.locations.add(new Point(0,4));
		this.locations.add(new Point(0,5));
		this.locations.add(new Point(1,5));
		this.locations.add(new Point(1,6));

		this.or = 1;

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

		if (ori == 1 || ori == 3) {
			temp.add(new Point(x+1, y));
			temp.add(new Point(x, y));
			temp.add(new Point(x, y+1));
			temp.add(new Point(x-1, y+1));

		}
		else  {

			temp.add(new Point(x, y-1));
			temp.add(new Point(x,y));
			temp.add(new Point(x+1,y));
			temp.add(new Point(x+1, y+1));

		}



		return temp;

	}



}
