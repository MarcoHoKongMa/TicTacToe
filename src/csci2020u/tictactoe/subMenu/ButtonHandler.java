package csci2020u.tictactoe.subMenu;

import csci2020u.tictactoe.Draw;
import csci2020u.tictactoe.clientConnection.Game;
import csci2020u.tictactoe.clientInterface.Main;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;

public class ButtonHandler {
    public static String playerSymbol;
    public static String oppoSymbol;
    public static boolean myTurn = false;
    private int row;
    private int col;

    public ButtonHandler(int index, int row, int col, Canvas[] canvasArray) {
        this.row = row;
        this.col = col;

        SubMenu.buttons[index].setOnAction(actionEvent -> {
            if(myTurn) {
                //Make button inaccessible
                SubMenu.buttons[index].setDisable(true);

                //Replacing Button with respective Canvas for symbol display

                Main.buttonGrid.add(canvasArray[index],col,row);

                //Updating the buttonGrid
                Main.gameBP.setCenter(Main.buttonGrid);

                //New instance to draw 'X' or 'O' symbol
                Draw newChoice = new Draw();

                // Draw player symbol
                newChoice.drawSymbol(playerSymbol,canvasArray[index], Main.gameBP);

                Game.updateBoard(index);

                if(Game.turns < 9) {
                    Game.getTurn();
                }
                else {
                    Game.getResult();
                }
            }
        });
    }

    public void drawOppoMove(int index) {
        //Make button inaccessible
        SubMenu.buttons[index].setDisable(true);

        //Replacing Button with respective Canvas for symbol display

        Main.buttonGrid.add(Main.canvasArray[index],col,row);

        //Updating the buttonGrid
        Main.gameBP.setCenter(Main.buttonGrid);

        //New instance to draw 'X' or 'O' symbol
        Draw newChoice = new Draw();

        // Draw player symbol
        newChoice.drawSymbol(oppoSymbol, Main.canvasArray[index], Main.gameBP);
    }
}
