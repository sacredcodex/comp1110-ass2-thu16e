package comp1110.ass2.gui;

import comp1110.ass2.Games;
import comp1110.ass2.Location;
import comp1110.ass2.Piece;
import comp1110.ass2.Puzzle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;
import java.util.Set;


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
    private final comp1110.ass2.Board gameBoard = new comp1110.ass2.Board();
    private final Star[][] stars = new Star[4][];//place on blank board

    private final Group pieceBoard = new Group();
    private final Star[][] pieceBoardStars = new Star[5][];//place on piece board
    private final Button[][] selects = new Button[4][];
    private Piece piecePreview;
    private VisualPiece visualPiecePriview;

    private final Group controlBoard = new Group();
    private final Slider difficultyControl = new Slider();
    // hint
    private Button button3;
    private String hintPieceStr = "";
    private boolean showHint=false;
    private int hintUse;

    private Timeline timeline;
    private int tmp = 0;
    private Label timer;

    private final Group helpBoard = new Group();
    private Label help1,help2;
    private final String[] help = new String[]{
            "How to start?",
            """
1. Select difficulty by slider. There are 5 levels:

   1   Starter, provide 4 or 5 pieces on the board.

   2   Junior, provide 3 or 2 pieces on the board.

   3   Expert, provide 2 pieces on the board.

   4   Master, provide 1 piece on the board.

   5   Wizard, provide a few stars on the board.

2. Both "Start" and "Random" can start a game,

   there will be more puzzles if you use "Random".


                                   1 / 2""",
            "How to play?",
            """
1. Click and select the piece you want.

2. Drag and place it to the right position.

3. Use mouse wheel to rotate the piece.

4. If you want to remove the piece, click the

   piece on board or click the pieces below.

5. Press '?' or button "hint" for hint, it will

   show you where you should place the piece.

   (except all pieces cannot be placed correctly)


                                   2 / 2"""
    };

    private void initializeBlankBoard(){
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

    private void initializePieceBoard(){
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
        // set stars
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
        movePiece(selects[0][0], 'r');
        movePiece(selects[1][0], 'o');
        movePiece(selects[2][0], 'y');
        movePiece(selects[3][0], 'g');
        movePiece(selects[0][1], 'b');
        movePiece(selects[1][1], 'i');
        movePiece(selects[2][1], 'p');

        visualPiecePriview = new VisualPiece(X_PIECE_PLACE + 2.5 * STAR_WIDTH, Y_PIECE_PLACE + 2.5 * STAR_HEIGHT, STAR_WIDTH);
        pieceBoard.getChildren().add(visualPiecePriview);
    }

    private void initializeControlBorad(){
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
        Rectangle rec1 = new Rectangle(774,554,100,40);
        rec1.setArcHeight(7);
        rec1.setArcWidth(7);
        rec1.setFill(new Color(0.97058825F, 0.5647059F, 0.49019608F,1.0));
        controlBoard.getChildren().add(rec1);
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
                int num;
                do {
                    gameBoard.setEmpty();
                    num = difficulty * 24 - 24 + random.nextInt(24);
                }while (Games.ALL_CHALLENGES[num].equals(gameBoard.getPuzzle()));
                gameBoard.setPuzzle(Games.ALL_CHALLENGES[num]);
                gameBoard.setSolution(Games.ALL_CHALLENGES_SOLUTIONS[num].substring(0,28));
                setBoardStars();

                initializeStart();

            }
        });
        button1.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button1.setLayoutX(772);
                button1.setLayoutY(552);
            }
        });
        button1.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button1.setLayoutX(770);
                button1.setLayoutY(550);
            }
        });

        Rectangle rec2 = new Rectangle(644,624,100,40);
        rec2.setArcHeight(7);
        rec2.setArcWidth(7);
        rec2.setFill(new Color(0.97058825F, 0.5647059F, 0.49019608F,1.0));
        controlBoard.getChildren().add(rec2);
        Button button2 = new Button("Restart");
        button2.setPrefSize(100, 40);
        button2.setLayoutX(640);
        button2.setLayoutY(620);
        button2.setFont(new Font(20));
        button2.setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL,new CornerRadii(7), Insets.EMPTY)));
        controlBoard.getChildren().add(button2);
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameBoard.setEmpty();
                if (!gameBoard.getPuzzle().equals(""))
                gameBoard.setPuzzle(gameBoard.getPuzzle());
                setBoardStars();
                resetPiecePreview();
            }
        });
        button2.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button2.setLayoutX(642);
                button2.setLayoutY(622);
            }
        });
        button2.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button2.setLayoutX(640);
                button2.setLayoutY(620);
            }
        });

        Rectangle rec3 = new Rectangle(774,624,100,40);
        rec3.setArcHeight(7);
        rec3.setArcWidth(7);
        rec3.setFill(new Color(0.97058825F, 0.5647059F, 0.49019608F,1.0));
        controlBoard.getChildren().add(rec3);
        button3 = new Button("Hint("+hintUse+")");
        button3.setPrefSize(100, 40);
        button3.setLayoutX(770);
        button3.setLayoutY(620);
        button3.setFont(new Font(20));
        button3.setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL,new CornerRadii(7), Insets.EMPTY)));
        controlBoard.getChildren().add(button3);
        button3.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                    button3.setLayoutX(772);
                    button3.setLayoutY(622);
                    showHint();
                }
            }
        });
        button3.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                    button3.setLayoutX(770);
                    button3.setLayoutY(620);
                    hideHint();
                }
            }
        });

        Rectangle rec4 = new Rectangle(644,554,100,40);
        rec4.setArcHeight(7);
        rec4.setArcWidth(7);
        rec4.setFill(new Color(0.97058825F, 0.5647059F, 0.49019608F,1.0));
        controlBoard.getChildren().add(rec4);
        Button button4 = new Button("Radom");
        button4.setPrefSize(100, 40);
        button4.setLayoutX(640);
        button4.setLayoutY(550);
        button4.setFont(new Font(20));
        button4.setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL,new CornerRadii(7), Insets.EMPTY)));
        controlBoard.getChildren().add(button4);
        button4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameBoard.setEmpty();
                int difficulty = (int) difficultyControl.getValue();
                Random random = new Random();
                if (difficulty == 4){
                    int num = random.nextInt(32);
                    gameBoard.setPuzzle(Puzzle.masters[num]);
                    gameBoard.setSolution(Puzzle.getMasterSolution(num));
                }else if (difficulty == 3){
                    int num = random.nextInt(2308);
                    gameBoard.setPuzzle(Puzzle.experts[num]);
                    gameBoard.setSolution(Puzzle.getExpertSolution(num));
                }else if (difficulty == 2){
                    int num = random.nextInt(2308);
                    gameBoard.setPuzzle(Puzzle.experts[num]);
                    gameBoard.setSolution(Puzzle.getExpertSolution(num));
                    char color = gameBoard.getUnusedColor().toString().charAt(1);
                    int index = gameBoard.getSolution().indexOf(color);
                    gameBoard.placePiece(gameBoard.getSolution().substring(index,index+4));
                    gameBoard.setPuzzle(gameBoard.toString());
                }else if (difficulty == 1){
                    int num = random.nextInt(2308);
                    gameBoard.setPuzzle(Puzzle.experts[num]);
                    gameBoard.setSolution(Puzzle.getExpertSolution(num));
                    char color = gameBoard.getUnusedColor().toString().charAt(1);
                    int index = gameBoard.getSolution().indexOf(color);
                    gameBoard.placePiece(gameBoard.getSolution().substring(index,index+4));
                    color = gameBoard.getUnusedColor().toString().charAt(1);
                    index = gameBoard.getSolution().indexOf(color);
                    gameBoard.placePiece(gameBoard.getSolution().substring(index,index+4));
                    if (random.nextBoolean()){
                        color = gameBoard.getUnusedColor().toString().charAt(1);
                        index = gameBoard.getSolution().indexOf(color);
                        gameBoard.placePiece(gameBoard.getSolution().substring(index,index+4));
                    }
                    gameBoard.setPuzzle(gameBoard.toString());
                }else if (difficulty == 5){
                    String wizardPuzzle = Puzzle.wizards[random.nextInt(1017)];
                    gameBoard.setPuzzle(wizardPuzzle.substring(wizardPuzzle.indexOf('W')));
                    gameBoard.setSolution(Puzzle.solutions[Integer.parseInt(wizardPuzzle.substring(0,wizardPuzzle.indexOf('W')))].substring(0,28));

                }


                //gameBoard.setPuzzle(gameBoard.getPuzzle());
                setBoardStars();
                initializeStart();
            }
        });
        button4.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button4.setLayoutX(642);
                button4.setLayoutY(552);
            }
        });
        button4.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button4.setLayoutX(640);
                button4.setLayoutY(550);
            }
        });
    }

    private void setTimer(){
        timer = new Label("         00 : 00 . 0     ");
        timer.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(7),Insets.EMPTY)));
        timer.setFont(new Font(36));
        timer.setLayoutX(610);
        timer.setLayoutY(392);
        timeline = new Timeline(new KeyFrame(Duration.millis(100), actionEvent -> timeLabel()));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    private void setHelp(){
        Rectangle rectangle1 = new Rectangle(615, 15, 315, 370);
        rectangle1.setFill(new Color(0,0,0,0.3));
        helpBoard.getChildren().add(rectangle1);
        Rectangle rectangle2 = new Rectangle(610, 10, 315, 370);
        rectangle2.setFill(Color.LEMONCHIFFON);
        helpBoard.getChildren().add(rectangle2);
        rectangle2.setFill(Color.LEMONCHIFFON);
        help1 = new Label(this.help[0]);
        help1.setLayoutY(30);
        help1.setLayoutX(640);
        help1.setFont(new Font(36));
        helpBoard.getChildren().add(help1);
        help2 = new Label(this.help[1]);
        help2.setLayoutY(90);
        help2.setLayoutX(620);
        help2.setFont(new Font(12));
        helpBoard.getChildren().add(help2);
        Button button = new Button();
        button.setLayoutY(10);
        button.setLayoutX(610);
        button.setPrefWidth(315);
        button.setPrefHeight(370);
        button.setBackground(new Background(new BackgroundFill(new Color(0,0,0,0), new CornerRadii(0), Insets.EMPTY)));
        button.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                changeHelp();
            }
        });
        helpBoard.getChildren().add(button);
    }

    private void setPiece(char color){
        piecePreview = new Piece(color);
        visualPiecePriview.setPiece(piecePreview);
    }

    // rotate selected Piece
    private void rotatePiece(int rotation){
        if (piecePreview != null) {
            piecePreview.rotatePiece(rotation);
            visualPiecePriview.setPiece(piecePreview);
        }
    }

    // show the game state
    private void setBoardStars(){
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

        Set<Character> unusedColor = gameBoard.getUnusedColor();
        if (unusedColor.contains('r'))
            selects[0][0].setOpacity(1.0);
        else selects[0][0].setOpacity(0.3);
        if (unusedColor.contains('o'))
            selects[1][0].setOpacity(1.0);
        else selects[1][0].setOpacity(0.3);
        if (unusedColor.contains('y'))
            selects[2][0].setOpacity(1.0);
        else selects[2][0].setOpacity(0.3);
        if (unusedColor.contains('g'))
            selects[3][0].setOpacity(1.0);
        else selects[3][0].setOpacity(0.3);
        if (unusedColor.contains('b'))
            selects[0][1].setOpacity(1.0);
        else selects[0][1].setOpacity(0.3);
        if (unusedColor.contains('i'))
            selects[1][1].setOpacity(1.0);
        else selects[1][1].setOpacity(0.3);
        if (unusedColor.contains('p'))
            selects[2][1].setOpacity(1.0);
        else selects[2][1].setOpacity(0.3);
        if (unusedColor.isEmpty() && !showHint )
            timeline.stop();




    }

    // clear some information when start a new game
    private void initializeStart(){
        showHint = false;
        hintPieceStr = "";
        hintUse = 0;
        button3.setText("Hint("+hintUse+")");
        tmp = -1;
        timeLabel();
        timeline.play();
        resetPiecePreview();
    }

    private void showHint() {
        if (!gameBoard.getUnusedColor().isEmpty() && !showHint && gameBoard.getSolution().length() == 28) {
            showHint = true;
            if (hintPieceStr.length() == 4 && gameBoard.isPieceValid(hintPieceStr)){
                gameBoard.placePiece(hintPieceStr);
                setBoardStars();
                switch (hintPieceStr.charAt(0)) {
                    case 'r' -> selects[0][0].setOpacity(1.0);
                    case 'o' -> selects[1][0].setOpacity(1.0);
                    case 'y' -> selects[2][0].setOpacity(1.0);
                    case 'g' -> selects[3][0].setOpacity(1.0);
                    case 'b' -> selects[0][1].setOpacity(1.0);
                    case 'i' -> selects[1][1].setOpacity(1.0);
                    case 'p' -> selects[2][1].setOpacity(1.0);
                }
            }else {
                hintUse = hintUse + 1;
                if (hintUse > 9)
                    hintUse = 9;
                button3.setText("Hint(" + hintUse + ")");
                if (piecePreview != null)
                    switch (piecePreview.getColor()) {
                        case 'r' -> hintPieceStr = gameBoard.getSolution().substring(0, 4);
                        case 'o' -> hintPieceStr = gameBoard.getSolution().substring(4, 8);
                        case 'y' -> hintPieceStr = gameBoard.getSolution().substring(8, 12);
                        case 'g' -> hintPieceStr = gameBoard.getSolution().substring(12, 16);
                        case 'b' -> hintPieceStr = gameBoard.getSolution().substring(16, 20);
                        case 'i' -> hintPieceStr = gameBoard.getSolution().substring(20, 24);
                        case 'p' -> hintPieceStr = gameBoard.getSolution().substring(24, 28);
                    }
                if (hintPieceStr.length() != 4 || !gameBoard.isPieceValid(hintPieceStr))
                    for (int i = 0; i < 7; i++) {
                        hintPieceStr = gameBoard.getSolution().substring(4 * i, 4 * i + 4);
                        if (gameBoard.isPieceValid(hintPieceStr))
                            break;
                        else
                            hintPieceStr = "";
                    }
            }
            if (!hintPieceStr.equals("")) {
                gameBoard.placePiece(hintPieceStr);
                setBoardStars();
                switch (hintPieceStr.charAt(0)) {
                    case 'r' -> selects[0][0].setOpacity(1.0);
                    case 'o' -> selects[1][0].setOpacity(1.0);
                    case 'y' -> selects[2][0].setOpacity(1.0);
                    case 'g' -> selects[3][0].setOpacity(1.0);
                    case 'b' -> selects[0][1].setOpacity(1.0);
                    case 'i' -> selects[1][1].setOpacity(1.0);
                    case 'p' -> selects[2][1].setOpacity(1.0);
                }
            }
        }
    }

    private void hideHint(){
        if (showHint && hintPieceStr.length() == 4) {
            gameBoard.removePiece(hintPieceStr);
            setBoardStars();
        }
        showHint = false;
    }

    // set timer output
    private void timeLabel(){
        tmp++;
        int a,b,c;
        c = tmp % 10;
        b = tmp / 10;
        a = b / 60;
        b = b % 60;
        String output = "         ";
        if (a == 0)
            output = output + "00";
        else if (a < 10)
            output = output + "0" + a;
        else if (a < 59)
            output = output + a;

        output = output + " : ";

        if (b == 0)
            output = output + "00";
        else if (b < 10)
            output = output + "0" + b;
        else
            output = output + b;

        output = output + " . "+c+"     ";

        if (a > 60)
            output = "         60 : 00 . 0     ";
        timer.setText(output);
    }

    // let selected piece movable
    private void movePiece(Node piece){
        //movePiecePreview();
        double[] startX = new double[1];
        double[] startY = new double[1];
        // place the piece, let piece move with mouse
        piece.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                startX[0] = mouseEvent.getSceneX() - visualPiecePriview.x;
                startY[0] = mouseEvent.getSceneY() - visualPiecePriview.y;
            }
        });
        piece.addEventFilter(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                visualPiecePriview.setX(mouseEvent.getSceneX() - startX[0]);
                visualPiecePriview.setY(mouseEvent.getSceneY() - startY[0]);
                visualPiecePriview.setPiece(piecePreview);
            }
        });
        piece.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
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
                    hintPieceStr = "";
                    gameBoard.placePiece(piecePreview, center);
                    setBoardStars();
                    resetPiecePreview();
                }
            }
        });
    }

    // drag select button to move the piece
    private void movePiece(Button piece, char color){
        //movePiecePreview();
        double[] startX = new double[1];
        double[] startY = new double[1];
        // place the piece, let piece move with mouse
        piece.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (gameBoard.getUnusedColor().contains(color)) {
                    startX[0] = mouseEvent.getSceneX();
                    startY[0] = mouseEvent.getSceneY();
                    visualPiecePriview.setX(mouseEvent.getSceneX());
                    visualPiecePriview.setY(mouseEvent.getSceneY());
                    setPiece(color);
                }
            }
        });
        piece.addEventFilter(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (gameBoard.getUnusedColor().contains(color)) {
                    visualPiecePriview.setX(mouseEvent.getSceneX());
                    visualPiecePriview.setY(mouseEvent.getSceneY());
                    visualPiecePriview.setPiece(piecePreview);
                }
            }
        });
        piece.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // click
                if (Math.abs(mouseEvent.getSceneX()-startX[0]) < 0.2 * STAR_WIDTH && Math.abs(mouseEvent.getSceneY()-startY[0]) < 0.2 * STAR_HEIGHT){
                    if (gameBoard.getUnusedColor().contains(color)){
                        visualPiecePriview.setX(X_PIECE_PLACE + 2.5 * STAR_WIDTH);
                        visualPiecePriview.setY(Y_PIECE_PLACE + 2.5 * STAR_HEIGHT);
                        setPiece(color);
                    }else if (gameBoard.getPuzzle().indexOf(color) == -1){
                        gameBoard.removePiece(color);
                        setBoardStars();
                    }
                }else if (piecePreview != null){
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
                    int x, y;
                    x = -1;
                    y = (int) Math.floor((visualPiecePriview.y - WHITE_EDGE - MARGIN) / STAR_HEIGHT);
                    if (y == 0 || y == 2) {
                        x = (int) ((visualPiecePriview.x - WHITE_EDGE - MARGIN) / STAR_WIDTH);
                    }
                    if (y == 1 || y == 3) {
                        x = (int) Math.floor((visualPiecePriview.x - WHITE_EDGE - MARGIN) / STAR_WIDTH - 0.5);
                    }
                    Location center = new Location(x, y);
                    if (center.onBoard() && gameBoard.isPieceValid(piecePreview, center)) {
                        hintPieceStr = "";
                        gameBoard.placePiece(piecePreview, center);
                        setBoardStars();
                        resetPiecePreview();
                    }
                }
            }
        });
    }

    // clear piecePreview and put visualPiecePreview back
    private void resetPiecePreview(){
        visualPiecePriview.getChildren().setAll();
        piecePreview = null;
    }

    // there are two pages of help, help will change after clicking help
    private void changeHelp(){
        if (help1.getText().equals(help[0])){
            help1.setText(help[2]);
            help2.setText(help[3]);
        }else{
            help1.setText(help[0]);
            help2.setText(help[1]);
        }
    }

    // remove piece from game board
    private void removePiece() {
        final char[] color = {'n'};
        final double[] centerX = new double[1];
        final double[] centerY = new double[1];
        double[] startX = new double[1];
        double[] startY = new double[1];
        blankBoard.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int x,y;
                x = -1;
                y = (int) Math.floor((mouseEvent.getSceneY() - WHITE_EDGE - MARGIN) / STAR_HEIGHT);
                if (y == 0 || y == 2) {
                    x= (int)((mouseEvent.getSceneX() - WHITE_EDGE - MARGIN) / STAR_WIDTH);
                }
                if (y == 1 || y == 3) {
                    x= (int)Math.floor((mouseEvent.getSceneX() - WHITE_EDGE - MARGIN) / STAR_WIDTH - 0.5);
                }
                Location location = new Location(x,y);
                if (location.onBoard())
                    color[0] = gameBoard.getColor(location.toString());
                if (color[0] != 'n' && !gameBoard.getUnusedColor().contains(color[0])) {
                    if (gameBoard.getPuzzle().indexOf(color[0]) == -1 || gameBoard.getPuzzle().indexOf(color[0]) > gameBoard.getPuzzle().indexOf('W')) {
                        piecePreview = new Piece(color[0]);
                        String state = gameBoard.toString();
                        piecePreview.rotatePiece(state.charAt(state.indexOf(color[0])+1));
                        Location loc = piecePreview.getCenter(state.substring(state.indexOf(color[0]), state.indexOf(color[0])+4));
                        centerX[0] = loc.getSceneX();
                        centerY[0] = loc.getSceneY();
                        visualPiecePriview.setX(centerX[0]);
                        visualPiecePriview.setY(centerY[0]);
                        visualPiecePriview.setPiece(piecePreview);
                        gameBoard.removePiece(color[0]);
                        setBoardStars();
                        startX[0] = mouseEvent.getSceneX() - visualPiecePriview.x;
                        startY[0] = mouseEvent.getSceneY() - visualPiecePriview.y;
                    }
                }
            }
        });
        blankBoard.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (gameBoard.getUnusedColor().contains(color[0])) {
                    visualPiecePriview.setX(mouseEvent.getSceneX()-startX[0]);
                    visualPiecePriview.setY(mouseEvent.getSceneY()-startY[0]);
                    visualPiecePriview.setPiece(piecePreview);
                }
            }
        });
        blankBoard.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (Math.abs(mouseEvent.getSceneX()-startX[0]-centerX[0]) < 0.5 * STAR_WIDTH && Math.abs(mouseEvent.getSceneY()-startY[0]-centerY[0]) < 0.5 * STAR_HEIGHT){
                    // click
                    if (Math.abs(mouseEvent.getSceneX()-startX[0]-centerX[0]) < 0.2 * STAR_WIDTH && Math.abs(mouseEvent.getSceneY()-startY[0]-centerY[0]) < 0.2 * STAR_HEIGHT){
                        resetPiecePreview();
                        setBoardStars();
                    }
                    //do nothing
                }else {
                    //place somewhere else
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
                    int x, y;
                    x = -1;
                    y = (int) Math.floor((visualPiecePriview.y - WHITE_EDGE - MARGIN) / STAR_HEIGHT);
                    if (y == 0 || y == 2) {
                        x = (int) ((visualPiecePriview.x - WHITE_EDGE - MARGIN) / STAR_WIDTH);
                    }
                    if (y == 1 || y == 3) {
                        x = (int) Math.floor((visualPiecePriview.x - WHITE_EDGE - MARGIN) / STAR_WIDTH - 0.5);
                    }
                    Location center = new Location(x, y);
                    if (center.onBoard() && piecePreview != null && gameBoard.isPieceValid(piecePreview, center)) {
                        hintPieceStr = "";
                        gameBoard.placePiece(piecePreview, center);
                        setBoardStars();
                        resetPiecePreview();
                    }
                }
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

        // set blankboard:
        root.getChildren().add(blankBoard);
        initializeBlankBoard();

        // set controlBoard: 4 buttons
        root.getChildren().add(controlBoard);
        initializeControlBorad();

        // timer
        setTimer();
        root.getChildren().add(timer);

        // help
        setHelp();
        root.getChildren().add(helpBoard);

        // set pieceBoard: select piece here
        root.getChildren().add(pieceBoard);
        initializePieceBoard();

        // keep pressing ? for hint, Q and E to rotate piece
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.SLASH) {
                    showHint();
                    keyEvent.consume();// when starting a new game with "/" pressed, the new game wouldl remove a puzzle piece
                }
                if (keyEvent.getCode() == KeyCode.Q)
                    rotatePiece(5);
                if (keyEvent.getCode() == KeyCode.E)
                    rotatePiece(1);
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.SLASH){
                    hideHint();
                }
            }
        });

        // let the piece movable
        movePiece(visualPiecePriview);

        removePiece();

        // rotate piece with scroll
        scene.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent scrollEvent) {
                if (scrollEvent.getDeltaY()>0)
                    rotatePiece(5);
                else
                    rotatePiece(1);
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
