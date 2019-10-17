package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import model.AdjacencyList;
import model.MapBuilder;

/**
 * This class include testcases to test maps to be valid with given constraints
 * to be a connected graph continent to be connected subgraph
 * 
 * @author s_shehna
 * @author f_yazdan
 * @author Babita kaur
 *
 */
public class TestMap {
	/**
	 * Object of the MapBuilder
	 */
	MapBuilder mb = new MapBuilder();
	/**
	 * Object of the Empty AdjacencyList
	 */
	AdjacencyList adEmpty = new AdjacencyList();
	/**
	 * Object of the AdjacencyList
	 */
	AdjacencyList ad = new AdjacencyList();
	/**
	 * String valid map with initialization
	 */
	String valid_map = "test";
	/**
	 * String inValid map with initialization
	 */
	String invalid_map = "test7";

	/**
	 *  This is the test method  for checking the validate map
	 * 
	 * @throws Exception
	 */
	@Test
	public void testvalidateMap() throws Exception {

		Assert.assertEquals(true, mb.loadMap(valid_map));
		Assert.assertEquals(true, mb.validateMap());
		Assert.assertEquals(false, mb.loadMap(invalid_map));
		Assert.assertEquals(false, mb.validateMap());
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
		Assert.assertEquals(true, mb.loadMap(valid_map));
		Assert.assertEquals(true, ad.isConnected());
		Assert.assertEquals(false, mb.loadMap(invalid_map));
		Assert.assertEquals(false, adEmpty.isConnected());
	}

	/**
	 * This is the test method for checking whether the map is SubGraph
	 * 
	 * @throws Exception
	 */
	@Test
	public void testisMapSubGraph() throws Exception {
		Assert.assertEquals(true, mb.loadMap(valid_map));
		Assert.assertEquals(true, mb.isMapSubGraph());
		Assert.assertEquals(false, mb.loadMap(invalid_map));
		Assert.assertEquals(false, mb.isMapSubGraph());
	}
}
