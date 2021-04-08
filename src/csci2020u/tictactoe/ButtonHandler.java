package csci2020u.tictactoe;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.util.*;

public class ButtonHandler {
    private int iter;

    public void createButton(int iter, int row, int col, Canvas[] canvasArray, String[] buttonNames) {
        this.iter = iter;

        Main.buttons[iter].setOnAction(actionEvent -> {
            //Make button inaccessible
            Main.buttons[iter].setDisable(true);

            //Replacing Button with respective Canvas for symbol display

            Main.buttonGrid.add(canvasArray[iter],col,row);

            //Updating the buttonGrid
            Main.gameBP.setCenter(Main.buttonGrid);

            //New instance to draw 'X' or 'O' symbol
            Draw newChoice = new Draw();

            //if player is X (Client 1)
            //draw X
            newChoice.drawSymbol("X",canvasArray[iter], Main.gameBP);

            //set buttonNames[i] as 'X' for Tic-Tac-Toe logic
            Main.choices.put(buttonNames[iter],"X");

            //else if player is O (Client 2)
            //draw O
            //newChoice.drawSymbol("O",cTopLeft,gameBP);

            //set topLeft as 'O' for Tic-Tac-Toe logic
            //choices.put("topLeft","O");
        });
    }
}
