package csci2020u.client.clientConnection;

import csci2020u.client.subMenu.ButtonHandler;
import csci2020u.client.subMenu.SubMenu;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class Game {
    // Game class Parameters
    public static String ip;
    private static PrintWriter clientOutput;
    private static BufferedReader clientInput;
    private static Socket socket;
    private static Stage primaryStage;
    private static int turns = 0;

    /**
     * This function recieves output form the server and performs
     * actions to update the client side interface.
     * @param primaryStage
     */
    public void run(Stage primaryStage) {
        this.primaryStage = primaryStage;
        try {
            socket = new Socket(ip, 1024);                                                 // Connect to the server
            clientOutput = new PrintWriter(socket.getOutputStream(), true);            // Output stream
            clientInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));   // Input Stream
        } catch(IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "SERVER CONNECTION FAILED\nPlease make sure your" +
                    " server is running and connected, then try again");
            alert.showAndWait();
            System.exit(1);
        }


        try {
            primaryStage.hide();
            String message = clientInput.readLine();
            if(message.equals("Searching for your opponent")) {
                System.out.println(message);
                message = clientInput.readLine();       // Obtain client symbol
            }
            System.out.println("Your opponent has been found");
            primaryStage.show();

            ButtonHandler.playerSymbol = message;
            if(message.equals("X")) {
                ButtonHandler.oppoSymbol = "O";
            }
            else {
                ButtonHandler.oppoSymbol = "X";
            }

            getTurn(); // Call a helper function
        } catch(IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "CONNECTION ERROR\nThis program will now be exited");
            alert.showAndWait();
            System.exit(1);
        }
    }

    /**
     * Function displays the interface is it is their turn. Otherwise the function
     * calls a helper function to obtain the opponent clients move.
     */
    public static void getTurn() {
        try {
            int myTurn;             // Either 1 or 0
            // Update turns
            myTurn = Integer.parseInt(clientInput.readLine());
            turns++;
            if(myTurn == 0) {
                // Disable
                ButtonHandler.myTurn = false;
                primaryStage.hide();
                // Get opponent's move
                getOppoMove();
            }
            else {
                // Enable
                ButtonHandler.myTurn = true;
                primaryStage.show();
            }
        } catch(IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "CONNECTION ERROR\nThis program will now be exited");
            alert.showAndWait();
            System.exit(1);
        }
    }

    /**
     * Function gets the index for the button that the opponent
     * client pressed.
     */
    public static void getOppoMove() {
        try {
            int box = Integer.parseInt(clientInput.readLine());
            SubMenu.buttonHandlers[box].drawOppoMove(box);
            try {
                int aWin = Integer.parseInt(clientInput.readLine());
                if(turns < 9 && aWin == 0) {
                    getTurn();
                }
                else {
                    getResult();
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function receives an output from the
     * server to indicate if they have won or lost.
     */
    public static void getResult() {
        try {
            primaryStage.show();
            System.out.println(clientInput.readLine() + "\n");
            ButtonHandler.myTurn = false;
            turns = 0;
            socket.close();
            clientOutput.close();
            clientInput.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function sends which button index was press by the client
     * to the server.
     * @param index index can range from 0-8.
     */
    public static void updateBoard(int index) {
        clientOutput.println(index);
        try {
            int aWin = Integer.parseInt(clientInput.readLine());
            if(turns < 9 && aWin == 0) {
                getTurn();
            }
            else {
                getResult();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function disconnects a client from the server.
     * @throws IOException
     */
    public void end() throws IOException {
        ButtonHandler.myTurn = false;
        turns = 0;
        socket.close();
        clientOutput.close();
        clientInput.close();
    }
}
