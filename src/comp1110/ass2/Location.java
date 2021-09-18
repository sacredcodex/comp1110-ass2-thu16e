package comp1110.ass2;

public class Location {
	/**
	 * This coordinate system has been abandoned!!!
	 * reference：https://www.redblobgames.com/grids/hexagons/#basics
	 *
	 * Cube coordinates: (x,y,z)
	 * When Location moves one space, two values in coordinates will change each time,
	 * one + 1, the other - 1, so:
	 *                  x + y + z == 0
	 *
	 * Definition of 6 direction unit vectors:
	 *                 4          5
	 *             (0,1,-1)   (1,0,-1)
	 *                  \      /
	 *     3 (-1,1,0)---(0,0,0)----(1,-1,0) 0
	 *                   /    \
	 *            (-1,0,1)   (0,-1,1)
	 *               2          1
	 *
	 * Let the top left of the board is (0, 0, 0),
	 *
	 * Distance: loc1(x1, y1, z1) and loc2(x2, y2, z2)
	 *           d := (|x1 - x2| + |y1 - y2 | + |z1 - z2|) / 2
	 *
	 * The reason why we choose cube coordinate:
	 *          simple distance formula in cube coordinate
	 *
	 * Sep 18th:
	 *      change the structure to a normal coordinate with column and row, just like: assets\blankBoardNumbered.png
	 *
	 *      1. Reason: the advantage of this cube coordinate is distance calculate, but we do not use it in this assignment
	 *      2. remove all methods not used
	 *      3. write all the methods again
	 *      4. Result: task 10 runtime reduce 6~11%
	 *
	 *
	 */

	private int column, row;

	public Location(){
		this.column = -1;
		this.row = -1;
	}

	/**
	 * constructor
	 * transform format in readme.md --> cube coordinate (x,y,z)
	 * @param column and
	 * @param row are 2 parameters in readme.md
	 */
	 public Location(int column,int row){
	 	this.row = row;
	 	this.column = column;
	 }

	/**
	 * constructor
	 * format in readme.md
	 * @param str format in readme.md with 2 characters
	 *            e.g. 03, 32, 52
	 */
	public Location(String str){
		char[] c=str.toCharArray();
		this.row = c[1] - '0';
		this.column = c[0] - '0';
	}

	/**
	 *
	 * @param direction:0~5 direction of unit vector
	 * @return Location in certain direction which is adjacent to this
	 */
	public Location getNext(int direction){
		int column = this.column;
		int row =this.row;
		switch (direction) {
			case 0 -> {
				column++;
			}
			case 1 -> {
				row++;
				if (row % 2 == 0)
					column++;
			}
			case 2 -> {
				row++;
				if (row % 2 == 1)
					column--;
			}
			case 3 -> {
				column--;
			}
			case 4 -> {
				row--;
				if (row % 2 == 1)
					column--;
			}
			case 5 -> {
				row--;
				if (row % 2 == 0)
					column++;
			}
		}
		return new Location(column, row);

	}

	/**
	 * @return format in readme.md "xy"
	 */
	@Override
	public String toString(){
		return ""+this.column+this.row;
	}

	/**
	 * @return whether the Location is on Board(one of 26 Locations)
	 */
	public boolean onBoard(){
		return ((row == 0 || row == 2) && column >= 0 && column <= 6) || ((row == 1 || row == 3) && column >= 0 && column <= 5);
	}

	public static boolean onBoard(String loc){
		char[] chars = loc.toCharArray();
		if (chars[0] >= '0' && chars[0] <= '5') {
			return chars[1] >= '0' && chars[1] <= '3';
		}else if (chars[0] == '6' )
			return chars[1] == '0' || chars[1] == '2';
		else return false;
	}

	//used in gui/Board.java
	public double getSceneY(){
		return 24 + (row + 0.5) * 69.282;
	}

	public double getSceneX(){
		if (row ==0 || row == 2)
			return 24 + (column + 0.5) * 80;
		else return 24 + (column + 1) * 80;
	}
}
