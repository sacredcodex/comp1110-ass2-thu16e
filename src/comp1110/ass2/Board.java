package comp1110.ass2;

public class Board {
	/**
	 * subscript is same with format in readme.md
	 * [0~3][0~6/5]
	 * empty is marked as 'n'
	 *
	 * Board:
	 *    [0][0]---[0][1]---[0][2]---[0][3]---[0][4]---[0][5]---[0][6]
	 *         \    /   \    /   \    /   \    /   \    /   \    /
	 *        [1][0]---[1][1]---[1][2]---[1][3]---[1][4]---[1][5]
	 *         /   \    /   \    /   \    /   \    /   \    /   \
	 *    [2][0]---[2][1]---[2][2]---[2][3]---[2][4]---[2][5]---[2][6]
	 *         \    /   \    /   \    /   \    /   \    /   \    /
	 *        [3][0]---[3][1]---[3][2]---[3][3]---[3][4]---[3][5]
	 *
	 *
	 *
	 */


	private char[][] color;

	/**
	 * constructor
	 * create 26 color
	 * set color='n'
	 */
	Board(){
		color = new char[4][];
		color[0] = new char[7];
		color[1] = new char[6];
		color[2] = new char[7];
		color[3] = new char[6];
		for (int i = 0; i < color.length; i++) {
			for (int j = 0; j < color[i].length; j++) {
				color[i][j] = 'n';
			}
		}
	}

	/**
	 *
	 * @param loc format in readme.md
	 * @return color
	 */
	public char getColor(String loc){
		char[] copy = loc.toCharArray();
		return this.color[copy[1]-'0'][copy[0]-'0'];
	}

	/**
	 *
	 * @param loc format in readme.md
	 * @param color 7 colors or empty
	 */
	public void setColor(String loc,char color){
		char[] copy = loc.toCharArray();
		this.color[copy[1]-'0'][copy[0]-'0'] = color;
	}

	/**
	 * print the stars on board
	 *
	 *   n --- n --- n --- n --- n --- n --- n
	 *    \  /  \  /  \  /  \  /  \  /  \  /
	 *     n --- n --- n --- n --- n --- n
	 *   /  \  /  \  /  \  /  \  /  \  /  \
	 * n --- n --- n --- n --- n --- n --- n
	 *  \  /  \  /  \  /  \  /  \  /  \  /
	 *   n --- n --- n --- n --- n --- n
	 *
	 */
	public void print(){
		System.out.println("  "+color[0][0]+" --- "+color[0][1]+" --- "+color[0][2]+" --- "+color[0][3]+" --- "+color[0][4]+" --- "+color[0][5]+" --- "+color[0][6]);
		System.out.println("   \\  /  \\  /  \\  /  \\  /  \\  /  \\  /");
		System.out.println("    "+color[1][0]+" --- "+color[1][1]+" --- "+color[1][2]+" --- "+color[1][3]+" --- "+color[1][4]+" --- "+color[1][5]);
		System.out.println("  /  \\  /  \\  /  \\  /  \\  /  \\  /  \\");
		System.out.println(color[2][0]+" --- "+color[2][1]+" --- "+color[2][2]+" --- "+color[2][3]+" --- "+color[2][4]+" --- "+color[2][5]+" --- "+color[2][6]);
		System.out.println(" \\  /  \\  /  \\  /  \\  /  \\  /  \\  /");
		System.out.println("  "+color[3][0]+" --- "+color[3][1]+" --- "+color[3][2]+" --- "+color[3][3]+" --- "+color[3][4]+" --- "+color[3][5]);
	}

	/**
	 * set color[][]
	 * @param puzzle both not wizard and wizard
	 */
	public void setPuzzle(String puzzle){
		String piece;
		if (puzzle.charAt(0)=='W'){
			for (int i = 0; 3*i+3 < puzzle.length(); i++) {
				this.setColor(puzzle.substring(3*i+2,3*i+4),puzzle.charAt(3*i+1));
			}
		}else{
			for (int i = 0; 4*i+4 < puzzle.length(); i++) {
				piece=puzzle.substring(4*i,4*i+4);
				placePiece(this,piece);
			}
		}
	}


	/**
	 * This method is only used for normal puzzle(without wizard)
	 *
	 * All star locations should be on board and empty
	 *
	 * @param loc the center star location to place the piece
	 * @return whether loc on board is valid to place the Piece
	 */
	public boolean isPieceValid(Board board ,Location loc){
		return true;//TODO: complete after Board
	}

	/**
	 * This method should be used for wizard puzzle
	 *
	 *
	 * @param loc the center star location to place the piece
	 * @return whether loc on board is valid to place the Piece
	 */
	public boolean isPieceValidForWizard(Board board ,Location loc){
		if (!isPieceValid(board, loc))
			return false;
		return true;//TODO: complete after Board
	}

	/**
	 *
	 * @param loc the center star location to place the piece
	 */
	public void placePiece(Board board,Location loc){
		//TODO: complete after Board
	}

	/**
	 *
	 * @param str format in readme.md
	 */
	public static void placePiece(Board board, String str){
		//TODO:
	}

	/**
	 * notice: it will also remove the wizard puzzle star
	 * @param board
	 * @param loc the start location to place the piece
	 */
	public void removePiece(Board board,Location loc){
		//TODO: complete after Board
	}

	/**
	 *
	 * @return solution of normal puzzle(not wizard)
	 */
	public String solvePuzzle(){
		return "";
	}
	/**
	 *
	 * @return solution of wizard puzzle
	 */
	public String solveWizard(){
		return "";
	}
}
