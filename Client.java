package voting;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main(String args[]) {
		String localhost = "127.0.0.1";
		int port = 3000;
		
		int eligible_voters = 10;
		int party_num = 5;
		
		try (Socket socket = new Socket(localhost, port)) {
			System.out.println("Connected to server on port " + port + ": " + localhost);
			
			DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
			dataOutputStream.writeInt(eligible_voters);
			dataOutputStream.writeInt(party_num);
			dataOutputStream.flush();
			System.out.println("Sent number of eligible voters and number of parties to the server.");
			
			socket.close();
			dataOutputStream.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		new BallotPaperFrame(eligible_voters, party_num);
	}
}
