package test;

import org.junit.Test;
import org.junit.Assert;

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
	/**
	 * Object of the MapBuilder
	 */
	MapGeo mapBuild = MapGeo.getInstance();
	/**
	 * String valid_file
	 */
	String VALID_FILE = "valid_map";
	/**
	 * String inValid_file
	 */
	String INVALID_FILE = "test2";
	MapDomination mapDomination = new MapDomination();
	MapConquest mapConquest = new MapConquest(mapDomination);
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
