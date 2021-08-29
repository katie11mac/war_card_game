import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;

public class DeckOfCards extends ArrayList<Card>{

	
	
	private ArrayList<Card> deck; // Size may change 
	private Image backOfDeck; 
	
	
	
	//------------------------------------CONSTRUCTORS-----------------------------------------
	/**
	 * Pass in a collection of Card objects (eg. specialized deck of cards) 
	 * 
	 * @param deck - Made deck of Card objects 
	 */
	public DeckOfCards(ArrayList<Card> deck) {
		this.deck = deck; 
	}
	
	
	/**
	 * Build a full deck of regular cards.
	 */
	public DeckOfCards() {
		String[] faceNames = Card.getValidFaceNames(); // static method (do not need an instance of a Card) 
		String[] suits = Card.getValidSuits(); 
		setBackOfDeck(new ImageIcon(this.getClass().getResource("/backOfCard.png" )).getImage()); 
		
		deck = new ArrayList<>(); 
		
		for (String faceName: faceNames) {
			for (String suit: suits) {
				deck.add(new Card(faceName, suit));  
			}
		}
	}
	
	
	
	//---------------------------------------SETTERS-------------------------------------------
	/**
	 * Set the deck to an already made deckOfCards
	 * 
	 * @param deck - collection of Card objects 
	 */
	public void setDeckOfCards(ArrayList<Card> deck) {
		this.deck = deck; 
	}
	

	/**
	 * Set the image on the back of the DeckOfCards
	 * 
	 * @param backOfDeck - Image for the back of the DeckOfCards 
	 */
	public void setBackOfDeck(Image backOfDeck) {
		this.backOfDeck = backOfDeck;
	}

	

	//---------------------------------------GETTERS-------------------------------------------
	/**
	 * Return deck of Card objects 
	 * 
	 * @return ArrayList<Card> - This collection of cards 
	 */
	public ArrayList<Card> getDeckOfCards() {
		return this.deck; 
	}
	
	
	/**
	 * Return back of deck Image
	 * 
	 * @return Image - Current backOfDeck Image
	 */
	public Image getBackOfDeck() {
		return backOfDeck;
	}

	
	
	// ------ IMPLEMENT SORTING METHODS HERE? ------ 
	
	
	
	//------------------------------------OTHER METHODS-----------------------------------------
	/**
	 * Randomly rearrange the order of the Cards in the collection 
	 */
	public void shuffle() {
		Collections.shuffle(this.deck);
	}
	
	
	/**
	 * Add a Card object to the deck or collection of Card objects 
	 * 
	 * @param card - Card object to add to collection 
	 */
	public void addToDeck(Card card) {
		this.deck.add(card); 
	}
	
	
	/**
	 * Add a collection of Card objects to the deck 
	 * 
	 * @param cards - collection of Card objects to add to the collection 
	 */
	public void addToDeck(ArrayList<Card> cards) {
		this.deck.addAll(cards); 
	}
	
	
	/**
	 * Take the first Card object from the deck 
	 * 
	 * @return Card - first Card in the collection 
	 */
	public Card drawCard() {
		return this.deck.remove(0); 
	}
	
	
	/**
	 * Take the first n number of Card objects from the deck 
	 * 
	 * @param n - number of Card objects to take from deck 
	 * @return ArrayList<Card> - collection of the Card objects taken from the deck 
	 */
	public ArrayList<Card> drawCards(int n){
		ArrayList<Card> cardsFromDeck = new ArrayList<Card>(); 
		for (int i = 0; i < n; i++) {
			cardsFromDeck.add(this.deck.remove(0)); 
		}
		return cardsFromDeck; 
	}
	
	
	/**
	 * Split the deck into n number of piles 
	 * 
	 * @param n - number of piles the deck of Card objects should be split into 
	 * @return ArrayList<Card>[] - array of Card ArrayLists, 
	 * 							   where each ArrayList represents a different pile of cards
	 */
	public ArrayList<Card>[] split(int n){
		
		// Validity Check: Make sure there are enough cards to make the split 
		if (deck.size() < n)
			throw new IllegalArgumentException("Not enough Cards in DeckOfCards to spilt into " + n + " piles");
		
		
		ArrayList<Card>[] piles = new ArrayList[n];
		
		
		int pileSize = deck.size() / n; 
		int remainder = deck.size() % n; // Need to account for uneven splits 
		
		
		// This may be really slow, try to find a faster way soon 
		
		int cardIndex = 0; //Tracking what card in the deck you are on (0 to 51) 
		
		for(int pilesMade = 0; pilesMade < n; pilesMade++) {
			piles[pilesMade] = new ArrayList<Card>(); 
			
			if(pilesMade == n - 1) {
				for(int c = 0; c < pileSize + remainder; c++) {
					//c: number of cards added to the pile
					piles[pilesMade].add(c, deck.remove(0)); 
					cardIndex++; 
				}
			} else { //last pile being made; want to add the rest of the cards to the pile 
				for(int c = 0; c < pileSize; c++) {
					//c: number of cards added to the pile
					piles[pilesMade].add(c, deck.remove(0)); 
					cardIndex++; 
				}
			}
			
		}
		
		return piles; 
	}
	
	
	/**
	 * Return information of the deckOfCards object in a clean format 
	 * 
	 * @return String - Every faceName and corresponding suit of each Card object 
	 */
	public String toString() {
		String deckString = ""; 
		for (Card card: deck) {
			deckString += card.toString() + " \n"; 
		}
		return deckString; 
	}
	
}
 