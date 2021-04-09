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
                    shownFirstPlayerMessage = true;
                }
            }
            else {
                System.out.println(symbol);
                serverOutput.println(symbol);
                break;
            }
        }
//        try {
//            socket.close();
//            serverInput.close();
//            serverOutput.close();
//        } catch(IOException e) {
//            e.printStackTrace();
//        }
    }

    public void setReady() {
        ready = true;
    }
}
