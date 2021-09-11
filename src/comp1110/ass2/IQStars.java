package comp1110.ass2;

import java.util.HashSet;
import java.util.Set;

public class IQStars {

    /**
     * Determine whether a game string describing either a wizard or a piece
     * is well-formed according to the following criteria:
     * - It consists of exactly three or four characters
     * - If it consists of three characters, it is a well-formed wizard string:
     *      - the first character is a valid colour character (r,o,y,g,b,i,p)
     *      - the second character is a valid column number
     *          - (0 .. 6) if the row number is 0 or 2
     *          - (0 .. 5) otherwise
     *      - the third character is a valid row number (0 .. 3)
     * - If it consists of four characters, it is a well-formed piece string:
     *      - the first character is a valid colour character (r,o,y,g,b,i,p)
     *      - the second character is a valid rotation value
     *          - (0 .. 2) if the colour is r or i
     *          - (0 .. 5) otherwise
     *      - the third character is a valid column number
     *          - (0 .. 6) if the row number is 0 or 2
     *          - (0 .. 5) otherwise
     *      - the fourth character is a valid row number (0 .. 3)
     *      @param gameString A string describing either a piece or a wizard
     *      @return True if the string is well-formed
     */
    // this method only check if the top left location is on board, not the whole piece
    static boolean isGameStringWellFormed(String gameString) {
        if (gameString==null)
            return false;
        if (gameString.length() == 3){
            Location loc = new Location(gameString.substring(1,3));
            if (!loc.onBoard())
                return false;
            char c = gameString.charAt(0);
            return c == 'r' || c == 'o' || c == 'y' || c == 'g' || c == 'b' || c == 'i' || c == 'p';
        }
        if (gameString.length() == 4){
            char c = gameString.charAt(0);
            if (c != 'r' && c != 'o' && c != 'y' && c != 'g' && c != 'b' && c != 'i' && c != 'p')
                return false;
            if (gameString.charAt(1)<'0')
                return false;
            if (c == 'r' || c == 'i'){
                if (gameString.charAt(1) > '2')
                    return false;
            }else{
                if (gameString.charAt(1) > '5')
                    return false;
            }
            Location loc = new Location(gameString.substring(2,4));
            return loc.onBoard();
        }
        //length < 3 || length > 4
        return false; // FIXME Task 3 (P): determine whether a wizard or piece string is well-formed
    }

    /**
     * compare c1 and c2, if c1 appears earlier than c2 in (r, o, y, g, b, i, p)
     * @return true if c1 appears earlier
     *         false otherwise
     *         if c1 == c2 return false
     */
    public static boolean isOrdered(char c1, char c2){
        if (c1 == 'r')
            return c2 != 'r';
        if (c1 == 'o')
            return c2 != 'r' && c2 != 'o';
        if (c1 == 'y')
            return c2 != 'r' && c2 != 'o' && c2 != 'y';
        if (c1 == 'g')
            return c2 == 'b' || c2 == 'i' || c2 == 'p';
        if (c1 == 'b')
            return c2 == 'i' || c2 == 'p';
        if (c1 == 'i')
            return c2 == 'p';
        return false;
    }

