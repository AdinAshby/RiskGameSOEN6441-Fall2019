package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
/**
 * This Test suite include all the other test suites
 * @author s_shehna
 *
 */
@RunWith(Suite.class)
@SuiteClasses({   TestSuite_Attack.class, TestSuite_GamePhases.class,
		TestSuite_Map.class })
public class TestSuite_Main {

}
