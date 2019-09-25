package model;

import java.io.Serializable;

/**
 * this class is made for dice
 * @author  Golnoosh
 */
public class Dice  implements Serializable {

    private int value;		//value of the dice can have a range between 1-6

    /**
     * constructor of the class
     * @param value the value of the dice
     */
    public Dice(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
