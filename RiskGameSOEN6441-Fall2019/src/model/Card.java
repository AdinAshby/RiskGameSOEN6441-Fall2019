package model;

import java.util.Random;
/**
* This function is is a constructor for initializing cardType.
* 
*@author f_yazdan
*@author Babita Kaur
*
*/
public class Card {
	private String cardType; 

	public enum CardType { INFANTRY , CAVALRY , ARTILLERY  ;
		
		public static String getRandomCard() {
        Random random = new Random();
        CardType c= values()[random.nextInt(values().length)];
        return c.name();
        
    }};	

	

	public Card() {
		cardType=CardType.getRandomCard();
//		System.out.println(cardType);
	}
	 
	
/**
 * This method is return the type of the card
 * 
 * @return cardType
 */
	public String getCardType() {

		return cardType;

	}

	
}
