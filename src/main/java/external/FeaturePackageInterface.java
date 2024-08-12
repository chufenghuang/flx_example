package external;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FeaturePackageInterface extends Remote{
    public void sendEvent (FL_EVENT_STEM e) throws RemoteException;
}
