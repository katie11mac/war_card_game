import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI implements ActionListener{
	
	
	public GUI() { //constructor 
		
		int windowWidth = 640;
		int windowHeight = windowWidth/12*9; 
		
		JFrame frame = new JFrame(); 
		
		JPanel panel = new JPanel(); 
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		panel.setLayout(null);
		
		JButton quitButton = new JButton("Quit"); 
		quitButton.setBounds(0, 10, 125, 25);
		panel.add(quitButton); 
		quitButton.addActionListener(this); 
		
		JButton drawButton = new JButton("Draw Card"); 
		drawButton.setBounds(windowWidth - 125, 10, 125, 25);
		panel.add(drawButton); 
		drawButton.addActionListener(this); 
		
		
		JLabel statusLabel = new JLabel("STATUS"); 
		statusLabel.setBounds(windowWidth/2 - 25, windowHeight/2 - 60,  125, 60);
		panel.add(statusLabel); 
		
		frame.add(panel, BorderLayout.CENTER); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Card Game: War");
		frame.setPreferredSize(new Dimension(windowWidth, windowHeight));
		frame.setMaximumSize(new Dimension(windowWidth, windowHeight));
		frame.setMinimumSize(new Dimension(windowWidth, windowHeight));
		frame.setResizable(false); // Cannot resize window; prevents problem
		frame.setLocationRelativeTo(null); // Window starts in center of screen
		frame.pack(); 
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GUI(); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
