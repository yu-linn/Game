package sprites;
/**
 * A class that represents a Sprite that stores a symbol
 * and the position of the sprite
 */
public abstract class Sprite {
	protected char symbol;
	protected int row;
	protected int column;
	/**
	 * Initializing the Sprite variables
	 */
	public Sprite(char symbol, int row, int column) {
		this.symbol = symbol;
		this.row = row;
		this.column = column;
	}
	/**
	 * Getter for symbol
	 */
	public char getSymbol() {
		return symbol;
	}
	/**
	 * Getter for row
	 */
	public int getRow() {
		return row;
	}
	/**
	 * Getter for column
	 */
	public int getColumn() {
		return column;
	}
	/**
	 * Returns a string representation of the Sprite.
	 */
	public String toString() {
		return Character.toString(this.symbol);
	}

}
