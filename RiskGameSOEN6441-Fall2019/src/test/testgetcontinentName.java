package test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import controller.DriverTest;
import model.Continent;
import model.Country;
import model.MapBuilder;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
public class testgetcontinentName {
	@Test
	public void testgetcontinentname() {
		MapBuilder mb = new MapBuilder();
        assertNotEquals("azio",mb.getContinentName(1));
	}

}
