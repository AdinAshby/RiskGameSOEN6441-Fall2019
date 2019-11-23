package model;

public interface Strategy {
	public void attack(Country attackerCountry, Country attackingCountry, int attackerNumDice, int defendNumDice, MapGeo mapBuild, int attackAllout);
	public boolean reinforce(MapGeo mapBuild, String countryName, int num, boolean finished);
	public void fortify(String fromCountry, String toCountry, int armiesToMove,  MapGeo mapBuild);
}