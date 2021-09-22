package comp1110.ass2;

public class Piece {
	/**
	 * For each pieces, it will be stored by an int array representing for the unit vector in it
	 *
	 * Rules:
	 * the star✡ with parentheses() is regarded as the center star, and other star are marked as
	 * a unit vector(0~5, unit vectors has been introduced in Location.java),
	 * which is from center star to this
	 *
	 *
	 * color  shape           set           note
 	 * r   ✡ --(✡)           1，2，3
	 *         / \
	 *       ✡    ✡
	 *
	 * o   ✡ --(✡)           1, 3
	 *           \
	 *            ✡
	 *
	 * y    ✡ --(✡)-- ✡     0, 2, 3
	 *         /
	 *        ✡
	 *
	 * g    ✡ --(✡)         1, 3            for this piece 'g', set[0] represents for 2 stars in the same direction:
	 *            \                             one star is at the unit vector,
	 *             ✡                            and the other star is at twice the unit vector
	 *              \
	 *               ✡
	 *
	 * b    ✡ --(✡)-- ✡     0, 1, 3
	 *            \
	 *             ✡
	 *
	 * i    ✡ -- ✡ -- ✡     0, 3
	 *
	 * p    ✡    ✡          0, 1, 4, 5      for this piece 'p', the center star should not be placed on Board
	 *       \  /
	 *       ( )-- ✡
	 *         \
	 *          ✡
	 *
	 * use 0~5 stands for different orientations
	 * all integers in set[] plus orientation, the shape of piece will not change and get a new piece
	 *
	 */

	private int[] shape;
	private final char color;
	private int direction = 0;

	/**
	 * Constructor:
	 * create integer arrays of different length when different color input,
	 * and set shape[] by the rules above
	 * In this constructor, the piece is created as the default direction 0
	 *
	 * @param color should be one of those 7 char above
	 */
	public Piece(char color){
		this.color = color;
		switch (color) {
			case 'r' -> {
				this.shape = new int[3];
				this.shape[0] = 1;
				this.shape[1] = 2;
				this.shape[2] = 3;
			}
			case 'o' -> {
				this.shape = new int[2];
				this.shape[0] = 1;
				this.shape[1] = 3;
			}
			case 'y' -> {
				this.shape = new int[3];
				this.shape[0] = 0;
				this.shape[1] = 2;
				this.shape[2] = 3;
			}
			case 'g' -> {
				this.shape = new int[2];
				this.shape[0] = 1;
				this.shape[1] = 3;
			}
			case 'b' -> {
				this.shape = new int[3];
				this.shape[0] = 0;
				this.shape[1] = 1;
				this.shape[2] = 3;
			}
			case 'i' -> {
				this.shape = new int[2];
				this.shape[0] = 0;
				this.shape[1] = 3;
			}
			case 'p' -> {
				this.shape = new int[4];
				this.shape[0] = 0;
				this.shape[1] = 1;
				this.shape[2] = 4;
				this.shape[3] = 5;
			}
			default -> this.shape = null;
		}
	}

	/**
	 * This is used in Board
	 * @return char, color of piece
	 */
	public char getColor() {
		return color;
	}

	/**
	 * This is used int Board
	 * @return int[]
	 */
	public int[] getShape() {
		return shape;
	}

	/**
	 * let piece rotate in rotation*60 degree
	 *
	 * @param rotation 0~5 is better, but could be larger, not negative.
	 */
	public void rotatePiece(int rotation){
		this.direction = (this.direction + rotation) % 6;
		if (this.color == 'r' || color == 'i')
			direction = direction % 3;
		for(int i = 0; i < this.shape.length; i++)
			shape[i]=(shape[i] + rotation) % 6;
	}

	/**
	 * return the direction vector from center star to the top left of the piece
	 * 0~5 -> 6 unit direction vectors
	 *        actually, it will only return 4, 5, 3 but not 0~2,
	 *        because the center star is always at the top left of 0~2 direction,
	 *        and piece 'p' has an empty center star but with 4 adjacent stars,
	 *        which means there is always at least one of 3~5 in these 4 adjacent
	 *        stars with total 6 direction
	 * 6 -> just the center star of the piece
	 *
	 * specially, piece 'g' has a star which distance to the center star d = 2
	 *            for this situation, use the unit vector twice
	 *            for example:
	 *            ✡                 this piece has the top left star which is
	 *             \                2 units away from the center
	 *              ✡               and return  44
	 *               \
	 *               （✡）-- ✡
	 *
	 * @return 3~6, 44, 55
	 */
	public int topLeft(){
		if (this.color == 'g'){
			switch (shape[0]){
				case 0:
					return 6;
				case 1:
					return 3;
				case 2:
					return 4;
				case 3:
					return 5;
				case 4:
					return 44;
				case 5:
					return 55;
			}
		}
		boolean exist3 = false;
		boolean exist4 = false;
		boolean exist5 = false;
		for (int i : this.shape){
			if (i == 3)
				exist3 = true;
			if (i == 4)
				exist4 = true;
			if (i == 5)
				exist5 = true;
		}
		if (exist4)
			return 4;
		else if (exist5)
			return 5;
		else if (exist3)
			return 3;
		else return 6;
	}

	/**
	 * change piece with the center star location into format in readme.md
	 *
	 * @param loc is the center star location
	 * @return String in format like readme.md, length() == 4
	 */
	public String toString(Location loc) {
		String res = "";

		res = res + this.color + this.direction;
		int topLeft = this.topLeft();
		if (topLeft == 3)
			return res + loc.getNext(3).toString();
		if (topLeft == 4)
			return res + loc.getNext(4).toString();
		if (topLeft == 5)
			return res + loc.getNext(5).toString();
		if (topLeft == 6)
			return res + loc.toString();
		if (topLeft == 44)
			return res + loc.getNext(4).getNext(4).toString();
		if (topLeft == 55)
			return res + loc.getNext(5).getNext(5).toString();
		return "";//placeholder
	}

	/**
	 * get the center star Location of the piece in readme.md format
	 *
	 * @param str the format in readme.md
	 *            only the last 2 chars are used
	 *            assumed this Piece is equal to what str expresses
	 * @return the center star location
	 */
	public Location getCenter(String str) {
		int topLeft = this.topLeft();
		Location loc = new Location(str.substring(2, 4));
		if (topLeft == 3)
			return loc.getNext(0);
		if (topLeft == 4)
			return loc.getNext(1);
		if (topLeft == 5)
			return loc.getNext(2);
		if (topLeft == 6)
			return loc;
		if (topLeft == 44)
			return loc.getNext(1).getNext(1);
		if (topLeft == 55)
			return loc.getNext(2).getNext(2);
		return loc;//placeholder, this line should not be used
	}
}
