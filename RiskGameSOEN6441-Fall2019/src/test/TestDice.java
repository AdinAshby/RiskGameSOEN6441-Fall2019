package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.INITIALIZE;

import model.Dice;

/**
 * This test case class tests the dice numbers 
 * @author s_shehna
 *
 */
public class TestDice {
	

	int diceNumber;
	boolean check;
	Dice dice;
	/**
	 * initializes data before testcases
	 */
	@Before
	public void setup()
	{
		diceNumber=3;
		dice= new Dice(diceNumber);
	}
	/**
	 * This test case tests dice numbers generated when rolled 3 times are always less than  6
	 */
	@Test
	public void testDiceValid() {
		
		int results[] = dice.getDiceArray();
			
		for(int i=0;i<3;i++) {
	
		check = results[i] > 6 ? false : true;

			assertTrue(check);
		
}
	
		
}
	/**
	 * This testcase test dice numbers generated for invalid values
	 */
	@Test
	public void testDiceInValid() {
		
		int results[] = dice.getDiceArray();
			
		for(int i=0;i<3;i++) {
	
		check = results[i] >6 ? true: false ;

			assertFalse(check);
		
}
	
		
}
		}


