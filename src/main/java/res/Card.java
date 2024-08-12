package res;

import java.io.Serializable;
import java.util.Random;

import res.Exceptions.*;

public class Card implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 180752536107364097L;
	
	boolean isFaceUp;
	int LetterIndex;
	int suitIndex;

	public Card(int i, int j, boolean isFaceUp) {
		this.LetterIndex = i;
		this.suitIndex = j;
		this.isFaceUp = isFaceUp;
	}

	static String[] letters = { "A", "2", "3", "4", "5", "6", "7", "8", "9",
			"10", "J", "Q", "K" };
	static int[] BJvalues = { 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };
	static String[] Suit = { "Spade", "Heart", "Diamond", "Club" };
	
	static Random r = new Random();

	// public static Card pick() {
	//	return new Card(r.nextInt(13), true);
	// }

	// public static Card pickWithFaceDown() {
	//	return new Card(r.nextInt(13), false);
	// }

	public int getBJValue() throws CardExceptions {
		if (LetterIndex > 12 || LetterIndex < 0) throw new CardExceptions ("CardException: Letter out of range");
		return BJvalues[LetterIndex];
	}

	public int getSuitIndex() throws CardExceptions {
		if (suitIndex > 3 || suitIndex < 0)throw new CardExceptions ("CardException: Suit out of range");
		return suitIndex;
	}
	
	public String getSuit() throws CardExceptions {
		if (suitIndex >3 || suitIndex < 0)throw new CardExceptions ("CardException: Suit out of range");
		return Suit[suitIndex];
	}
	
	public void openFaceUp() {
		isFaceUp = true;
	}

	public String getLetter() throws CardExceptions {
		if (LetterIndex >12 || LetterIndex < 0)throw new CardExceptions ("CardException: Letter out of range");
		return letters[LetterIndex];
	}
}
