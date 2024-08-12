package blackjack.Split;

import res.*;
import external.*;
import java.util.*;
import blackjack.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import external.*;
import domainType.*;

feature Split{
    domain: Split;
    anchor: BasicBJ;
    specializations: (SplitPlayer) player;
    straightPrecedence(Split, BasicBJ);
	spEnterPlay{
		condition:state.equalsToConstant(State.PLAY);
		event:enter; {
			when(((SplitPlayer)player).splittable()) priorityPrecedence(Split, BasicBJ);{
				player.askHitStandOrSplit();
			}
		
		}
	}
	
	split{
		condition:	state.equalsToConstant(State.PLAY);
		event:	Split; {
			player.addHand();	//split hand, and then deliver card
			player.deliverCard(deck.dealCardFaceUp());
			
			if(player.splittable()){
				player.askHitStandOrSplit();
			}
			else{
				player.askHitOrStand();
			}
		}
	}
	
	spLeavePlay{
		condition:	state.equalsToConstant(State.PLAY);
		event: leave;{
			System.out.println("Check if the player has more hands:" + player.hasMoreHands());
			player.minusHand();
			if(player.hasMoreHands()){
				player.getNextHandToPlay();		
				state = State.PLAY;
			} 
		}
	}
		
	splitEnterJudge{
		condition:	state.equalsToConstant(State.JUDGE);
		event:	enter; {
			if(0 == player.getCurrentHand()){			
				//ProceedTo (BasicBJ);
			}
			else{
				System.out.println("Judge split game hand " + player.getCurrentHand());
				player.swapHand();
			
			}
		}
	}
	
	splitLeaveJudge{
		condition:	state.equalsToConstant(State.JUDGE);
		event:	leave; {
			if(0 == player.getCurrentHand()){
				state = State.END;
			}
			else if(!player.isLastHand()){
				state = State.JUDGE;
			}
			else if(player.isLastHand()){
				state = State.END;
			}
		}
	}	

}