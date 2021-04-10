package csci2020u.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static int player = 0;
    private static ServerThread[] threads;

    public static void main(String[] args) {
        // Server class parameters
        int numClients = 0;
        int serverPort = 1024;
        int maxClients = 2;
        ServerSocket serverSocket;
        Socket clientSocket;
        int turns = 0;

        // Symbols that will be assigned to each client
        String[] symbol = new String[]{"X", "O"};

        // Looks for two clients to establish a connection to the server
        try {
            serverSocket = new ServerSocket(serverPort);        // Create the server socket
            threads = new ServerThread[maxClients];             // Create the thread array
            while(numClients < 2) {                             // Establish a connection with the client
                clientSocket = serverSocket.accept();
                threads[numClients] = new ServerThread(clientSocket, symbol[numClients], numClients);  // Pass the client thread its socket and symbol
                threads[numClients].start();   // Execute the thread
                numClients++;
            }
            // Indicates that the two clients are ready to play the game
            threads[0].setReady();
            threads[1].setReady();

            // Begin assigning turns to the clients
            while(turns < 10) {
                if(player == 0) {
                    threads[0].updateTurn(1);
                    threads[1].updateTurn(0);
                    threads[0].makeMove();
                }
                else {
                    threads[0].updateTurn(0);
                    threads[1].updateTurn(1);
                    threads[1].makeMove();
                }
                turns++;
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateBoard(int move) {
        if(player == 0) {
            threads[1].updateOppoMove(move);
            player = 1;
        }
        else {
            threads[0].updateOppoMove(move);
            player = 0;
        }
    }
}
