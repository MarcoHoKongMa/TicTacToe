package csci2020u.tictactoe.clientInterface;

import csci2020u.tictactoe.mainMenu.MainMenu;
import csci2020u.tictactoe.subMenu.SubMenu;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Map;
import java.util.TreeMap;

/**
 * The main class instantiates the entire client side interface.
 */

public class Main extends Application {
    public static GridPane buttonGrid;
    public static BorderPane gameBP;
    public static BorderPane howToPLayBP;
    public static BorderPane aboutBP;
    public static Map<String, String> choices;
    public static Scene menuScene;
    public static Canvas[] canvasArray;

    @Override
    public void start(Stage primaryStage) {
        // Initialize Main Menu
        VBox menuGrid;

        //MAP FOR TIC-TAC-TOE LOGIC
        choices = new TreeMap<>();

//------MATCH SCENE SET UP

        //  BorderPane layout for the game
        gameBP = new BorderPane();

        //Grid that displays possible spots for X's and O's
        buttonGrid = new GridPane();
        buttonGrid.setAlignment(Pos.CENTER);
        gameBP.setCenter(buttonGrid);

        //Canvases that display either 'X' or 'O' in their slot
        canvasArray = new Canvas[9];
        for (int i=0; i<canvasArray.length; i++){
            canvasArray[i] = new Canvas();
            canvasArray[i].setWidth(100);
            canvasArray[i].setHeight(100);
        }

        // Sub Menu Elements
        SubMenu subMenu = new SubMenu(primaryStage, canvasArray);

        Scene matchScene = new Scene(gameBP);
        Button playAgainButton = subMenu.getPlayAgain();

//------HOW TO PLAY SCENE SET UP

        //BorderPane for About
        howToPLayBP = new BorderPane();
        Scene howToPlayScene = new Scene(howToPLayBP,500,500);
        Button backButtonHowToPlay = subMenu.getBackButtonHowToPlay();

//------ABOUT SCENE SET UP

        //Parses an xml file (Like Midterm 2)
        aboutBP = new BorderPane();
        Scene aboutScene = new Scene(aboutBP,500,500);
        Button backButtonAbout = subMenu.getBackButtonAbout();

//------MAIN MENU SCENE SET UP
        // Main Menu
        MainMenu mainMenu = new MainMenu(primaryStage, playAgainButton, matchScene, backButtonHowToPlay, howToPlayScene
                , backButtonAbout, aboutScene);

        //  Main Menu Layout
        menuGrid = mainMenu.getMenu();
        menuScene = new Scene(menuGrid,500,500);

        menuGrid.setStyle("-fx-focus-color: lightgray; -fx-faint-focus-color: transparent;");

//------DISPLAYING THE MENU SCENE
        primaryStage.setScene(menuScene);
        primaryStage.setTitle("Tic-Tac-Toe v1.0");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
