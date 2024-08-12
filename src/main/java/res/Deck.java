package res;

import java.io.Serializable;
import java.util.Random;

import res.Exceptions.DeckExceptions;
import res.Exceptions.LowCardException;
import res.Exceptions.NoMoreCardsException;

public class Deck implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7679192113026357513L;
	private final static int DECK_SIZE = 52;
	private final static int MAX_NUMBER_OF_DECKS = 8;

	private Card[] deck;
	private int[] copies; // Number of copies of the card
	private int ndecks; // Number of decks in the deck
	private int ncards; // Number of cards not yet played
	private int low_watermark; // Throw exception if ncards is less than this

	private Random r = new Random();

	private int N = 0; // Index to cards in deck

	public Deck(int i, int j) throws DeckExceptions { // i is number of decks; j is low-watermark
		ndecks = i;
		if (i <= 0)
			throw new DeckExceptions("DeckException: Zero or -ve number of decks");
		if (i > MAX_NUMBER_OF_DECKS)
			throw new DeckExceptions("DeckException: exceeded 8 decks");
		ncards = i * DECK_SIZE;
		low_watermark = j;
		createDeck();
	}

	// public Card dealFrom() {
	// return deck[--N];
	// }

	// public boolean isEmpty() {
	// return (N == 0);
	// }

	public int size() {
		return (DECK_SIZE * ndecks);
	}

	// public void shuffle() {
	// for (int i = 0; i < N; i++) {
	// int r = (int) (Math.random() * i);
	// Card swap = deck[i];
	// deck[i] = deck[r];
	// deck[r] = swap;
	// }
	// }

	// Zshawn and Francis --------------------
	public void createDeck() {
		deck = new Card[DECK_SIZE];
		copies = new int[DECK_SIZE];
		N = 0;
		for (int i = 0; i < 4; i++) { // For each suit
			for (int j = 0; j < 13; j++) {
				deck[N] = new Card(j, i, false);
				copies[N] = ndecks;
				N++;
			}
		}
	}

	int numDealt = 0;

	public Card dealFrom() throws NoMoreCardsException, LowCardException {
		int x = r.nextInt(DECK_SIZE); // randomly pick a card that is still available

		if (numDealt == 0) {
			x = 0;
		} else if (numDealt == 1) {
			x = 9;
		} else {
			do {
				x = r.nextInt(DECK_SIZE);
			} while (copies[x] == 0);
		}
		numDealt++;
		copies[x]--;
		ncards--;
		if (ncards == 0)
			throw new NoMoreCardsException();
		else if (ncards < low_watermark)
			throw new LowCardException(low_watermark);

		return deck[x];

	}

	// public void cardPicked(){
	// N = N--;
	// }
	// public void reset_count(){
	// N = 0;
	// }

	public int remaining() {
		return ncards;
	}

	public void shuffle() {
		for (int i = 0; i < DECK_SIZE; i++) {
			copies[i] = ndecks;
		}
	}

	public void addDeck(int i) { // add i decks to deck
		// if (i == 0) throw ZeroDeckException;
		// if (i > 8) throw TooManyDecksException;
		ndecks = ndecks + i;
		ncards = ncards + i * DECK_SIZE;
		for (int j = 0; j < DECK_SIZE; j++) {
			copies[j] = copies[j] + i;
		}
	}

	/******************************************************************/

	// public Card dealCardFaceUp ()throws NoMoreCardsException, LowCardException{
	// Card x = dealFrom();
	// x.isFaceUp = true;
	// return (x);
	// }
	//
	// public Card dealCardFaceDown ()throws NoMoreCardsException, LowCardException
	// {
	// Card x = dealFrom ();
	// x.isFaceUp = false;
	// return (x);
	// }

	public Deck(int size) {
		try {
			Constructor(1, 10);
		} catch (DeckExceptions e) {
			e.printStackTrace();
		}
	}

	public void Constructor(int i, int j) throws DeckExceptions { // i is number of decks; j is low-watermark
		ndecks = i;
		if (i <= 0)
			throw new DeckExceptions("DeckException: Zero or -ve number of decks");
		if (i > MAX_NUMBER_OF_DECKS)
			throw new DeckExceptions("DeckException: exceeded 8 decks");
		ncards = i * DECK_SIZE;
		low_watermark = j;
		createDeck();
	}

	public Card dealCardFaceUp() {
		try {
			Card x = dealFrom();
			x.isFaceUp = true;
			return (x);
		} catch (NoMoreCardsException | LowCardException e) {
			System.out.println("deck exp1");
			e.printStackTrace();
		}
		return null;
	}

	public Card dealCardFaceDown() {
		try {
			Card x = dealFrom();
			x.isFaceUp = false;
			return (x);
		} catch (NoMoreCardsException | LowCardException e) {
			System.out.println("deck exp2");
			e.printStackTrace();
		}
		return null;
	}
}
