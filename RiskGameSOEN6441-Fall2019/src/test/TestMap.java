package test;

import  org.junit.Assert;

import org.junit.Test;
import model.AdjacencyList;
import model.MapBuilder;
/**
 *  This class include testcases to test maps 
 *  to be valid with given constraints
 *  to be a connected graph
 *  continent to be connected subgraph
 * @author s_shehna
 *
 */
public class TestMap {
MapBuilder mb = new MapBuilder();
AdjacencyList ad = new AdjacencyList();
String valid_map = "test";
String invalid_map = "test7";
	@Test
	public void testvalidateMap() throws Exception
	{
		Assert.assertEquals(true, mb.loadMap(valid_map));
		Assert.assertEquals(true, mb.validateMap());
		Assert.assertEquals(false, mb.loadMap(invalid_map));
		Assert.assertEquals(false, mb.validateMap());
	}
	@Test
	public void testConnectedGraph() throws Exception
	{
		Assert.assertEquals(true, mb.loadMap(valid_map));
		Assert.assertEquals(true, ad.isConnected() );
		Assert.assertEquals(false, mb.loadMap(invalid_map));
		Assert.assertEquals(false, ad.isConnected() );
	}
@Test
public void testisMapSubGraph() throws Exception
{
	Assert.assertEquals(true, mb.loadMap(valid_map));
	Assert.assertEquals(true, mb.isMapSubGraph());
	Assert.assertEquals(false, mb.loadMap(invalid_map));
	Assert.assertEquals(false, mb.isMapSubGraph());
}
}
