import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class GUIApplication {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIApplication window = new GUIApplication();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIApplication() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton drawButton = new JButton("Draw Card");
		drawButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		drawButton.setBounds(327, 6, 117, 29);
		frame.getContentPane().add(drawButton);
		
		JButton quitButton = new JButton("Quit");
		quitButton.setBounds(6, 6, 117, 29);
		frame.getContentPane().add(quitButton);
		
		JButton restartButton = new JButton("Restart Game");
		restartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		restartButton.setBounds(165, 243, 117, 29);
		frame.getContentPane().add(restartButton);
		
		JLabel statusLabel = new JLabel("Game Status");
		statusLabel.setBounds(165, 111, 117, 42);
		frame.getContentPane().add(statusLabel);
		
		
	}
}
