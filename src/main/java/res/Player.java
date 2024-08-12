package res;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.NotBoundException;

//import blackjack.playagain.PlayAgain;
//import blackjack.splitplay.*;
import blackjack.Hit;
import blackjack.Init;
import blackjack.Stand;
import external.FL_EVENT_STEM;
import external.FeaturePackageInterface;
import res.Exceptions.CardExceptions;

public class Player extends CardHolder {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2495390222796292985L;
	public int times = 1;
	public boolean inputFlag = true;
	public String currentInputMethod;

	public NonBlockReadBuffer res;

	public Player(String name, String ipAddress) {
		super(name, ipAddress);
		this.setIpAddress(ipAddress);
	}

	public Player(String name) {
		super(name);
	}

	public void askIpaddress() {
		System.out.println(this.name + " please input your ip address :");
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		try {

			String res = r.readLine();
			if (res.equals("h"))
				fp.sendEvent(new Hit());
			else if (res.equals("s")) {
				FeaturePackageInterface fpr = null;
				try {
					fpr = (FeaturePackageInterface) Naming.lookup("rmi://localhost:9002/fp");
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fpr.sendEvent(new Stand());
			} else
				System.out.println("Illegal Input!");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	/**
	 * Original code
	 */
	// public void askHitOrStand () {
	// System.out.println (this.name);
	// System.out.println ("Hit or Stand?(h/s)");
	// r = new BufferedReader(new InputStreamReader(System.in));
	// try {
	// String res = r.readLine();
	//
	// if (res.equals("h")) {
	// fp.sendEvent(new blackjack.Hit());
	// }
	// else if (res.equals("s")) {
	// fp.sendEvent(new blackjack.Stand());
	// }
	// else {
	// System.out.println ("Illegal Input!");
	// }
	// } catch (IOException e) {
	// e.printStackTrace();
	// System.exit(-1);
	// }
	// }

	/**
	 * modified by Ritchie thread version
	 */
	// public void askHitOrStand () {
	// askHitOrStandth = new askHitOrStandThread(this, times++);
	// askHitOrStandth.start();
	// }

	/**
	 * modified by Ritchie no thread version
	 */

	// public void askHitOrStand () {
	// this.currentInputMethod = "askHitOrStand";
	// inputFlag = true;
	// System.out.println (this.name);
	// System.out.println ("Hit or Stand?(h/s)");
	// BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
	// try {
	// while(inputFlag){
	// if(r.ready()){
	// String res = r.readLine();
	//
	// inputFlag = false;
	//
	// if (res.equals("h")) {
	// sendEvent(new blackjack.Hit());
	// }
	// else if (res.equals("s")) {
	// sendEvent(new blackjack.Stand());
	// }
	// else {
	// System.out.println ("Illegal Input! Input again! thread" + times);
	// }
	// }
	// }
	// times++;
	// } catch (IOException e) {
	// e.printStackTrace();
	// System.exit(-1);
	// }
	// }

	/**
	 * NonBlockReadBuffer version
	 */
	public void askHitOrStand() {
		this.currentInputMethod = "askHitOrStand";
		System.out.println(this.name);
		System.out.println("Hit or Stand?(h/s)");

		res = new NonBlockReadBuffer();

		String re;
		try {
			re = res.getLine();

			if (re == null) {
				System.out.println("No input");
			} else if (re.equals("h")) {
				fp.sendEvent(new blackjack.Hit());
			} else if (re.equals("s")) {
				fp.sendEvent(new blackjack.Stand());
			} else {
				System.out.println("Illegal Input!");
				this.askHitOrStand();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
	// public void askToPlayAgain () {
	// 	System.out.println (this.name);
	// 	System.out.println ("Play again?(y/n)");
	// 	BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
	// 	try {
	// 		String res = r.readLine();
	// 		PlayAgain e = new PlayAgain ();
	// 		if (res.equals("y")) e.putPlay(true);
	// 		else if (res.equals("n")) e.putPlay(false);
	// 		else System.out.println ("Illegal Input!");
	// 		fp.sendEvent(e);
	// 	} catch (IOException e) {
	// 		e.printStackTrace();
	// 		System.exit(-1);
	// 	}
	// }

	public void sayGameStart() {
		System.out.println("Game Start");
	}

	public void sayGameOver() {
		System.out.println("Game Over");

	}

	public void warning(String s, int count) {
		System.out.println(s + count + "warning");
	}

	public void sendInit() {
		System.out.println("player.sendInit -- about to do so");
		fp.sendEvent(new Init());
	}

	public void sendEvent(FL_EVENT_STEM e) {
		fp.sendEvent(e);
	}

	public void hasWon() {
		System.out.println(this.name + " has Won");

	}

	public void hasDrawn() {
		System.out.println(this.name + "'s game is a Draw");

	}

	public void hasLost() {
		System.out.println(this.name + " has Lost!");

	}

	/***************************************************************/

	// public void deliverCard (Card c) throws CardExceptions {
	// super.deliverCard(c);
	// }
	// public int getCardsSum() throws CardExceptions {
	// return super.getCardsSum();
	// }
	// public boolean isBJ() throws CardExceptions {
	// return super.isBJ();
	// }

	public boolean isBJ() {
		return super.isBJ();
	}

	public int getCardsSum() {
		return super.getCardsSum();
	}

	public void deliverCard(Card c) {
		try {
			super.deliverCard(c);
		} catch (CardExceptions e) {
			System.out.println("player exp1");
			e.printStackTrace();
		}
	}

	public void reset() {
		super.reset();
		System.out.println(this.name + " reset");
	}

	public Player(int num) {
		super("player");
	}
}
