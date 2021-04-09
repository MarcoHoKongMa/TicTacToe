package csci2020u.tictactoe;

import javafx.scene.control.Alert;

import java.io.*;
import java.net.Socket;

public class Game {
    private PrintWriter clientOutput;
    private BufferedReader clientInput;
    private Socket socket;

    public void run() {
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
            String message;
            message = clientInput.readLine();
            Alert search;
            if(message.equals("Searching for your opponent")) {
                search = new Alert(Alert.AlertType.INFORMATION, message);
                search.show();
                message = clientInput.readLine();
            }

            search = new Alert(Alert.AlertType.INFORMATION, "Your opponent has been found");
            search.hide();
            search.show();

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
