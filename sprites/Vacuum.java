package sprites;

import game.Constants;
/**
 * A subclass of Sprite that represents a Vacuum that stores a symbol
 * and the position of the sprite. Keeps track of the capacity, score, fullness
 * and the Sprite under the vacuum.
 */

public class Vacuum extends Sprite implements Moveable{
	private int score;
	private int capacity;
	private int fullness;
	private Sprite under;
	/** Inherits from Sprite */
	public Vacuum(char symbol, int row, int column, int capacity) {
		super(symbol, row, column);
		// TODO Auto-generated constructor stub
		this.capacity = capacity;
		this.fullness = Constants.EMPTY;
	}
	/** Getter for score. Returns the current score of the vacuum */
	public int getScore() {
		return score;
	}
	/** Checks if the vacuum can clean. 
	 * If it can, return true, else return false */
	public boolean clean(int score) {
		if (this.fullness == Constants.CAPACITY) {
			return false;
			}
		else {
			this.fullness += Constants.FULLNESS_INC;
			this.score += score;
			return true;
			}
		}
	/** If vacuum is on a dumpster, fullness is set to 0 */
	public void empty() {
		if (under.getSymbol() == Constants.DUMPSTER) {
			this.fullness = Constants.EMPTY;
		}
	}
	/** returns the sprite under the vacuum */
	public Sprite getUnder() {
		return under;
	}
	/** setter for the sprite under the vacuum */
	public void setUnder(Sprite under) {
		this.under = under;
	}
	@Override
	/** Move vacuum to a new row and column*/
	public void moveTo(int row, int column) {
		// TODO Auto-generated method stub
		this.row = row;
		this.column = column;
		
	}
	

}
