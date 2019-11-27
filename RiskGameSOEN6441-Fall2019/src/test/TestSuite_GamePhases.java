package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
/**
 * This test suite include test classes for different game phases
 * @author s_shehna
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ TestFortify.class, TestReinforcement.class, TestStartUp.class })
public class TestSuite_GamePhases {

}
