package test;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

import model.MapConquest;
import model.MapDomination;
import model.MapGeo;

/**
 * This class contain test cases to test map files
 * 
 * @author s_shehna
 * @author f_yazdan

 *
 */
public class TestMapFiles {
	
	MapGeo mapBuild;
	
	String VALID_FILE;
	
	String INVALID_FILE;
	MapDomination mapDomination;
	MapConquest mapConquest;
	/**
	 * intializes data before testcases
	 */
	@Before
	public void setup()
	{
		mapDomination = new MapDomination();
		 mapConquest = new MapConquest(mapDomination);
		 VALID_FILE = "valid_map";
		 INVALID_FILE = "test2";
		 mapBuild = MapGeo.getInstance();
	}
	/**
	 * This is the test method for checking whether the map file is valid
	 * 
	 * @throws Exception
	 */
	@Test
      public void testValidMapFiles() throws Exception {
		Assert.assertEquals(true, mapConquest.readConquest(VALID_FILE));
	}

	/**
	 * This is the test method for checking whether the map file is inValid
	 * 
	 * @throws Exception
	 */
	@Test
	public void testInvalidMapFiles() throws Exception {
		Assert.assertEquals(false, mapDomination.read(INVALID_FILE));
	}

}
