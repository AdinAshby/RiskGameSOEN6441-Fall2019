package model;

import java.util.ArrayList;

public interface Observer {
	public void update(int counterForPhases, String playerName);
	public void update(double percentageControlled, int totalNumberOfArmies, ArrayList<String> continentsControlled);
}