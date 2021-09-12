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
    private static final int SHADOW = 5;
    private static final double STAR_WIDTH = 82.26684575;
    private static final double STAR_HEIGHT = STAR_WIDTH * Math.sqrt(3) / 2;
    private static final double MARGIN = 0.175 * STAR_WIDTH;
    private static final double RECTA_WIDTH = 2 * MARGIN + 7 * STAR_WIDTH; //604.66
    private static final double RECTA_HEIGHT = 2 * MARGIN + 4 * STAR_HEIGHT;
    private static final Color BLACKBLUE = new Color(0.172, 0.172, 0.27, 1.0);


    private final Group root = new Group();
    private final Group blankBoard = new Group();
    private Star[][] stars = new Star[4][];//place on blank board



    public void initializeBlankBoard(){
        // back board
        Rectangle boardShadow = new Rectangle(WHITE_EDGE + SHADOW, WHITE_EDGE + SHADOW, RECTA_WIDTH, RECTA_HEIGHT);
        boardShadow.setArcWidth(15);
        boardShadow.setArcHeight(15);
        boardShadow.setFill(Color.GRAY);
        blankBoard.getChildren().add(boardShadow);
        Rectangle board = new Rectangle(WHITE_EDGE, WHITE_EDGE, RECTA_WIDTH, RECTA_HEIGHT);
        board.setArcWidth(15);
        board.setArcHeight(15);
        board.setFill(BLACKBLUE);
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


        //set something that will not move in game
        Rectangle pieceBoard = new Rectangle(WHITE_EDGE, 2 * WHITE_EDGE + RECTA_HEIGHT, 5 * STAR_WIDTH, 5 * STAR_HEIGHT);
        pieceBoard.setArcHeight(60);
        pieceBoard.setArcWidth(60);
        pieceBoard.setFill(BLACKBLUE);
        root.getChildren().add(pieceBoard);

        // set backboard
        root.getChildren().add(blankBoard);
        initializeBlankBoard();

        //set pieces
        Piece piece = new Piece('r');
        VisualPiece redPiece = new VisualPiece(300,300,piece, STAR_WIDTH);
        root.getChildren().add(redPiece);



        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
