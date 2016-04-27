package ui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.util.Scanner;
import game.VacuumGame;
/** A simple TextUI for the game. */
public class TextUI implements UI{
	private VacuumGame game;
	/** Initializes a TextUI for the given MazeGame.
	 * @param game The MazeGame of this TextUI
	 */
	public TextUI(VacuumGame game) {
		this.game = game;
	}
	@Override
	public void launchGame() {
		// TODO Auto-generated method stub

		int numRows = this.game.getNumRows();
		int numCols = this.game.getNumColumns();
		String [] tiles = new String[numRows];
		//put sprites into a string and print
		for (int i = 0; i < numRows; i++) {
			String s = new String();
			for (int j = 0; j < numCols; j++) {
				s += this.game.getSprite(i, j).toString();}
			tiles[i] = s;
			System.out.println(tiles[i]);
		}
		//When game is not over, run the game.
		while (!this.game.gameOver()) {
			String m;
			//ask for user input for move
			Scanner input = new Scanner(System.in);
			System.out.print("Enter next move:");
			m = input.next();
			this.game.move(m.charAt(0));
			//update grid
			for (int i = 0; i < numRows; i++) {
				String s = new String();
				for (int j = 0; j < numCols; j++) {
					s += this.game.getSprite(i, j).toString();}
				tiles[i] = s;
				System.out.println(tiles[i]);
			}
		}
	}
	@Override
	//show winner when game is over
	public void displayWinner() {
		// TODO Auto-generated method stub
	    if (!this.game.gameOver()) {
	        return;
	    }
	    
	    int won = this.game.getWinner();
		String message;

		if (won == 1) {
			message = "Congratulations Player 1! You won the game with a score of " + 
					this.game.getVacuumOne().getScore() + ".";
		} else { 
			message = "Congratulations Player 2! You won the game with a score of " + 
					this.game.getVacuumTwo().getScore() + ".";
		}
		System.out.println(message);
	}
}

