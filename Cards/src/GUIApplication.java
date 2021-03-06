import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
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

/**
 * 
 * Card game of war through JAVASwing. 
 * 
 * @author kmacalintal
 *
 */

public class GUIApplication {

	
	
	//-------GUI OBJECTS------
	private JFrame frame;
	private JButton drawButton; 
	private JButton quitButton; 
	private JButton restartButton; 
	private JLabel updateLabel; 
	private JLabel resultsLabel; 
	private JLabel playerCardImg; 
	private JLabel computerCardImg;
	
	
	//-----BACKEND OBJECTS------
	public static DeckOfCards deck;
	public static Queue<Card> computerPile; 
	public static Queue<Card> playerPile; 

	
	//-------WAR VARIABLES------
	// Used to help navigate and determine whether war needs to happen 
	public static boolean war; 
	ArrayList<Card> prize = new ArrayList<Card>(); 
	

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		initializeGame(); 
		System.out.println("GAME INITALIZED"); 
		
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
	 * Set the game up for war (eg. shuffle and split the deck into two piles).
	 * 
	 */
	public static void initializeGame() {

		deck = new DeckOfCards(); 
		war = false; 
		deck.shuffle();
		
		System.out.println(deck); 
		System.out.println("***DECK HAS BEEN SHUFFLED***"); 
		
		ArrayList<Card>[] splitDeck = deck.split(2); 
		
		computerPile = new LinkedList<Card>(splitDeck[0]);
		playerPile = new LinkedList<Card>(splitDeck[1]);
		
	}
	
	
	
	/**
	 * Compare the two cards drawn and communicate the results with the player. 
	 * 
	 * @param computerCard - Card object drawn from the top of the computer's pile 
	 * @param playerCard - Card object drawn from the top of the computer's pile 
	 */
	public void roundResults(Card computerCard, Card playerCard) {
		
		updateLabel.setText("ROUND RESULTS");
		
		// Display cards drawn by each player 
		computerCardImg.setIcon(new ImageIcon(computerCard.getImage().getScaledInstance(110, 170, Image.SCALE_SMOOTH))); 
		playerCardImg.setIcon(new ImageIcon(playerCard.getImage().getScaledInstance(110, 170, Image.SCALE_SMOOTH))); 
		
		
		// Compare the cards and analyze it 
		int compareResults = (computerCard.compareTo(playerCard)); 
		
		String results = "<html> Computer: " + computerCard + " <br/> You: " + playerCard + "<br/>"; 
		
		if (compareResults == -1) { // Computer drew card lower than Player 
			playerPile.add(computerCard); 
			playerPile.add(playerCard); 
			resultsLabel.setText(results + "You won this round! </html>");
			war = false; 
		} else if (compareResults == 1) { // Computer drew card higher than Player 
			computerPile.add(computerCard); 
			computerPile.add(playerCard); 
			resultsLabel.setText(results + "You lost this round! </html>");
			war = false; 
		} else { // Both players drew cards of the same rank 
			prize.add(computerCard); 
			prize.add(playerCard); 
			resultsLabel.setText(results + "WAAARRR! </html>");
			war = true; 
		}
		
	}
	
	
	
	/**
	 * Game of War 
	 * 
	 * "If the cards are the same rank, it is War. 
	 * Each player turns up one card face down and one card face up. 
	 * The player with the higher cards takes both piles (six cards). 
	 * If the turned-up cards are again the same rank, each player places 
	 * another card face down and turns another card face up. 
	 * The player with the higher card takes all 10 cards, and so on." 
	 * 
	 * via https://bicyclecards.com/how-to-play/war/ 
	 * 
	 * @param computerPile - computer's pile of cards 
	 * @param playerPile - player's pile of cards 
	 * @param prize - ArrayList<Cards> that hold all the cards the winner of war would get 
	 */
	public void war(Queue<Card> computerPile, Queue<Card> playerPile, ArrayList<Card> prize) {
		
		updateLabel.setText("***WAR***");
		System.out.println();
		System.out.println("***WAR***"); 
		
		// Not enough cards in each pile to play war 
		if ((computerPile.size() < 2) || (playerPile.size() < 2)) {
			System.out.println("There were not enough cards to play war. But here are the results..."); 
			updateLabel.setText("There were not enough cards to play war. But here are the results..."); 
			finalResults(); 
			
		} else {
			
			// Extra cards that have to be given up
			prize.add(computerPile.remove()); 
			prize.add(playerPile.remove()); 
			
			
			// Cards that will be compared 
			Card computerCard = computerPile.remove(); 
			computerCardImg.setIcon(new ImageIcon(computerCard.getImage().getScaledInstance(110, 170, Image.SCALE_SMOOTH))); 
			Card playerCard = playerPile.remove(); 
			playerCardImg.setIcon(new ImageIcon(playerCard.getImage().getScaledInstance(110, 170, Image.SCALE_SMOOTH))); 

			
			// Analyzing results 
			String results = "<html> Each player has turned one card face down. <br/> Computer: " + computerCard + " <br/> You: " + playerCard + "<br/>";
			
			int compareResults = (computerCard.compareTo(playerCard)); 
			
			prize.add(computerCard); 
			prize.add(playerCard); 
			
			System.out.println(prize); 
			
			if (compareResults == -1) { // Computer drew card lower than Player 
				playerPile.addAll(prize); 
				results += "YOU WON THE WAR AND GET ALL THE CARDS DRAWN"; 
				prize.clear();
				war = false; 
			} else if (compareResults == 1) { // Computer drew card higher than Player 
				computerPile.addAll(prize); 
				results += "COMPUTER WON THE WAR AND GETS ALL THE CARDS DRAWN"; 
				prize.clear();
				war = false; 
			} else { // Both players drew cards of the same rank; another round of war 
				results += "WAR ... AGAIN";
				war = true; 
			}
			
			resultsLabel.setText(results);
			
		}
		
	}
	
	
	
