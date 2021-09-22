package comp1110.ass2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.*;


@Timeout(value = 1000, unit = MILLISECONDS)
public class PieceTest {

	private void testShape(char color, int[] expected) {
		int[] out = new Piece(color).getShape();
		assertArrayEquals(out, expected, "For color " + color + ", was expecting " + expected + ", but got " + out);
	}
	@Test
	public void shapeTest() {
		testShape('r', new int[]{1,2,3});
		testShape('o', new int[]{1,3});
		testShape('y', new int[]{0,2,3});
		testShape('g', new int[]{1,3});
		testShape('b', new int[]{0,1,3});
		testShape('i', new int[]{0,3});
		testShape('p', new int[]{0,1,4,5});
		testShape(' ', null);
		testShape('G', null);
		testShape('O', null);
		testShape('e', null);
	}

	private void testRotate(char color, int rotation, int[] expected) {
		Piece piece = new Piece(color);
		piece.rotatePiece(rotation);
		int[] out = piece.getShape();
		assertArrayEquals(out, expected, "For color " + color + ", was expecting " + expected + ", but got " + out);
	}
	@Test
	public void rotateTest() {
		testRotate('r', 0, new int[]{1,2,3});
		testRotate('o', 0, new int[]{1,3});
		testRotate('y', 0, new int[]{0,2,3});
		testRotate('g', 0, new int[]{1,3});
		testRotate('b', 0, new int[]{0,1,3});
		testRotate('i', 0, new int[]{0,3});
		testRotate('p', 0, new int[]{0,1,4,5});
		testRotate('r', 4, new int[]{5,0,1});
		testRotate('o', 8, new int[]{3,5});
		testRotate('y', 2, new int[]{2,4,5});
		testRotate('g', 43, new int[]{2,4});
		testRotate('b', 6, new int[]{0,1,3});
		testRotate('i', 5, new int[]{5,2});
		testRotate('p', 2, new int[]{2,3,0,1});
	}

	private void testTopLeft(char color, int rotation, int expected) {
		Piece piece = new Piece(color);
		piece.rotatePiece(rotation);
		int out = piece.topLeft();
		assertEquals(expected, out, "For color " + color + ", rotate " + rotation + " times, was expecting " + expected + ", but got " + out);
	}
	@Test
	public void topLeft(){
		testTopLeft('r', 2, 4);
		testTopLeft('o', 5, 6);
		testTopLeft('y', 3, 5);
		testTopLeft('g', 0, 3);
		testTopLeft('b', 4, 4);
		testTopLeft('g', 4, 55);
		testTopLeft('i', 1, 4);
		testTopLeft('p', 0, 4);
		testTopLeft('r', 1, 4);
		testTopLeft('i', 2, 5);
		testTopLeft('g', 3, 44);
		testTopLeft('p', 2, 3);
		testTopLeft('p', 5, 4);
		testTopLeft('i', 4, 4);
	}

	private void testCenter(String pieceStr, String expected){
		Piece piece = new Piece(pieceStr.charAt(0));
		piece.rotatePiece(pieceStr.charAt(1) - '0');
		String out = piece.getCenter(pieceStr).toString();
		assertEquals(expected, out, "For piece String "+ pieceStr + ", was expected " + expected + ", but got " + out);
	}
	@Test
	public void centerTest() {
		testCenter("r032", "42");
		testCenter("g300", "12");
		testCenter("o322", "23");
	}
}


