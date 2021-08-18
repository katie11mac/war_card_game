import java.util.Arrays;


import java.util.List;

public class Card implements Comparable<Card>{

	
	private String faceName; 
	private String suit; 
	private int ranking; 
	
	
	//------------------------------------CONSTRUCTORS-----------------------------------------
	/*
	 * Create a new Card object. 
	 */
	public Card() {
		
	}
	
	
	/*
	 * Create a new Card object with specified values. 
	 * 
	 * @param faceName - face of card 
	 * @param suit - suit of card 
	 */
	public Card(String faceName, String suit) {
		setFaceName(faceName); 
		setSuit(suit); 
		setRanking(); 
	}
	
	
	
	//---------------------------------------GETTERS-------------------------------------------
	/*
	 * Get face of Card object 
	 * 
	 * @return String - face name of Card
	 */
	public String getFaceName(){
		return this.faceName; 
	}
	
	
	/*
	 * Get suit of Card object 
	 * 
	 * @return String - suit name of Card
	 */
	public String getSuit() {
		return this.suit; 
	}
	
	
	/*
	 * Get ranking of Card object 
	 * 
	 * @return int - rank of Card (higher the value the higher the rank) 
	 */
	public int getRanking() {
		return this.ranking; 
	}
	
	
	
	//---------------------------------------SETTERS-------------------------------------------
	/*
	 * If the entry is valid, set the face of the Card object 
	 * 
	 * @param faceName - face name of Card
	 */
	public void setFaceName(String faceName) {
		faceName = faceName.toUpperCase(); 
		
		if(checkValidity(getValidFaceNames(), faceName))
			this.faceName = faceName; 
		else 
			throw new IllegalArgumentException("Valid face names are: " + Arrays.toString(getValidFaceNames())); 
	}
	

	/*
	 * If the entry is valid, set the suit of the Card object 
	 * 
	 * @param suit - suit of Card
	 */
	public void setSuit(String suit) {
		suit = suit.toUpperCase(); 
		
		if(checkValidity(getValidSuits(), suit))
			this.suit = suit; 
		else 
			throw new IllegalArgumentException("Valid suits are: " + Arrays.toString(getValidSuits()));  
	}
	
	
	/*
	 * Set the ranking of the a Card object, given that it has a valid face value 
	 */
	private void setRanking() {
		String[] faceNames = getValidFaceNames(); 
		
		this.ranking = -1; 
		
		// Assign the ranking based on the hard coded order of faces 
		for (int i = 0; (i < faceNames.length) && (ranking == -1); i++) {
			if (faceNames[i].equals(this.faceName.toUpperCase()))
				this.ranking = i + 1; 
		}
		
		if (this.ranking == -1)
			throw new IllegalArgumentException("Card is invalid because it does not have a ranking."); 
	}
	
	
	
	//------------------------------------OTHER METHODS-----------------------------------------
	/*
	 * Return the valid entries for a face name of a Card object 
	 * 
	 * @return String[] - array of all the valid entries for face name 
	 */
	public static String[] getValidFaceNames(){
		String[] validFaceNames = {"ACE", "2", "3", "4", "5", "6", "7", "8", "9", "10", "JACK", "QUEEN", "KING"}; 
		return validFaceNames; 
	}
	
	
	/*
	 * Return the valid entries for a suit of a Card object 
	 * 
	 * @return String[] - array of all the valid entries for a suit
	 */
	public static String[] getValidSuits(){
		String[] validSuits = {"HEARTS", "DIAMONDS", "CLUBS", "SPADES"}; 
		return validSuits; 
	}
	
	
	/*
	 * Check the validity of an entry value for the Card object 
	 * 
	 * @param validEntries - Array of Strings that are valid entries or valid  
	 * @param entry - String to check if it is a valid entry 
	 * 
	 * @return boolean - true if entry is valid and found in validEntries 
	 * 				   - false if entry is not valid and not found in valid Entries 
	 */
	private boolean checkValidity(String[] validEntries, String entry) {
		for (String e : validEntries) {
			if (entry.equals(e))
				return true; 
		}
		return false; 
	}
	
	
	/*
	 * Compares two Card objects. 
	 * 
	 * @param other 
	 * 
	 * @return int - 0 if Cards have the same ranking 
	 * 			   - -1 if this Card has a smaller ranking than the other Card 
	 * 			   - 1 if this Card has a bigger ranking than the other Card
	 */
	public int compareTo(Card other) {
		if (this.ranking == other.ranking) 
			return 0; 
		else if (this.ranking < other.ranking)
			return -1; 
		else // if this > other 
			return 1; 	
	}
	
	
	/*
	 * Return information of the Card object in a clean format 
	 * 
	 * @return String - faceName and suit of Card object 
	 */
	public String toString() {
		return (this.faceName + " of " + this.suit + " "); 
	}
	
	
}
