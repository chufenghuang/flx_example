package res;

import java.rmi.Remote;
import java.rmi.RemoteException;

import external.FeaturePackageInterface;
import res.Exceptions.CardExceptions;

public interface RemoteCardHolder extends Remote {

	public void askIpaddress() throws RemoteException;
	
	public abstract void addCard(Card c) throws RemoteException;

	public abstract void deliverCard(Card c) throws CardExceptions,RemoteException;

	public abstract void openFaceDownCards() throws CardExceptions,RemoteException;

	public abstract boolean isBJ() throws CardExceptions,RemoteException;

	public abstract int getCardsSum() throws CardExceptions,RemoteException;

	public abstract void reset() throws RemoteException;

	public abstract String showCard() throws CardExceptions,RemoteException;

	public abstract String getName() throws RemoteException;

	public abstract void sayGameStart() throws RemoteException;

	public abstract void askHitOrStand() throws RemoteException;

	public abstract void hasDrawn() throws RemoteException;

	public abstract void hasWon()  throws RemoteException;

	public abstract void hasLost() throws RemoteException;

	public abstract void sayGameOver()  throws RemoteException;

	void setFeaturePackage(FeaturePackageInterface featureP) throws RemoteException;

}