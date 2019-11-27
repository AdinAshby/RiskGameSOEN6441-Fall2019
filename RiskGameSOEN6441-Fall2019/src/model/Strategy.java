package model;

public interface Strategy {
	public void attack(Country attackerCountry, Country attackingCountry, int attackerNumDice, int defendNumDice, int attackAllout);
	public boolean reinforce(MapGeo mapGeo, String countryName, int num, boolean finished);
	public void fortify(String fromCountry, String toCountry, int armiesToMove,  MapGeo mapGeo);
}