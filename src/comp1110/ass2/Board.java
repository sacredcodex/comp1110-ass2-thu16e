package comp1110.ass2;

public class Board {
	/**
	 * subscript is same with format in readme.md
	 *      [0~3][0~6/5]
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
	 * in the char array color:
	 * r, o, y, g, b, i, p represent for 7 pieces
	 * n represents for empty
	 *
	 * Use upper case for wizard state
	 * R, O, Y, G, B, I, P
	 * NOTE: 'a' - 'A' = 32
	 */

	private char[][] color;

	/**
	 * constructor， initialize
	 * create 26 color
	 * set color = 'n'
	 */
	public Board(){
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
	 * get one color of loc
	 *
	 * @param loc format in readme.md
	 * @return char
	 */
	public char getColor(String loc){
		char[] copy = loc.toCharArray();
		return this.color[copy[1]-'0'][copy[0]-'0'];
	}

	/**
	 * get all colors on the board
	 *
	 * @return char[][]
	 */
	public char[][] getColors() {
		return color;
	}

	/**
	 * color[loc] := color
	 * @param loc format in readme.md
	 * @param color 7 colors(lower or upper case) or empty
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
	 * lower case for piece, upper case for wizard
	 * if one location is mentioned in both piece statement and wizard statement,
	 * color[loc] save as lower case
	 *
	 * @param puzzle should be wel formed, contain both piece and wizard
	 */
	public void setPuzzle(String puzzle){
		int indexOfW = puzzle.indexOf('W');
		String partOne = puzzle.substring(0,indexOfW);
		String partTwo = puzzle.substring(indexOfW+1);
		String piece;
		for (int i = 0; i < partOne.length() / 4; i++) {
			piece = partOne.substring(4 * i, 4 * i + 4);
			placePiece(piece);
		}
		String wizard;
		for (int i = 0; i < partTwo.length() / 3; i++){
			wizard = partTwo.substring(3 * i, 3 * i + 3);
			if (getColor(wizard.substring(1,3) )== 'n')
				setColor(wizard.substring(1,3), (char)(wizard.charAt(0)-32));
		}
	}

	/**
	 *
	 * @param loc Location
	 * @param color one of 7 lower case chars
	 * @return true if loc is on board and
	 *                      1. the color at this loc is empty
	 *                   or 2. wizard state with the same color
	 */
	public boolean isLocationValid(Location loc, char color){
		if (!loc.onBoard())
			return false;
		return (getColor(loc.toString()) == 'n' || getColor(loc.toString()) == color - 32 );
	}

	/**
	 * All star locations should be on board and empty or wizard
	 *
	 * @param loc the center star location to place the piece
	 * @return whether loc is on board and empty to place the Piece
	 */
	public boolean isPieceValid(Piece piece ,Location loc){
		if (!(piece.getColor() == 'p') && !isLocationValid(loc, piece.getColor()))
			return false;
		for (int i : piece.getShape()){
			if (!isLocationValid(loc.getNext(i), piece.getColor()))
				return false;
		}
		if (piece.getColor() == 'g')
			return isLocationValid(loc.getNext(piece.getShape()[0]).getNext(piece.getShape()[0]), piece.getColor());
		return true;
	}

	public boolean isPieceValid(String str){
		Piece piece = new Piece(str.charAt(0));
		piece.rotatePiece(str.charAt(1)-'0');
		return this.isPieceValid(piece, piece.getCenter(str));
	}

	/**
	 * put the piece onto the board
	 * change these location color into the piece color
	 * for wizard location, color[loc] keep upper case
	 *
	 * @param loc the center star location to place the piece
	 */
	public void placePiece(Piece piece,Location loc){
		if (!(piece.getColor() == 'p'))
			if (getColor(loc.toString()) == 'n')
				setColor(loc.toString(), piece.getColor());
		for (int i : piece.getShape()){
			if (getColor(loc.getNext(i).toString()) == 'n')
				setColor(loc.getNext(i).toString(), piece.getColor());
		}
		if (piece.getColor() == 'g')
			if (getColor(loc.getNext(piece.getShape()[0]).getNext(piece.getShape()[0]).toString()) == 'n')
				setColor(loc.getNext(piece.getShape()[0]).getNext(piece.getShape()[0]).toString(), piece.getColor());
	}

	/**
	 *
	 * @param str format in readme.md
	 */
	public void placePiece(String str){
		Piece piece = new Piece(str.charAt(0));
		piece.rotatePiece(str.charAt(1)-'0');
		this.placePiece(piece, piece.getCenter(str));
	}

	/**
	 * notice: it will keep the wizard star
	 *
	 * @param loc the center star location to place the piece
	 */
	public void removePiece(Piece piece, Location loc){
		if (getColor(loc.toString()) > 96)//if lower case
			setColor(loc.toString(), 'n');
		for (int i : piece.getShape()){
			if (getColor(loc.getNext(i).toString()) > 96)//if lower case
				setColor(loc.getNext(i).toString(), 'n');
		}
		if (piece.getColor() == 'g')
			if (getColor(loc.getNext(piece.getShape()[0]).getNext(piece.getShape()[0]).toString()) > 96)//if lower case
				setColor(loc.getNext(piece.getShape()[0]).getNext(piece.getShape()[0]).toString(), 'n');
	}

	/**
	 *
	 * @return solution of normal puzzle(not wizard)
	 */
	public String solvePuzzle(){
		return "";//FIXME：TASK10
	}
	/**
	 *
	 * @return solution of wizard puzzle
	 */
	public String solveWizard(){
		return "";//FIXME: TASK13
	}
}
