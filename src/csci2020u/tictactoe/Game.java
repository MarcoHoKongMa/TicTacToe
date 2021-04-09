package csci2020u.tictactoe;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class Game {
    private PrintWriter clientOutput;
    private BufferedReader clientInput;
    private Socket socket;

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
            Alert search;
            AlertThread alertThread;
            if(searching.equals("Searching for your opponent")) {
//                search = new Alert(Alert.AlertType.INFORMATION, searching);
//                alertThread = new AlertThread(search);
//                alertThread.start();
                System.out.println(searching);
                System.out.println(clientInput.readLine());
                System.out.println("Testing 1");
            }

//            search = new Alert(Alert.AlertType.INFORMATION, "Your opponent has been found");
//            alertThread = new AlertThread(search);
//            alertThread.start();
//            search.hide();
//            search.show();
            System.out.println("Your opponent has been found");
            primaryStage.show();

            ButtonHandler.playerSymobol = message;
        } catch(IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "CONNECTION ERROR\nThis program will now be exited");
            alert.showAndWait();
            System.exit(1);
        }
    }

    public void end() throws IOException {
        socket.close();
        clientOutput.close();
        clientInput.close();
    }
}
