package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
/**
 * This testsuite include all the testclasses related to Attack phase
 * @author s_shehna
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ TestArmies.class, TestAttack.class, TestPlayer.class , TestDefend.class})
public class TestSuite_Attack {

}
