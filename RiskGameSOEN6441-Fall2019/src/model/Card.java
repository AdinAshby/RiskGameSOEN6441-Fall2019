package model;

import java.io.Serializable;
import java.util.Random;
/**
 * This function is is a constructor for initializing cardType.
 * 
 * @author f_yazdan
 * @author s_shehna
 * @author Babita Kaur
 * @author AdinAshby
 *
 */
public class Card implements Serializable {
	/**
	 * String card Type
	 */
	private String cardType; 
	/**
	 * 
	 * enum class containing Card Types
	 *
	 */

	public enum CardType { INFANTRY , CAVALRY , ARTILLERY  ;

		public static String getRandomCard() {
			Random random = new Random();
			CardType c= values()[random.nextInt(values().length)];
			return c.name();

		}};	

		/**
		 * This is a constructor
		 */

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
