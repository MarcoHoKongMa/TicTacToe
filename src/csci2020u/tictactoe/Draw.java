package csci2020u.tictactoe;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;



public class Draw {

    /*
     * Function uses GraphicsContext to draw given symbol
     * to the buttonGrid.
     *
     * @param choice: the symbol being drawn
     * @param c: the Canvas that is being drawn on
     * @param b: the BorderPane that displays the canvas
     * */
    public void drawSymbol(String choice, Canvas c,BorderPane b){

        GraphicsContext gc = c.getGraphicsContext2D();
        double adjust = 25;

        if (choice.equals("X")){

            //Draw 'X'

            gc.setStroke(Color.BLACK);
            gc.setFill(Color.BLACK);

            gc.strokeRect(adjust + 0,adjust + 0,10,10);
            gc.fillRect(adjust + 0,adjust + 0,10,10);

            gc.strokeRect(adjust + 30,adjust + 10,10,10);
            gc.fillRect(adjust + 30,adjust + 10,10,10);

            gc.strokeRect(adjust + 10,adjust + 10,10,10);
            gc.fillRect(adjust + 10,adjust + 10,10,10);

            gc.strokeRect(adjust + 20,adjust + 20,10,10);
            gc.fillRect(adjust + 20,adjust + 20,10,10);

            gc.strokeRect(adjust + 30,adjust + 30,10,10);
            gc.fillRect(adjust + 30,adjust + 30,10,10);

            gc.strokeRect(adjust + 10,adjust + 30,10,10);
            gc.fillRect(adjust + 10,adjust + 30,10,10);

            gc.strokeRect(adjust + 40,adjust + 40,10,10);
            gc.fillRect(adjust + 40,adjust + 40,10,10);

            gc.strokeRect(adjust + 40,adjust + 0,10,10);
            gc.fillRect(adjust + 40,adjust + 0,10,10);

            gc.strokeRect(adjust + 0,adjust + 40,10,10);
            gc.fillRect(adjust + 0,adjust + 40,10,10);

        }
        else if(choice.equals("O")){

            //Draw 'O'

            gc.setStroke(Color.BLUE);
            gc.setFill(Color.BLUE);

            gc.strokeRect(25,25,50,10);
            gc.fillRect(25,25,50,10);

            gc.strokeRect(25,25,10,50);
            gc.fillRect(25,25,10,50);

            gc.strokeRect(25,40+25,50,10);
            gc.fillRect(25,40+25,50,10);

            gc.strokeRect(40+25,25,10,50);
            gc.fillRect(40+25,25,10,50);
        }

    }
}