package terminal;
/**
 * @author Vedad Vreto
 */
import java.util.Arrays;
import java.util.Scanner;

import logic.*;

public class PlanetCrushers {
	
	 static Board number_board;
	 public static boolean selected = false;
	 static int[][] planets;
	 static int score = 0;
	 static int moves = 10;
	 static int row_size = 6;
	 static int column_size = 6;
	 public static int selectedx = -1;
	 public static int selectedy = -1;
	
	 /**
	  * PRINT THE BOARD
	  */
	public static void printBoard() {
		System.out.println("");
		for (int[] row : number_board.board) System.out.println(Arrays.toString(row));
        System.out.println("");
	}
	
	/**
	 * REFRESH THE BOARD
	 */
	public static void refreshBoard() {
    	for(int i=0;i<row_size;i++) {
			for(int j=0;j<column_size;j++) {
				planets[i][j] = number_board.getPiece(i, j);
			}
		}
		
    }
	
	/**
	 * SET THE BOARD
	 */
	public static void setBoard() {
		number_board.clearBoard();
		score = 0;
		while(number_board.clearedElement()) {
			number_board.fillBoard();
			number_board.eliminate();
		}
		refreshBoard();
		printBoard();
	}
	
	/**
	 * GET USER INPUT FROM CONSOLE
	 * @return
	 */
	private static String getInput() {
		Scanner s = new Scanner(System.in);
		String input = "";
		try {
			input = s.nextLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return input;
	}
	
	/**
	 * SWAP SELECTED PIECE
	 * @param row
	 * @param column
	 */
	public static void swapPiece(int row, int column) {
		if(!selected) {
    		selected = true;
    		selectedx = row;
    		selectedy = column;
    		return;
    	}
    	
    	if(!(number_board.checkMatch(row, column, selectedx, selectedy))) {
    		selected = false;
    		selectedx = -1;
    		selectedy = -1;
    		System.out.println("Invalid move!");
    		return;
    	}
    	
    	score+=100;
    	moves--;
    	number_board.swapPieces(row, column, selectedx, selectedy);
    	System.out.println("Swapped: (" + row + "," + column + ") with (" + selectedx + "," + selectedy + ")");
    	selected = false;
    	selectedx = -1;
    	selectedy = -1;
    	refreshBoard();
	}

	public static void main(String[] args) {
		boolean STARTED = true;
   	 	System.out.println("Planet Crushers\n");
   	 	System.out.println("Match three of the same planets to crush them.\n");
   	 	System.out.println("Enter the coordinates of the planets you want to swap one at a time.");
   	 	System.out.println("The game ends when there is no moves left");
   	 	number_board = new Board(row_size,column_size);
   	 	planets = new int[row_size][column_size];
   	 	setBoard();
   	 	while(STARTED) {
   	 		if(moves==0) {
   	 			System.out.println("End of the Game.");
   	 			System.out.println("Your Score was: " + score);
   	 			System.exit(0);
   	 		}
   	 		System.out.println("Enter a point: ");
   	 		String x = getInput();
   	 		String y = getInput();
   	 		swapPiece(Integer.parseInt(x),Integer.parseInt(y));
   	 		printBoard();
   	 	}
   	 
	}

}
