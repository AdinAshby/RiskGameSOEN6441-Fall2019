package model;
/**
 * This is subject interface for implementing observer
 * @author f_yazdan
 * @author s_shehna
 * @author AdinAshby
 */
public interface Subject {
	public void registerPhaseObserver(Observer addObserver);
	public void registerWorldDominationObserver(Observer addObserver);
	public void unregisterPhaseObserver(Observer removeObserver);
	public void unregisterWorldDomination(Observer removeObserver);
	public void notifyObserverForWorldDomination();
	public void notifyObserverForPhases();
}
