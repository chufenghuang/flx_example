package res;

import java.util.Random;

import res.Exceptions.CardExceptions;
import res.Exceptions.DeckExceptions;

public class SplitDeck extends Deck {

	public SplitDeck() throws DeckExceptions{
		super(1, 10);
		
	}
	
	public Card dealSplittableHandFirstCard(){
		Random rand = new Random();
		int number = rand.nextInt(13);
		return new Card(number, 1, true);
	}
	
	public Card dealSplittableHandSecondCard(Card c) {
		
		
		return new Card(c.LetterIndex, 2, true);
	}
	
}
