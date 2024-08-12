package blackjack.PackageIntergration;

import external.*;
import res.*;
import blackjack.*;
import blackjack.Split.*;
import blackjack.Natural.*;


feature package NaturalSplit {
    features: Natural, Split;
    priorityPrecedence(Split,Natural);

    //straightPrecedence(NaturalSplit, BasicBJ);
    //straightPrecedence(NaturalSplit, Split);
    //straightPrecedence(NaturalSplit, Natural);

   EnterPlay {
        condition: state.equalsToConstant(State.PLAY); 
        event: enter; {
            straightPrecedence(Natural, Split, BasicBJ);
        }
    }

    EnterJudge {
        condition: state.equalsToConstant(State.JUDGE); 
        event: enter; {
            straightPrecedence(Natural, Split, BasicBJ);
        }
    }

    LeavePlay {
        condition: state.equalsToConstant(State.PLAY);
	    event: leave;{
	    	when(true) straightPrecedence(Natural, Split, BasicBJ); {}
	    }
    }

    LeaveJudge{
        condition: state.equalsToConstant(State.JUDGE); 
        event: leave; {
            straightPrecedence(Natural, Split, BasicBJ);
        }
    }

}
