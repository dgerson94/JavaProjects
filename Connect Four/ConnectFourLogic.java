import javax.swing.JOptionPane;


public class ConnectFourLogic {

	private static final int HOLD = 10;  //value of row that isn't relevant till we have correct value.
	private static final int MAX_TURN = 41; //after 42 turns there needs to be a winner or tie
	private static final int ROWS = 6; // amount of rows on board
	private static final int COLUMNS = 7; //amount of columns on board
	private int turnCnt; // count of turns for logic
	private int c; //number of column for this turn
	private int r; //number of row for this turn
	private char [][] board; //the matrix for the game board

	//constructor. column and row are "fake" values until vaild turn happens.
	public ConnectFourLogic(int turnCnt) {
		this.turnCnt = turnCnt;
		this.c = HOLD;
		this.board = new char [ROWS][COLUMNS];
		this.r = HOLD;
		for (int row = 0; row < board.length; row++){
			for (int col = 0; col < board[0].length; col++){
				board[row][col] = ' ';
			}
		}
	}

	//check if player can place disk in this column, returns true if move is valid false if player can't place disk there.
	public boolean checkTurn(int n) {
		char player;
		c = n;
		//if the top row of this column is full send error and player must hit different button.
		if (board[0][c] != ' '){
			JOptionPane.showMessageDialog(null, "This Column is full please place your peice in a different column.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		//the top row of this column isn't full and we can place a disk in the column
		else {
			//make player 'R' or 'B'
			player = choosePlayer(turnCnt);
			//this is a valid move, place the piece on the board.
			for (int row = board.length-1; row >= 0; row--){
				if(board[row][c] == ' '){
					board [row][c] = player;
					r = row;
					break;
				}
			}
			return true;
		}
	}
	
	//check if move led to someone winning or tie
		public void playTurn() {
			char player;
			boolean winner = false;
			//make player 'R' or 'B' depending on turn.
			player = choosePlayer(turnCnt);
			winner = checkForWinner(player);

			//if winner = true than player of this turn is winner
			if (winner == true) {
				if (player == 'R')
					JOptionPane.showMessageDialog(null, "The first player has won the Game! Please press clear to start new game.");
				else
					JOptionPane.showMessageDialog(null, "The Second player has won the Game! Please press clear to start new game.");
			}
			else {
				//if 42  turns have been played and there is still no winner than the game is a tie.
				if (turnCnt == MAX_TURN)
					JOptionPane.showMessageDialog(null, "This game has ended in a tie. Please press clear to start new game.");
			}
			//this turn is over, add one to turn counter.
			turnCnt++;

		}

		//check if after this turn there is a four in a row, if so - declare that player of this turn is winner.
		private boolean checkForWinner (char player) {
			//check for four horizontal
			for(int row = 0; row < board.length; row++){
				for (int col = 0;col < board[0].length - 3;col++){
					if (board[row][col] == player   && 
							board[row][col+1] == player &&
							board[row][col+2] == player &&
							board[row][col+3] == player){
						return true;
					}
				}			
			}

			//check for four vertical
			for(int row = 0; row < board.length - 3; row++){
				for(int col = 0; col < board[0].length; col++){
					if (board[row][col] == player   && 
							board[row+1][col] == player &&
							board[row+2][col] == player &&
							board[row+3][col] == player){
						return true;
					}
				}
			}

			//check for upward diagonal
			for(int row = 3; row < board.length; row++){
				for(int col = 0; col < board[0].length - 3; col++){
					if (board[row][col] == player   && 
							board[row-1][col+1] == player &&
							board[row-2][col+2] == player &&
							board[row-3][col+3] == player){
						return true;
					}
				}
			}

			//check for downward diagonal
			for(int row = 0; row < board.length - 3; row++){
				for(int col = 0; col < board[0].length - 3; col++){
					if (board[row][col] == player   && 
							board[row+1][col+1] == player &&
							board[row+2][col+2] == player &&
							board[row+3][col+3] == player){
						return true;
					}
				}
			}
			//if no 4 in a row were found return false as there is no winner.
			return false;
		}
		//function that decides who player is, if it is an even turn than 'R' (the first player) is taking the turn, if odd than 'B' is taking the turn
		private char choosePlayer(int turnCnt) {
			if (turnCnt % 2 == 0)
				return 'R';
			else
				return 'B';
		}
		//get row of current turn
		public int getRow() {
			return r;
		}
		//get turn of game right now
		public int getTurn() {
			return turnCnt;
		}

	}
