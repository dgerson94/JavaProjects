import javax.swing.JOptionPane;
//War class starts a game of War
public class War {
	private static final int ON = 1;
	private static final int OFF = 0;
	private static final int MIN_FOR_WAR = 2; //the amount of cards that one can't start a war battle with this number or less.
	private static final int EMPTY = 0;
	public static void main(String[] args) {
		String player1, player2;
		int warFlag = OFF; //a flag, on when we need to do a "War" and not a regular battle.
		JOptionPane.showMessageDialog(null,"Welcome to a game of War.");
		//get names of the two players and initialize their decks. First need to initialize the Game deck. Ask for lower case so name won't equal TIE.
		player1 = JOptionPane.showInputDialog("Enter name of player number 1. Please use lower case.");
		player2 = JOptionPane.showInputDialog("Please enter name of player number 2. Please use lower case.");
		DeckOfCards gameDeck = new DeckOfCards();
		//shuffle the deck so the cards won't be split in order
		gameDeck.shuffle();
		//initzialize the decks for the two players
		DeckOfCards deck1 = new DeckOfCards(player1);
		DeckOfCards deck2 = new DeckOfCards(player2);
		//split the game deck between the two players
		gameDeck.split(deck1,deck2);
		//declarations needed for upcoming loop
		String winner;		
		Card player1Card, player2Card;
		/*deck of cards that will contain all the cards won in a battle or war.
		 * In case of tie, the cards will be added to the winner deck and the winner of the war battle will recieve these cards as well. 
		 */
		DeckOfCards winnerDeck = new DeckOfCards("Winner");
		//game continues as long as no one has zero cards, or we are in the middle of a war and each person has at least three cards.
		while ((deck1.size()!= EMPTY && deck2.size() != EMPTY) || (warFlag == ON && (deck1.size() <= MIN_FOR_WAR || deck2.size() <= MIN_FOR_WAR ))) {
			if (warFlag == OFF) {
				//each player takes his top card. We then add it to winnersDeck.
				player1Card = deck1.dealCard();
				player2Card = deck2.dealCard();
				winnerDeck.add(player1Card);
				winnerDeck.add(player2Card);
				winner = player1Card.battle(deck1.getPlayer(), player2Card, deck2.getPlayer());
				//player 1 had higher card, add the cards from winner deck to player1 deck 
				if (winner.equals(deck1.getPlayer())) {
					deck1.merge(winnerDeck);
					winnerDeck.empty();
					JOptionPane.showMessageDialog(null,deck1.getPlayer() + " card:  "+ player1Card + "\n"+ deck2.getPlayer() + " card:  "
							+ player2Card + "\nWinner of this battle:  " + winner+ "\nNumber of cards for " + deck1.getPlayer() + ":  " + 
							deck1.size() + "\n number of cards for " + deck2.getPlayer() +":  " + deck2.size());
				}
				//player 2 had higher card, add the cards fro, winner pile to player2 deck
				else if (winner.equals(deck2.getPlayer())) {	
					deck2.merge(winnerDeck);
					winnerDeck.empty();
					JOptionPane.showMessageDialog(null,deck1.getPlayer() + " card:  "+ player1Card + "\n"+ deck2.getPlayer() + " card:  "
								+ player2Card + "\nWinner of this battle:  " + winner+ "\nNumber of cards for " + deck1.getPlayer() +":  " + 
							deck1.size() + "\n number of cards for " + deck2.getPlayer() +":  " + deck2.size());
				}
				//there was a tie.
				else {
					//turn on war flag so the game knows to start a war battle.
					warFlag = ON;
					JOptionPane.showMessageDialog(null,deck1.getPlayer() + " card:  "+ player1Card + "\n"+ deck2.getPlayer() + " card:  " + player2Card + "\nThis  battle was a tie.");
				}
			}
			//case where there is a war battle
			else {
				//check to make sure both players have three cards in order to have war battle
				if (deck1.size() > MIN_FOR_WAR && deck2.size() > MIN_FOR_WAR) {
					//add the first two cards into winners pile from each hand.
					for (int i = 0; i < MIN_FOR_WAR;i++) 
						winnerDeck.add(deck1.dealCard());
					for (int i = 0; i < MIN_FOR_WAR;i++) 
						winnerDeck.add(deck2.dealCard());
					//we are going to war, so turn flag off.
					warFlag = OFF; 
				}
			}

		}
		/*
		 * If we have reached here one of the players now has zero cards in his hand. The ways that this can happen:
		 * 1) one player has zero cards and other player has cards - other player wins.
		 * 2) during war, one of the players has less than the min amount of cards for war - other player wins.
		 * 3) both players have less than the min amount of cards for war - tie.
		 */
		//both players have no cards, very rare case.
		if (warFlag == ON) {
			//both players are in war and have less than 3 cards
			if ((deck1.size() <= MIN_FOR_WAR) && (deck2.size() <= MIN_FOR_WAR))
				JOptionPane.showMessageDialog(null,"Both players have less than the minimum cards, hence the game is a tie.");
			//only one player has less than three cards
			else {
				//one player ran out of cards during war, hence he lost.
				if (deck1.size() <= MIN_FOR_WAR)
					JOptionPane.showMessageDialog(null,deck2.getPlayer()+" has won!");
				else
					JOptionPane.showMessageDialog(null,deck1.getPlayer()+" has won!");
			}
		}	
		//we are not in war. One player lost war and has no cards left,hence he lost.
		else {
			if (deck1.size() == EMPTY)
				JOptionPane.showMessageDialog(null,deck2.getPlayer()+" has won!");
			else
				JOptionPane.showMessageDialog(null,deck1.getPlayer()+" has won!");
		}

	}
	
}
