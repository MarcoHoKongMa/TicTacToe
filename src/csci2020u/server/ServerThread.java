package csci2020u.server;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket = null;
    private BufferedReader serverInput = null;
    private PrintWriter serverOutput = null;
    private String symbol;
    private boolean ready = false;
    private boolean shownFirstPlayerMessage = false;
    private int num;

    public ServerThread(Socket socket, String symbol, int num) {
        try {
            this.num = num;
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
            System.out.println(num + ": Not Ready");            // Needed to avoid java Threads bug
            if(!ready) {
                if(!shownFirstPlayerMessage) {
                    serverOutput.println("Searching for your opponent");
                    shownFirstPlayerMessage = true;
                }
            }
            else {
                serverOutput.println(symbol);
                break;
            }
        }
        try {
            socket.close();
            serverInput.close();
            serverOutput.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void setReady() {
        ready = true;
    }
}
