package csci2020u.server;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    // ServerThread Parameters
    private Socket socket = null;               // Socket that leads to the client
    private BufferedReader serverInput = null;
    private PrintWriter serverOutput = null;
    private String symbol;                      // Symbol that the player is assigned
    private boolean ready = false;              // Boolean indicates if client is ready to play
    private boolean showFirstPlayerMessage;
    private boolean win = false;                // Boolean indicates if client has won the game

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

    /**
     * Send a string to a client and outputs a message that
     * another client is needed before the game can start.
     */
    public void run() {
        // Waits for a second client to connect to the server
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

    /**
     * Obtains the button that was press by the client and sends it to a helper function
     * to update the opponent clients interface.
     */
    public void makeMove() {
        int move = -1;
        try {
            move = Integer.parseInt(serverInput.readLine()); // Which button was clicked by the client
            Server.updateBoard(move);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The function sends either 1 (is clients turn) or 0 (not clients turn) to indicate to a client if it is their turn.
     * @param condition Either 1 or 0.
     */
    public void updateTurn(int condition) {
        serverOutput.println(condition);
    }

    /**
     * Sends the button index that was clicked by the opponent client to the other
     * client.
     * @param index Index represents a button that was pressed.
     */
    public void updateOppoMove(int index) {
        serverOutput.println(index);
    }

    /**
     * Updates the win variable indicating whether if a client has won the game.
     * @param status Either true or false.
     */
    public void setWin(boolean status) {
        win = status;
    }

    /**
     * Updates the clients sides to indicate if one of them has won the game
     * or both of them have achieve a tie.
     * @param aWin True or False.
     */
    public void updateWin(boolean aWin) {
        if(aWin){
            serverOutput.println(1);
        }
        else {
            serverOutput.println(0);
        }
    }

    /**
     * Outputs a message to one of the clients if they have won and another
     * message to the other client if they have lost.
     */
    public void exit() {
        if(win) {
            serverOutput.println("CONGRATS, YOU WIN!!!");
        }
        else {
            serverOutput.println("OOF, TOOK A L! Better luck next time");
        }
        try {
            socket.close();
            serverInput.close();
            serverOutput.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
