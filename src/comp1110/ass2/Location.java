package comp1110.ass2;

public class Location {
	/**
	 * Structure:
	 *       4       5
	 *        \     /
	 *  3 ---(x,y,z)--- 0
	 *        /    \
	 *       2      1
	 *
	 * Board:
	 *    (0,0,0)---(1,-1,0)---(2,-2,0)---(3,-3,0)---(4,-4,0)---(5,-5,0)---(6,-6,0)
	 *         \     /    \     /    \     /    \     /    \     /    \     /
	 *        (0,-1,1)---(1,-2,1)---(2,-3,1)---(3,-4,1)---(4,-5,1)---(5,-6,1)
	 *         /    \     /    \     /    \     /    \     /    \     /    \
	 * (-1,-1,2)---(0,-2,2)---(1,-3,2)---(2,-4,2)---(3,-5,2)---(4,-6,2)---(5,-7,2)
	 *        \     /    \     /    \     /    \     /    \     /    \     /
	 *      (-1,-2,3)---(0,-3,3)---(1,-4,3)---(2,-5,3)---(3,-6,3)---(4,-7,3)
	 *
	 * Unit vector:
	 *                4          5
	 *            (0,1,-1)---(1,0,-1)
	 *             /   \      /    \
	 *    3 (-1,1,0)---(0,0,0)----(1,-1,0) 0
	 *            \     /    \    /
	 *           (-1,0,1)---(0,-1,1)
	 *              2          1
	 *
	 * Piece:
	 *      mark a line with 4 stars as int array 0,0,0
	 *      more details in Piece.java
	 *      when rotate the piece use +1,+2...
	 *          then the piece will change to 1,1,1
	 *
	 *
	 *
	 */

	private int x,y,z;//x+y+z=0
	Location[] neighbor;//neighbor[0~5]


	 Location(int x,int y, int z){
	 	if(x+y+z==0) {
		    this.x = x;
		    this.y = y;
		    this.z = z;
	 	}
	 }

	/**
	 * two of x,y,z
	 * @param a
	 * @param b
	 * @param str "xy","xz","yz"
	 */
	 Location(int a,int b,String str){

	 }

	/**
	 * readme --> x,y,z
	 * @param x
	 * @param y
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

	public Location[] getNeighbor() {
		return neighbor;
	}

	/**
	 * return one of neighbors
	 * @param rotation 0~5
	 * @return
	 */
	public Location getNeighbor(int rotation){

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

	public void setNeighbor(Location[] neighbor) {
		this.neighbor = neighbor;
	}

	/**
	 *
	 * @param loc
	 * @param rotation
	 */
	public void setNeighbor(Location loc,int rotation){

	}

	/**
	 * distance=1/2*(|x1-x2|+|y1-y2|+|z1-z2|)
	 * @param loc
	 * @return distance between this and loc (an integer)
	 */
	public int getDistance(Location loc){

	}

	/**
	 *
	 * @param loc is adjacent to this
	 * @return 0~5 unit vector
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
	 * @return whether the Location is on Board, or is one of the 26 Locations
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
