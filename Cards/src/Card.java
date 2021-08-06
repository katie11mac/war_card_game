import java.util.Arrays;


import java.util.List;

public class Card implements Comparable<Card>{

	private String faceName; 
	private String suit; 
	private int ranking; 
	
	
	//------------------------------------CONSTRUCTORS-----------------------------------------
	public Card() {
		
	}
	
	public Card(String faceName, String suit) {
		setFaceName(faceName); 
		setSuit(suit); 
		setRanking(); 
	}
	
	//---------------------------------------GETTERS-------------------------------------------
	public String getFaceName(){
		return this.faceName; 
	}
	
	public String getSuit() {
		return this.suit; 
	}
	
	public int getRanking() {
		return this.ranking; 
	}
	
	//---------------------------------------SETTERS-------------------------------------------
	
	public void setFaceName(String faceName) {
		faceName = faceName.toUpperCase(); 
		
		if(checkValidity(getValidFaceNames(), faceName))
			this.faceName = faceName; 
		else 
			throw new IllegalArgumentException("Valid face names are: " + Arrays.toString(getValidFaceNames())); 
	}

	
	public void setSuit(String suit) {
		suit = suit.toUpperCase(); 
		
		if(checkValidity(getValidSuits(), suit))
			this.suit = suit; 
		else 
			throw new IllegalArgumentException("Valid suits are: " + Arrays.toString(getValidSuits()));  
	}
	
	private void setRanking() {
		String[] faceNames = getValidFaceNames(); 
		
		this.ranking = -1; 
		
		for (int i = 0; (i < faceNames.length) && (ranking == -1); i++) {
			if (faceNames[i].equals(this.faceName.toUpperCase()))
				this.ranking = i + 1; 
		}
		
		if (this.ranking == -1)
			throw new IllegalArgumentException("Card is invalid because it does not have a ranking."); 
	}
	
	
	//------------------------------------OTHER METHODS-----------------------------------------
	
	public static String[] getValidFaceNames(){
		String[] validFaceNames = {"ACE", "2", "3", "4", "5", "6", "7", "8", "9", "10", "JACK", "QUEEN", "KING"}; 
		return validFaceNames; 
	}
	
	public static String[] getValidSuits(){
		String[] validSuits = {"HEARTS", "DIAMONDS", "CLUBS", "SPADES"}; 
		return validSuits; 
	}
	
	private boolean checkValidity(String[] validEntries, String entry) {
		for (String e : validEntries) {
			if (entry.equals(e))
				return true; 
		}
		return false; 
	}
	
	public int compareTo(Card other) {
		if (this.ranking == other.ranking) 
			return 0; 
		else if (this.ranking < other.ranking)
			return -1; //any neg 
		else // if this > other 
			return 1; //any pos 	
	}
	
	public String toString() {
		return (this.faceName + " of " + this.suit + " "); 
	}
	
}
