package model;

public interface Strategy {
	public void attack(Country attackerCountry, Country attackingCountry, int attackerNumDice, int defendNumDice, MapBuilder mapBuild);
	public boolean reinforce(MapBuilder mapBuild, String countryName, int num, boolean finished);
	public void fortify(String fromCountry, String toCountry, int armiesToMove,  MapBuilder mapBuild);
}