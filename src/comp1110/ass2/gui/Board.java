package comp1110.ass2.gui;

import comp1110.ass2.Games;
import comp1110.ass2.Location;
import comp1110.ass2.Piece;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Random;


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
    private comp1110.ass2.Board gameBoard = new comp1110.ass2.Board();
    private Star[][] stars = new Star[4][];//place on blank board

    private final Group pieceBoard = new Group();
    private Star[][] pieceBoardStars = new Star[5][];//place on piece board
    private final Button[][] selects = new Button[4][];
    private Piece piecePreview;
    private VisualPiece visualPiecePriview;

    private final Group controlBoard = new Group();
    private Slider difficultyControl = new Slider();




    public void initializeBlankBoard(){
        // back board
        Rectangle boardShadow = new Rectangle(WHITE_EDGE + SHADOW, WHITE_EDGE + SHADOW, RECTA_WIDTH, RECTA_HEIGHT);
        boardShadow.setArcWidth(15);
        boardShadow.setArcHeight(15);
        boardShadow.setFill(new Color(0,0,0,0.5));
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
        boardShadow.setFill(new Color(0,0,0,0.5));
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
                pieceBoardStars[i][j].setStroke(BLACKBLUE.darker());
                pieceBoard.getChildren().add(pieceBoardStars[i][j]);
            }
        }


        //set select color buttons
        double startX,startY;
        VisualPiece[][] pieces = new VisualPiece[4][];
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
                selects[i][j].setBackground(new Background(new BackgroundFill(new Color(0,0,0,0.1),new CornerRadii(5), Insets.EMPTY)));
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
                if (gameBoard.getUnusedColor().contains('r')){
                    setPiece('r');
                }else if (gameBoard.getPuzzle().indexOf('r') == -1){
                    gameBoard.removePiece('r');
                    setBoardStars();
                }
            }
        });
        selects[1][0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (gameBoard.getUnusedColor().contains('o')){
                    setPiece('o');
                }else if (gameBoard.getPuzzle().indexOf('o') == -1){
                    gameBoard.removePiece('o');
                    setBoardStars();
                }
            }
        });
        selects[2][0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (gameBoard.getUnusedColor().contains('y')){
                    setPiece('y');
                }else if (gameBoard.getPuzzle().indexOf('y') == -1){
                    gameBoard.removePiece('y');
                    setBoardStars();
                }
            }
        });
        selects[3][0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (gameBoard.getUnusedColor().contains('g')){
                    setPiece('g');
                }else if (gameBoard.getPuzzle().indexOf('g') == -1){
                    gameBoard.removePiece('g');
                    setBoardStars();
                }
            }
        });
        selects[0][1].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (gameBoard.getUnusedColor().contains('b')){
                    setPiece('b');
                }else if (gameBoard.getPuzzle().indexOf('b') == -1){
                    gameBoard.removePiece('b');
                    setBoardStars();
                }
            }
        });
        selects[1][1].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (gameBoard.getUnusedColor().contains('i')){
                    setPiece('i');
                }else if (gameBoard.getPuzzle().indexOf('i') == -1){
                    gameBoard.removePiece('i');
                    setBoardStars();
                }
            }
        });
        selects[2][1].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (gameBoard.getUnusedColor().contains('p')){
                    setPiece('p');
                }else if (gameBoard.getPuzzle().indexOf('p') == -1){
                    gameBoard.removePiece('p');
                    setBoardStars();
                }
            }
        });


        visualPiecePriview = new VisualPiece(X_PIECE_PLACE + 2.5 * STAR_WIDTH, Y_PIECE_PLACE + 2.5 * STAR_HEIGHT, STAR_WIDTH);
        pieceBoard.getChildren().add(visualPiecePriview);
    }

    public void initializeControlBorad(){
        Rectangle rectangle1 = new Rectangle(615,450,315,240);
        rectangle1.setFill(new Color(0,0,0,0.3));
        rectangle1.setArcHeight(15);
        rectangle1.setArcWidth(15);
        Rectangle rectangle2 = new Rectangle(610, 445, 315, 240);
        rectangle2.setFill(Color.LIGHTSALMON);
        rectangle2.setArcHeight(15);
        rectangle2.setArcWidth(15);
        controlBoard.getChildren().addAll(rectangle1,rectangle2);
        // difficulty slider
        Label label1 = new Label("Difficulty");
        label1.setLayoutX(630);
        label1.setLayoutY(470);
        label1.setFont(new Font(24));

        controlBoard.getChildren().add(label1);
        difficultyControl.setLayoutX(740);
        difficultyControl.setLayoutY(480);
        difficultyControl.setMin(1);
        difficultyControl.setMax(5);
        difficultyControl.setShowTickLabels(true);
        difficultyControl.setShowTickMarks(false);
        difficultyControl.setValue(0);
        difficultyControl.setMajorTickUnit(1);
        difficultyControl.setMinorTickCount(0);
        difficultyControl.setSnapToTicks(true);
        controlBoard.getChildren().add(difficultyControl);
        //button
        Button button1 = new Button("Start");
        button1.setPrefSize(100, 40);
        button1.setLayoutX(770);
        button1.setLayoutY(550);
        button1.setFont(new Font(20));
        button1.setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL,new CornerRadii(7), Insets.EMPTY)));
        controlBoard.getChildren().add(button1);
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int difficulty = (int) difficultyControl.getValue();
                Random random = new Random();
                gameBoard.setEmpty();
                gameBoard.setPuzzle(Games.ALL_CHALLENGES[difficulty * 24 -24 + random.nextInt(24)]);
                setBoardStars();
            }
        });
        Button button2 = new Button("Restart");
        button2.setPrefSize(100, 40);
        button2.setLayoutX(640);
        button2.setLayoutY(550);
        button2.setFont(new Font(20));
        button2.setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL,new CornerRadii(7), Insets.EMPTY)));
        controlBoard.getChildren().add(button2);
    }

    public void setPiece(char color){
        piecePreview = new Piece(color);
        visualPiecePriview.setPiece(piecePreview);
    }

    public void rotatePiece(int rotation){
        if (piecePreview != null) {
            piecePreview.rotatePiece(rotation);
            visualPiecePriview.setPiece(piecePreview);
        }
    }

    public void setBoardStars(){
        char[][] colors = gameBoard.getColors();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < colors[i].length; j++) {
                if (colors[i][j] > 'a' && colors[i][j] < 'z' && colors[i][j] != 'n'){
                    stars[i][j].setPieceStar(colors[i][j]);
                }
                if (colors[i][j] == 'n'){
                    stars[i][j].setEmptyStar();
                }
                if (colors[i][j] > 'A' && colors[i][j] < 'Z'){
                    stars[i][j].setWizardStar((char)(colors[i][j] + 32));
                }
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

        //set controlBoard
        root.getChildren().add(controlBoard);
        initializeControlBorad();

        // set pieceBoard
        root.getChildren().add(pieceBoard);
        initializePieceBoard();

        // Keyboard listener
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.Q)
                    rotatePiece(-1);
                if (keyEvent.getCode() == KeyCode.E)
                    rotatePiece(1);
            }
        });

        //movePiecePreview();
        double[] startX = new double[1];
        double[] startY = new double[1];
        // place the piece, let piece move with mouse
        visualPiecePriview.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                startX[0] = mouseEvent.getSceneX() - visualPiecePriview.x;
                startY[0] = mouseEvent.getSceneY() - visualPiecePriview.y;
            }
        });
        visualPiecePriview.addEventFilter(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                visualPiecePriview.setX(mouseEvent.getSceneX() - startX[0]);
                visualPiecePriview.setY(mouseEvent.getSceneY() - startY[0]);
                visualPiecePriview.setPiece(piecePreview);
            }
        });
        visualPiecePriview.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // On board
                if (visualPiecePriview.x < 0)
                    visualPiecePriview.setX(0);
                if (visualPiecePriview.x > BOARD_WIDTH)
                    visualPiecePriview.setX(BOARD_WIDTH);
                if (visualPiecePriview.y < 0)
                    visualPiecePriview.setY(0);
                if (visualPiecePriview.y > BOARD_WIDTH)
                    visualPiecePriview.setY(BOARD_HEIGHT);

                // toLocation
                int x,y;
                x = -1;
                y = (int) Math.floor((visualPiecePriview.y - WHITE_EDGE - MARGIN) / STAR_HEIGHT);
                if (y == 0 || y == 2) {
                    x= (int)((visualPiecePriview.x - WHITE_EDGE - MARGIN) / STAR_WIDTH);
                }
                if (y == 1 || y == 3) {
                    x= (int)Math.floor((visualPiecePriview.x - WHITE_EDGE - MARGIN) / STAR_WIDTH - 0.5);
                }
                Location center = new Location(x,y);
                if (center.onBoard() && gameBoard.isPieceValid(piecePreview, center)) {
                    gameBoard.placePiece(piecePreview, center);
                    setBoardStars();
                    visualPiecePriview.getChildren().setAll();
                    piecePreview = null;
                    visualPiecePriview.setX(X_PIECE_PLACE + 2.5 * STAR_WIDTH);
                    visualPiecePriview.setY(Y_PIECE_PLACE + 2.5 * STAR_HEIGHT);
                }

            }
        });
        // remove piece from game board
        blankBoard.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int x,y;
                x = -1;
                y = (int) Math.floor((mouseEvent.getSceneY() - WHITE_EDGE - MARGIN) / STAR_HEIGHT);
                if (y == 0 || y == 2) {
                    x= (int)((mouseEvent.getSceneX() - WHITE_EDGE - MARGIN) / STAR_WIDTH);
                }
                if (y == 1 || y == 3) {
                    x= (int)Math.floor((mouseEvent.getSceneX()- WHITE_EDGE - MARGIN) / STAR_WIDTH - 0.5);
                }
                Location click = new Location(x,y);
                if (click.onBoard()){
                    char color = gameBoard.getColor(click.toString());
                    Character.toLowerCase(color);
                    if (gameBoard.getPuzzle().indexOf(color) == -1) {
                        gameBoard.removePiece(color);
                        setBoardStars();
                    }
                }
            }
        });




        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
