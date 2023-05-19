package voting;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class BallotPaperFrame extends JFrame implements ActionListener {
	JLabel label;
	ButtonGroup group;
	JButton btn;
	
	int eligible_voters;
	int party_num;
	int counter = 0;
	
	BallotPaperFrame(int eligible_voters, int party_num) {
		this.eligible_voters = eligible_voters;
		this.party_num = party_num;
		
		label = new JLabel();
		label.setText("Ballot Paper");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.TOP);
		
		setTitle("Ballot paper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
        setSize(640, 640);
        setLayout(new GridLayout(10, 1, 10, 10));
        
        add(label);
        
        group = new ButtonGroup();
        for (int i = 0; i < party_num; i++) {
        	JRadioButton radioBtn = new JRadioButton("Party " + (i + 1));
        	group.add(radioBtn);
			add(radioBtn);
		}
        
        btn = new JButton("Submit");
        btn.addActionListener(this);
//        int btnWidth = 100;
//        int btnHeight = 50;
//        int btnX = (getWidth() - btnWidth) / 2;
//        int btnY = getHeight() - btnHeight;
//        btn.setBounds(btnX, btnY, btnWidth, btnHeight);
        add(btn);
        
        setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn) {
			counter++;
			if (counter > eligible_voters) {
				JOptionPane.showMessageDialog(null, "Error!");
            } else {
            	sendNumberToServer(party_selected);
            	System.out.println("Sent " + counter);
            }
		}
	}
	
	private void sendNumberToServer(int counter) {
		String localhost = "127.0.0.1";
		int port = 3000;
		
		try (Socket socket = new Socket(localhost, port)) {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(counter);
            outputStream.flush();
            JOptionPane.showMessageDialog(null, "Number sent to the server: " + counter);
        } catch (IOException e1) {
            e1.printStackTrace();
        }  
	}
}
