import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


/**
 * Game of war through the console. 
 * 
 * @author kmacalintal
 *
 */

public class Driver {
	
	public static DeckOfCards deck = new DeckOfCards(); 
	
	public static Scanner scan = new Scanner(System.in); 
	
	public static void main(String[] args) {
		warGame(); 
	}

	public static boolean warGame() {
		
		System.out.println("***PLAYING WAR***");
		
		deck.shuffle();
		System.out.println("***DECK HAS BEEN SHUFFLED***"); 
		
		ArrayList<Card>[] splitDeck = deck.split(2); 
		
		Queue<Card> computerPile = new LinkedList<Card>(splitDeck[0]);
		Queue<Card> playerPile = new LinkedList<Card>(splitDeck[1]);
		
		System.out.println("***DECK HAS BEEN SPLIT INTO TWO PILES***"); 
		System.out.println();
		
		System.out.println("***BEGINNING GAME***");

		boolean playing = true; 
		
		System.out.println();
		
		while((computerPile.size() > 0) && (playerPile.size() > 0) && (playing == true)) {
			
//			System.out.println("COMPUTER PILE: " + computerPile); 
//			System.out.println("YOUR PILE: " + playerPile); 
			
			Card computerCard = computerPile.remove(); 
			Card playerCard = playerPile.remove(); 
			
			System.out.println("Computer Card: " + computerCard); 
			System.out.println("Player Card: " + playerCard);
			
			int compareResults = (computerCard.compareTo(playerCard)); 
			
			System.out.print("Results: ");
			
			if (compareResults == -1) { // computer has less card than player 
				playerPile.add(computerCard); 
				playerPile.add(playerCard); 
				System.out.println("You WON this round! :) "); 
			} else if (compareResults == 1) {
				computerPile.add(computerCard); 
				computerPile.add(playerCard); 
				System.out.println("You LOST this round. :("); 
			} else { 
				System.out.println("*!!!WAR!!!*");
				ArrayList<Card> prize = new ArrayList<Card>(); 
				prize.add(computerCard); 
				prize.add(playerCard); 
				
				boolean warResults = war(computerPile, playerPile, prize); 
				
				if (warResults == false) {
					playing = false;
					return true; 
				}
			}
			
			System.out.println();
			System.out.println("Type anything to draw a card or q to quit.");
			char choice = scan.nextLine().strip().toLowerCase().charAt(0); 
			
			if (choice == 'q')
				playing = false; 

			System.out.println();
			
		}
		
		if(playerPile.size() == 0 || computerPile.size() == 0) {
			System.out.println("It looks like someone ran out of cards...");
		}
		
		System.out.println();
		System.out.println("The computer has " + computerPile.size() + " cards,");
		System.out.println("and you have " + playerPile.size() + " cards, so...");
		
		if (computerPile.size() > playerPile.size() ) {
			System.out.println("COMPUTER HAS WON THE GAME!!!"); 
		} else if (computerPile.size() < playerPile.size() ){
			System.out.println("YOU HAVE WON THE GAME!!!");
		}
		
		deck.addToDeck(new ArrayList<Card>(computerPile));
		deck.addToDeck(new ArrayList<Card>(playerPile));
		//System.out.println(deck); 
		//System.out.println(deck.getSize());
		
		return true;
		
	}
	
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
	
}
