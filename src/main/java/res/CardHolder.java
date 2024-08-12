package res;

import java.util.ArrayList;
import java.util.List;

import external.FeaturePackage;
import res.Exceptions.CardExceptions;

public class CardHolder {// extends UnicastRemoteObject implements RemoteCardHolder {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8908977392732221989L;

	private String ipAddress;

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	protected List<Card> cards = new ArrayList<Card>();
	String name;
	static FeaturePackage fp = null;

	public CardHolder(String name, String ipaddress) {
		this.name = name;
		this.ipAddress = ipaddress;
	}

	public CardHolder(String name2) {
		this.name = name2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Resources.RemoteCardHolder#setFeaturePackage(external.FeaturePackage)
	 */
	public void setGame(FeaturePackage featureP) {
		fp = featureP;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Resources.RemoteCardHolder#addCard(Resources.Card)
	 */
	public void addCard(Card c) {
		cards.add(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Resources.RemoteCardHolder#deliverCard(Resources.Card)
	 */
	public void deliverCard(Card c) throws CardExceptions {
		cards.add(c);
		if (c.isFaceUp)
			System.out.println(this.name + " gets " + c.getLetter() + " of " + c.getSuit());
		else if (!c.isFaceUp)
			System.out.println(this.name + " gets facedown card");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Resources.RemoteCardHolder#openFaceDownCards()
	 */
	public void openFaceDownCards() throws CardExceptions {
		for (Card c : cards) {
			if (!c.isFaceUp) {
				c.isFaceUp = true;
				System.out.println(this.name + " opens: " + c.getLetter() + " of " + c.getSuit());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Resources.RemoteCardHolder#isBJ()
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see Resources.RemoteCardHolder#getCardsSum()
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see Resources.RemoteCardHolder#reset()
	 */

	public void reset() {
		cards.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Resources.RemoteCardHolder#showCard()
	 */

	public String showCard() throws CardExceptions {
		StringBuffer sb = new StringBuffer();
		sb.append(getName());
		sb.append(":");
		for (Card c : cards) {
			sb.append(c.getLetter());
			sb.append(c.getSuit());
			sb.append(" ");
		}
		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Resources.RemoteCardHolder#getName()
	 */

	public String getName() {
		return name;
	}

	public void sayGameStart() {
		// TODO Auto-generated method stub

	}

	public void askHitOrStand() {
		// TODO Auto-generated method stub

	}

	public void hasDrawn() {
		// TODO Auto-generated method stub

	}

	public void hasWon() {
		// TODO Auto-generated method stub

	}

	public void hasLost() {
		// TODO Auto-generated method stub

	}

	public void sayGameOver() {
		// TODO Auto-generated method stub

	}

	public void askIpaddress() {
		// TODO Auto-generated method stub

	}

	/**************************************************************/
	// public int getCardsSum() throws CardExceptions {
	// int sum = 0;
	// int noA = 0;
	// for (Card c : cards) {
	// if(c.getLetter().equals("A")){
	// noA++;
	// }
	// sum += c.getBJValue();
	// }
	//
	// for(int i = 0; i < noA; i++){
	// if(sum <= 21){
	// break;
	// }
	// sum -= 10;
	// }
	// return sum;
	// }

	// public boolean isBJ() throws CardExceptions {
	// if (cards.size() != 2)
	// return false;
	//
	// if (cards.get(0).getLetter().equals("A")) {
	// if (cards.get(1).getLetter().equals("J")
	// || cards.get(1).getLetter().equals("Q")
	// || cards.get(1).getLetter().equals("K")
	// || cards.get(1).getLetter().equals("10")) {
	// return true;
	// }
	// }
	//
	// if (cards.get(0).getLetter().equals("J")
	// || cards.get(0).getLetter().equals("Q")
	// || cards.get(0).getLetter().equals("K")
	// || cards.get(0).getLetter().equals("10")) {
	// if (cards.get(1).getLetter().equals("A")) {
	// return true;
	// }
	// }
	// return false;
	// }

	public boolean isBJ() {
		try {
			if (cards.size() != 2)
				return false;

			if (cards.get(0).getLetter().equals("A")) {
				if (cards.get(1).getLetter().equals("J")
						|| cards.get(1).getLetter().equals("Q")
						|| cards.get(1).getLetter().equals("K")
						|| cards.get(1).getLetter().equals("10")) {
					return true;
				}
			}

			if (cards.get(0).getLetter().equals("J")
					|| cards.get(0).getLetter().equals("Q")
					|| cards.get(0).getLetter().equals("K")
					|| cards.get(0).getLetter().equals("10")) {
				if (cards.get(1).getLetter().equals("A")) {
					return true;
				}
			}
		} catch (CardExceptions e) {
			e.printStackTrace();
		}
		return false;
	}

	public int getCardsSum() {
		int sum = 0;
		try {
			int noA = 0;
			for (Card c : cards) {
				if (c.getLetter().equals("A")) {
					noA++;
				}
				sum += c.getBJValue();
			}

			for (int i = 0; i < noA; i++) {
				if (sum <= 21) {
					break;
				}
				sum -= 10;
			}
		} catch (CardExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sum;
	}
}
