package csci2020u.tictactoe;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

public class Main extends Application {
    protected static GridPane buttonGrid;
    protected static BorderPane gameBP;
    protected static Map<String, String> choices;
    protected static Button[] buttons;

    @Override
    public void start(Stage primaryStage) throws Exception{
        // ButtonHandler
        ButtonHandler[] buttonHandlers = new ButtonHandler[9];

        for(int i = 0; i < 9; i++) {
            buttonHandlers[i] = new ButtonHandler();
        }

        //MAP FOR TIC-TAC-TOE LOGIC
        choices = new TreeMap<>();

//------MAIN MENU SCENE SET UP
        //  Main Menu Layout
        VBox menuGrid = new VBox();

        //  Menu Title
        Text menuTitle = new Text("Tic-Tac-Toe");
        menuTitle.setFont(Font.font("Courier New"));

        //  Main Menu Buttons
        Button playButton = new Button("PLAY");
        Button howToPlayButton = new Button("HOW TO PLAY");
        Button aboutButton = new Button("ABOUT");
        Button[] menuButtons = {playButton, howToPlayButton, aboutButton};

        menuGrid.getChildren().add(menuTitle);
        for (Button menuButton : menuButtons) {
            menuButton.setPrefWidth(100);
            menuGrid.getChildren().add(menuButton);
        }
        menuGrid.setAlignment(Pos.CENTER);
        menuGrid.setSpacing(10);
        Scene menuScene = new Scene(menuGrid,500,500);

//------MATCH SCENE SET UP

        //  BorderPane layout for the game
        gameBP = new BorderPane();

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

        //Grid that displays possible spots for X's and O's
        buttonGrid = new GridPane();
        int buttonIndex = 0;

        //Adding Buttons to Grid (initially)
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                buttonGrid.add(buttons[buttonIndex], j, i);
                buttonIndex++;
            }
        }
        buttonGrid.setAlignment(Pos.CENTER);
        gameBP.setCenter(buttonGrid);

        //Canvases that display either 'X' or 'O' in their slot
        Canvas[] canvasArray = new Canvas[9];
        for (int i=0; i<canvasArray.length; i++){
            canvasArray[i] = new Canvas();
            canvasArray[i].setWidth(100);
            canvasArray[i].setHeight(100);
        }

        Scene matchScene = new Scene(gameBP);
        Button playAgainButton = new Button("PLAY AGAIN");

//------HOW TO PLAY SCENE SET UP

        //BorderPane for About
        BorderPane howToPLayBP = new BorderPane();

        Scene howToPlayScene = new Scene(howToPLayBP,500,500);

        Button backButtonHowToPlay = new Button("Back to Menu");

//------ABOUT SCENE SET UP

        //Parses an xml file (Like Midterm 2)
        BorderPane aboutBP = new BorderPane();
        Scene aboutScene = new Scene(aboutBP,500,500);
        Button backButtonAbout = new Button("Back to Menu");

//------MENU EVENT HANDLERS

        // PLAY BUTTON HANDLING
        playButton.setOnAction(actionEvent -> {
            //**TO DO: SET THIS TO ONLY DISPLAY WHEN GAME IS DONE**
            gameBP.setBottom(playAgainButton);

            //Switches to matchScene
            primaryStage.setScene(matchScene);
            primaryStage.show();
        });

        // HOW TO PLAY BUTTON HANDLING
        howToPlayButton.setOnAction(actionEvent -> {
            howToPLayBP.setTop(backButtonHowToPlay);

            //Parses a txt File, displaying all game rules

            //Switches to howToPlayScene
            primaryStage.setScene(howToPlayScene);
            primaryStage.show();
        });

        // ABOUT BUTTON HANDLING
        aboutButton.setOnAction(actionEvent -> {
            About newAboutPage = new About();

            //Setting the Back button at the top of the scene
            aboutBP.setTop(backButtonAbout);

            //Parses XML (RELATIVE PATH!)
            File f = new File("./resources/tic_tac_toe_about.xml");

            //Calling Function that parses XML file and adds contents to aboutBP CENTER
            newAboutPage.parseXML(f,aboutBP);

            //Switches to aboutScene
            primaryStage.setScene(aboutScene);
            primaryStage.show();
        });

//------IN GAME BUTTON HANDLERS

        // PLAY AGAIN BUTTON
        playAgainButton.setOnAction(actionEvent -> {
            System.out.println("Clicked on 'PLAY AGAIN' button");

            //Switches to LOADING SCENE or something in between to reset everything
            primaryStage.setScene(menuScene);
            primaryStage.show();

            //Clear choices MAP
            choices.clear();

            //  Remove all the canvases and Re-enable the buttons in the Grid
            for (int i=0; i<buttons.length; i++){
                buttonGrid.getChildren().remove(canvasArray[i]);
                buttons[i].setDisable(false);
            }
        });

        String[] buttonNames = { "topLeft", "topCenter", "topRight", "centerLeft", "center", "centerRight",
        "bottomLeft", "bottomCenter", "bottomRight" };

    //--CHOICE BUTTON HANDLERS
        int a = 0;
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                buttonHandlers[a].createButton(a, row, col, canvasArray, buttonNames);
                a++;
            }
        }

//------BACK BUTTON HANDLERS

        // ABOUT BACK BUTTON
        backButtonAbout.setOnAction(actionEvent -> {
            System.out.println("Clicked on 'Back to Menu' button");

            //Switches to mainScene
            primaryStage.setScene(menuScene);
            primaryStage.show();
        });

        // HOW TO PLAY BACK BUTTON
        backButtonHowToPlay.setOnAction(actionEvent -> {
            System.out.println("Clicked on 'Back to Menu' button");

            //Switches to mainScene
            primaryStage.setScene(menuScene);
            primaryStage.show();
        });

//------DISPLAYING THE MENU SCENE
        primaryStage.setScene(menuScene);
        primaryStage.setTitle("Tic-Tac-Toe v1.0");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
