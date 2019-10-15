package test;

import org.junit.jupiter.api.Test;
import org.junit.Assert;
import model.MapBuilder;
import model.AdjacencyList;
public class TestMap {
	MapBuilder mb = new MapBuilder();
	AdjacencyList ad = new AdjacencyList();
	@Test
	void testValidateMap() 
	{
		Assert.assertEquals(true, ad.isConnected());
	}
}
