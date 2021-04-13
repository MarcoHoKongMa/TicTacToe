package csci2020u.client.subMenu;

import csci2020u.client.Draw;
import csci2020u.client.clientConnection.Game;
import csci2020u.client.clientInterface.Main;
import javafx.scene.canvas.Canvas;

public class ButtonHandler {
    // ButtonHandler Class Parameters
    public static String playerSymbol;
    public static String oppoSymbol;
    public static boolean myTurn = false;
    private int row;
    private int col;

    // Constructor
    public ButtonHandler(int index, int row, int col, Canvas[] canvasArray) {
        this.row = row;
        this.col = col;

        SubMenu.buttons[index].setOnAction(actionEvent -> {
            if(myTurn) {
                // Once a button has been press it will be disabled
                SubMenu.buttons[index].setDisable(true);

                // Replacing Button with respective Canvas for the clients symbol
                Main.buttonGrid.add(canvasArray[index],col,row);

                //Updating the buttonGrid
                Main.gameBP.setCenter(Main.buttonGrid);

                //New instance to draw 'X' or 'O' symbol
                Draw newChoice = new Draw();

                // Draw player symbol
                newChoice.drawSymbol(playerSymbol,canvasArray[index], Main.gameBP);

                Game.updateBoard(index);
            }
        });
    }

    /**
     * Function adds client symbol through a canvas onto the
     * buttonGrid.
     * @param index Index specifies which button needs to be replaced
     *              by a canvas.
     */
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
