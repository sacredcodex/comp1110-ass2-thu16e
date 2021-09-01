package comp1110.ass2;

public class Piece {
	/**
	 * For each pieces, it will be stored by an int array representing for the unit vector in it
	 *
	 * Rules:
	 * the star✡ with parentheses() is regarded as the center star, and other star are marked as
	 * a unit vector(0~5, unit vectors has been introduced in Location.java)
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
	private char color;

	/**
	 * create integer arrays of different length when different color input,
	 * and set shape[] by following rules above
	 *
	 * @param color should be one of those 7 char above
	 */
	Piece(char color){
		switch (color) {
			case 'r' -> {
				this.shape = new int[3];
				this.shape[0] = 1;
				this.shape[1] = 2;
				this.shape[2] = 3;
				this.color = color;
			}
			case 'o' -> {
				this.shape = new int[2];
				this.shape[0] = 1;
				this.shape[1] = 3;
				this.color = color;
			}
			case 'y' -> {
				this.shape = new int[3];
				this.shape[0] = 0;
				this.shape[1] = 2;
				this.shape[2] = 3;
				this.color = color;
			}
			case 'g' -> {
				this.shape = new int[2];
				this.shape[0] = 1;
				this.shape[1] = 3;
				this.color = color;
			}
			case 'b' -> {
				this.shape = new int[3];
				this.shape[0] = 0;
				this.shape[1] = 1;
				this.shape[2] = 3;
				this.color = color;
			}
			case 'i' -> {
				this.shape = new int[2];
				this.shape[0] = 0;
				this.shape[1] = 3;
				this.color = color;
			}
			case 'p' -> {
				this.shape = new int[4];
				this.shape[0] = 0;
				this.shape[1] = 1;
				this.shape[2] = 4;
				this.shape[4] = 5;
				this.color = color;
			}
		}
	}

	/**
	 * get pieces in different orientations
	 * @param rotation 0~5
	 */
	public void rotatePiece(int rotation){
		for(int i = 0; i < this.shape.length; i++)
			shape[i]=(shape[i] + rotation) % 6;
	}


	/**
	 *
	 * @return String in format like readme.md
	 */
	public String toString(){
		//TODO:
	}
}
