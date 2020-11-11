package model;
import java.awt.Point;
import java.util.ArrayList;

public class O extends Tetromino {

	public Point origin;


	public O(Game game) {
		/**
		 * Constructor
		 */
		super(game, "O", Cell.YELLOW);
		this.locations.add(new Point(0,5));

		this.locations.add(new Point(0,5));
		this.locations.add(new Point(0,6));
		this.locations.add(new Point(1,5));
		this.locations.add(new Point(1,6));
		origin = this.locations.get(0);
	}

	/**
	 * rotates the piece counter-clockwise
	 */
	@Override
	public boolean rotate() {
		return true;
	}

	public ArrayList<Point> orient() {
		return this.locations;
	}

}
