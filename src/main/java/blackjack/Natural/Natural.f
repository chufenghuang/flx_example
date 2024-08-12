package blackjack.Natural;

import blackjack.*;
import res.*;
import external.*;

feature Natural{
	domain: Blackjack;
	anchor: BasicBJ;
	straightPrecedence(Natural, BasicBJ);
	
	naturalEnterPlay{
	  condition: state.equalsToConstant(State.PLAY);
	  event: enter;{
        
	  	if(player.isBJ()) {			
	  		state = State.JUDGE;
	  	}
	  }
	}
	
	naturalLeavePlay{
	  condition: state.equalsToConstant(State.PLAY);
	  event: leave;{
	  	when(player.isBJ()) priorityPrecedence(Natural, BasicBJ);{
	  		if(dealer.isAJKQOpenCard()){
	  			dealer.openFaceDownCards();
	  		}
	  	}
	  }
	}
	
}