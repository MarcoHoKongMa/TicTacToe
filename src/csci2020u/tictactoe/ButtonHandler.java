package csci2020u.tictactoe;

import javafx.scene.canvas.Canvas;

public class ButtonHandler {
    protected static String playerSymobol;
    protected static String oppoSymbol;

    public ButtonHandler(int index, int row, int col, Canvas[] canvasArray, String[] buttonNames) {
        SubMenu.buttons[index].setOnAction(actionEvent -> {
            //Make button inaccessible
            SubMenu.buttons[index].setDisable(true);

            //Replacing Button with respective Canvas for symbol display

            Main.buttonGrid.add(canvasArray[index],col,row);

            //Updating the buttonGrid
            Main.gameBP.setCenter(Main.buttonGrid);

            //New instance to draw 'X' or 'O' symbol
            Draw newChoice = new Draw();

            //if player is X (Client 1)
            //draw X
            newChoice.drawSymbol(playerSymobol,canvasArray[index], Main.gameBP);

            //set buttonNames[i] as 'X' for Tic-Tac-Toe logic
            Main.choices.put(buttonNames[index], playerSymobol);

            //else if player is O (Client 2)
            //draw O
            //newChoice.drawSymbol("O",cTopLeft,gameBP);

            //set topLeft as 'O' for Tic-Tac-Toe logic
            //choices.put("topLeft","O");
        });
    }
}
