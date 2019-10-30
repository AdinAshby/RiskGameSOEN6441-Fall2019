package model;

public class Dice {
private int numDice;

public int getNumDice() {
	numDice=(int) (Math.random() * 6 + 1);
	return numDice;
}
public void setNumDice(int numDice) {
	this.numDice = numDice;
}

}
