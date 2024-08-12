package res.Exceptions;

import res.Player;

public class TimeOutException extends Exception {

	public Player player; 
	public int timesOfPlay;
	public String currentInputMethod;
	
	public TimeOutException() {
		// TODO Auto-generated constructor stub
	}

	public TimeOutException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public TimeOutException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public TimeOutException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public TimeOutException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}
	
	public TimeOutException (Player player, String currentInputMethod) {
		super();
		this.player = player;
		this.currentInputMethod = currentInputMethod;
	}

}
