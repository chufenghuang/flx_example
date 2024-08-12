package test;
import blackjack.Split.*;
import blackjack.Init;
import res.*;

public class SplitTest {
	public static void main(String[] args) {
		// Instantiate Player and Dealer objects
		SplitPlayer player = new SplitPlayer(1000);
		Dealer dealer = new Dealer(100);
		SplitFeature spControl = new SplitFeature(player, dealer);
		player.setGame(spControl);
		spControl.sendEvent(new Init());
	}
}