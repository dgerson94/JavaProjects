//Card Class represents a playing card in the game of War.
public class Card {
	private final String face; //face on the card (King,Queen, Nine, etc.)
	private final String suit; //suit of the card (Spade/Diamond/Heart/Spades)
	private final int value; //value of card in game of War
	private static final String TIE = "TIE";

	//three argument constructor initializes card's face, suit, and value.
	public Card (String cardFace, String cardSuit, int cardValue){
		this.face = cardFace;
		this.suit = cardSuit;
		this.value = cardValue;
	}

	//return String representation of Card.
	public String toString() {
		return face + " of " + suit;
	}

	//returns the card value, 2 is the lowest with a value of zero, and ace is the highest with value of 12
	public int getValue() {
		return value;
	}

	/*compares to cards and gives us the name of winner. 
	 * Arguments:	First - the name of the player who's card that the method calls to.
	 * 				Second - the second card in the battle.
	 * 				Third - the name of the player who's card was put in the second argument.
	 * If there is a tie, then return the string "TIE".
	 */
	public String battle(String player1, Card c2, String player2) {
		if (this.getValue()>c2.getValue())
			return player1;
		else if (this.getValue()<c2.getValue())
			return player2;
		else
			return TIE;
	}
}
