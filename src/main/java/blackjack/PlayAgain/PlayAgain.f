package blackjack.PlayAgain;

import res.*;
import external.*;
import java.util.*;
import blackjack.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

feature PlayAgain{
    domain: PlayAgain;
    anchor: BasicBJ;
    priorityPrecedence(PlayAgain,BasicBJ);
    playAgain{
        condition: state.equalsToConstant(State.END);
        event: enter;{
			System.out.println ("Play again?(y/n)");
            BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
	 		String res = "";
            res = r.readLine();
	 		if (res.equals("y")) {
                state = State.INIT;
                sendEvent(new Init());
            }else{
                System.out.println ("Game Over!!!");
                state = State.END;
            }
		}
    }
}