    /**
     * Determine whether a game state string is well-formed:
     * - The string is of the form [piecePlacement]W[wizardPlacement],
     *      where [piecePlacement] and [wizardPlacement] are replaced by the
     *      corresponding strings below
     * - [piecePlacement] string specification:
     *      - it consists of exactly n four-character piece strings (where n = 0 .. 7);
     *      - each piece string is well-formed
     *      - no piece appears more than once in the string
     *      - the pieces are ordered correctly within the string (r,o,y,g,b,i,p)
     * - [wizardPlacement] string specification:
     *      - it consists of exactly n three-character wizard strings (where n is some non-negative integer)
     *      - each wizard string is well-formed
     *      - the strings are ordered correctly within the string (r,o,y,g,b,i,p)
     *      - if there is more than one wizard of a single colour these wizards are ordered first by
     *          row and then by column in ascending order (note that this does not prevent two
     *          wizards of the same colour being placed in the same location - we will check for this
     *          in a later task).
     * @param gameStateString A string describing a game state
     * @return True if the game state string is well-formed
     */
    public static boolean isGameStateStringWellFormed(String gameStateString) {
        int indexOfW = gameStateString.indexOf('W');
        String partOne = gameStateString.substring(0,indexOfW);
        String partTwo = gameStateString.substring(indexOfW+1,gameStateString.length());
        if (!(partOne.length() % 4 == 0 && partOne.length() <= 28))
            return false;
        if (partTwo.length() % 3 != 0)
            return false;

        //piece string
        String piece;
        char previousColor = 'r';
        for (int i = 0; i < partOne.length() / 4; i++) {
            piece = partOne.substring(4 * i, 4 * i + 4);
            if (!isGameStringWellFormed(piece))
                return false;
            if (i >= 1)
                if (!isOrdered(previousColor, piece.charAt(0)))
                    return false;
            previousColor = piece.charAt(0);
        }

        //wizard string
        String wizard;
        char previousRow = 0;
        char previousColumn = 0;
        for (int i = 0; i < partTwo.length() / 3; i++) {
            wizard = partTwo.substring(3 * i, 3 * i + 3);
            if (!isGameStringWellFormed(wizard))
                return false;
            if (i >= 1) {
                if (!isOrdered(previousColor, wizard.charAt(0))) {
                    if (previousColor == wizard.charAt(0)) {
                        if (previousRow >= wizard.charAt(2)){
                            if (previousRow == wizard.charAt(2)){
                                if (previousColumn > wizard.charAt(1))
                                    return false;
                            }else
                                return false;
                        }
                    }else
                        return false;
                }
            }
            previousColor = wizard.charAt(0);
            previousRow = wizard.charAt(2);
            previousColumn = wizard.charAt(1);
        }

        return true;

        // FIXME Task 4 (P): determine whether a game state string is well-formed
    }

