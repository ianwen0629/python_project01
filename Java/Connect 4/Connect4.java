public class Connect4
{
	// These variables below are just useful to 
	// remember how many rows and columns you have
	private final static int ROWS = 6;
	private final static int COLS = 7;

	// Create private instance variables here
	private char[][] board;

	// The constructor should create a grid of size ROWS X COLS
	public Connect4(){
		board = new char[ROWS][COLS];
		for (int i = 0; i < ROWS; i++){
			for (int j = 0; j < COLS; j++){
				board[i][j] = '.';
			}
		}
	}

	/* This should create a board that shows the contents of the board
An empty cell is represented by .
The two players are represented by x and o

So the empty board should look like this

 0 1 2 3 4 5 6
|.|.|.|.|.|.|.|
+-+-+-+-+-+-+-+
|.|.|.|.|.|.|.|
+-+-+-+-+-+-+-+
|.|.|.|.|.|.|.|
+-+-+-+-+-+-+-+
|.|.|.|.|.|.|.|
+-+-+-+-+-+-+-+
|.|.|.|.|.|.|.|
+-+-+-+-+-+-+-+
|.|.|.|.|.|.|.|
+-+-+-+-+-+-+-+

*/
	public String toString(){
		String output = " 0 1 2 3 4 5 6\n";
		for (int i = 0; i < ROWS; i++){
			for (int j = 0; j < COLS; j++){
				output += "|" + board[i][j];
			}
			output += "|\n+-+-+-+-+-+-+-+\n";
		}
		return output;
	}

	/*
	This method adds a piece

	@param piece Either an 'x' or an 'o' for the player
	@param col The column to add a piece
	@return true if the piece is successfully added
					false if the column is out of range or
					false if the column selected is already full
	*/
	public boolean addPiece(char piece, int col){
		if (col >= COLS || board[0][col] != '.'){
			System.out.println("Please enter a valid column");
			return false;
		}
		for (int i = 0; i < ROWS; i++){
			if (board[i][col] != '.'){
				board[i - 1][col] = piece;
				return true;
			}
		}
		board[ROWS - 1][col] = piece;
		return true;
	}

	/*
	This method determines who the winner is
	@return '.' if there is no winner or 'x' or 'o' if they have won
	*/
	public char winner(){
		for (int i = ROWS - 1; i >= 0; i--){
			for (int j = 0; j < COLS - 3; j++){
				if (board[i][j] != '.' && board[i][j] == board[i][j + 1] && board[i][j] == board[i][j + 2] && board[i][j] == board[i][j + 3]) return board[i][j];
			}
		}
		for (int i = 0; i < COLS; i++){
			for (int j = ROWS - 1; j > 2; j--){
				if (board[j][i] != '.' && board[j][i] == board[j - 1][i] && board[j][i] == board[j - 2][i] && board[j][i] == board[j - 3][i]) return board[j][i];
			}
		}
		for (int i = ROWS - 1; i > 2; i--){
			for (int j = 0; j < COLS - 3; j++){
				if (board[i][j] != '.' && board[i][j] == board[i - 1][j + 1] && board[i][j] == board[i - 2][j + 2] && board[i][j] == board[i - 3][j + 3]) return board[i][j];
			}
		}
		for (int i = ROWS - 1; i > 2; i--){
			for (int j = COLS - 1; j > 2; j--){
				if (board[i][j] != '.' && board[i][j] == board[i - 1][j - 1] && board[i][j] == board[i - 2][j - 2] && board[i][j] == board[i - 3][j - 3]) return board[i][j];
			}
		}
		return '.';
	}
}