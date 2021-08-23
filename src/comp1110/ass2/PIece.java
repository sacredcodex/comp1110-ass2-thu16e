package comp1110.ass2;

public class PIece {
	int[] shape;
	int color;

	PIece(int color){
		this.color=color;
		switch(color) {
			case 1:
				shape = new int[];

		}
	}

	public void rotatePiece(int rotation){
		for(int i=0;i< shape.length;i++)
			shape[i]=(shape[i] + 1) % 6;
	}

	/**
	 * @return whether loc on board is valid to place the Piece
	 */
	public boolean setPiece(Board board,Location loc){

	}

	public void placePiece(Board board,Location loc){

	}

	public void removePiece(Board board,Location loc){

	}
}
