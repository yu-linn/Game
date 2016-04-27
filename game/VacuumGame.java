package game;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import sprites.CleanHallway;
import sprites.Dirt;
import sprites.Dumpster;
import sprites.DustBall;
import sprites.Sprite;
import sprites.Vacuum;
import sprites.Wall;
import game.Constants;

/**
 * A class that represents the basic functionality of the vacuum game.
 * This class is responsible for performing the following operations:
 * 1. At creation, it initializes the instance variables used to store the
 *        current state of the game.
 * 2. When a move is specified, it checks if it is a legal move and makes the
 *        move if it is legal.
 * 3. It reports information about the current state of the game when asked.
 */
public class VacuumGame {

    // a random number generator to move the DustBalls
    private Random random;

    // the grid
    private Grid<Sprite> grid;

    // the first player
    private Vacuum vacuum1;

    /// the second player
    private Vacuum vacuum2;

    // the dirt (both static dirt and mobile dust balls)
    private List<Dirt> dirts;

    // the dumpsters
    private List<Dumpster> dumpsters;

    /**
     * Creates a new VacuumGame that corresponds to the given input text file.
     * Assumes that the input file has one or more lines of equal lengths, and
     * that each character in it (other than newline) is a character that 
     * represents one of the sprites in this game.
     * @param layoutFileName path to the input grid file
     */
    public VacuumGame(String layoutFileName) throws IOException {
        this.dirts = new ArrayList<Dirt>();
        this.dumpsters = new ArrayList<Dumpster>(); // Jen: may not need this
        this.random = new Random();

        // open the file, read the contents, and determine 
        // dimensions of the grid
        int[] dimensions = getDimensions(layoutFileName);
        this.grid = new ArrayGrid<Sprite>(dimensions[0], dimensions[1]);

        // open the file again, read the contents, and store them in grid
        Scanner sc = new Scanner(new File(layoutFileName));

	// INITIALIZE THE GRID HERE

        String[] cur = new String[this.grid.getNumRows()];
        int k = 0;
        //put text into array of strings
        for (int i = 0; i < this.grid.getNumRows(); i++) {
            cur[i] = sc.nextLine();
        }
        //put each sprite into the appropriate cell
        for (int y=0; y< this.grid.getNumRows(); y++)
        {
            for (int x=0; x<this.grid.getNumColumns(); x++)
            {	//if char in the string equals clean symbol, place CleanHallway sprite in grid
            	if (cur[y].charAt(x) == Constants.CLEAN) {
                	Sprite item = new CleanHallway(Constants.CLEAN, y,x);
                	this.grid.setCell(y, x, item);
                }
            	//if char in the string equals dumpster symbol, place Dumpster sprite in grid
                else if (cur[y].charAt(x) == Constants.DUMPSTER) {
                	Sprite item = new Dumpster(Constants.DUMPSTER, y,x);
                	this.grid.setCell(y, x, item);
                }
            	//if char in the string equals dirt symbol, place Dirt sprite in grid
                else if (cur[y].charAt(x)==Constants.DIRT) {
                	Dirt item = new Dirt(Constants.DIRT,y,x,Constants.DIRT_SCORE);
                	//add dirts into this.dirts
                	this.dirts.add(item);
                	this.grid.setCell(y, x, item);
                
                }
            	//if char in the string equals wall symbol, place Wall sprite in grid
                else if (cur[y].charAt(x) == Constants.WALL) {
                	Sprite item = new Wall(Constants.WALL, y,x);
                	this.grid.setCell(y, x, item);
                }
            	//if char in the string equals dust ball symbol, place DustBall sprite in grid
                else if (cur[y].charAt(x) == Constants.DUST_BALL) {
                	DustBall item = new DustBall(Constants.DUST_BALL,y,x,Constants.DUST_BALL_SCORE);
                	//add dustball into this.dirts
                	//this.dirts.add(item);
                	this.grid.setCell(y, x, item);
                
                }
            	//if char in the string equals player 1 symbol, place Vacuum of vacuum1 sprite in grid
                else if (cur[y].charAt(x) == Constants.P1) {
                	this.vacuum1 = new Vacuum(Constants.P1,y,x,Constants.INIT_SCORE);
                	Sprite t = new CleanHallway(Constants.CLEAN, y,x);
                	this.vacuum1.setUnder(t);
                	this.grid.setCell(y, x, this.vacuum1);
                }
            	//if char in the string equals player 2 symbol, place Vacuum of vacuum2 sprite in grid
                else if (cur[y].charAt(x) == Constants.P2) {
                	this.vacuum2 = new Vacuum(Constants.P2,y,x,Constants.INIT_SCORE);
                	Sprite t = new CleanHallway(Constants.CLEAN, y,x);
                	this.vacuum2.setUnder(t);
                	this.grid.setCell(y, x, this.vacuum2);
                }
            	 //reads the next char
            }
        }

        sc.close();
    }


