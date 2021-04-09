package csci2020u.tictactoe;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SubMenu {
    private static Button playAgainButton;
    private static Button backButtonHowToPlay;
    private static Button backButtonAbout;
    protected static Button[] buttons;
    ButtonHandler[] buttonHandlers = new ButtonHandler[9];
    protected static Game game;

    public SubMenu(Stage primaryStage, Canvas[] canvasArray) {
        game = new Game();
        playAgainButton = new Button("PLAY AGAIN");
        backButtonHowToPlay = new Button("Back to Menu");
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
        String[] buttonNames = { "topLeft", "topCenter", "topRight", "centerLeft", "center", "centerRight",
                "bottomLeft", "bottomCenter", "bottomRight" };
        //--CHOICE BUTTON HANDLERS
        int a = 0;
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                buttonHandlers[a] = new ButtonHandler(a, row, col, canvasArray, buttonNames);
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
            for (int i=0; i < buttons.length; i++){
                Main.buttonGrid.getChildren().remove(canvasArray[i]);
                buttons[i].setDisable(false);
            }

            try {
                game.end();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

//------BACK BUTTON HANDLERS

        // HOW TO PLAY BACK BUTTON
        backButtonHowToPlay.setOnAction(actionEvent -> {

            //Switches to mainScene
            primaryStage.setScene(Main.menuScene);
            primaryStage.show();
        });

        // ABOUT BACK BUTTON
        backButtonAbout.setOnAction(actionEvent -> {

            //Switches to mainScene
            primaryStage.setScene(Main.menuScene);
            primaryStage.show();
        });
    }

    public Button getPlayAgain() {
        return playAgainButton;
    }

    public Button getBackButtonHowToPlay() {
        return backButtonHowToPlay;
    }

    public Button getBackButtonAbout() {
        return backButtonAbout;
    }
}
