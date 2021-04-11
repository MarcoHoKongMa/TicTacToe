package csci2020u.server;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket = null;
    private BufferedReader serverInput = null;
    private PrintWriter serverOutput = null;
    private String symbol;
    private boolean ready = false;
    private boolean showFirstPlayerMessage;

    // Constructor
    public ServerThread(Socket socket, String symbol, boolean firstPlayer) {
        try {
            this.socket = socket;
            this.symbol = symbol;
            this.showFirstPlayerMessage = firstPlayer;
            this.serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.serverOutput = new PrintWriter(socket.getOutputStream(), true);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        // Waits for a second client connection
        while(true) {
            if(!ready) {
                if(showFirstPlayerMessage) {
                    serverOutput.println("Searching for your opponent");
                    showFirstPlayerMessage = false;
                }
            }
            else {
                break;
            }
        }
    }

    /**
     * Updates the ready variable to indicate that a second client has
     * connected to the server.
     */
    public void setReady() {
        serverOutput.println(symbol);
        ready = true;
    }

    public void makeMove() {
        int move = -1;
        try {
            move = Integer.parseInt(serverInput.readLine());
            Server.updateBoard(move);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void updateTurn(int condition) {
        serverOutput.println(condition);
    }

    public void updateOppoMove(int index) {
        serverOutput.println(index);
    }

    public void exit() {
        serverOutput.println("Thanks for Playing");
        try {
            socket.close();
            serverInput.close();
            serverOutput.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
