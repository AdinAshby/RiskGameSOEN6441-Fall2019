package model;

/**
 * This class is for managing Dice during the game
 * @author f_yazdan
 * @author s_ansari
 */
public class Dice {
	/**
	 *private number of Dice
	 */
	private int numDice;
	/**
	 * private array of dice
	 */
	private int[] diceArray;

	/**
	 * This is a constructor to create random number of dice
	 * @param numOfDice
	 */
	public Dice(int numOfDice) {
		diceArray = new int[numOfDice];
		for (int i = 0; i < numOfDice; i++) {
			numDice = (int) (Math.random() * 6 + 1);
			diceArray[i] = numDice;
		}
	}
/**
 * This method return the array containing dice
 * @return diceArray
 */
	public int[] getDiceArray() {
		return diceArray;
	}

	/**
	 * This method initializes the dice array object
	 * @param diceArray
	 */
	public void setDiceArray(int[] diceArray) {
		this.diceArray = diceArray;
	}
/**
 * This method returns number of dice
 * @return numDice
 */
	public int getNumDice() {
		numDice = (int) (Math.random() * 6 + 1);
		return numDice;
	}
/**
* This method sets the dice to a given number
 * @param numDice integer
 */
	public void setNumDice(int numDice) {
		this.numDice = numDice;
	}
/**
 * This method show dice number
 */
	public void showDice() {
		for (int i = 0; i < diceArray.length; i++) {
			System.out.print(diceArray[i] + "-");
		}
		System.out.println("");
	}
/**
 * This method return first and second maximum number of dice
 * @return max 
 */
	public int[] getMax() {
		int i, first, second;
		int[] maxArr = null;

		if (diceArray.length < 2) {
			maxArr= new int[1];
			maxArr[0] = diceArray[0];
			return maxArr;
		}

		if (diceArray.length == 2) {
			maxArr = new int[2];
			if (diceArray[0] > diceArray[1]) {
				maxArr[0] = diceArray[0];
				maxArr[1] = diceArray[1];
			} else {
				maxArr[0] = diceArray[1];
				maxArr[1] = diceArray[0];
			}
			return maxArr;
		}

		first = second = Integer.MIN_VALUE;
		for (i = 0; i < diceArray.length; i++) {

			if (diceArray[i] > first) {
				second = first;
				first = diceArray[i];
			}

			else if (diceArray[i] > second) {
				second = diceArray[i];
			}

		}
//		showDice();
//		System.out.println("Two largest dice are " + first + " " + second);
		maxArr = new int[2];
		maxArr[0] = first;
		maxArr[1] = second;
		return maxArr;
	}
/**
 * This method compare the max array and return the winner
 * @param otherDice
 * @return winner
 */
	public boolean[] isWinner(Dice otherDice) {
		int winnerCount = 0;
		if (this.diceArray.length > otherDice.getDiceArray().length) {
			winnerCount = otherDice.getDiceArray().length;
		} else {
			winnerCount = this.diceArray.length;
		}
		boolean[] winner = new boolean[winnerCount];

		if (this.getMax()[0] > otherDice.getMax()[0]) {
			winner[0] = true;
		} else {
			winner[0] = false;
		}
		
		if(otherDice.getDiceArray().length>1) {
		if (winnerCount>1 && this.getMax()[1] > otherDice.getMax()[1]) {
			winner[1] = true;
		} else {
			winner[1] = false;
		}
		}

		return winner;

	}

}
