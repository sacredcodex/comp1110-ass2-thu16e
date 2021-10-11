package comp1110.ass2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
 *
 */
public class Board {

	private char[][] color;
	private String solution = "";
	private String puzzle = "";
	private HashSet<Character> unusedColor = new HashSet<>();
	private HashSet<Character> wizardColor = new HashSet<>();

	/**
	 * constructor， initialize
	 * create 26 color
	 * set color = 'n'
	 * @author Yu Cong
	 */
	public Board(){
		color = new char[4][];
		color[0] = new char[7];
		color[1] = new char[6];
		color[2] = new char[7];
		color[3] = new char[6];
		for (int i = 0; i < 4; i++) {
			Arrays.fill(color[i], 'n');
		}

		unusedColor.add('r');
		unusedColor.add('o');
		unusedColor.add('y');
		unusedColor.add('g');
		unusedColor.add('b');
		unusedColor.add('i');
		unusedColor.add('p');
	}

	/**
	 * get one color of loc
	 *
	 * @param loc format in readme.md
	 * @return char
	 * @author Yu Cong
	 */
	public char getColor(String loc){
		if (!Location.onBoard(loc))
			return ' ';
		char[] copy = loc.toCharArray();
		return this.color[copy[1]-'0'][copy[0]-'0'];
	}

	/**
	 * @author Yu Cong
	 */
	public char[][] getColors() {
		return color;
	}

	/**
	 * @author Yu Cong
	 */
	public String getSolution() {
		return solution;
	}

	/**
	 * @author Yu Cong
	 */
	public String getPuzzle(){
		return puzzle;
	}

	/**
	 * @author Yu Cong
	 */
	public Set<Character> getUnusedColor() {
		return unusedColor;
	}

	/**
	 * color[loc] := color
	 * @param loc format in readme.md
	 * @param color 7 colors(lower or upper case) or empty
	 * @author Yu Cong
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
	 * @author Xiao Cui
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
	 * set all color[][] == 'n'
	 * @author Xiao Cui
	 */
	public void setEmpty(){
		unusedColor.clear();
		unusedColor.add('r');
		unusedColor.add('o');
		unusedColor.add('y');
		unusedColor.add('g');
		unusedColor.add('b');
		unusedColor.add('i');
		unusedColor.add('p');

		for (int i = 0; i < 4; i++) {
			Arrays.fill(color[i], 'n');
		}
	}

