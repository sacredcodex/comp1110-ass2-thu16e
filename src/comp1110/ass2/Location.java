package comp1110.ass2;

public class Location {
	/**
	 * reference：https://www.redblobgames.com/grids/hexagons/#basics
	 *
	 * Cube coordinates: (x,y,z)
	 * When Location moves one space, two values in coordinates will change each time,
	 * one + 1, the other - 1, so x + y + z == 0
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
	 * Board:
	 *    (0,0,0)---(1,-1,0)---(2,-2,0)---(3,-3,0)---(4,-4,0)---(5,-5,0)---(6,-6,0)
	 *         \     /    \     /    \     /    \     /    \     /    \     /
	 *        (0,-1,1)---(1,-2,1)---(2,-3,1)---(3,-4,1)---(4,-5,1)---(5,-6,1)
	 *         /    \     /    \     /    \     /    \     /    \     /    \
	 * (-1,-1,2)---(0,-2,2)---(1,-3,2)---(2,-4,2)---(3,-5,2)---(4,-6,2)---(5,-7,2)
	 *        \     /    \     /    \     /    \     /    \     /    \     /
	 *      (-1,-2,3)---(0,-3,3)---(1,-4,3)---(2,-5,3)---(3,-6,3)---(4,-7,3)
	 *
	 * Distance: loc1(x1, y1, z1) and loc2(x2, y2, z2)
	 *           d := (|x1 - x2| + |y1 - y2 | + |z1 - z2|) / 2
	 *
	 * The reason why we choose cube coordinate:
	 *          simple distance formula in cube coordinate
	 *
	 *
	 */

	private int x,y,z; //x+y+z==0

	/**
	 * constructor with x, y, z
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
	 * @param str："xy","xz","yz" str decides which two parameters
	 */
	 Location(int a,int b,String str){
		//TODO: fix this when this constructor is used, or delete after complete
	 }

	/**
	 * constructor
	 * transform format in readme.md --> cube coordinate (x,y,z)
	 * @param x and
	 * @param y are 2 parameters in readme.md
	 */
	 Location(int x,int y){
	 	this.z=y;
	 	if (y==0||y==1)
	 		this.x=x;
	 	else this.x=x-1;
	 	this.y= -this.x - this.y;
	 }

	/**
	 * constructor
	 * format in readme.md
	 * @param str format in readme.md with 2 characters
	 *            e.g. 03, 34, 52
	 */
	Location(String str){
		char[] c=str.toCharArray();
		int x=c[0]-'0';
		int y=c[1]-'0';
		this.z=y;
		if (y==0||y==1)
			this.x=x;
		else this.x=x-1;
		this.y= -this.x - this.y;
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
		int x=this.x;
		int y=this.y;
		int z=this.z;
		switch (direction) {
			case 0 -> {
				x++;
				y--;
			}
			case 1 -> {
				y--;
				z++;
			}
			case 2 -> {
				x--;
				z++;
			}
			case 3 -> {
				x--;
				y++;
			}
			case 4 -> {
				y++;
				z--;
			}
			case 5 -> {
				x++;
				z--;
			}
		}
		return new Location(x,y,z);

	}

	/**
	 * distance=1/2*(|x1-x2|+|y1-y2|+|z1-z2|)
	 * @param loc compare this and loc
	 * @return distance between this and loc
	 */
	public int getDistance(Location loc){
		return (Math.abs(this.x-loc.x)+Math.abs(this.y-loc.y)+Math.abs(this.z-loc.z))/2;
	}

	/**
	 *
	 * @param loc compare this and loc
	 * @return whether the distance is 1
	 */
	public boolean isAdjacent(Location loc){
		return this.getDistance(loc)==1;
	}

	/**
	 *
	 * @param loc is adjacent to this
	 * @return direction of (this->loc) vector
	 */
	public int getDirection(Location loc){
		//loc-this
		int dx=loc.getX()-this.x;
		int dy=loc.getY()-this.y;
		int dz=loc.getZ()-this.z;
		if ( dx == 1 && dy == -1 && dz == 0 )
			return 0;
		if ( dx == 0 && dy == -1 && dz == 1 )
			return 1;
		if ( dx == -1 && dy == 0 && dz == 1 )
			return 2;
		if ( dx == -1 && dy == 1 && dz == 0 )
			return 3;
		if ( dx == 0 && dy == 1 && dz == -1 )
			return 4;
		if ( dx == 1 && dy == 0 && dz == -1 )
			return 5;
		return -1;
	}

	/**
	 *
	 * @return format in readme.md "xy"
	 */
	@Override
	public String toString(){
		if (this.z<2)
			return ""+this.x+this.z;
		else return ""+(this.x+1)+this.z;
	}

	/**
	 * this location should be wellformed( x+y+z==0)
	 * @return whether the Location is on Board(one of 26 Locations)
	 */
	public boolean onBoard(){
		switch (this.z){
			case 0:
				if (this.x >= 0 && this.x <= 6 )
					return true;
				break;
			case 1:
				if (this.x >= 0 && this.x <= 5 )
					return true;
				break;
			case 2:
				if (this.x >= -1 && this.x <= 5 )
					return true;
				break;
			case 3:
				if (this.x >= -1 && this.x <= 4 )
					return true;
				break;
		}
		return false;
	}

	/**
	 *
	 * @param loc compare this and loc
	 * @return whether two location is same
	 */
	public boolean equals(Location loc){
		return this.getDistance(loc)==0;
	}
}
