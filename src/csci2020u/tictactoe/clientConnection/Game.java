package csci2020u.tictactoe.clientConnection;

import csci2020u.tictactoe.AlertThread;
import csci2020u.tictactoe.subMenu.ButtonHandler;
import csci2020u.tictactoe.subMenu.SubMenu;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class Game {
    private static PrintWriter clientOutput;
    private static BufferedReader clientInput;
    private Socket socket;
    private int turns = 0;
    private int myTurn = 0;
    public static boolean madeTurn = false;

    public void run(Stage primaryStage) {
        try {
            socket = new Socket("127.0.0.1", 1024);                            // Connect to the server
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
            String message;
            message = clientInput.readLine();
            String searching = message;
//            Alert search;
//            AlertThread alertThread;
            if(searching.equals("Searching for your opponent")) {
//                search = new Alert(Alert.AlertType.INFORMATION, searching);
//                alertThread = new AlertThread(search);
//                alertThread.start();
                System.out.println(searching);
                message = clientInput.readLine();
            }

//            search = new Alert(Alert.AlertType.INFORMATION, "Your opponent has been found");
//            alertThread = new AlertThread(search);
//            alertThread.start();
//            search.hide();
//            search.show();
            System.out.println("Your opponent has been found");
            primaryStage.show();

            ButtonHandler.playerSymbol = message;
            if(message.equals("X")) {
                ButtonHandler.oppoSymbol = "O";
            }
            else {
                ButtonHandler.oppoSymbol = "X";
            }

            while(turns < 10) {
                // Update turns
                myTurn = Integer.parseInt(clientInput.readLine());
                if(myTurn == 0) {
                    // Disable
                    SubMenu.buttonHandlers[0].disable();
                    // Get opponent's move
                    int index = Integer.parseInt(clientInput.readLine());
                    SubMenu.buttonHandlers[index].drawOppoMove(index);
                }
                else {
                    // Enable
                    SubMenu.buttonHandlers[0].enable();
                    // Make a move
                    while(!madeTurn) {

                    }
                    madeTurn = false;
                }

                turns++;
            }
        } catch(IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "CONNECTION ERROR\nThis program will now be exited");
            alert.showAndWait();
            System.exit(1);
        }
    }

    public static void updateBoard(int index) {
        clientOutput.println(index);
    }

    public void end() throws IOException {
        socket.close();
        clientOutput.close();
        clientInput.close();
    }
}
