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
            serverSocket = new ServerSocket(serverPort);        // Create the server socket
            threads = new ServerThread[maxClients];             // Create the thread array
            while(numClients < 2) {                             // Establish a connection with the client
                clientSocket = serverSocket.accept();
                threads[numClients] = new ServerThread(clientSocket, symbol[numClients]);  // Pass the client thread its socket and symbol
                threads[numClients].start();                                               // Execute the thread
                numClients++;
            }
            threads[0].setReady();
            threads[1].setReady();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
