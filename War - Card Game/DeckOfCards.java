import java.security.SecureRandom;
import java.util.ArrayList;

//DeckOFCards class represents a deck of playing cards for the game War.

public class DeckOfCards {
	//random number generator
	private static final SecureRandom randomNumbers = new SecureRandom();
	private static final int NUMBER_OF_CARDS_IN_GAME = 52; //each game of war has 52 cards.
	private static final int CARDS_PER_SUIT = 13; //each suit has 13 cards.
	private String player; //name of player with this deck. The original deck belongs to "GAME".
	private ArrayList<Card> deck; //an arrayList that holds all the cards that a deck has.
	private static final int TOP_CARD = 0; 
	private static final int EMPTY = 0; 

	//constructor of full deck fills first deck with 52 playing cards
	public DeckOfCards(){
		String[] faces = {"Deuce","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack",
				"Queen","King","Ace"};
		String[] suits = {"Hearts","Diamonds","Clubs","Spades"};
		this.player = "GAME"; //a name that isn't shared with other players. Could have left empty.
		deck = new ArrayList<Card>(NUMBER_OF_CARDS_IN_GAME);
		//add the amount of cards in game to deck
		for (int count = 0; count <NUMBER_OF_CARDS_IN_GAME; count++) {
			deck.add(count, new Card(faces[count % CARDS_PER_SUIT], suits[count / CARDS_PER_SUIT], (count % CARDS_PER_SUIT)));
		}
	}

	//constructor of empty deck belonging to player. One argument constructor.
	public DeckOfCards(String playerName) {
		this.player = playerName; //player name
		deck = new ArrayList<Card>(EMPTY); //create empty deck for player, will receive cards from merge with game deck later.
	}

	//shuffle cards in deck
	public void shuffle () {
		//for each Card, pick another random Card from the deck and swap them
		for (int first = 0; first<deck.size();first++) {
			int second = randomNumbers.nextInt(deck.size());

			//swap current Card with randomly selected card.
			Card temp1 = deck.get(first);
			Card temp2 = deck.get(second);
			deck.set(first, temp2);
			deck.set(second, temp1);
		}


	}

	//deal one card.
	public Card dealCard() {
		//determine whether Cards remain to be dealt
		if ( deck.size() != EMPTY) {
			//return the top card and take out of deck
			Card top = deck.get(TOP_CARD) ; 
			deck.remove(TOP_CARD);
			return top;
		}
		else {
			System.out.println("Deck is empty, can't deal card");
			return null;
		}
	}

	//get deck size
	public int size () {
		return deck.size();
	}

	//add cards in newDeck to deck 
	public void merge (DeckOfCards newDeck) {
		while (newDeck.size()!= EMPTY) {
			deck.add(newDeck.dealCard());
		}
	}

	//get name of player of this deck
	public String getPlayer() {
		return player;
	}

	/*splits deck between two other decks given in argument. Only use on a full deck of cards. 
	Could use deck.size() instead of static variable if this function was needed in any case other than splitting the game deck.*/
	public void split(DeckOfCards deck1, DeckOfCards deck2) {
		//go through the first half of the cards and put in deck 1.
		for (int i=0; i < NUMBER_OF_CARDS_IN_GAME/2; i++) {
			deck1.add(deck.get(i));
		}
		//go through the second half of the cards and put in deck 2.
		for (int i = NUMBER_OF_CARDS_IN_GAME/2; i < NUMBER_OF_CARDS_IN_GAME; i++) {
			deck2.add(deck.get(i));
		}
	}

	//add cards to the deck.
	public void add(Card c) {
		deck.add(c);
	}

	//empty deck
	public void empty() {
		deck.clear();
	}
}


