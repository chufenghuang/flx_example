package test;


import blackjack.Natural.NaturalFeature;
import blackjack.Init;
import res.*;

public class NaturalTest {
	public static void main(String[] args) {
		// Instantiate Player and Dealer objects
		Player player = new Player(1000);
		Dealer dealer = new Dealer(100);

		NaturalFeature bjControl = new NaturalFeature(player, dealer);

	
		player.setGame(bjControl);
		bjControl.sendEvent(new Init());
	}
}