package model;

import java.util.LinkedList;

public class Continent {
	private String name;
	private int continentId;
	private enum color  {Red,blue, yellow,green};
	private LinkedList <Integer> countries = new LinkedList<Integer>();
	private LinkedList <Integer> adjacentContinent = new LinkedList<Integer>();
}
