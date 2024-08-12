package test;


import blackjack.PlayAgain.PlayAgainFeature;
import blackjack.Init;
import res.*;

public class PlayAgainTest {
	public static void main(String[] args) {
		// Instantiate Player and Dealer objects
		Player player = new Player(1000);
		Dealer dealer = new Dealer(100);

		PlayAgainFeature bjControl = new PlayAgainFeature(player, dealer);
	
		player.setGame(bjControl);
		bjControl.sendEvent(new Init());
	}
}
