package res;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import blackjack.*;
import blackjack.Split.*;
import res.Exceptions.CardExceptions;

/*
 * 		Split Player Extends Player Objects
 * 		This object is used to access split game features
 * 
 */
public class SplitPlayer extends Player {
//	
	private int splitIndex = -1; // Index of card to be moved to new hand
	public List<List<Card>> hands = new ArrayList<List<Card>>(); // List of hands the player has (Just 1 means no split
																	// so this is empty)
	public List<List<Card>> finishedHands = new ArrayList<List<Card>>(); // List of hands already judged
	private int currentHand; // Represents the current hand
//	
	// Constructor

	public SplitPlayer(String name) {
		super(name);
		hands.add(this.cards);
		setCurrentHand(0);
	}

//	
	public List<Card> getCards() {
		return this.cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public boolean askHitStandOrSplit() {	
		this.currentInputMethod = "askSplit";
		System.out.println(this.name + ": Would you like to split? [h/s/p]: ");

		res = new NonBlockReadBuffer();

		String re = null;
		try {
			re = res.getLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (0 == this.currentHand) {// BasicBJ
			if (re == null) {
				System.out.println("No input");
			} else if (re.equals("p")) {
				System.out.println("Current game will be split!");
				fp.sendEvent(new Split());
			} else if (re.equals("h")) {
				System.out.println("Player do not want to split and ask for hit!");
				fp.sendEvent(new Hit());
			} else if (re.equals("s")) {
				System.out.println("Player do not want to split and ask for stand!");
				fp.sendEvent(new Stand());
			} else {
				System.out.println("Illegal Input!");
				this.askHitStandOrSplit();
			}
		} else {// SplitBJ
			if (re == null) {
				System.out.println("No input");
			} else if (re.equals("p")) {
				System.out.println("Current game will be split again!");
				sendEvent(new Split());
			} else if (re.equals("h")) {
				System.out.println("Player do not want to split any more and ask for hit!");
				System.out.println("Hand " + currentHand + " begins:");
				sendEvent(new Hit());
			} else if (re.equals("s")) {
				System.out.println("Player do not want to split any more and ask for stand!");
				System.out.println("Hand " + currentHand + " begins:");
				sendEvent(new Stand());
			} else {
				System.out.println("Illegal Input!");
				this.askHitStandOrSplit();
			}
		}
		return false;
	}

//	public boolean askHitStandOrSplit() {
//		this.currentInputMethod = "askSplit";
//		System.out.println(this.name + ": Would you like to split? [y/n]: ");
//
//		res = new NonBlockReadBuffer();
//
//		String re = null;
//		try {
//			re = res.getLine();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		if (0 == this.currentHand) {// BasicBJ
//			if (re == null) {
//				System.out.println("No input");
//			} else if (re.equals("y")) {
//				System.out.println("Current game will be split!");
//				fp.sendEvent(new Split());
//			} else if (re.equals("n")) {
//				System.out.println("Player do not want to split!");
//				this.askHitOrStand();
//			} else {
//				System.out.println("Illegal Input!");
//				this.askHitStandOrSplit();
//			}
//		} else {// SplitBJ
//			if (re == null) {
//				System.out.println("No input");
//			} else if (re.equals("y")) {
//				System.out.println("Current game will be split again!");
//				sendEvent(new Split());
//			} else if (re.equals("n")) {
//				System.out.println("Player do not want to split any more!");
//				System.out.println("Hand " + currentHand + " begins:");
//				this.askHitOrStand();
//			} else {
//				System.out.println("Illegal Input!");
//				this.askHitStandOrSplit();
//			}
//		}
//		return false;
//	}

	public void createNewHand(Card c) {
		List<Card> newHand = new ArrayList<Card>();
		newHand.add(c);
		hands.add(newHand);
	}

	public int getSplitIndex() {
		return splitIndex;
	}

	/*
	 * change or added by Ritchie
	 */
	public boolean hasMoreHands() {
		if (this.hands.size() - 1 != getCurrentHand()) {
			System.out.print(this.hands.size() - 1 - getCurrentHand());
			System.out.println(" more games");
			return true;
		} else {
			if (0 == getCurrentHand())
				return false;
			else {
				addNewFinishedHands();
				//setCurrentHand(1);
				return false;
			}
		}
	}

	/*
	 * change or added by Ritchie
	 */
	public void addNewFinishedHands() {
		List<Card> newHand = new ArrayList<Card>();
		for (int i = 0; i < cards.size(); i++) {
			newHand.add(null);
			newHand.set(i, cards.get(i));
		}
		finishedHands.add(newHand);
	}

	/*
	 * change or added by Ritchie
	 */
	public boolean isLastHand() {
		if (getCurrentHand() == finishedHands.size()) { // out bound, last+1
			return true;
		} else {
			setCurrentHand(getCurrentHand() + 1);
			return false;
		}
	}

	/*
	 * change or added by Ritchie
	 */
	public void swapHand() {
		cards = finishedHands.get(getCurrentHand() - 1);
		// System.out.println("swap hand");
	}

	public void deliverCard(Card c) {
		super.deliverCard(c);
	}

	public int getCardsSum() {
		return super.getCardsSum();
	}

	public void askHitOrStand() {
		super.askHitOrStand();
	}

	private void revealHand(int index) throws CardExceptions {
		List<Card> temp = hands.get(index);
		String output = "Hand " + index + " [";
		for (int i = 0; i < temp.size(); i++) {
			Card c = temp.get(i);
			String suit = c.getSuit().substring(0, 1);

			if (i == temp.size() - 1) {
				output += c.getLetter() + suit;
			} else {
				output += c.getLetter() + suit + ", ";
			}

		}
		output += "]";

		show(output);
	}

	public void revealHands() throws CardExceptions {
		for (int i = 0; i < hands.size(); i++) {
			revealHand(i);
		}
	}

	private void show(String s) {
		System.out.println(s);
	}

	public int getCurrentHand() {
		return currentHand;
	}

	public void setCurrentHand(int currentHand) {
		this.currentHand = currentHand;
	}

	/****************************************************/
	/*
	 * change or added by Ritchie
	 */
//	public boolean CheckPlayerSplit() throws CardExceptions{
//
//		splitIndex = -1;
//		
//		System.out.println("Check if player can split");
//		
//		if(this.cards.size() < 2){
//			System.out.println("currently can not split");
//			return false;
//		}
//		else if(this.cards.get(super.cards.size()-2).getLetter() == this.cards.get(super.cards.size()-1).getLetter()){
//			splitIndex = super.cards.size()-1;
//			return true;
//		}
//		else{
//			System.out.println("currently can not split");
//			return false;	
//		}
//	}

	public boolean splittable() {
		splitIndex = super.cards.size() - 1;
		return true;
//		
//		splitIndex = -1;
//		System.out.println("Check if player can split");
//		try {
//			if (this.cards.size() < 2) {
//				System.out.println("currently can not split");
//				return false;
//			} else if (this.cards.get(super.cards.size() - 2).getLetter() == this.cards.get(super.cards.size() - 1)
//					.getLetter()) {
//				splitIndex = super.cards.size() - 1;
//				return true;
//			} else {
//				System.out.println("currently can not split");
//				return false;
//			}
//		} catch (CardExceptions e) {
//			e.printStackTrace();
//		}
//		return false;
	}

	/*
	 * change or added by Ritchie
	 */
	public void addHand() {
		if (0 == getCurrentHand())// the first time get split
		{
			createNewHand(cards.get(0));
			createNewHand(cards.get(splitIndex));
			cards.remove(splitIndex);
			setCurrentHand(getCurrentHand() + 1);
		} else {
			createNewHand(cards.get(splitIndex));
			cards.remove(splitIndex);
		}
	}

	public SplitPlayer(int num) {
		super(num);
		hands.add(this.cards);
		setCurrentHand(0);
	}

	public void dealCardToSplittedHands(Deck deck) {
		this.deliverCard(deck.dealCardFaceUp());
	}

	/*
	 * change or added by Ritchie
	 */
	public void getNextHandToPlay() {
		this.setCurrentHand(this.getCurrentHand() + 1);
		this.splitIndex = -1;

		addNewFinishedHands();
		this.cards.clear();

		this.cards.add(hands.get(getCurrentHand()).get(0));
		System.out.print("Change to hand ");
		System.out.println(getCurrentHand());
	}

	public void minusHand() {

	}
	
	public void getNetHandToPlay() {}

}
