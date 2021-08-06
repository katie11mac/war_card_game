import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckOfCards extends ArrayList<Card> implements Comparable<DeckOfCards>{

	private ArrayList<Card> deck; // Size may change 
	private int size; // key for comparing, may not be necessary 
	
	//------------------------------------CONSTRUCTORS-----------------------------------------
	
	/*
	 * Pass in a collection of Card objects (specialized deck of cards) 
	 */
	public DeckOfCards(ArrayList<Card> deck) {
		this.deck = deck; 
		this.size = deck.size(); 
	}
	
	
	/*
	 * Build a full deck of regular cards 
	 */
	public DeckOfCards() {
		String[] faceNames = Card.getValidFaceNames(); // static method (do not need an instance of a Card) 
		String[] suits = Card.getValidSuits(); 
		
		deck = new ArrayList<>(); 
		
		for (String faceName: faceNames) {
			for (String suit: suits) {
				deck.add(new Card(faceName, suit));  
			}
		}
		
		this.size = deck.size(); 
		
	}
	
	//---------------------------------------SETTERS-------------------------------------------
	
	public void setDeckOfCards(ArrayList<Card> deck) {
		this.deck = deck; 
	}
	
	public void setSize(int size) {
		this.size = size; 
	}
	
	//---------------------------------------GETTERS-------------------------------------------

	public ArrayList<Card> getDeckOfCards() {
		return this.deck; 
	}
	
	public int getSize() {
		return this.size;
	}
	
	//-----------------------------------SORTING METHODS----------------------------------------
	
	
	
	//------------------------------------OTHER METHODS-----------------------------------------
	
	public void shuffle() {
		Collections.shuffle(this.deck);
	}
	
	public void addToDeck(Card card) {
		this.deck.add(card); 
	}
	
	public void addToDeck(ArrayList<Card> cards) {
		this.deck.addAll(cards); 
	}
	
	public Card drawCard() {
		return this.deck.remove(0); 
	}
	
	public ArrayList<Card> drawCards(int numOfCards){
		ArrayList<Card> cardsFromDeck = new ArrayList<Card>(); 
		for (int i = 0; i < numOfCards; i++) {
			cardsFromDeck.add(this.deck.remove(0)); 
		}
		return cardsFromDeck; 
		
	}
	//add to deck, single card parameter 
	
	// add to deck, collection of cards 
	
	// draw from deck, top card 
	
	// draw from deck, number of cards 
	
	//ArrayList<Card>[]
	public ArrayList<Card>[] split(int numOfPiles){
		if (this.size < numOfPiles)
			throw new IllegalArgumentException("Not enough Cards in DeckOfCards to spilt into " + numOfPiles + " piles");
		
		ArrayList<Card>[] piles = new ArrayList[numOfPiles];
		
		
		//need to also account for uneven splits 
		int pileSize = this.size / numOfPiles; 
		int remainder = this.size % numOfPiles; 
		
		
		// This may be really slow, try to find a faster way soon 
		
		int cardIndex = 0; //keeps track of what card in the deck you are on 0 to 51 
		
		for(int pilesMade = 0; pilesMade < numOfPiles; pilesMade++) {
			piles[pilesMade] = new ArrayList<Card>(); 
			
			if(pilesMade == numOfPiles - 1) {
				for(int c = 0; c < pileSize + remainder; c++) {//think of c as the number of cards added to the pile
					
					piles[pilesMade].add(c, deck.remove(0)); 
					cardIndex++; 
				}
			} else {//last pile being made; want to add the rest of the cards to the pile 
				for(int c = 0; c < pileSize; c++) {//think of c as the number of cards added to the pile
					piles[pilesMade].add(c, deck.remove(0)); 
					cardIndex++; 
				}
			}
			
			//System.out.println(piles[pilesMade]);
			//System.out.println(piles[pilesMade].size()); 
		}
		
//		for(int i = 0; i < piles[0].size(); i++) { 
//			  System.out.println(piles[0].get(i)); 
//		}
//		
		return piles; 
	}
	
	public String toString() {
		String deckString = ""; 
		for (Card card: deck) {
			deckString += card.toString() + " \n"; 
		}
		return deckString; 
	}
	
	//honestly this might not be necessary 
	public int compareTo(DeckOfCards other) {
		if (this.size == other.size) 
			return 0; 
		else if (this.size < other.size)
			return -1; //any neg 
		else // if this > other 
			return 1; //any pos 	
	}
	
}
 