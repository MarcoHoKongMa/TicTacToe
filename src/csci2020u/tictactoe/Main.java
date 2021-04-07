package csci2020u.tictactoe;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //MAP FOR TIC-TAC-TOE LOGIC
        Map<String, String> choices = new TreeMap<>();

//------MAIN MENU SCENE SET UP

        //Grid for the Menu Button
        GridPane menuGrid = new GridPane();

        //Menu Title
        Text menuTitle = new Text("Tic-Tac-Toe");
        menuTitle.setFont(Font.font("Courier New"));

        //Menu Buttons
        Button playButton = new Button("PLAY");
        playButton.setPrefWidth(100);

        Button howToPlayButton = new Button("HOW TO PLAY");
        howToPlayButton.setPrefWidth(100);

        Button aboutButton = new Button("ABOUT");
        aboutButton.setPrefWidth(100);

        //Adding Buttons to menuGrid
        menuGrid.add(menuTitle,0,0);
        menuGrid.add(playButton,0,1);
        menuGrid.add(howToPlayButton,0,2);
        menuGrid.add(aboutButton,0,3);

        menuGrid.setAlignment(Pos.CENTER);
        menuGrid.setHgap(10);
        menuGrid.setVgap(10);

        Scene menuScene = new Scene(menuGrid,500,500);

//------MATCH SCENE SET UP

        //BorderPane for Game
        BorderPane gameBP = new BorderPane();

        //BUTTONS FOR ALL POSSIBLE CHOICES
        Button topLeft = new Button();
        topLeft.setPrefWidth(100);
        topLeft.setPrefHeight(100);

        Button topCenter = new Button();
        topCenter.setPrefWidth(topLeft.getPrefWidth());
        topCenter.setPrefHeight(topLeft.getPrefHeight());

        Button topRight = new Button();
        topRight.setPrefWidth(topLeft.getPrefWidth());
        topRight.setPrefHeight(topLeft.getPrefHeight());

        Button centerLeft = new Button();
        centerLeft.setPrefWidth(topLeft.getPrefWidth());
        centerLeft.setPrefHeight(topLeft.getPrefHeight());

        Button center = new Button();
        center.setPrefWidth(topLeft.getPrefWidth());
        center.setPrefHeight(topLeft.getPrefHeight());

        Button centerRight = new Button();
        centerRight.setPrefWidth(topLeft.getPrefWidth());
        centerRight.setPrefHeight(topLeft.getPrefHeight());

        Button bottomLeft = new Button();
        bottomLeft.setPrefWidth(topLeft.getPrefWidth());
        bottomLeft.setPrefHeight(topLeft.getPrefHeight());

        Button bottomCenter = new Button();
        bottomCenter.setPrefWidth(topLeft.getPrefWidth());
        bottomCenter.setPrefHeight(topLeft.getPrefHeight());

        Button bottomRight = new Button();
        bottomRight.setPrefWidth(topLeft.getPrefWidth());
        bottomRight.setPrefHeight(topLeft.getPrefHeight());

        //Grid that displays possible spots for X's and O's
        GridPane buttonGrid = new GridPane();

        //Adding Buttons to Grid (initially)
        buttonGrid.add(topLeft,0,0);
        buttonGrid.add(topCenter,1,0);
        buttonGrid.add(topRight,2,0);

        buttonGrid.add(centerLeft,0,1);
        buttonGrid.add(center,1,1);
        buttonGrid.add(centerRight,2,1);

        buttonGrid.add(bottomLeft,0,2);
        buttonGrid.add(bottomCenter,1,2);
        buttonGrid.add(bottomRight,2,2);

        buttonGrid.setAlignment(Pos.CENTER);

        gameBP.setCenter(buttonGrid);

        //Canvases that display either 'X' or 'O' in their slot
        Canvas cTopLeft = new Canvas();
        cTopLeft.setWidth(100);
        cTopLeft.setHeight(100);

        Canvas cTopCenter = new Canvas();
        cTopCenter.setWidth(100);
        cTopCenter.setHeight(100);

        Canvas cTopRight = new Canvas();
        cTopRight.setWidth(100);
        cTopRight.setHeight(100);

        Canvas cCenterLeft = new Canvas();
        cCenterLeft.setWidth(100);
        cCenterLeft.setHeight(100);

        Canvas cCenter = new Canvas();
        cCenter.setWidth(100);
        cCenter.setHeight(100);

        Canvas cCenterRight = new Canvas();
        cCenterRight.setWidth(100);
        cCenterRight.setHeight(100);

        Canvas cBottomLeft = new Canvas();
        cBottomLeft.setWidth(100);
        cBottomLeft.setHeight(100);

        Canvas cBottomCenter = new Canvas();
        cBottomCenter.setWidth(100);
        cBottomCenter.setHeight(100);

        Canvas cBottomRight = new Canvas();
        cBottomRight.setWidth(100);
        cBottomRight.setHeight(100);

        Scene matchScene = new Scene(gameBP);

        Button playAgainButton = new Button("PLAY AGAIN");

