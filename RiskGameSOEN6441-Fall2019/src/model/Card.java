package model;

import java.util.Random;

public class Card {
	private String cardType; 

	public enum CardType { INFANTRY , CAVALRY , ARTILLERY  ;
		
		public static String getRandomCard() {
        Random random = new Random();
        CardType c= values()[random.nextInt(values().length)];
        return c.name();
        
    }};	

	/**

 * This function is is a constructor for initializing cardType.

 * 

 * @author Babita Kaur, Golnoosh

 */
	public Card() {
		cardType=CardType.getRandomCard();
//		System.out.println(cardType);
	}
	 
	
/*
	public Card(CardType card) {

		switch (card) {

		case INFANTRY:

			this.cardType = CardType.INFANTRY;

			break;

		case  CAVALRY:

			this.cardType = CardType.CAVALRY;

			break;

		case  ARTILLERY:

			this.cardType = CardType.ARTILLERY;

			break; 

		}

	}
*/
	

	public String getCardType() {

		return cardType;

	}

	
}
