package game;
/** Generic class ArrayGrid that implements the generic interface Grid.*/
public class ArrayGrid<T> implements Grid<T> {
	private int numRows;
	private int numColumns;
	private T[][] array;
	/** Initialize ArrayGrid*/
	public ArrayGrid(int numRows, int numColumns) {
		this.numRows = numRows;
		this.numColumns = numColumns;
		this.array = (T[][]) new Object[numRows][numColumns];
	}

	@Override
	public void setCell(int row, int column, T item) {
		// TODO Auto-generated method stub
		this.array[row][column] = item;
		
	}

	@Override
	public T getCell(int row, int column) {
		// TODO Auto-generated method stub
		return this.array[row][column];
	}

	@Override
	public int getNumRows() {
		// TODO Auto-generated method stub
		return numRows;
	}

	@Override
	public int getNumColumns() {
		// TODO Auto-generated method stub
		return numColumns;
	}
	/** Check if an ArrayGrid equals another Object of ArrayGrid*/
	public boolean equals(ArrayGrid<T> other) {
		return (this.numRows == other.numRows) && (this.numColumns == other.numColumns) && (this.array == other.array);
	}
	/** @Return a string representation of ArrayGrid*/
	public String toString() {
	    String builder = "";
	    //add the string representation of every element of array into the string builder.
	    for(int i = 0; i < getNumRows(); i++)
	    {
	        for(int j = 0; j < getNumColumns(); j++)
	        {	String k = array[i][j].toString();
	            builder += k;
	        }
	        builder += "\n";
	    }    
	    return builder.toString();
		
	} 

}