	/**
     * Returns the dimensions of the grid in the file named layoutFileName.
     * @param layoutFileName path of the input grid file
     * @return an array [numRows, numCols], where numRows is the number
     * of rows and numCols is the number of columns in the grid that
     * corresponds to the given input grid file
     * @throws IOException
     */
    private int[] getDimensions(String layoutFileName) throws IOException {       

        Scanner sc = new Scanner(new File(layoutFileName));

        // find the number of columns
        String nextLine = sc.nextLine();
        int numCols = nextLine.length();

        int numRows = 1;

        // find the number of rows
        while (sc.hasNext()) {
            numRows++;
            nextLine = sc.nextLine();
        }

        sc.close();
        return new int[]{numRows, numCols};
    }

    /** Getter for grid. Return grid*/
	public Grid<Sprite> getGrid() {
		return grid;
	}

	/** Getter for vacuum1. Return the vacuum1*/
	public Vacuum getVacuumOne() {
		return vacuum1;
	}
	/** Getter for vacuum2. Return the vacuum2*/
	public Vacuum getVacuumTwo() {
		return vacuum2;
	}
	/** Getter for the number of rows in the grid. Return the number of rows*/
	public int getNumRows() {
		return this.grid.getNumRows();
		
	}
	/** Getter for the number of columns in the grid. Return the number of columns*/
	public int getNumColumns() {
		return this.grid.getNumColumns();
	}
	/** Get the sprite in the cell (i,j)*/
	public Sprite getSprite(int i, int j) {
		return this.grid.getCell(i, j);
	}
	/** A method that allows the vacuum to move about the grid.
	 * If the moveMove is valid move, apply move and return true.
	 *  If not, return false.
	 *  @param the char the player inputs to move.
	 *  @return return true if valid move, else false
	 *  */
	public boolean move(char nextMove) {
		int x = this.vacuum1.getColumn();
		int y = this.vacuum1.getRow();
		int s = this.vacuum2.getRow();
		int t = this.vacuum2.getColumn();
		//p1 moves left
		if (nextMove == Constants.P1_LEFT) {
			return this.validmove(y, x, y, x-1, this.vacuum1);
		}
		//p1 moves right
		else if (nextMove == Constants.P1_RIGHT) {
			return this.validmove(y, x, y, x+1, this.vacuum1);
		}
		//p1 moves down	
		else if (nextMove == Constants.P1_DOWN) {
			return this.validmove(y, x, y+1, x, this.vacuum1);
		}
		//p1 moves up
		else if (nextMove == Constants.P1_UP) {
			return this.validmove(y, x, y-1, x, this.vacuum1);
		}
		//p2 moves left
		else if (nextMove == Constants.P2_LEFT) {
			return this.validmove(s, t, s, t-1, this.vacuum2);
		}
		//p2 moves right
		else if (nextMove == Constants.P2_RIGHT) {
			return this.validmove(s, t, s, t+1, this.vacuum2);
		}
		//p2 moves down
		else if (nextMove == Constants.P2_DOWN) {
			return this.validmove(s, t, s+1, t, this.vacuum2);
		}
		//p2 moves up
		else if (nextMove == Constants.P2_UP) {
			return this.validmove(s,t,s-1,t,this.vacuum2);
		}
		//not valid move
		else {
			
			return false;
		}
		//move dustballs
		
	}
	/** Helper function to the method move.
	 * @param Takes the current position x,y of the vacuum and
	 * checks if the new position is i,j is a valid move. If it is, apply the move and
	 * update the grid.
	 * @return if valid move return true, else false.*/
	private boolean validmove(int y, int x, int i, int j, Vacuum v) {
		boolean notvalid = false;
		//if the sprite of the new cell is a wall
		if (getSprite(i,j).getSymbol() == Constants.WALL) {
			notvalid = false;
			
		}
		//if the sprite of the new cell is a player
		else if (getSprite(i,j).getSymbol() == Constants.P2 || getSprite(i,j).getSymbol() == Constants.P1) {
			notvalid = false;
			
		}
		//if the sprite of the new cell is a clean hallway
		else if (getSprite(i,j).getSymbol() == Constants.CLEAN) {
			Sprite n = v.getUnder();
			v.setUnder(getSprite(i,j));
			v.moveTo(i, j);
			this.grid.setCell(y, x, n);
			this.grid.setCell(i,j,v);
			notvalid = true;
			
		}
		//if the sprite of the new cell is a dumpster
		else if (getSprite(i,j).getSymbol() == Constants.DUMPSTER) {
			Sprite n = v.getUnder();
			v.setUnder(getSprite(i,j));
			v.empty();
			v.moveTo(i, j);
			this.grid.setCell(y,x,n);
			this.grid.setCell(i,j, v);
			notvalid = true;
			
		}
		//if the sprite of the new cell is a dustball
		else if (getSprite(i,j).getSymbol() == Constants.DUST_BALL) {
			 if (v.clean(Constants.DUST_BALL_SCORE)) {
				 //if vacuum can clean
				 Sprite n = v.getUnder();
				 Sprite h = new CleanHallway(Constants.CLEAN,i,j);
				 this.dirts.remove(getSprite(i,j));
				 v.setUnder(h);
				 v.moveTo(i,j);
				 this.grid.setCell(y, x, n);
				 this.grid.setCell(i, j, v);
				 notvalid = true;
				 
			 }
			 else if(!v.clean(Constants.DUST_BALL_SCORE)) {
				//if vacuum cannot clean
				 Sprite n = v.getUnder();
				 v.setUnder(getSprite(i,j));
				 v.moveTo(i, j);
				 this.grid.setCell(y,x,n);
				 this.grid.setCell(i, j, v);
				 notvalid = true;
				 
			}
		}
		//if the sprite of the new cell is a clean hallway
		else if (getSprite(i,j).getSymbol() == Constants.DIRT) {
			 if (v.clean(Constants.DIRT_SCORE)) {
				//if vacuum can clean
				 Sprite n = v.getUnder();
				 Sprite h = new CleanHallway(Constants.CLEAN,i,j);
				 this.dirts.remove(getSprite(i,j));
				 v.setUnder(h);
				 v.moveTo(i,j);
				 this.grid.setCell(y, x, n);
				 this.grid.setCell(i, j,v);
				 notvalid = true;
				 
			 }
			 else if (!v.clean(Constants.DIRT_SCORE)) {
				//if vacuum cannot clean
				 Sprite n = v.getUnder();
				 v.setUnder(getSprite(i,j));
				 v.moveTo(i, j);
				 this.grid.setCell(y,x,n);
				 this.grid.setCell(i, j, v);
				 notvalid = true;
				 
				 }
		}
		else {
			//not valid move
			notvalid = false;
		}
		//move the dustballs on the grid
		this.dustmove();
		return notvalid;
	}
	/** Finds all the dustballs on the grid and call dustballMove for each dustball*/
	private void dustmove() {
		for (int i=0; i < this.grid.getNumRows(); i++) {
			for (int j=0; j<this.grid.getNumColumns(); j++ ) {
				if (getSprite(i,j).getSymbol() == Constants.DUST_BALL) {
					this.dustballMove(getSprite(i,j).getRow(),getSprite(i,j).getColumn(), (DustBall) getSprite(i,j));
				}
			}
		}
	}
	/** A method that makes the dust ball move each time the method
	 * move is applied
	 * @param move the current position (y,x) of dustball*/
	private void dustballMove(int y,int x, DustBall dustball) {
		Random rand = new Random();
		//generate a random direction for dust ball
		int direction = rand.nextInt(4) + 1;
		int i = y;
		int j = x;
		if (direction == 1) { //up
			i -= 1;
		}
		else if (direction == 2) { //down
			i += 1;
		}
		else if (direction == 3) { //left
			j-= 1;
		}
		else if (direction == 4) { //right
			j+=1;
		}
		char s = getSprite(i,j).getSymbol();
		//if the new cell the dust ball goes into is a dirt or a hallway, the move is valid
		if ((s == Constants.CLEAN) || (s == Constants.DIRT) || (s == Constants.DUST_BALL)) {
			Dirt n = new Dirt(Constants.DIRT,y,x,Constants.DIRT_SCORE);
			this.dirts.remove(dustball);
			dustball.moveTo(i, j);
			this.grid.setCell(y, x, n);
			this.grid.setCell(i,j,dustball);
		}
		//do nothing if dust ball cannot make valid move
		else {
			
		}
		
	}
	/** Checks to see if the game is over, when there are no dirts
	 * and dustballs left.
	 * @return return true if this.dirts is empty*/
	public boolean gameOver() {
		if (this.dirts.isEmpty()) {
			return true;}
		else {
			return false;
		}
	}
	/** @Return 1 if vacuum1 score is higher than vacuum2.*/
    public int getWinner() {
    	if (getVacuumOne().getScore() > getVacuumTwo().getScore()) {
    		return 1;
    	}
    	else {
    		return 2;
    	}
    }
}
