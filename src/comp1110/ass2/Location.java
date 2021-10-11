package comp1110.ass2;

/**
 * reference：https://www.redblobgames.com/grids/hexagons/#basics
 * Cube coordinates: (x,y,z)
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
 * Sep 18th:
 *      change the structure to a normal coordinate with column and row, just like: assets\blankBoardNumbered.png
 *      retain the definition of unit vectors
 *
 *      Reason:
 *      1. The advantage of this cube coordinate is distance calculation, but we do not use it in this assignment
 *      what we do:
 *      2. Remove all methods not used
 *      3. Rebuild all the methods
 *      Result:
 *      4. Task 10 runtime reduce 6~11%
 *
 * @author Yu Cong
 */
public class Location {

	private int column, row;

	public Location(){
		this.column = -1;
		this.row = -1;
	}

	/**
	 * constructor
	 */
	 public Location(int column,int row){
	 	this.row = row;
	 	this.column = column;
	 }

	/**
	 * constructor
	 * @param str format in readme.md with 2 characters
	 *            e.g. 03, 32, 52
	 */
	public Location(String str){
		char[] c=str.toCharArray();
		this.row = c[1] - '0';
		this.column = c[0] - '0';
	}

	/**
	 * get the location added with a unit vector
	 * @param direction:0~5 direction of unit vector
	 * @return Location in certain direction which is adjacent to this Location
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

	/**
	 * directly judge whether a String is on board without create a new Location
	 */
	public static boolean onBoard(String loc){
		char[] chars = loc.toCharArray();
		if (chars[0] >= '0' && chars[0] <= '5') {
			return chars[1] >= '0' && chars[1] <= '3';
		}else if (chars[0] == '6' )
			return chars[1] == '0' || chars[1] == '2';
		else return false;
	}

	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	/**
	 * used in gui/Board.java
	 */
	public double getSceneY(){
		return 24 + (row + 0.5) * 69.282;
	}
	public double getSceneX(){
		if (row ==0 || row == 2)
			return 24 + (column + 0.5) * 80;
		else return 24 + (column + 1) * 80;
	}
}
