package comp1110.ass2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.*;


@Timeout(value = 1000, unit = MILLISECONDS)
public class BoardTest {
	private void test(Board board, String expected){
		assertTrue(expected.equals(board.toString()),"For challenge: "+board.getPuzzle()+", was expecting "+expected+", but get"+board.toString());
	}

	@Test
	public void toStringTest(){
		for (int i = 0; i < 96; i++) {
			Board board = new Board();
			board.setPuzzle(Games.ALL_CHALLENGES_SOLUTIONS[i]);
			test(board, Games.ALL_CHALLENGES_SOLUTIONS[i]);
		}
	}
}
