package res;

import java.rmi.RemoteException;

import res.Exceptions.CardExceptions;

public class Dealer extends CardHolder {

	/**
	 * 
	 */
	private static final long serialVersionUID = -48510605602751872L;

	public Dealer(String name, String ipaddress) {
		super(name, ipaddress);
	}

	public void openCards() {
		for (Card c : cards) {
			c.openFaceUp();
		}
	}

	public boolean isHitting() throws CardExceptions, RemoteException {
		return getCardsSum() < 17;
	}

	/**********************************************************/

	// Deliver card
	// public void deliverCard(Card c) throws CardExceptions {
	// super.deliverCard(c);
	// }

	// public void openFaceDownCards() throws CardExceptions {
	// super.openFaceDownCards();
	// }

	// public boolean isAJKQOpenCard() throws CardExceptions {
	// if ((cards.get(0).getLetter().equals("A"))
	// || (cards.get(0).getLetter().equals("J"))
	// || (cards.get(0).getLetter().equals("Q"))
	// || (cards.get(0).getLetter().equals("K"))) {
	// return false;
	// } else {
	// return true;
	// }
	//
	// }

	public boolean isAJKQOpenCard() {
		try {
			if ((cards.get(0).getLetter().equals("A"))
					|| (cards.get(0).getLetter().equals("J"))
					|| (cards.get(0).getLetter().equals("Q"))
					|| (cards.get(0).getLetter().equals("K"))) {
				return false;
			}
		} catch (CardExceptions e) {
			e.printStackTrace();
		}
		return true;
	}

	public void deliverCard(Card c) {
		try {
			super.deliverCard(c);
		} catch (CardExceptions e) {
			e.printStackTrace();
		}
	}

	public void openFaceDownCards() {
		try {
			super.openFaceDownCards();
		} catch (CardExceptions e) {
			e.printStackTrace();
		}
	}

	public Dealer(int num) {
		this("Dealer", "localhost");
	}
}
