package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
/**
 * This test suite contain all the test classes
 * @author s_shehna
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ TestMap.class, TestMapFiles.class, TestPlayer.class })
public class TestSuite {

}
