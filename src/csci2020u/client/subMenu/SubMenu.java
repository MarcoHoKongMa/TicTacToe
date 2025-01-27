package csci2020u.client.subMenu;

import csci2020u.client.clientConnection.Game;
import csci2020u.client.clientInterface.Main;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class instantiates all of the sub menu elements and handles all
 * of the sub menu elements and interactions.
 */

public class SubMenu {
    // SubMenu Class Parameters
    private static Button playAgainButton;
    private static Button backButtonAbout;
    protected static Button[] buttons;
    public static ButtonHandler[] buttonHandlers = new ButtonHandler[9];
    public static Game game;

    // Constructor
    public SubMenu(Stage primaryStage, Canvas[] canvasArray) {
        game = new Game();
        playAgainButton = new Button("PLAY AGAIN");
        backButtonAbout = new Button("Back to Menu");

        //  BUTTONS FOR ALL POSSIBLE CHOICES
        // Buttons from index 0-2 represent buttons top left, top center, top right, respectively
        // Buttons from index 3-5 represent buttons center left, center, center right, respectively
        // Buttons from index 6-8 represent buttons bottom left, bottom center, bottom right, respectively
        buttons = new Button[9];
        for (int i=0; i<buttons.length; i++){
            buttons[i] = new Button();
            buttons[i].setPrefWidth(100);
            buttons[i].setPrefHeight(100);
        }
        //Adding Buttons to Grid (initially)
        int buttonIndex = 0;
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                Main.buttonGrid.add(buttons[buttonIndex], j, i);
                buttonIndex++;
            }
        }

//------IN GAME BUTTON HANDLERS

        //--CHOICE BUTTON HANDLERS
        int a = 0;
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                buttonHandlers[a] = new ButtonHandler(a, row, col, canvasArray);
                a++;
            }
        }

        // PLAY AGAIN BUTTON
        playAgainButton.setOnAction(actionEvent -> {

            //Switches to LOADING SCENE or something in between to reset everything
            primaryStage.setScene(Main.menuScene);
            primaryStage.show();

            //Clear choices MAP
            Main.choices.clear();

            //  Remove all the canvases and Re-enable the buttons in the Grid
            GraphicsContext gc;
            for (int i=0; i < buttons.length; i++){
                Main.buttonGrid.getChildren().remove(canvasArray[i]);
                buttons[i].setDisable(false);
                gc = canvasArray[i].getGraphicsContext2D();
                gc.clearRect(0, 0, canvasArray[i].getWidth(), canvasArray[i].getHeight());
            }

            try {
                game.end();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

//------BACK BUTTON HANDLERS

        // ABOUT BACK BUTTON
        backButtonAbout.setOnAction(actionEvent -> {

            //Switches to mainScene
            primaryStage.setScene(Main.menuScene);
            primaryStage.show();
        });
    }

    /**
     * Getter function to return the playAgainButton.
     * @return Button.
     */
    public Button getPlayAgain() {
        return playAgainButton;
    }

    /**
     * Getter function to return the backButtonAbout.
     * @return Button.
     */
    public Button getBackButtonAbout() {
        return backButtonAbout;
    }
}
