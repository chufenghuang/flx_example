package test;


import blackjack.BasicBJFeature;
import blackjack.Init;
import res.*;

public class BasicBJTest {
	public static void main(String[] args) {
		// Instantiate Player and Dealer objects
		Player player = new Player(1000);
		Dealer dealer = new Dealer(100);
		BasicBJFeature bjControl = new BasicBJFeature(player, dealer);

	
		player.setGame(bjControl);
		bjControl.sendEvent(new Init());
	}
}
