package comp1110.ass2.gui;

import comp1110.ass2.Piece;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;

public class Board extends Application {


    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private static final int WHITE_EDGE = 10;
    private static final int RECTA_WIDTH = BOARD_WIDTH - 2 * WHITE_EDGE;// 913
    private static final double MARGIN = RECTA_WIDTH / 42.0;// 21.7
    private static final double STAR_WIDTH = (RECTA_WIDTH - 2 * MARGIN) / 7;// 124.2
    private static final double STAR_HEIGHT = STAR_WIDTH * Math.sqrt(3) / 2;// 107.6
    private static final double RECTA_HEIGHT = 2 * MARGIN + 4 * STAR_HEIGHT;// 473.8

    private final Group root = new Group();
    private final Group blankBoard = new Group();
    private Star[][] stars = new Star[4][];//place on blank board



    public void initializeBlankBoard(){
        // back board
        Rectangle board = new Rectangle(WHITE_EDGE, WHITE_EDGE, RECTA_WIDTH, RECTA_HEIGHT);
        board.setArcWidth(15);
        board.setArcHeight(15);
        board.setFill(new Color(0.172, 0.172, 0.27, 1.0));
        blankBoard.getChildren().add(board);

        // set empty stars
        comp1110.ass2.Board state = new comp1110.ass2.Board();
        stars[0] = new Star[7];
        stars[1] = new Star[6];
        stars[2] = new Star[7];
        stars[3] = new Star[6];
        double starX, starY;
        for (int i = 0; i < 4; i++) {
            starY = WHITE_EDGE + MARGIN + (2 * i + 1) / 2.0 * (Math.sqrt(3) / 2) * STAR_WIDTH;
            for (int j = 0; j < state.getColors()[i].length; j++) {
                if (i % 2 == 0)
                    starX = WHITE_EDGE + MARGIN + (2 * j + 1) / 2.0 * STAR_WIDTH;
                else starX = WHITE_EDGE + MARGIN + (j + 1) * STAR_WIDTH;
                stars[i][j] = new Star(starX, starY, STAR_WIDTH);
                stars[i][j].setEmptyStar();
                blankBoard.getChildren().add(stars[i][j]);
            }
        }
    }

    // FIXME Task 8 (CR): Implement a basic playable IQ Stars game in JavaFX that only allows pieces to be placed in valid places

    // FIXME Task 9 (D): Implement challenges (you may use the set of challenges in the Games class)

    // FIXME Task 11 (HD): Implement hints (should become visible when the user presses '/' -- see gitlab issue for details)

    // FIXME Task 12 (HD): Generate interesting challenges (each challenge must have exactly one solution)




    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("IQ Stars");
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);

        // set backboard
        root.getChildren().add(blankBoard);
        initializeBlankBoard();

        //test piece
        Piece piece = new Piece('r');
        VisualPiece pinkPiece = new VisualPiece(300,300,piece, STAR_WIDTH);
        root.getChildren().add(pinkPiece);
        pinkPiece.setScaleX(0.5);
        pinkPiece.setScaleY(0.5);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
