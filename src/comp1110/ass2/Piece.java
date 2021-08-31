package comp1110.ass2;

public class Piece {
	/**
	 * For each pieces, it will be stored by an int array represent for the unit vector in it
	 *
	 * Rules:
	 * color  shape
 	 * r   ✡ -- ✡           0，1，3
	 *           \
	 *       ✡ -- ✡
	 *
	 * o   ✡ -- ✡           0.1
	 *           \
	 *            ✡
	 *
	 * y    ✡    ✡ -- ✡     1,5,0
	 *       \  /
	 *        ✡
	 *
	 * g    ✡ -- ✡          0,1,1
	 *            \
	 *             ✡
	 *              \
	 *               ✡
	 *
	 * b    ✡ -- ✡ -- ✡     0,0,2
	 *               /
	 *             ✡
	 *
	 * i    ✡ -- ✡ -- ✡     0,0
	 *
	 * p    ✡ -- ✡          0,1,2
	 *            \
	 *             ✡
	 *            /
	 *          ✡
	 *
	 * use 0~5 stand for different orientations
	 * all integers in shape[] plus orientation, the shape of piece will not change and get a new piece
	 *
	 *
	 */
	private int[] shape;
	private char color;
	private int length;

	/**
	 * create integer arrays of different length when different color input,
	 * and set shape[] by following rules above
	 * @param color
	 */
	Piece(char color){
		switch (color){
			case 'r':
				this.length = 4;
				this.shape = new int[this.length-1];
				this.shape[0] = 0;
				this.shape[1] = 1;
				this.shape[2] = 3;
				this.color=color;
				break;
			case 'o':
				this.length = 3;
				this.shape = new int[this.length-1];
				this.shape[0] = 0;
				this.shape[1] = 1;
				this.color=color;
				break;
			case 'y':
				this.length = 4;
				this.shape = new int[this.length-1];
				this.shape[0] = 1;
				this.shape[1] = 5;
				this.shape[2] = 0;
				this.color=color;
				break;
			case 'g':
				this.length = 4;
				this.shape = new int[this.length-1];
				this.shape[0] = 0;
				this.shape[1] = 1;
				this.shape[2] = 1;
				this.color=color;
				break;
			case 'b':
				this.length = 4;
				this.shape = new int[this.length-1];
				this.shape[0] = 0;
				this.shape[1] = 0;
				this.shape[2] = 2;
				this.color=color;
				break;
			case 'i':
				this.length = 3;
				this.shape = new int[this.length-1];
				this.shape[0] = 0;
				this.shape[1] = 0;
				this.color=color;
				break;
			case 'p':
				this.length = 4;
				this.shape = new int[this.length-1];
				this.shape[0] = 0;
				this.shape[1] = 1;
				this.shape[2] = 2;
				this.color=color;
				break;
		}
	}

	/**
	 * get pieces in different orientations
	 * @param rotation 0~5
	 */
	public void rotatePiece(int rotation){
		for(int i = 0; i < this.length - 1; i++)
			shape[i]=(shape[i] + rotation) % 6;
	}

	/**
	 *
	 * @param board
	 * @param loc the start location to place the piece
	 * @return whether loc on board is valid to place the Piece
	 */
	public boolean isPieceValid(Board board,Location loc){
		return true;//TODO: complete after Board
	}

	/**
	 *
	 * @param board
	 * @param loc the start location to place the piece
	 */
	public void placePiece(Board board,Location loc){
		//TODO: complete after Board
	}

	/**
	 *
	 * @param str format in readme.md
	 */
	public static void placePiece(Board board, String str){
		//TODO:
	}

	/**
	 * notice: it will also remove the wizard puzzle star
	 * @param board
	 * @param loc the start location to place the piece
	 */
	public void removePiece(Board board,Location loc){
		//TODO: complete after Board
	}

	/**
	 *
	 * @return String in format like readme.md
	 */
	public String toString(){
		//TODO:
	}
}