	/**
	 * set color[][]
	 * lower case for piece, upper case for wizard
	 * if one location is mentioned in both piece statement and wizard statement,
	 * color[loc] save as lower case
	 *
	 * @param puzzle should be wel formed, contain both piece and wizard
	 * @author Xiao Cui
	 */
	public void setPuzzle(String puzzle){
		this.puzzle = puzzle;
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
			setColor(wizard.substring(1,3), (char)(wizard.charAt(0)-32));
			wizardColor.add(wizard.charAt(0));
		}

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < color[i].length; j++) {
				unusedColor.remove(color[i][j]);
			}
		}
	}

	/**
	 * @author Yu Cong
	 */
	public void setSolution(String solution) {
		this.solution = solution;
	}

	/**
	 *
	 * @param loc Location
	 * @param color one of 7 lower case chars
	 * @return true if loc is on board and
	 *                      1. the color at this loc is empty
	 *                   or 2. wizard state with the same color
	 * @author Yu Cong
	 */
	public boolean isLocationValid(Location loc, char color){
		if (!unusedColor.contains(color))
			return false;
		if (!loc.onBoard())
			return false;
		return (getColor(loc.toString()) == 'n' || getColor(loc.toString()) == color - 32 );
	}

	/**
	 * All star locations should be on board and empty or wizard(same color)
	 *
	 * @param loc the center star location to place the piece
	 * @return whether loc is on board and empty to place the Piece
	 * @author Yu Cong
	 */
	public boolean isPieceValid(Piece piece, Location loc){
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

	/**
	 * @author Yu Cong
	 */
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
	 * @author Yu Cong
	 */
	public void placePiece(Piece piece, Location loc){
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

		unusedColor.remove(piece.getColor());
		wizardColor.remove(piece.getColor());
	}

	/**
	 * @author Yu Cong
	 */
	public void placePiece(String str){
		Piece piece = new Piece(str.charAt(0));
		piece.rotatePiece(str.charAt(1)-'0');
		this.placePiece(piece, piece.getCenter(str));
	}

	/**
	 * after placePiece, check if the piece cover all the wizard star in same color
	 * @param c color
	 * @return true, if number of color star is correct
	 * @author Yu Cong
	 */
	public boolean isCoveredWizard(char c){
		int count = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < this.color[i].length;j++) {
				if (this.color[i][j] == c || this.color[i][j] == c - 32)
					count++;
			}
		}
		if (count == 3 && (c == 'o' || c == 'i'))
			return true;
		return count == 4 && (c == 'r' || c == 'y' || c == 'g' || c == 'b' || c == 'p');
	}

	/**
	 * remove all stars in color(lower case)
	 * star in upper case is wizard star, which should not be removed
	 * @param color r, o, y, g, b, i, p
	 * @author Xiao Cui
	 */
	public void removePiece(char color){
		unusedColor.add(color);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < this.color[i].length; j++) {
				if (this.color[i][j] == color)
					this.color[i][j] = 'n';
			}
		}
	}

	/**
	 * @author Xiao Cui
	 */
	public void removePiece(String str){
		this.removePiece(str.charAt(0));
	}

	/**
	 *  Solve puzzle without wizard star
	 * @author Xiao Cui
	 */
	public void solvePuzzle(){
		if (this.solution.equals("")) {

			if (unusedColor.isEmpty())
				solution = this.toString();

			String pieceStr;
			char c = unusedColor.toString().charAt(1);

			for (int rotation = 0; rotation < 6; rotation++) {
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < color[i].length; j++) {
							if (color[i][j] == 'n') {
								pieceStr = "" + c + rotation + j + i;
								if (isPieceValid(pieceStr)) {
									placePiece(pieceStr);

									if (isCoveredWizard(c)) {
										solvePuzzle();
									}
									removePiece(pieceStr);
								}
							}

					}
				}
			}
		}
	}

	/**
	 * solve puzzles with wizard stars
	 * @author Xiao Cui
	 */
	public void solveWizard(){
		if (wizardColor.isEmpty()){
			solvePuzzle();
		}else{
			String pieceStr;
			char c = wizardColor.toString().charAt(1);

			for (int rotation = 0; rotation < 6; rotation++) {
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < color[i].length; j++) {
						if (color[i][j] == c - 32 || color[i][j] == 'n') {
							pieceStr = "" + c + rotation + j + i;
							if (isPieceValid(pieceStr)) {
								placePiece(pieceStr);

								if (isCoveredWizard(c)) {
									solveWizard();
								}
								removePiece(pieceStr);
								wizardColor.add(c);
							}
						}

					}
				}
			}
		}
	}

	/**
	 * a gameStateString with 'W' as the end
	 * the board should not have empty star(s)
	 * @return a String format in readme
	 * @author Xiao Cui
	 */
	public String toString(){
		char[] c = {'r', 'o', 'y', 'g', 'b', 'i', 'p'};
		boolean[] exist = {false,false,false,false,false,false,false};
		String[] loc= new String[7];

		// get topLeft
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < color[i].length; j++) {
				switch (color[i][j]){
					case 'R', 'r' -> {
						if (!exist[0]) {
							exist[0] = true;
							loc[0] = "" + j + i;
						}
					}
					case 'O', 'o' -> {
						if (!exist[1]) {
							exist[1] = true;
							loc[1] = "" + j + i;
						}
					}
					case 'Y', 'y' -> {
						if (!exist[2]) {
							exist[2] = true;
							loc[2] = "" + j + i;
						}
					}
					case 'G', 'g' -> {
						if (!exist[3]) {
							exist[3] = true;
							loc[3] = "" + j + i;
						}
					}
					case 'B', 'b' -> {
						if (!exist[4]) {
							exist[4] = true;
							loc[4] = "" + j + i;
						}
					}
					case 'I', 'i' -> {
						if (!exist[5]) {
							exist[5] = true;
							loc[5] = "" + j + i;
						}
					}
					case 'P', 'p' -> {
						if (!exist[6]) {
							exist[6] = true;
							loc[6] = "" + j + i;
						}
					}
				}
			}
		}

		int[] rotation = new int[7];
		Location topLeft;
		// get  rotation
		for (int i = 0; i < 7; i++) {
			if (exist[i]){
				topLeft = new Location(loc[i]);
				switch (i){
					// red
					case 0:
						if (getColor(topLeft.getNext(0).toString()) == 'r' || getColor(topLeft.getNext(0).toString()) == 'R'){
							if (getColor(topLeft.getNext(2).toString()) == 'r' || getColor(topLeft.getNext(2).toString()) == 'R')
								rotation[i] = 2;
							else rotation[i] = 0;
						}else
							rotation[i] = 1;
						break;
					//orange
					case 1:
						if (getColor(topLeft.getNext(0).toString()) == 'o' || getColor(topLeft.getNext(0).toString()) == 'O'){
							if (getColor(topLeft.getNext(2).toString()) == 'o' || getColor(topLeft.getNext(2).toString()) == 'O')
								rotation[i] = 5;
							else rotation[i] =0;
						}else if (getColor(topLeft.getNext(1).toString()) == 'o' || getColor(topLeft.getNext(1).toString()) == 'O') {
							if (getColor(topLeft.getNext(1).getNext(0).toString()) == 'o' || getColor(topLeft.getNext(1).getNext(0).toString()) == 'O')
								rotation[i] = 3;
							else rotation[i] = 1;
						}else if (getColor(topLeft.getNext(2).toString()) == 'o' || getColor(topLeft.getNext(2).toString()) == 'O') {
							if (getColor(topLeft.getNext(2).getNext(1).toString()) == 'o' || getColor(topLeft.getNext(2).getNext(1).toString()) == 'O')
								rotation[i] = 4;
							else rotation[i] = 2;
						}
						break;
					// yellow
					case 2:
						if (getColor(topLeft.getNext(0).toString()) == 'y' || getColor(topLeft.getNext(0).toString()) == 'Y'){
							if (getColor(topLeft.getNext(0).getNext(0).toString()) == 'y' || getColor(topLeft.getNext(0).getNext(0).toString()) == 'Y')
								rotation[i] = 0;
							else rotation[i] = 2;
						}else if (getColor(topLeft.getNext(1).toString()) == 'y' || getColor(topLeft.getNext(1).toString()) == 'Y'){
							if (getColor(topLeft.getNext(2).toString()) == 'y' || getColor(topLeft.getNext(2).toString()) == 'Y') {
								if (getColor(topLeft.getNext(1).getNext(1).toString()) == 'y' || getColor(topLeft.getNext(1).getNext(1).toString()) == 'Y')
									rotation[i] = 1;
								else rotation[i] = 3;
							}else rotation[i] = 4;
						}else
							rotation[i] = 5;
						break;
					//greean
					case 3:
						if (getColor(topLeft.getNext(0).toString()) == 'g' || getColor(topLeft.getNext(0).toString()) == 'G'){
							if (getColor(topLeft.getNext(2).toString()) == 'g' || getColor(topLeft.getNext(2).toString()) == 'G')
								rotation[i] = 5;
							else rotation[i] =0;
						}else if (getColor(topLeft.getNext(1).toString()) == 'g' || getColor(topLeft.getNext(1).toString()) == 'G') {
							if (getColor(topLeft.getNext(1).getNext(1).toString()) == 'g' || getColor(topLeft.getNext(1).getNext(1).toString()) == 'G')
								rotation[i] = 3;
							else rotation[i] = 1;
						}else if (getColor(topLeft.getNext(2).toString()) == 'g' || getColor(topLeft.getNext(2).toString()) == 'G') {
							if (getColor(topLeft.getNext(2).getNext(2).toString()) == 'g' || getColor(topLeft.getNext(2).getNext(2).toString()) == 'G')
								rotation[i] = 4;
							else rotation[i] = 2;
						}
						break;
					// blue
					case 4:
						if (getColor(topLeft.getNext(0).toString()) == 'b' || getColor(topLeft.getNext(0).toString()) == 'B'){
							if (getColor(topLeft.getNext(1).toString()) == 'b' || getColor(topLeft.getNext(1).toString()) == 'B')
								rotation[i] = 4;
							else rotation[i] = 0;
						}else if (getColor(topLeft.getNext(1).toString()) == 'b' || getColor(topLeft.getNext(1).toString()) == 'B'){
							if (getColor(topLeft.getNext(2).toString()) == 'b' || getColor(topLeft.getNext(2).toString()) == 'B'){
								if (getColor(topLeft.getNext(1).getNext(0).toString()) == 'b' || getColor(topLeft.getNext(1).getNext(0).toString()) == 'B'){
									rotation[i] = 3;
								}else rotation[i] = 5;
							}else rotation[i] = 1;
						}else rotation[i] = 2;
						break;
					//purple
					case 5:
						for (int j = 0; j < 3; j++) {
							if (getColor(topLeft.getNext(j).toString()) == 'i' || getColor(topLeft.getNext(j).toString()) == 'I'){
								rotation[i] = j;
								break;
							}
						}
						break;
					//pink
					case 6:
						if (getColor(topLeft.getNext(1).toString()) == 'p' || getColor(topLeft.getNext(1).toString()) == 'P'){
							if (getColor(topLeft.getNext(1).getNext(0).toString()) == 'p' || getColor(topLeft.getNext(1).getNext(0).toString()) == 'P')
								rotation[i] = 2;
							else rotation[i] = 1;
						}else if (getColor(topLeft.getNext(0).toString()) == 'p' || getColor(topLeft.getNext(0).toString()) == 'P'){
							if (getColor(topLeft.getNext(2).toString()) == 'p' || getColor(topLeft.getNext(2).toString()) == 'P') {
								if (getColor(topLeft.getNext(0).getNext(1).toString()) == 'p' || getColor(topLeft.getNext(0).getNext(1).toString()) == 'P')
									rotation[i] = 5;
								else rotation[i] = 4;
							}else rotation[i] = 0;
						}else rotation[i] = 3;
						break;
				}
			}
		}

		StringBuilder res = new StringBuilder();
		for (int i = 0; i < 7; i++) {
			if (exist[i]){
				res.append(c[i]).append(rotation[i]).append(loc[i]);
			}
		}
		return res+"W";
	}
}
