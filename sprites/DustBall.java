package sprites;
/**
 * A subclass of Dirt that represents a dust ball that stores a symbol
 * and the position of the sprite. Keeps track of the score of the dust ball as well.
 */
public class DustBall extends Dirt implements Moveable{
	/** Inherits from Dirt */
	public DustBall(char symbol, int row, int column, int value) {
		super(symbol, row, column, value);
		// TODO Auto-generated constructor stub
	}
	/** Move dust ball to new row and column */
	@Override
	public void moveTo(int row, int column) {
		// TODO Auto-generated method stub
		this.row = row;
		this.column = column;
	}

}
