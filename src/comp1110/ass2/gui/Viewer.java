package comp1110.ass2.gui;

import comp1110.ass2.Board;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * A very simple viewer for game states in the IQ-Stars game.
 * <p>
 * NOTE: This class is separate from your main game class. This
 * class does not play a game, it just illustrates various game
 * states.
 */
public class Viewer extends Application {

    /* board layout */
    private static final int VIEWER_WIDTH = 720;
    private static final int VIEWER_HEIGHT = 480;

    private static final double STAR_WIDTH = 700 * (20.0 / 21) / 7;
    private static final double MARGIN = 50.0 / 3;

    private final Group root = new Group();
    private final Group controls = new Group();
    private TextField textField;

    static Star[][] stars = new Star[4][];



    /**
     * Draw a game state in the window, removing any previously drawn one
     *
     * @param gameStateString A valid game state string
     */
    void makeGameState(String gameStateString) {
        Board board = new Board();
        board.setPuzzle(gameStateString);
        char[][] color = board.getColors();
        double centerX, centerY;

        for (int i = 0; i < color.length; i++) {

            centerY = 10 + MARGIN + (2 * i + 1) / 2.0 * (Math.sqrt(3) / 2) * STAR_WIDTH;

            for (int j = 0; j < color[i].length; j++) {

                if (i % 2 == 0)
                    centerX = 10 + MARGIN + (2 * j + 1) / 2.0 * STAR_WIDTH;
                else centerX = 10 + MARGIN + (j + 1) * STAR_WIDTH;

                if (color[i][j] >= 'a' && color[i][j] <= 'z')
                    if (color[i][j] == 'n')
                        stars[i][j].setEmptyStar();
                    else
                        stars[i][j].setPieceStar(color[i][j]);
                if (color[i][j] >= 'A' && color[i][j] <= 'Z')
                    stars[i][j].setWizardStar((char)(color[i][j] + 32));

            }
            
        }
        
        // FIXME Task 5 (CR): implement the simple game state viewer
    }

    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label label1 = new Label("Game State:");
        textField = new TextField();
        textField.setPrefWidth(300);
        Button button = new Button("Refresh");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                makeGameState(textField.getText());
                textField.clear();
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("IQ Stars Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        // background
        Rectangle board = new Rectangle(10, 10, 700, 364);
        board.setArcHeight(12);
        board.setArcWidth(12);
        board.setFill(new Color(0.172, 0.172, 0.27, 1.0));
        root.getChildren().add(board);

        // Stars
        stars[0] = new Star[7];
        stars[1] = new Star[6];
        stars[2] = new Star[7];
        stars[3] = new Star[6];
        double centerY, centerX;
        for (int i = 0; i < stars.length; i++) {

            centerY = 10 + MARGIN + (2 * i + 1) / 2.0 * (Math.sqrt(3) / 2) * STAR_WIDTH;

            for (int j = 0; j < stars[i].length; j++) {

                if (i % 2 == 0)
                    centerX = 10 + MARGIN + (2 * j + 1) / 2.0 * STAR_WIDTH;
                else centerX = 10 + MARGIN + (j + 1) * STAR_WIDTH;

                stars[i][j] = new Star(centerX, centerY, STAR_WIDTH);
                stars[i][j].setEmptyStar();
                root.getChildren().add(stars[i][j]);
            }

        }

        root.getChildren().add(controls);

        makeControls();

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
