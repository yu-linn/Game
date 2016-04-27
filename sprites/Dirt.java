package sprites;
/**
 * A subclass of Sprite that represents a dirt that stores a symbol
 * and the position of the sprite. Keeps track of the value of the score of the dirt.
 */
public class Dirt extends Sprite {
	protected int value;
	/** Inherits from Sprite */
	public Dirt(char symbol, int row, int column,int value) {
		super(symbol, row, column);
		// TODO Auto-generated constructor stub
		this.value = value;
	}
	/** Getter for value. Returns value*/
	public int getValue() {
		return value;
	}
	

}
