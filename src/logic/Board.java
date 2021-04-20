package logic;
/** @author Vedad Vreto 
 * 
 */
import java.awt.Color;
import java.util.Arrays;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class Board {
	public static int score, moves, selectedx, selectedy, row_size, column_size;
	public int[][] board;
	
	/**
	 * CREATE NEW BOARD
	 * @param rows
	 * @param cols
	 */
	public Board(int rows,int cols) {
		row_size = rows;
		column_size = cols;
		board = new int[row_size][column_size];
		for(int i=0;i<row_size;i++) {
			for(int j=0;j<column_size;j++) {
				board[i][j] = 0;
			}
		}
	}
	
	/**
	 * COPY OLD BOARD
	 * @param b
	 */
	public Board(Board b) {
		row_size = b.getRowSize();
		column_size = b.getColSize();
		board = new int[row_size][column_size];
		for(int i=0;i<row_size;i++) {
			for(int j=0;j<column_size;j++) {
				board[i][j] = b.board[i][j];
			}
		}
	}
	
	/**
	 * GET ROW SIZE
	 * @return
	 */
	public int getRowSize() {
		return row_size;
	}
	
	/**
	 * GET COLUMN SIZE
	 * @return
	 */
	public int getColSize() {
		return column_size;
	}
	
	/**
	 * GET A RANDOM NUMBER FROM 1 TO 6
	 * @return
	 */
	public static int getRandom() {
    	Random r = new Random();
    	return r.nextInt(6);
    }
	
	/**
	 * GET PIECE FROM DESIRED POSITION
	 * @param x
	 * @param y
	 * @return
	 */
	public int getPiece(int x, int y) {
		return board[x][y];
	}
	
	/**
	 * CHECK IF A CLEARED PIECE EXISTS
	 * @return
	 */
	public boolean clearedElement() {
		boolean exists = false;
		for(int i=0;i<row_size;i++) {
			for(int j=0;j<column_size;j++) {
				if(board[i][j] == 0) exists = true;
			}
		}
		return exists;
	}
	
	/**
	 * CHECK IF TWO PIECES MATCH
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public boolean checkMatch(int x1, int y1, int x2, int y2) {	
		if ( (x1==x2 && y1+1 == y2) || (x1 == x2 && y1-1 == y2)){
			//
		}
		else if ((x1-1 == x2 && y1 == y2) || (x1 + 1 == x2 && y1 == y2)) {
			//
		}
		else {
			return false;
		}
		
		/**
		 * COPY OF THE CURRENT BOARD
		 */
		Board cpyBoard = new Board(this);
		cpyBoard.eliminate();
		if(cpyBoard.clearedElement()) {
			return false;
		}
		/**
		 * TEMPORARY BOARD FOR SWAPPING PIECES
		 */
		int tmp = cpyBoard.board[x1][y1];
		cpyBoard.board[x1][y1] = cpyBoard.board[x2][y2];
		cpyBoard.board[x2][y2] = tmp;
		cpyBoard.eliminate();
		if(cpyBoard.clearedElement()) {
			return true;
		}
		else return false;
	}
	
	/**
	 * CLEAR 3 OR MORE PIECES
	 */
    public void eliminate() {
    	for(int i = 0; i < row_size; i++)
	    {
	        for(int j = 0;j< column_size ; j++)
	        {
	        	if(j>0 && j<column_size-1) {
		            if( (board[i][j-1] == board[i][j]) && (board[i][j+1] == board[i][j]))
		            {   
		            	if(j+1<column_size-1 && board[i][j+2]==board[i][j+1]) {
		            		board[i][j+2] = 0;
		            		score += 150;
		            	}
		            	else if(j-1>0 && board[i][j-2]==board[i][j-1]){
		            		board[i][j-2] = 0;
		            		score += 150;
		            	}
		                board[i][j] = 0;
		                board[i][j+1] = 0;
		                board[i][j-1] = 0;
		                score += 100;
		            }
	        	}
	        	if(i>0 && i<row_size-1) {
	        		if( (board[i-1][j] == board[i][j]) && (board[i+1][j] == board[i][j]))
		            {   
	        			if(i+1<row_size-1 && board[i+2][j]==board[i+1][j]) {
		            		board[i+2][j] = 0;
		            		score += 150;
		            	}
		            	else if(i-1>0 && board[i-2][j]==board[i-1][j]){
		            		board[i-2][j] = 0;
		            		score += 150;
		            	}
		                board[i][j] = 0;
		                board[i+1][j] = 0;
		                board[i-1][j] = 0;
		                score += 100;
		            }
	        	}
	        }
	        
	    }
    	
    }
    
    /**
     * SWAP TWO PIECES
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public void swapPieces(int x1, int y1, int x2, int y2) {
    	int tmp = board[x1][y1];
    	board[x1][y1] = board[x2][y2];
    	board[x2][y2] = tmp;
    	do {
    		fillBoard();
    		eliminate();
    	}while(clearedElement());
    }
    
    
    /**
     * FILL THE BOARD WITH NEW PIECES
     */
    public void fillBoard() {
    	for(int j=0;j<column_size;j++) {
    		int [] tmpColumn = new int[row_size];
			for(int i=0;i<row_size;i++) {
				tmpColumn[i] = 0;
			}
    	int target_index = row_size - 1;
    	for(int i=row_size - 1; i>=0;i--) {
    		if(board[i][j] != 0) {
    			tmpColumn[target_index] = board[i][j];
    			target_index--;
    		}
    	}
    	
    	while(target_index >= 0) {
    		tmpColumn[target_index] = getRandom();
    		target_index--;
    		}
    	
    	for(int i=0;i<row_size;i++) {
    		board[i][j] = tmpColumn[i];
    	}
    	
    	}
    	
    }
    
    /**
     * CLEAR THE BOARD
     */
    public void clearBoard() {
    	for (int i=0;i<row_size;i++) {
    		for(int j=0;j<column_size;j++) {
    			board[i][j] = 0;
    		}
    	}
    }
}
