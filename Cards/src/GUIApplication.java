import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
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
	
	
	public static Card card1 = new Card("9", "spades"); 
	public static Card card2 = new Card("ace", "clubs"); 
	public static Card card3 = new Card("King", "diamonds"); 
	public static Card card4 = new Card("9", "hearts"); 
	
	public static DeckOfCards deck;

	
	//public static DeckOfCards deck = new DeckOfCards();
	
	
	
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
		deck = new DeckOfCards(new ArrayList<>(Arrays.asList(card1, card2, card3, card4))); 
		deck.shuffle();
		System.out.println(deck); 
		System.out.println("***DECK HAS BEEN SHUFFLED***"); 
		
		ArrayList<Card>[] splitDeck = deck.split(2); 
		
		computerPile = new LinkedList<Card>(splitDeck[0]);
		playerPile = new LinkedList<Card>(splitDeck[1]);
		
	}
	
	// returns true if the player has won 
	public boolean roundResults(Card computerCard, Card playerCard) {
		int compareResults = (computerCard.compareTo(playerCard)); 
		
		String results = "<html> Computer: " + computerCard + " <br/> You: " + playerCard + "<br/>"; 
		
		if (compareResults == -1) { // computer has less card than player 
			playerPile.add(computerCard); 
			playerPile.add(playerCard); 
			statusLabel.setText(results + "You won this round! </html>");
			return true; 
		} else if (compareResults == 1) {
			computerPile.add(computerCard); 
			computerPile.add(playerCard); 
			statusLabel.setText(results + "You lost this round! </html>");
			return false; 
		} else { 
			// NEED TO CODE THIS WAR LATER 
			statusLabel.setText(results + "WAAARRR! </html>");
			return false; 
		}
	}
	
	
	// NEED TO GO OVER THIS
	public static boolean war(Queue<Card> computerPile, Queue<Card> playerPile, ArrayList<Card> prize) {
		
		System.out.println();
		if ((computerPile.size() < 2) || (playerPile.size() < 2)) {
			System.out.println("There were not enough cards to play war. But here are the results..."); 
			if (computerPile.size() > playerPile.size() ) {
				System.out.println("COMPUTER HAS WON THE GAME!!!"); 
			} else if (computerPile.size() < playerPile.size() ){
				System.out.println("YOU HAVE WON THE GAME!!!");
			}
			deck.addToDeck(prize);
			return false; 
		}
		
		System.out.println("***WAR***");
		prize.add(computerPile.remove()); 
		prize.add(playerPile.remove()); 
		
		System.out.println("Each player has thrown their cards for war..."); 
		
		
		Card computerCard = computerPile.remove(); 
		Card playerCard = playerPile.remove(); 
		
		System.out.println("COMPUTER CARD: " + computerCard); 
		System.out.println("PLAYER CARD: " + playerCard);
		
		int compareResults = (computerCard.compareTo(playerCard)); 
		
		prize.add(computerCard); 
		prize.add(playerCard); 
		
		System.out.print("Results: "); 
		
		if (compareResults == -1) { // computer has less card than player 
			playerPile.addAll(prize); 
			System.out.println("YOU WON THE WAR AND GET ALL THE CARDS DRAWN"); 
		} else if (compareResults == 1) {
			computerPile.addAll(prize); 
			System.out.println("COMPUTER WON THE WAR AND GETS ALL THE CARDS DRAWN"); 
		} else { // war 
			System.out.println("*!!!WAR ... AGAIN!!!*");
			war(computerPile, playerPile, prize); 
		}
		
		return true; 
	}
	
	public void finalResults() {
		String results = "<html>"; 
		
		if(playerPile.size() == 0 || computerPile.size() == 0) {
			results += "<br/>A player ran out of cards."; 
		}
	
		results += ("<br/>Computer: " + computerPile.size() + " cards");
		results += ("<br/>You: " + playerPile.size() + " cards");
		
		if (computerPile.size() > playerPile.size() ) {
			results += ("<br/>COMPUTER HAS WON THE GAME!!! </html>"); 
		} else if (computerPile.size() < playerPile.size() ){
			results += ("<br/>YOU HAVE WON THE GAME!!! </html>");
		} else {
			results += ("<br/> It was a tie!</html>"); 
		}
	
		statusLabel.setText(results);
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
				statusLabel.setText("The game has been reset. Press Draw Card to being.");
				initializeGame(); 
			}
		});
		restartButton.setBounds(165, 243, 117, 29);
		frame.getContentPane().add(restartButton);
		
		
		
		// ----------QUIT BUTTON---------
		quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finalResults(); 
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
				
					finalResults(); 
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
