package comp1110.ass2;

public class Piece {
	/**
	 * For each pieces, it will be stored by an int array represent for the unit vector in it
	 *
	 * Rules:
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
	 *
	 *
	 *
	 *
	 */
	int[] shape;
	char color;

	/**
	 * create integer arrays of different length when different color input,
	 * and set shape[] by following rules above
	 * @param color
	 */
	Piece(char color){

	}

	/**
	 * get pieces in different orientations
	 * @param rotation
	 */
	public void rotatePiece(int rotation){
		for(int i=0;i< shape.length;i++)
			shape[i]=(shape[i] + 1) % 6;
	}

	/**
	 *
	 * @param board
	 * @param loc the start location to place the piece
	 * @return whether loc on board is valid to place the Piece
	 */
	public boolean isPieceValid(Board board,Location loc){
		return true;
	}

	/**
	 *
	 * @param board
	 * @param loc the start location to place the piece
	 */
	public void placePiece(Board board,Location loc){
	}

	/**
	 * notice: it will also remove the wizard puzzle star
	 * @param board
	 * @param loc the start location to place the piece
	 */
	public void removePiece(Board board,Location loc){

	}
}
