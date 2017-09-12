package sudoku;

import java.util.Random;

public class Sudoku {
	static int[][] board;
	
	public Sudoku(){
		board = new int[9][9]; //9 rows and 9 cols
	}

	protected int returnStartOfSubGrid(int coordinate){ //cooridnate could be row or col
		int result = (coordinate/3) * 3;
		return result;
	}
	
	protected int produceRandNum(){
		Random rand = new Random();
		int num = rand.nextInt(9);   //picks a random number between 0 and 9
		return num;
	}

	public boolean checkRow(int num, int row){
		for (int i=0; i < board.length; i++){
			if (board[row][i]== num){
				return false; //not safe to place
			}
		}
		return true;
	}
	
	public boolean checkCol(int num, int col){
		for (int i = 0; i < board.length; i++){
			if (board[i][col]== num){
				return false;
			}
		}
		return true;		
	}
	/*
	 * Purpose: Checks the subgrid on the overall board. Starts on the top left hand corner or right hand corner
	 * 
	 * Argument: integer number (the one that we are checking), int row, int col1
	 * 
	 * Return: True, if the number is not on the board yet. 
	 *  	   False, if the number is already on the board.
	 */
	public boolean checkSubGrid(int num, int row, int col){
		int r = returnStartOfSubGrid(row);
		int c = returnStartOfSubGrid(col);
		
		for (int i = r; i < r+3; i++){
			for (int j = c; j < c+3; j++){
				if (board[i][j] == num){
					return false;
				}	
			}
		}
		return true;
	}
	/*
	 * Purpose: This function combines all the three boolean functions above to make sure that it is safe to place a
	 * number on the board
	 * 
	 * Argument(s): integer number, row, column
	 * 
	 * Return: boolean
	 */
	public boolean isSafeToPlace(int num, int row, int col){
		//check subgrid, if the number is safe to place
		if (checkSubGrid(num, row, col) == true){
			if (checkRow(num, row)== true){
				if (checkCol(num, col)== true){
					return true;
				}
			}
		}
		return false;
	}
	public boolean checkIfUsed(int num, boolean[] list){
		int index = num-1;
		if (list[index] == false){
			list[index] = true; //set now to true.
			return false;     //return false. it has not been used
		}else{
			return true; //else, return true, meaning that it has been used
		}		
	}
	
	public void resetList(boolean[] l){
		for (int i = 0; i < l.length; i++){
			l[i] = false;
		}
	}
	
//function will return a 2D array, which is then printed later
	public boolean fillBoard(int row, int col){
		int num = produceRandNum();
		boolean[] list = {false, false, false, false, false, false, false, false, false};
		
		//first, check if the random number that was generated had already been used
		boolean used = checkIfUsed(num, list);
		if (row > 8){
			return true;
		}
		if (col > 8){
			return true;
		}
		//check if num generated was 0
		
		if (num == 0){
			num = 1;
		}
		while (num != 9 && num != 0){
			if (row >= 0 && row <=9){
				if (isSafeToPlace(num, row, col) == true && !used){
					fillBoard(row + 1, col);
				}
			}
		}
		num = 1;
		resetList(list);

		board[row][col] = 0; //takes care of backtracking
		return false;
		//reset list
		
			
//			else{
//				//used is true, so we must use a different number. Thus, calling on fillboard function again to produce a random number
//				fillBoard(row + 1, col);
//				return true;
//			}
//			
//		}
		
		
		//then, check if the spot on the board is empty
//		if (board[row][col] != 0){
//			int temp_index = board[row][col]-1;
//			//change the value in list to be true
//			list[temp_index] = true; 
//			return fillBoard(row +1, col);
//		}
		
		//if empty, then we can place num in after checking subgrid, row, col
		
		
		//while the number has not been used and it is safe to place
		
		
		
		//first check if the spot in the board is filled, if not, check if the number has been used from the random number generator
		//if yes, then increment the number. Then, call on fillboard again but incrementing the row and col
		
		
//		
		       
		
	}
	
	public static void printBoard(int[][] board){
		for(int i = 0; i < 9; ++i){
			if (i % 3 ==0){
				System.out.println("-------------------------");
			}
			for(int j = 0; j < 9; ++j){
				if (j % 3 == 0){
					System.out.print("| ");
				}
				System.out.print(board[i][j] + " ");
			}
			System.out.println("|");
		}
		System.out.println("-------------------------");
	}	
	
	public static void main(String [] args){
		Sudoku sudoku = new Sudoku();
		int row = 0;
		int col = 0;
//		sudoku.printBoard(board);

		if (sudoku.fillBoard(row, col)==true){
			sudoku.printBoard(board);

		}	
	}
	
}
/*
 * Purpose: To check if it is safe to place a number on the grid
 * Idea: Our main goal is to check if the number will be "safe" in the sub-grid.
 *       From there, we can then check if it is "safe" to place by checking if 
 *       the number is already present on the entire row and col
 * Arguments: int row, int col
 * Return type: boolean
 */