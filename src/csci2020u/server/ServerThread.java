package csci2020u.server;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket = null;
    private BufferedReader serverInput = null;
    private PrintWriter serverOutput = null;
    private String symbol;
    protected boolean ready = false;
    protected boolean shownFirstPlayerMessage = false;

    public ServerThread(Socket socket, String symbol) {
        try {
            this.socket = socket;
            this.symbol = symbol;
            this.serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.serverOutput = new PrintWriter(socket.getOutputStream(), true);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while(true) {
            if(!ready) {
                if(!shownFirstPlayerMessage) {
                    serverOutput.println("Searching for your opponent");
                }
            }
            else {
                serverOutput.println(symbol);
            }
        }
    }

    public void setReady() {
        ready = true;
    }
}
