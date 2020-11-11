package model;
import java.awt.Point;
import java.util.Random;

public class Tetris extends Game {
	private Random random;		//not used for this project
	private int  strategy;	//not used for this project
	private int score;

	private int N = 7; //number of possible pieces
	private int lineCount ; // number of lines collapsed
	private boolean needNewPiece = true;
	private Tetromino livePiece; 	//current piece
	private boolean gameOverFlag = false;

	public Tetris(int maxRows, int maxCols, Random random, int strategy) {
		super(maxRows, maxCols);
		this.random = random;
		this.strategy = strategy;
		score = 0;
		livePiece = null;
	}

	@Override
	public boolean isGameOver() {
		return gameOverFlag;
	}

	@Override
	public int getScore() {
		return score;
	}

	@Override
	public void nextAnimationStep(Integer piece) {

		if(needNewPiece) {
			insertNewPiece(piece);
			needNewPiece = false;
		}else {
			if(!livePiece.moveDown()) {
				collapse();
				needNewPiece = true;
				nextAnimationStep(piece);
			}
		}
	}

	private void collapse() {
		int r =  getMaxRows() - 1 ;
		int c = getMaxCols() - 1;
		int count = 0;				//this will keep track of how many lines are completed
		for(int i = r; i > 0; i--) {
			int j = 0;
			for( j = 0; j <= c; j++) {
				if(getBoardCell(i,j) == Cell.EMPTY) break;
			}

			if(j > c) {
				count++; //this counts how many lines you cleared at once
				for(int k = i; k > 0; k--) {
					for(j = 0; j <= c; j++) {
						setBoardCell(k,j, getBoardCell(k-1,j));
					}
				}
				for(int k = 0; k <= c; k++) {
					setBoardCell(k,0,Cell.EMPTY);
				}
				i++; //repeat the same row
			}
		}
		/* These scores as taken from Tetris Wiki Fandom.
		 * http://tetris.wikia.com/wiki/Scoring
		*/
		switch(count) {
		case(1): score += 40; break;
		case(2): score += 100; break;
		case(3): score += 300; break;
		case(4): score += 1200; break;
		default: break;
		}
		lineCount+=count;
	}

	@Override
	public void processCell(Action action, int rowIndex, int colIndex) {
		switch(action) {
		case MOVELFT:
			livePiece.moveLeft();
			break;
		case MOVEDOWN:
			livePiece.moveDown();
			break;
		case MOVERIGHT:
			livePiece.moveRight();
			break;
		case ROTATE:
			livePiece.rotate();
			break;
		case FALL:
			livePiece.fall();
			break;

		default:
			break;
		}
	}

	/**
	 * Randomly creates a piece and inserts it at the center of row 0
	 * @param piece is a integer which will dictate which piece. Null for random
	 * It is passed in from nextAnimationStep
	 */
	private void insertNewPiece(Integer piece) {
		int n;
		if (piece == null)
			n= (int)(Math.random() * N + 1);
		else
			n = piece;
		Tetromino p = null;



		switch(n) {
		case 1:
			p = new I(this);
			break;
		case 2:
			p = new O(this);
			break;
		case 3:
			p = new T(this);
			break;
		case 4:
			p = new J(this);
			break;
		case 5:
			p = new L(this);
			break;
		case 6:
			p = new S(this);
			break;
		case 7:
			p = new Z(this);
			break;
		}
		int x = 0;
		int y = getMaxCols() / 2;		//horizontal center

		if(p.insertAt(new Point(x, y)))
			livePiece = p;
		else {
			gameOverFlag = true;
		}
	}
	
	public Cell[][] getBoard() {return board;}

	//returns a string
	@Override
	public String toString() {
		String result = "";
		for(int i=0; i<getMaxRows();i++) {
			for(int j=0; j<getMaxCols();j++) {
				result += board[i][j].getName()+",";
			}
		}
		return result;
	}


}