//------HOW TO PLAY SCENE SET UP

        //BorderPane for About
        BorderPane howToPLayBP = new BorderPane();

        Scene howToPlayScene = new Scene(howToPLayBP,500,500);

        Button backButtonHowToPlay = new Button("Back to Menu");

//------ABOUT SCENE SET UP

        //Parses an xml file (Like Midterm 2)

        //BorderPane for About
        BorderPane aboutBP = new BorderPane();

        Scene aboutScene = new Scene(aboutBP,500,500);

        Button backButtonAbout = new Button("Back to Menu");

//------MENU EVENT HANDLERS

        // PLAY BUTTON HANDLING
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Clicked on 'PLAY' button");

                //**TO DO: SET THIS TO ONLY DISPLAY WHEN GAME IS DONE**
                gameBP.setBottom(playAgainButton);

                //Switches to matchScene
                primaryStage.setScene(matchScene);
                primaryStage.show();
            }
        });

        // HOW TO PLAY BUTTON HANDLING
        howToPlayButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                System.out.println("Clicked on 'How To Play' button");

                howToPLayBP.setTop(backButtonHowToPlay);

                //Parses a txt File, displaying all game rules

                //Switches to howToPlayScene
                primaryStage.setScene(howToPlayScene);
                primaryStage.show();
            }
        });

        // ABOUT BUTTON HANDLING
        aboutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                System.out.println("Clicked on 'About' button");
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
            }
        });

