package comp1110.ass2;

public class Board {
	/**
	 * subscript is same with format in readme.md
	 * [0~3][0~6/7]
	 * empty is marked as 'n'
	 */
	char[][] color;

	/**
	 * constructor
	 * create 26 color
	 * set color='n'
	 */
	Board(){

	}

	/**
	 *
	 * @param loc format in readme.md
	 * @return color
	 */
	public char getColor(String loc){
		return ' ';
	}

	/**
	 *
	 * @param loc format in readme.md
	 * @param color 7 colors or empty
	 */
	public void setColor(String loc,char color){

	}

	/**
	 * print the stars on board
	 */
	public void print(){

	}

	/**
	 *
	 * @param puzzle normal puzzle(not wizard)
	 */
	public void setPuzzle(String puzzle){}

	/**
	 * input format is not sure
	 */
	public void setWizard(){}

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
	public void solveWizard(){
		return "";
	}
}
