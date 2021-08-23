package comp1110.ass2;

public class Location {
	/**
	 * reference：https://www.redblobgames.com/grids/hexagons/#basics
	 *
	 * Cube coordinates: x,y,z
	 * Board:
	 *    (0,0,0)---(1,-1,0)---(2,-2,0)---(3,-3,0)---(4,-4,0)---(5,-5,0)---(6,-6,0)
	 *         \     /    \     /    \     /    \     /    \     /    \     /
	 *        (0,-1,1)---(1,-2,1)---(2,-3,1)---(3,-4,1)---(4,-5,1)---(5,-6,1)
	 *         /    \     /    \     /    \     /    \     /    \     /    \
	 * (-1,-1,2)---(0,-2,2)---(1,-3,2)---(2,-4,2)---(3,-5,2)---(4,-6,2)---(5,-7,2)
	 *        \     /    \     /    \     /    \     /    \     /    \     /
	 *      (-1,-2,3)---(0,-3,3)---(1,-4,3)---(2,-5,3)---(3,-6,3)---(4,-7,3)
	 *
	 * Definition of 6 direction unit vectors:
	 *                4          5
	 *            (0,1,-1)---(1,0,-1)
	 *             /   \      /    \
	 *    3 (-1,1,0)---(0,0,0)----(1,-1,0) 0
	 *            \     /    \    /
	 *           (-1,0,1)---(0,-1,1)
	 *              2          1
	 *
	 * Piece:
	 *      mark a line with 4 stars as an int array 0,0,0
	 *      more details in Piece.java
	 *      when rotate the piece use +1,+2...
	 *          then the piece will change to 1,1,1  2,2,2  ...
	 *
	 */

	private int x,y,z;//x+y+z==0

	/**
	 * constructor
	 */
	 Location(int x,int y, int z){
	 	if(x+y+z==0) {
		    this.x = x;
		    this.y = y;
		    this.z = z;
	 	}
	 }

	/**
	 * constructor
	 * x+y+z==0
	 * @param a and
	 * @param b are two parameters of x,y,z
	 * @param str："xy","xz","yz"
	 */
	 Location(int a,int b,String str){

	 }

	/**
	 * constructor
	 * format in readme.md --> cube coordinate x,y,z
	 * @param x and
	 * @param y are parameters in readme.md
	 */
	 Location(int x,int y){

	 }

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	/**
	 *
	 * @param direction:0~5 direction of unit vector
	 * @return Location in certain direction which is adjacent to this
	 */
	public Location getNext(int direction){

	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setZ(int z) {
		this.z = z;
	}

	/**
	 * distance=1/2*(|x1-x2|+|y1-y2|+|z1-z2|)
	 * @param loc
	 * @return distance between this and loc
	 */
	public int getDistance(Location loc){

	}

	/**
	 *
	 * @param loc is adjacent to this
	 * @return direction of (this->loc) vector
	 */
	public int getDirection(Location loc){

	}

	/**
	 *
	 * @param loc
	 * @return whether the distance is 1
	 */
	public boolean isAdjacent(Location loc){

	}

	/**
	 *
	 * @return format in readme.md
	 */
	@Override
	public String toString(){

	}

	/**
	 *
	 * @return whether the Location is on Board(one of 26 Locations)
	 */
	public boolean onBoard(){

	}

	/**
	 *
	 * @param loc
	 * @return whether two location is same
	 */
	public boolean equals(Location loc){

	}
}