	/**
	 * Compare the size of the computer's and player's pile.
	 * Communicate the results with the user. The bigger pile wins. 
	 * 
	 */
	public void finalResults() {
		
		showBackOfDeck(); 
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
	
		resultsLabel.setText(results);
		
	}
	
	
	
	/**
	 * Display the card on the back of the deck
	 *
	 */
	public void showBackOfDeck() {
		computerCardImg.setIcon(new ImageIcon(deck.getBackOfDeck().getScaledInstance(110, 170, Image.SCALE_SMOOTH))); 
		playerCardImg.setIcon(new ImageIcon(deck.getBackOfDeck().getScaledInstance(110, 170, Image.SCALE_SMOOTH))); 
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
		frame.setBounds(100, 100, 605, 385);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		// ---------RESTART BUTTON-----------
		restartButton = new JButton("Restart Game");
		restartButton.addActionListener(new ActionListener() {
			/*
			 * Restart the game. 
			 */
			public void actionPerformed(ActionEvent e) {
	
				showBackOfDeck(); 
				updateLabel.setText("The game has been reset. Press Draw Card to being.");
				resultsLabel.setText(""); 
				initializeGame(); 
				
			}
		});
		restartButton.setBounds(243, 328, 117, 29);
		frame.getContentPane().add(restartButton);
		
		
		
		// ----------QUIT BUTTON---------
		quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener() {
			/*
			 * Quit/Pause the game currently being played
			 */
			public void actionPerformed(ActionEvent e) {
				showBackOfDeck(); 
				updateLabel.setText("You quit the game.");
				resultsLabel.setText("<html> Press Draw Card to continue game. <br/> Or press Restart Game to play a new game. </html>");
			}
		});
		quitButton.setBounds(6, 6, 117, 29);
		frame.getContentPane().add(quitButton);
		
		
		
		// ----------DRAW BUTTON---------
		drawButton = new JButton("Draw Card");
		drawButton.addActionListener(new ActionListener() {
			/*
			 * Draw another card from each player's pile. 
			 */
			public void actionPerformed(ActionEvent e) {
				updateLabel.setText("");
				
				System.out.println("DRAW BUTTON PRESSED"); 
				System.out.print("Computer: " + computerPile.size()); 
				System.out.println(" Player: " + playerPile.size());
				
				
				if ((computerPile.size() > 0) && (playerPile.size() > 0) && war == false) {
					// Regular round 
					Card computerCard = computerPile.remove(); 
					Card playerCard = playerPile.remove(); 
					roundResults(computerCard, playerCard); 
				} else if ((computerPile.size() > 0) && (playerPile.size() > 0) && war == true) {
					// Round for war
					war(computerPile, playerPile, prize); 
				} else { 
					// Not enough cards to play anymore 
					finalResults(); 
				}
			}
		});
		drawButton.setBounds(482, 6, 117, 29);
		frame.getContentPane().add(drawButton);
		
		
		
		// ------- UPDATE LABEL ---------
		updateLabel = new JLabel("");
		updateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		updateLabel.setBounds(85, 45, 438, 25);
		frame.getContentPane().add(updateLabel);

		
		
		// ------- RESULTS LABEL -------
		resultsLabel = new JLabel("", SwingConstants.CENTER);
		resultsLabel.setBounds(149, 108, 304, 174);
		frame.getContentPane().add(resultsLabel);
		
		
		
		// ------ PLAYER'S IMAGE -------
		playerCardImg = new JLabel("Your Cards");
		playerCardImg.setBounds(482, 108, 117, 174);
		frame.getContentPane().add(playerCardImg);
		
		
		
		// ------ COMPUTER'S IMAGE -------
		computerCardImg = new JLabel("Computer's Cards");
		computerCardImg.setBounds(6, 108, 117, 174);
		frame.getContentPane().add(computerCardImg);
				
		
	}
}
