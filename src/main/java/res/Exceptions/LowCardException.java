package res.Exceptions;

public class LowCardException extends DeckExceptions {
	public LowCardException (int msg){
		super ("LowCardException" + msg + "cards left");
	}
}