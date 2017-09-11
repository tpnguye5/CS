package sudoku;

import java.util.Random;
/*Purpose: To generate a correct Sudoku board every time using recursion and back-tracking
 * 
 */
public class Sudoku {
	//declaring the board
	protected int[][] board;
	
	//constructor: creates a board of default size 9 by 9
	public Sudoku(){
		board = new int[9][9]; //9 rows and 9 cols
	}

	protected int returnStartOfSubGrid(int coordinate){ //cooridnate could be row or col
		int result = (coordinate/3) * 3;
		return result;
		
		//call on each to get beginning of for loop
	}
	
	protected int produceRandNum(){
		Random rand = new Random();
		int num = rand.nextInt(9);   //picks a random number between 0 and 9
		return num;
	}
	
	/*
	 * Purpose: To check if it is safe to place a number on the grid
	 * Idea: Our main goal is to check if the number will be "safe" in the sub-grid.
	 *       From there, we can then check if it is "safe" to place by checking if 
	 *       the number is already present on the entire row and col
	 * Arguments: int row, int col
	 * Return type: boolean
	 */
//	protected boolean safeToPlace(int num, int row, int col){
//		
//		int r = returnStartOfSubGrid(row);
//		int c = returnStartOfSubGrid(col);
//		
		//check the subgrid
//		for (int i = r; i < r + 3; i++){
//			if (board[r][col] == num){
//				return false;
//			}
//		for (int j = c; j < c + 3; j++){
//			if (board[row][j] == num){
//				return false;
//			}
		
//		for (int i = r; i < r+3; i++){
//			for (int j = c; j < c+3; j++){
//				if (board[i][j] == num){
//					return false;
//				}	
//
//			}
//		}
//			
		//check row and col
		
		
		
		
//		for (int boardR = row; boardR < board.length; boardR++){
//			if (board[boardR][col] == num){
//				return false;
//			}
//			for (int boardC = c; boardC < board[boardR].length; boardC++){
//				if (board[row][boardC] == num){
//					return false;
//				}
//			}
//		 }
		
		//after checking the sub grid and the row and col, it is okay to place num.
//		return true;
//		
//	}
	public boolean checkRow(int num, int row){
		for (int i=0; i <= board.length; i++){
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
			return true;
		}
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
//function will return a 2D array, which is then printed later
	public boolean fillBoard(int row, int col){
		//get random number
		int num = produceRandNum();
		
		int[] list = {1,2,3,4,5,6,7,8,9};
		
		while (!isSafeToPlace(num, row, col)){
			//call the function and increment num
			if (num <=9){
				num += 1;
			}else{
				
				num = 1;
				return false;
			}
			
		}
		
		return true;
	}
	
	public boolean checkSubGrid(int num){
		
		return false; //no, it is not okay, to place in subgrid
	}
	
	public void printBoard(){
		
	}	
	public void main(){
		Sudoku sudoku = new Sudoku();
		
		sudoku.fillboard();
		sudoku.printBoard();
	}
	
}
