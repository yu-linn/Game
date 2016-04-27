package game;
/** Interface of a Grid of generic type.
 */
public interface Grid<T> {
	/** Sets the item into the cell of the location (row, column)
	 */
	public void setCell(int row, int column, T item);
	/** Returns the item into the cell of the location (row, column)
	 */
	public T getCell(int row, int column);
	/** Getter for number of rows in grid.
	 */
	public int getNumRows();
	/** Getter for number of columns in grid.
	 */
	public int getNumColumns();
	/** Checks if an Object equals Object other.
	 */
	public boolean equals(Object other);
	/** Returns a String representation of the grid.
	 */
	public String toString();

}
