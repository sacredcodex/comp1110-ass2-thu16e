package comp1110.ass2.gui;

import comp1110.ass2.Piece;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Board extends Application {


    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private static final int WHITE_EDGE = 10;
    private static final int SHADOW = 5;
    private static final double STAR_WIDTH = 80;
    private static final double STAR_HEIGHT = STAR_WIDTH * Math.sqrt(3) / 2; //69.282
    private static final double MARGIN = 0.175 * STAR_WIDTH; //14
    private static final double RECTA_WIDTH = 2 * MARGIN + 7 * STAR_WIDTH; //588
    private static final double RECTA_HEIGHT = 2 * MARGIN + 4 * STAR_HEIGHT; //305.128
    private static final Color BLACKBLUE = new Color(0.172, 0.172, 0.27, 1.0);
    private static final double PIECE_BOARD_HEIGHT = BOARD_HEIGHT - 3 * WHITE_EDGE - RECTA_HEIGHT;
    private static final int MARGIN_IN_PIECE_BOARD = 9;
    private static final double X_PIECE_PLACE = WHITE_EDGE + MARGIN_IN_PIECE_BOARD + 2;
    private static final double Y_PIECE_PLACE = 2 * WHITE_EDGE + RECTA_HEIGHT + MARGIN_IN_PIECE_BOARD + 2;


    private final Group root = new Group();
    private final Group blankBoard = new Group();
    private Star[][] stars = new Star[4][];//place on blank board
    private final Group pieceBoard = new Group();
    private Star[][] pieceBoardStars = new Star[5][];//place on piece board
    private VisualPiece[][] pieces = new VisualPiece[4][];
    private Button[][] selects = new Button[4][];



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

    public void initializePieceBoard(){
        // back board
        Rectangle boardShadow = new Rectangle(WHITE_EDGE + SHADOW, 2 * WHITE_EDGE + RECTA_HEIGHT + SHADOW, RECTA_WIDTH, PIECE_BOARD_HEIGHT);
        boardShadow.setArcHeight(15);
        boardShadow.setArcWidth(15);
        boardShadow.setFill(Color.GRAY);
        pieceBoard.getChildren().add(boardShadow);
        Rectangle board = new Rectangle(WHITE_EDGE, 2 * WHITE_EDGE + RECTA_HEIGHT, RECTA_WIDTH, PIECE_BOARD_HEIGHT);
        board.setArcHeight(15);
        board.setArcWidth(15);
        board.setFill(BLACKBLUE);
        pieceBoard.getChildren().add(board);
        //
        Rectangle piecePlaceShadow = new Rectangle(X_PIECE_PLACE - 3, Y_PIECE_PLACE - 3, 5 * STAR_WIDTH + 3, 5 * STAR_HEIGHT + 3);
        piecePlaceShadow.setArcHeight(15);
        piecePlaceShadow.setArcWidth(15);
        piecePlaceShadow.setFill(BLACKBLUE.brighter().brighter());
        pieceBoard.getChildren().add(piecePlaceShadow);
        Rectangle piecePlace = new Rectangle(X_PIECE_PLACE, Y_PIECE_PLACE, 5 * STAR_WIDTH, 5 * STAR_HEIGHT);
        piecePlace.setArcHeight(15);
        piecePlace.setArcWidth(15);
        piecePlace.setFill(BLACKBLUE);
        piecePlace.setStroke(BLACKBLUE.darker().darker());
        pieceBoard.getChildren().add(piecePlace);
        // set empty stars
        pieceBoardStars[0] = new Star[5];
        pieceBoardStars[1] = new Star[4];
        pieceBoardStars[2] = new Star[5];
        pieceBoardStars[3] = new Star[4];
        pieceBoardStars[4] = new Star[5];
        double starX, starY;
        for (int i = 0; i < 5; i++) {
            starY = Y_PIECE_PLACE + (2*i+1) * STAR_HEIGHT / 2;
            for (int j = 0; j < pieceBoardStars[i].length; j++) {
                if (i % 2 == 0)
                    starX = X_PIECE_PLACE + (2 * j + 1) / 2.0 * STAR_WIDTH;
                else starX = X_PIECE_PLACE + (j + 1) * STAR_WIDTH;
                pieceBoardStars[i][j] = new Star(starX, starY, STAR_WIDTH);
                pieceBoardStars[i][j].setEmptyStar();
                pieceBoard.getChildren().add(pieceBoardStars[i][j]);
            }
        }
        //set select color buttons
        double startX,startY;

        pieces[0] = new VisualPiece[2];
        pieces[1] = new VisualPiece[2];
        pieces[2] = new VisualPiece[2];
        pieces[3] = new VisualPiece[1];

        selects[0] = new Button[2];
        selects[1] = new Button[2];
        selects[2] = new Button[2];
        selects[3] = new Button[1];
        for (int i = 0; i < 4; i++) {
            startY = Y_PIECE_PLACE + i * STAR_HEIGHT * (4.0 / 3);
            for (int j = 0; j < pieces[i].length; j++) {
                startX = X_PIECE_PLACE + (5 + j) * STAR_WIDTH + 2;
                pieces[i][j] = new VisualPiece(0 ,0 , STAR_WIDTH/3);
                selects[i][j] = new Button();
                selects[i][j].setPrefSize(STAR_WIDTH-5,STAR_HEIGHT-5);
                selects[i][j].setLayoutX(startX);
                selects[i][j].setLayoutY(startY);
                selects[i][j].setGraphic(pieces[i][j]);
                selects[i][j].setBackground(new Background(new BackgroundFill(new Color(0,0,0,0),new CornerRadii(0), Insets.EMPTY)));
                pieceBoard.getChildren().add(selects[i][j]);
            }
        }
        pieces[0][0].setPiece(new Piece('r'));
        pieces[0][1].setPiece(new Piece('b'));
        pieces[1][0].setPiece(new Piece('o'));
        pieces[1][1].setPiece(new Piece('i'));
        pieces[2][0].setPiece(new Piece('y'));
        pieces[2][1].setPiece(new Piece('p'));
        pieces[3][0].setPiece(new Piece('g'));
        selects[0][0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });


    }


    // FIXME Task 8 (CR): Implement a basic playable IQ Stars game in JavaFX that only allows pieces to be placed in valid places

    // FIXME Task 9 (D): Implement challenges (you may use the set of challenges in the Games class)

    // FIXME Task 11 (HD): Implement hints (should become visible when the user presses '/' -- see gitlab issue for details)

    // FIXME Task 12 (HD): Generate interesting challenges (each challenge must have exactly one solution)




    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("IQ Stars");
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);


        // set pieceBoard
        root.getChildren().add(pieceBoard);
        initializePieceBoard();

        // set backboard
        root.getChildren().add(blankBoard);
        initializeBlankBoard();







        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
