package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import model.AdjacencyList;
import model.MapConquest;
import model.MapGeo;

/**
 * This class include testcases to test maps to be valid with given constraints
 * to be a connected graph continent to be connected subgraph
 * 
 * @author s_shehna
 * @author f_yazdan
 *
 */
public class TestMap {
	/**
	 * Object of the MapBuilder
	 */
	MapGeo mapBuild = MapGeo.getInstance();
	/**
	 * Object of the Empty AdjacencyList
	 */
	MapConquest mapConquest = new MapConquest();
	AdjacencyList adEmpty = new AdjacencyList();
	/**
	 * Object of the AdjacencyList
	 */
	AdjacencyList ad = new AdjacencyList();
	/**
	 * String valid map with initialization
	 */
	String VALID_MAP = "test";
	/**
	 * String inValid map with initialization
	 */
	String INVALID_MAP = "test7";

	/**
	 *  This is the test method  for checking the map validation for valid map file
	 * 
	 * @throws Exception
	 */
	@Test
	public void testvalidateMapForValidMap() throws Exception {

		Assert.assertEquals(true, mapConquest.readConquest(VALID_MAP));
		Assert.assertEquals(true, mapBuild.validateMap());
		
	}
	/**
	 *  This is the test method  for checking the map validation for valid map file
	 * 
	 * @throws Exception
	 */
	@Test
	public void testvalidateMap() throws Exception {


		Assert.assertEquals(false, mapConquest.readConquest(INVALID_MAP));
		Assert.assertEquals(false, mapBuild.validateMap());
	}

	/**
	 * This is the test method for  checking the ConnectedGraph
	 * 
	 * @throws Exception
	 */
	@Test
	public void testConnectedGraph() throws Exception {
		ad.addVertex(555);
		ad.addVertex(622);
		ad.addVertex(8686);
		ad.addVertex(45);
		ad.addEdge(555, 8686);
		ad.addEdge(622, 8686);
		ad.addEdge(45, 555);
		Assert.assertEquals(true, mapConquest.readConquest(VALID_MAP));
		Assert.assertEquals(true, ad.isConnected());
		Assert.assertEquals(false, mapConquest.readConquest(INVALID_MAP));
		Assert.assertEquals(false, adEmpty.isConnected());
	}

	/**
	 * This is the test method for checking whether the map is SubGraph with valid map file
	 * 
	 * @throws Exception
	 */
	@Test
	public void testIsMapSubGraphValid() throws Exception {
		Assert.assertEquals(true, mapConquest.readConquest(VALID_MAP));
		Assert.assertEquals(true, mapBuild.isMapSubGraph());
		
	}
	/**
	 * This is the test method for checking whether the map is SubGraph with invalid map file
	 * 
	 * @throws Exception
	 */
	@Test
	public void testIsMapSubGraphInValid() throws Exception {
		
		Assert.assertEquals(false, mapConquest.readConquest(INVALID_MAP));
		Assert.assertEquals(false, mapBuild.isMapSubGraph());
	}
}
