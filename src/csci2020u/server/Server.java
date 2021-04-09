package csci2020u.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int numClients = 0;
        int serverPort = 1024;
        int maxClients = 2;
        ServerThread[] threads;
        ServerSocket serverSocket;
        Socket clientSocket;

        String[] symbol = new String[]{"X", "O"};

        try {
            serverSocket = new ServerSocket(serverPort);
            threads = new ServerThread[maxClients];
            while(numClients < 2) {
                clientSocket = serverSocket.accept();
                threads[numClients] = new ServerThread(clientSocket, symbol[numClients]);
                threads[numClients].start();
                numClients++;
            }
            threads[0].setReady();
            threads[1].setReady();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
