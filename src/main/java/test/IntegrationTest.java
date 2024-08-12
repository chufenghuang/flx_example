package test;

import blackjack.Init;
import blackjack.PackageIntergration.*;
import res.*;

public class IntegrationTest {
    	public static void main(String[] args) {
		// Instantiate Player and Dealer objects
		SplitPlayer player = new SplitPlayer(1000);
		Dealer dealer = new Dealer(100);

		NaturalSplitFeaturePackage bjControl = new NaturalSplitFeaturePackage(player, dealer);

	
		player.setGame(bjControl);
		bjControl.sendEvent(new Init());
	}
}
