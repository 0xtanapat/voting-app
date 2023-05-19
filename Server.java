package voting;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
	public static void main(String args[]) {
		int port = 3000;
		List<Integer> numbersReceived = new ArrayList<>();
		
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			serverSocket.setReuseAddress(true);
            System.out.println("Server started on port " + port + ". " + "Waiting for a client to connect...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
            int eligible_voters = dataInputStream.readInt();
            int party_num = dataInputStream.readInt();
            System.out.println("Received number of eligible voters and number of parties from client: " + eligible_voters + " and " + party_num + " respectively.");

            dataInputStream.close();
            clientSocket.close();
        
            while (numbersReceived.size() < eligible_voters) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected.");

                InputStream inputStream = socket.getInputStream();
                int number = inputStream.read();
                numbersReceived.add(number);

                System.out.println("Received number: " + number);

                socket.close();
            }
            System.out.println("All numbers received. Server is stopping.");
            serverSocket.close();
		} catch (IOException e) {
            e.printStackTrace();
        }
	}
}
