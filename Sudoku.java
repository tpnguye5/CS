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
//		for (int i=0; i < board.length; i++){
//			if (board[i][col]== num){ 				//row
//				return false; //not safe to place
//			}
//		}
//		
//		for (int j = 0; j < board.length; j++){ 	//col
//			if (board[row][j]== num){
//				return false;
//			}
//		}
//		
//		int r = returnStartOfSubGrid(row);			//subgrid
//		int c = returnStartOfSubGrid(col);
//		
//		for (int k = r; k < r+3; k++){
//			for (int l = c; l < c+3; l++){
//				if (board[k][l] == num){
//					return false;
//				}	
//			}
//		}
		if (checkSubGrid(num, row, col) && checkRow(num, row) && checkCol(num, col)){
			return true;
		}
//			
		return false;
	}
	
//	public boolean checkIfNOTUsed(int num, boolean[] list){
//		int index = num;
//		while (index < list.length){
//			if (list[index] == false){
//				return true;     //return false. it has not been used
//			} 
//			index++;
//		}
//		return false;
//		
//	}
	
//	protected int getNextNum(int row, int col, boolean[] visited){
//		Random rand = new Random();
//		int num = rand.nextInt(9);   //picks a random number between 0 and 9
//		//check row, col, subgrid, check if has not placed
//		int i = 0;
//		
//		while (i < 9){
////			System.out.println(isSafeToPlace(num, row, col));
////			System.out.println(checkIfNOTUsed(num, visited));
////			System.out.println("--------------------------------");
//			
////			if (checkIfNOTUsed(num, visited)){
//			if (isSafeToPlace(num, row, col)){
//				//set visited to be true
//				visited[i] = true;
//				return num;
////				}
//				
//			}else{
//				if (num <= 9){
//					num = 1;
//				}else{
//					num++;
//				}
//				i ++;
//			}
//		}
//		return -1;
//	}
	
//------------------------------------------------------------------------------------------------------------------------
//function will return a 2D array, which is then printed later
	public boolean fillBoard(int row, int col){
//		System.out.println("row:" + row + " col :" + col);
//		printBoard(board);
		Random rand = new Random();
		int num = rand.nextInt(9);
		if (row > 8){
			return true;
		}
		
		System.out.println("************************");
		
		
		
		int[] tries = new int[9];
		int first = rand.nextInt(9);
		
		for (int i = 0; i < board.length-1; i++){
			tries[i] = (first + i) %9 + 1;
		}
		
		for (int j = 0; j < board.length-1; j++){
			int attempt = tries[j];
			
			if (isSafeToPlace(attempt, row, col)){
				board[row][col] = attempt;
				
				if (col < 8){
					if (fillBoard(row, col + 1)){
						return true;
					}
				}else{
					if (fillBoard(row + 1, 0)){
						return true;
					}
				}
//				if (col >= board.length-1){
//					col = 0;
//					row = row+ 1;
//				}
//				if (row >= board.length-1){
//					return true;
//				}
			}
		}
		
		
		
		
//		System.out.println("row:" + row + " col :" + col);
		
//		boolean[] visited = {false, false, false, false, false, false, false, false, false};
//
//		for (int i = 1; i <= board.length; i++){
//			int num = getNextNum(row, col, visited);
//			if (num != -1){
//				board[row][col]= num;
//
////				if (isSafeToPlace(num, row, col)){
////				board[row][col] = num;
////				}
//				if (fillBoard(row, col + 1)){
//	//				System.out.println(col);
//					return true;
//				}else{
//					board[row][col] = 0;
//				}
//				}
//			
//		}
		
//		int num;
//		
//		while ((num= getNextNum(row, col, visited)) != -1){
//			//always give next num
//			//place num
//			board[row][col]= num;
//			
//			if (fillBoard(row, col + 1)){
////				System.out.println(col);
//				return true;
//			}else{
//				board[row][col] = 0;
//			}
//		}
		
		board[row][col] = 0; //takes care of backtracking
		return false;
		
	}
//------------------------------------------------------------------------------------------------------------------------

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
//			System.out.println("ji");
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