package csci2020u.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static int player;
    private static ServerThread[] threads;
    private static WinChecker[] winCheckers;

    public static void main(String[] args) {
        // Server class parameters
        int numClients;
        int serverPort = 1024;
        int maxClients = 2;
        ServerSocket serverSocket;
        Socket clientSocket;
        int turns;

        // Symbols that will be assigned to each client
        String[] symbol = new String[]{"X", "O"};
        boolean firstPlayer;
        boolean aWin;

        // Looks for two clients to establish a connection to the server
        try {
            serverSocket = new ServerSocket(serverPort);        // Create the server socket
            threads = new ServerThread[maxClients];             // Create the thread array
            winCheckers = new WinChecker[maxClients];
            while(true) {
                numClients = 0;
                firstPlayer = true;
                aWin = false;
                turns = 0;
                player = 0;
                while(numClients < 2) {                             // Establish a connection with the client
                    clientSocket = serverSocket.accept();
                    threads[numClients] = new ServerThread(clientSocket, symbol[numClients], firstPlayer);  // Pass the client thread its socket and symbol
                    threads[numClients].start();   // Execute the thread
                    firstPlayer = false;
                    winCheckers[numClients] = new WinChecker();
                    numClients++;
                }
                // Indicates that the two clients are ready to play the game
                threads[0].setReady();
                threads[1].setReady();

                // Begin assigning turns to the clients
                while(turns < 9 && !aWin) {
                    if(player == 0) {
                        threads[0].updateTurn(1);
                        threads[1].updateTurn(0);
                        threads[0].makeMove();
                        aWin = winCheckers[0].checkWin();
                        threads[0].setWin(aWin);
                    }
                    else {
                        threads[0].updateTurn(0);
                        threads[1].updateTurn(1);
                        threads[1].makeMove();
                        winCheckers[1].checkWin();
                        aWin = winCheckers[1].checkWin();
                        threads[1].setWin(aWin);
                    }
                    threads[0].updateWin(aWin);
                    threads[1].updateWin(aWin);
                    turns++;
                }

                threads[0].exit();
                threads[1].exit();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateBoard(int move) {
        if(player == 0) {
            threads[1].updateOppoMove(move);
            winCheckers[player].update(move);
            player = 1;
        }
        else {
            threads[0].updateOppoMove(move);
            winCheckers[player].update(move);
            player = 0;
        }
    }
}
