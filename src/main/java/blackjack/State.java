package blackjack;
import external.*;
import ddt.lang.*;
import res.*;import java.io.Serializable;
public class
 State extends DTenum implements Serializable
{
private enum RawEnum  {INIT, DEAL_CARD, PLAY, JUDGE, END}private final RawEnum value;
public String name(){return value.name();}
private 
State(final RawEnum value){super(value.ordinal());this.value = value;}public final static State INIT = new State(RawEnum.INIT);
public final static State DEAL_CARD = new State(RawEnum.DEAL_CARD);
public final static State PLAY = new State(RawEnum.PLAY);
public final static State JUDGE = new State(RawEnum.JUDGE);
public final static State END = new State(RawEnum.END);
}
