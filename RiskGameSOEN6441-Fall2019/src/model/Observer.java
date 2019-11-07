package model;

public interface Observer {
	public void update(int counterForPhases, String playerName);
	public void update(double percentageControlled, int totalNumberOfArmies, String[] continentsControlled);
}