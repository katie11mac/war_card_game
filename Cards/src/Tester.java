import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tester {

	public static void main(String[] args) {
		Card card1 = new Card("9", "spades"); 
		System.out.println(card1); 
		
		Card card2 = new Card("ace", "clubs"); 
		System.out.println(card2); 
		
		Card card3 = new Card("King", "diamonds"); 
		System.out.println(card3); 
		
		Card card4 = new Card("9", "hearts"); 
		System.out.println(card4); 
		
		System.out.println(); 
		
		System.out.println("*****PRINTING DECK*****"); 
		
		DeckOfCards deck = new DeckOfCards(); 
		//deck.shuffle();
		System.out.print(deck);  
		
		System.out.println("*****DONE*****"); 
//		
//		System.out.println(); 
//		
//		System.out.println("*****COMPARE TO RESULTS*****"); 
//		System.out.println(card1.compareTo(card2));  
//		System.out.println(card1.compareTo(card3));  
//		System.out.println(card1.compareTo(card4));  
				
		DeckOfCards smallDeck = new DeckOfCards(new ArrayList<>(Arrays.asList(card1, card2, card3, card4)));
		
		System.out.println("DRAWING CARD: " + smallDeck.drawCard());
		System.out.println("DECK NOW: " + smallDeck);
		System.out.println("ADDING CARD");
		smallDeck.addToDeck(card1); 
		System.out.println();
		System.out.println("DECK NOW: " + smallDeck);
		System.out.println();
		
		System.out.println("DRAWING CARD: " + smallDeck.drawCards(2)); 
		System.out.println("DECK NOW: " + smallDeck);
		ArrayList<Card> addToDeck = new ArrayList<>(Arrays.asList(card2, card3));
		System.out.println("ADDING CARDS");
		smallDeck.addToDeck(addToDeck);
		System.out.println("DECK NOW: " + smallDeck);
		
		//List<Card>[] splitDeck = deck.split(5); 
		//System.out.println(deck.compareTo(smallDeck));
		//System.out.println(splitDeck.getClass());
//		for(Card s : splitDeck[0]) { 
//			  System.out.println(s.toString()); 
//		}
//		for(Card s : splitDeck[1]) { 
//			  System.out.println(s.toString()); 
//		}
		//System.out.println(List.toString(deck.split(2))); 
		//System.out.println(smallDeck.split(4));
		
	//	System.out.println("PRINTING DECK: " + deck); 
	//	System.out.println("PRINITNG SMALL DECK: " + smallDeck); 
	}
	
	

}
