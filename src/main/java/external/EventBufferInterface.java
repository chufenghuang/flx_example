package external;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EventBufferInterface extends Remote {
	 
public void addEvent(FL_EVENT_STEM e) throws RemoteException;
}