//------IN GAME BUTTON HANDLERS

        // PLAY AGAIN BUTTON
        playAgainButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Clicked on 'PLAY AGAIN' button");

                //Switches to LOADING SCENE or something in between to reset everything
                primaryStage.setScene(menuScene);
                primaryStage.show();

                //Clear choices MAP
                choices.clear();

                //remove everything that was drawn onto the Grid
                buttonGrid.getChildren().remove(cTopLeft);
                buttonGrid.getChildren().remove(cTopCenter);
                buttonGrid.getChildren().remove(cTopRight);
                buttonGrid.getChildren().remove(cCenterLeft);
                buttonGrid.getChildren().remove(cCenter);
                buttonGrid.getChildren().remove(cCenterRight);
                buttonGrid.getChildren().remove(cBottomLeft);
                buttonGrid.getChildren().remove(cBottomCenter);
                buttonGrid.getChildren().remove(cBottomRight);

                //Re-enable the buttons in the Grid
                topLeft.setDisable(false);
                topCenter.setDisable(false);
                topRight.setDisable(false);
                centerLeft.setDisable(false);
                center.setDisable(false);
                centerRight.setDisable(false);
                bottomLeft.setDisable(false);
                bottomCenter.setDisable(false);
                bottomRight.setDisable(false);

            }
        });

    //--CHOICE BUTTON HANDLERS

        // TOP LEFT BUTTON
        topLeft.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                //Make button inaccessible
                topLeft.setDisable(true);

                //Replacing Button with respective Canvas for symbol display
                buttonGrid.add(cTopLeft,0,0);

                //Updating the buttonGrid
                gameBP.setCenter(buttonGrid);

                //New instance to draw 'X' or 'O' symbol
                Draw newChoice = new Draw();

                //if player is X (Client 1)
                    //draw X
                    newChoice.drawSymbol("X",cTopLeft,gameBP);

                    //set topLeft as 'X' for Tic-Tac-Toe logic
                    //choices.put("topLeft","X");

                //else if player is O (Client 2)
                    //draw O
                    //newChoice.drawSymbol("O",cTopLeft,gameBP);

                    //set topLeft as 'O' for Tic-Tac-Toe logic
                    //choices.put("topLeft","O");
            }
        });

        // TOP CENTER BUTTON
        topCenter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                //Make button inaccessible
                topCenter.setDisable(true);

                //Replacing Button with respective Canvas for symbol display
                buttonGrid.add(cTopCenter,1,0);

                //Updating the buttonGrid
                gameBP.setCenter(buttonGrid);

                //New instance to draw 'X' or 'O' symbol
                Draw newChoice = new Draw();

                //if player is X (Client 1)
                    //draw X
                    newChoice.drawSymbol("X",cTopCenter,gameBP);

                    //set topLeft as 'X' for Tic-Tac-Toe logic
                    //choices.put("topCenter","X");

                //else if player is O (Client 2)
                    //draw O
                    //newChoice.drawSymbol("O",cTopCenter,gameBP);

                    //set topLeft as 'O' for Tic-Tac-Toe logic
                    //choices.put("topCenter","O");
            }
        });

        // TOP RIGHT BUTTON
        topRight.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                //Make button inaccessible
                topRight.setDisable(true);

                //Replacing Button with respective Canvas for symbol display
                buttonGrid.add(cTopRight,2,0);

                //Updating the buttonGrid
                gameBP.setCenter(buttonGrid);

                //New instance to draw 'X' or 'O' symbol
                Draw newChoice = new Draw();

                //if player is X (Client 1)
                    //draw X
                    newChoice.drawSymbol("X",cTopRight,gameBP);

                    //set topLeft as 'X' for Tic-Tac-Toe logic
                    //choices.put("topRight","X");

                //else if player is O (Client 2)
                    //draw O
                    //newChoice.drawSymbol("O",cTopRight,gameBP);

                    //set topLeft as 'O' for Tic-Tac-Toe logic
                    //choices.put("topRight","O");
            }
        });

        // CENTER LEFT BUTTON
        centerLeft.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                //Make button inaccessible
                centerLeft.setDisable(true);

                //Replacing Button with respective Canvas for symbol display
                buttonGrid.add(cCenterLeft,0,1);

                //Updating the buttonGrid
                gameBP.setCenter(buttonGrid);

                //New instance to draw 'X' or 'O' symbol
                Draw newChoice = new Draw();

                //if player is X (Client 1)
                    //draw X
                    newChoice.drawSymbol("X",cCenterLeft,gameBP);

                    //set topLeft as 'X' for Tic-Tac-Toe logic
                    //choices.put("centerLeft","X");

                //else if player is O (Client 2)
                    //draw O
                    //newChoice.drawSymbol("O",cCenterLeft,gameBP);

                    //set topLeft as 'O' for Tic-Tac-Toe logic
                    //choices.put("centerLeft","O");
            }
        });

        // CENTER BUTTON
        center.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                //Make button inaccessible
                center.setDisable(true);

                //Replacing Button with respective Canvas for symbol display
                buttonGrid.add(cCenter,1,1);

                //Updating the buttonGrid
                gameBP.setCenter(buttonGrid);

                //New instance to draw 'X' or 'O' symbol
                Draw newChoice = new Draw();

                //if player is X (Client 1)
                    //draw X
                    newChoice.drawSymbol("X",cCenter,gameBP);

                    //set topLeft as 'X' for Tic-Tac-Toe logic
                    //choices.put("center","X");

                //else if player is O (Client 2)
                    //draw O
                    //newChoice.drawSymbol("O",cCenter,gameBP);

                    //set topLeft as 'O' for Tic-Tac-Toe logic
                    //choices.put("center","O");
            }
        });

        // CENTER RIGHT BUTTON
        centerRight.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                //Make button inaccessible
                centerRight.setDisable(true);

                //Replacing Button with respective Canvas for symbol display
                buttonGrid.add(cCenterRight,2,1);

                //Updating the buttonGrid
                gameBP.setCenter(buttonGrid);

                //New instance to draw 'X' or 'O' symbol
                Draw newChoice = new Draw();

                //if player is X (Client 1)
                    //draw X
                    newChoice.drawSymbol("X",cCenterRight,gameBP);

                    //set topLeft as 'X' for Tic-Tac-Toe logic
                    //choices.put("centerRight","X");

                //else if player is O (Client 2)
                    //draw O
                    //newChoice.drawSymbol("O",cCenterRight,gameBP);

                    //set topLeft as 'O' for Tic-Tac-Toe logic
                    //choices.put("centerRight","O");
            }
        });

        // BOTTOM LEFT BUTTON
        bottomLeft.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                //Make button inaccessible
                bottomLeft.setDisable(true);

                //Replacing Button with respective Canvas for symbol display
                buttonGrid.add(cBottomLeft,0,2);

                //Updating the buttonGrid
                gameBP.setCenter(buttonGrid);

                //New instance to draw 'X' or 'O' symbol
                Draw newChoice = new Draw();

                //if player is X (Client 1)
                    //draw X
                    newChoice.drawSymbol("X",cBottomLeft,gameBP);

                    //set topLeft as 'X' for Tic-Tac-Toe logic
                    //choices.put("bottomLeft","X");

                //else if player is O (Client 2)
                    //draw O
                    //newChoice.drawSymbol("O",cBottomLeft,gameBP);

                    //set topLeft as 'O' for Tic-Tac-Toe logic
                    //choices.put("bottomLeft","O");
            }
        });

        // BOTTOM CENTER BUTTON
        bottomCenter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                //Make button inaccessible
                bottomCenter.setDisable(true);

                //Replacing Button with respective Canvas for symbol display
                buttonGrid.add(cBottomCenter,1,2);

                //Updating the buttonGrid
                gameBP.setCenter(buttonGrid);

                //New instance to draw 'X' or 'O' symbol
                Draw newChoice = new Draw();

                //if player is X (Client 1)
                    //draw X
                    newChoice.drawSymbol("X",cBottomCenter,gameBP);

                    //set topLeft as 'X' for Tic-Tac-Toe logic
                    //choices.put("bottomCenter","X");

                //else if player is O (Client 2)
                    //draw O
                    //newChoice.drawSymbol("O",cBottomCenter,gameBP);

                    //set topLeft as 'O' for Tic-Tac-Toe logic
                    //choices.put("bottomCenter","O");
            }
        });

        // BOTTOM RIGHT BUTTON
        bottomRight.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                //Make button inaccessible
                bottomRight.setDisable(true);

                //Replacing Button with respective Canvas for symbol display
                buttonGrid.add(cBottomRight,2,2);

                //Updating the buttonGrid
                gameBP.setCenter(buttonGrid);

                //New instance to draw 'X' or 'O' symbol
                Draw newChoice = new Draw();

                //if player is X (Client 1)
                    //draw X
                    newChoice.drawSymbol("X",cBottomRight,gameBP);

                    //set topLeft as 'X' for Tic-Tac-Toe logic
                    //choices.put("bottomRight","X");

                //else if player is O (Client 2)
                    //draw O
                    //newChoice.drawSymbol("O",cBottomRight,gameBP);

                    //set topLeft as 'O' for Tic-Tac-Toe logic
                    //choices.put("bottomRight","O");
            }
        });

//------BACK BUTTON HANDLERS

        // ABOUT BACK BUTTON
        backButtonAbout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Clicked on 'Back to Menu' button");

                //Switches to mainScene
                primaryStage.setScene(menuScene);
                primaryStage.show();
            }
        });

        // HOW TO PLAY BACK BUTTON
        backButtonHowToPlay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Clicked on 'Back to Menu' button");

                //Switches to mainScene
                primaryStage.setScene(menuScene);
                primaryStage.show();
            }
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
