package csci2020u.client.mainMenu;

import csci2020u.client.subMenu.About;
import csci2020u.client.subMenu.SubMenu;
import csci2020u.client.clientInterface.Main;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

/**
 * The class instantiates all of the main menu elements and handles all of the elements
 * interactions in the main menu.
 */

public class MainMenu {
    // Menu Components
    protected Button playButton;
    protected Button aboutButton;
    private static VBox menuGrid;

    // Constructor
    public MainMenu(Stage primaryStage, Button playAgainButton, Scene matchScene, Button backButtonAbout,
                    Scene aboutScene) {
        Button[] menuButtons;
        Text menuTitle;

        // Menu Title
        menuTitle = new Text("Tic-Tac-Toe");
        menuTitle.setFont(Font.font("Courier New"));

        //  Main Menu Buttons
        playButton = new Button("PLAY");
        aboutButton = new Button("ABOUT");
        menuButtons = new Button[]{playButton, aboutButton};
        menuGrid = new VBox();

        menuGrid.getChildren().add(menuTitle);
        for (Button menuButton : menuButtons) {
            menuButton.setPrefWidth(100);
            menuGrid.getChildren().add(menuButton);
        }
        menuGrid.setAlignment(Pos.CENTER);
        menuGrid.setSpacing(10);

//------MENU EVENT HANDLERS

        // PLAY BUTTON HANDLING
        playButton.setOnAction(actionEvent -> {
            //**TO DO: SET THIS TO ONLY DISPLAY WHEN GAME IS DONE**
            Main.gameBP.setBottom(playAgainButton);

            //Switches to matchScene
            primaryStage.setScene(matchScene);
            primaryStage.show();

            SubMenu.game.run(primaryStage);
        });

        // ABOUT BUTTON HANDLING
        aboutButton.setOnAction(actionEvent -> {
            About newAboutPage = new About();

            //Setting the Back button at the top of the scene
            Main.aboutBP.setTop(backButtonAbout);

            //Parses XML (RELATIVE PATH!)
            File f = new File("./resources/tic_tac_toe_about.xml");

            //Calling Function that parses XML file and adds contents to aboutBP CENTER
            newAboutPage.parseXML(f, Main.aboutBP);

            //Switches to aboutScene
            primaryStage.setScene(aboutScene);
            primaryStage.show();
        });

        Main.buttonGrid.setStyle("-fx-focus-color: lightgray; -fx-faint-focus-color: transparent;");
        Main.gameBP.setStyle("-fx-focus-color: lightgray; -fx-faint-focus-color: transparent;");
        Main.aboutBP.setStyle("-fx-focus-color: lightgray; -fx-faint-focus-color: transparent;");
    }

    /**
     * Getter function that returns a the menuGrid
     * @return VBox.
     */
    public VBox getMenu() {
        return menuGrid;
    }
}