    /**
     * Determine whether a game state is valid.
     *
     * To be valid, the game state must satisfy the following requirements:
     * - string must be well-formed
     * - pieces must be entirely on the board
     * - pieces must not overlap each other
     * - wizards must be on the board
     * - each location may have at most one wizard
     * - each piece must cover all wizards of the same colour
     * - each piece must not cover any wizards of a different colour
     *
     * @param gameStateString A game state string
     * @return True if the game state represented by the string is valid
     */
    public static boolean isGameStateValid(String gameStateString) {
        if (!isGameStateStringWellFormed(gameStateString))
            return false;
        int indexOfW = gameStateString.indexOf('W');
        String partOne = gameStateString.substring(0,indexOfW);
        String partTwo = gameStateString.substring(indexOfW+1,gameStateString.length());
        Board board = new Board();

        String piece;
        for (int i = 0; i < partOne.length() / 4; i++) {
            piece = partOne.substring(4 * i, 4 * i + 4);
            if (board.isPieceValid(piece))
                board.placePiece(piece);
            else
                return false;
        }
        String wizard;
        for (int i = 0; i < partTwo.length() / 3; i++){
            wizard = partTwo.substring(3 * i, 3 * i + 3);
            if (board.getColor(wizard.substring(1,3)) == 'n')
                board.setColor(wizard.substring(1,3), (char)(wizard.charAt(0)-32));
            else if (board.getColor(wizard.substring(1,3)) != wizard.charAt(0))
                return false;
        }

        int[] countStar = new int[7];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < board.getColors()[i].length; j++) {
                switch (board.getColors()[i][j]){
                    case 'r' -> countStar[0]++;
                    case 'R' -> countStar[0]++;
                    case 'o' -> countStar[1]++;
                    case 'O' -> countStar[1]++;
                    case 'y' -> countStar[2]++;
                    case 'Y' -> countStar[2]++;
                    case 'g' -> countStar[3]++;
                    case 'G' -> countStar[3]++;
                    case 'b' -> countStar[4]++;
                    case 'B' -> countStar[4]++;
                    case 'i' -> countStar[5]++;
                    case 'I' -> countStar[5]++;
                    case 'p' -> countStar[6]++;
                    case 'P' -> countStar[6]++;
                }
            }
        }
        if (countStar[0] > 4)
            return false;
        if (countStar[1] > 3)
            return false;
        if (countStar[2] > 4)
            return false;
        if (countStar[3] > 4)
            return false;
        if (countStar[4] > 4)
            return false;
        if (countStar[5] > 3)
            return false;
        if (countStar[6] > 4)
            return false;

        return true;  // FIXME Task 6 (D): determine whether a game state is valid
    }

    /**
     * Given a string describing a game state, and a location
     * that must be covered by the next move, return a set of all
     * possible viable piece strings which cover the location.
     *
     * For a piece string to be viable it must:
     *  - be a well formed piece string
     *  - be a piece that is not already placed
     *  - not overlap a piece that is already placed
     *  - cover the given location
     *  - cover all wizards of the same colour
     *  - not cover any wizards of a different colour
     *
     * @param gameStateString A starting game state string
     * @param col      The location's column.
     * @param row      The location's row.
     * @return A set of all viable piece strings, or null if there are none.
     */
    static Set<String> getViablePieceStrings(String gameStateString, int col, int row) {
        Board board = new Board();
        board.setPuzzle(gameStateString);
        Location loc = new Location(""+col+row);
        Location adjacentLoc, distance2Loc;
        Piece piece = null;

        char[] color = {'r', 'o', 'y', 'g', 'b', 'i', 'p'};
        // not mentioned or in wizard string
        Set<Character> unusedColor = new HashSet<>();
        for (int i = 0; i < 7; i++) {
            if (gameStateString.substring(0, gameStateString.indexOf('W')).indexOf(color[i]) == -1)
                unusedColor.add(color[i]);
        }

        Set<String> res = new HashSet<>();

        //input location is center star
        for (int rotation = 0; rotation < 6; rotation++) {
            for (char c : unusedColor){
                if (c == 'p')
                    continue; // center star of pink piece is empty
                piece = new Piece(c);
                piece.rotatePiece(rotation);
                if (board.isPieceValid(piece, loc)) {
                        res.add(piece.toString(loc));
                }
            }

        }
        // center star is around input loc
        for (int i = 0; i < 6; i++) {
            // adjacent
            adjacentLoc = loc.getNext(i);
            if (adjacentLoc.onBoard()) {
                for (int rotation = 0; rotation < 6; rotation++) {
                    for (char c : unusedColor){
                        piece = new Piece(c);
                        piece.rotatePiece(rotation);
                        if (board.isPieceValid(piece, adjacentLoc)) {
                            for (int j : piece.getShape()) {
                                if (j == (i + 3) % 6){
                                        res.add(piece.toString(adjacentLoc));
                                }
                            }
                        }
                    }

                }
            }
            // green piece
            if (unusedColor.contains('g')) {
                distance2Loc = loc.getNext(i).getNext(i);
                if (distance2Loc.onBoard()) {
                    // rotation = (3 + i - 1) % 6
                    piece = new Piece('g');
                    piece.rotatePiece((2 + i) % 6);
                    if (board.isPieceValid(piece, distance2Loc)){
                            res.add(piece.toString(distance2Loc));
                    }
                }
            }
        }

        // remove pieces which does not cover the wizard star:
        int countStar;
        Set<String> toBeRemoved = new HashSet<>();
        for (String pieceStr : res ){
            board.placePiece(pieceStr);
            countStar = 0;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < board.getColors()[i].length; j++) {
                    if (board.getColors()[i][j] == pieceStr.charAt(0) || board.getColors()[i][j] == pieceStr.charAt(0) - 32)
                        countStar++;
                }
            }
            board.removePiece(pieceStr);
            if (countStar == 3 && (pieceStr.charAt(0) == 'o' || pieceStr.charAt(0) == 'i'))
                continue;
            if (countStar == 4 && (pieceStr.charAt(0) == 'r' || pieceStr.charAt(0) == 'y' || pieceStr.charAt(0) == 'g' ||
                    pieceStr.charAt(0) == 'b' || pieceStr.charAt(0) == 'p' ))
                continue;
            toBeRemoved.add(pieceStr);
        }
        for (String i : toBeRemoved) {
            res.remove(i);
        }

        if (res.isEmpty())
            return null;
        return res;  // FIXME Task 7 (P): determine the set of all viable piece strings given an existing game state
    }

    /**
     * Implement a solver for this game that can return the solution to a
     * particular challenge.
     *
     * This task is at the heart of the assignment and requires you to write
     * solver, similar to the boggle solver presented as part of the J14 lecture.
     *
     * NOTE: Simply looking up the provided answers does not constitute a general
     * solver.  Such an implementation is not a solution to this task, and
     * will not receive marks.
     *
     * @param challenge A game state string describing the starting game state.
     * @return A game state string describing the encoding of the solution to
     * the challenge.
     */
    public static String getSolution(String challenge) {
        Board board = new Board();
        board.setPuzzle(challenge);
        board.solvePuzzle();
        String wizard = challenge.substring(challenge.indexOf('W')+1);
        return board.getSolution() + wizard;
        // FIXME Task 10 (CR): determine the solution to the game, given a particular challenge
    }
}
