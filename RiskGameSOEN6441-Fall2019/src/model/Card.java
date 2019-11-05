package model;

public class Card {
	private CardType cardType; 

	

	/**

 * This function is is a constructor for initializing cardType.

 * 

 * @author Babita Kaur

 */

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

	

	public CardType getCardType() {

		return cardType;

	}

	
}
