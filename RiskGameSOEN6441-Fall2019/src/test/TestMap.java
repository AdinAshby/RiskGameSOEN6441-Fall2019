package test;

import  org.junit.Assert;

import org.junit.Test;
import model.AdjacencyList;
import model.MapBuilder;
public class TestMap {
MapBuilder mb = new MapBuilder();
AdjacencyList ad = new AdjacencyList();
String valid_map = "test";
String invalid_map = "test6";
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
