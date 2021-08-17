import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class GUIApplication {

	private JFrame frame;
	private JButton drawButton; 
	private JButton quitButton; 
	private JButton restartButton; 
	private JLabel statusLabel; 
	
	
	public static DeckOfCards deck = new DeckOfCards();
	public static Queue<Card> computerPile; 
	public static Queue<Card> playerPile; 
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		initializeGame(); 
		System.out.println("GAME INITALIZED"); // DEBUGGING
		
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

	
	public static void initializeGame() {
		
		deck.shuffle();
		System.out.println("***DECK HAS BEEN SHUFFLED***"); 
		
		ArrayList<Card>[] splitDeck = deck.split(2); 
		
		computerPile = new LinkedList<Card>(splitDeck[0]);
		playerPile = new LinkedList<Card>(splitDeck[1]);
		
	}
	
	// returns true if the player has won 
	public boolean roundResults(Card computerCard, Card playerCard) {
		int compareResults = (computerCard.compareTo(playerCard)); 
		
		
		if (compareResults == -1) { // computer has less card than player 
			playerPile.add(computerCard); 
			playerPile.add(playerCard); 
			statusLabel.setText("<html> Computer: " + computerCard + " <br/> You: " + playerCard + "<br/>" + "You won this round! </html>");
			return true; 
		} else if (compareResults == 1) {
			computerPile.add(computerCard); 
			computerPile.add(playerCard); 
			statusLabel.setText("<html> Computer: " + computerCard + " <br/> You: " + playerCard + "<br/>" + "You lost this round! </html>");
			return false; 
		} else { 
			// NEED TO CODE THIS WAR LATER 
			statusLabel.setText("<html> Computer: " + computerCard + " <br/> You: " + playerCard + "<br/>" + "WAAARRR! </html>");
			return false; 
		}
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
		
		// -----------FRAME-----------
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		// -------RESET BUTTON-----------
		restartButton = new JButton("Restart Game");
		restartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statusLabel.setText("The game has been reset");
			}
		});
		restartButton.setBounds(165, 243, 117, 29);
		frame.getContentPane().add(restartButton);
		
		
		
		// ----------QUIT BUTTON---------
		quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statusLabel.setText("You have quit the game");
			}
		});
		quitButton.setBounds(6, 6, 117, 29);
		frame.getContentPane().add(quitButton);
		
		
		
		
		// ----------DRAW BUTTON---------
		drawButton = new JButton("Draw Card");
		drawButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("DRAW BUTTON PRESSED"); // DEBUGGING
				System.out.print("Computer: " + computerPile.size()); 
				System.out.println("Player: " + playerPile.size()); 
				if ((computerPile.size() > 0) && (playerPile.size() > 0)) {
					Card computerCard = computerPile.remove(); 
					Card playerCard = playerPile.remove(); 
					roundResults(computerCard, playerCard); 
				} else { 
				
					String results = ""; 
				
					if(playerPile.size() == 0 || computerPile.size() == 0) {
						results += "A player ran out of cards."; 
					}
				
					results += ("Computer: " + computerPile.size() + " cards");
					results += ("You: " + playerPile.size() + "cards");
					
					if (computerPile.size() > playerPile.size() ) {
						results += ("COMPUTER HAS WON THE GAME!!!"); 
					} else if (computerPile.size() < playerPile.size() ){
						results += ("YOU HAVE WON THE GAME!!!");
					}
				
					statusLabel.setText(results);
					
					deck.addToDeck(new ArrayList<Card>(computerPile));
					deck.addToDeck(new ArrayList<Card>(playerPile));
				}
			}
		});
		drawButton.setBounds(327, 6, 117, 29);
		frame.getContentPane().add(drawButton);
		
		
		
		
		
		// ------- GAME STATUS LABEL -------
		statusLabel = new JLabel("Game Status", SwingConstants.CENTER);
		statusLabel.setBounds(6, 47, 438, 184);
		frame.getContentPane().add(statusLabel);
		
		
	}
}
