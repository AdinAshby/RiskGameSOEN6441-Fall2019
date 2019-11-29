package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
/**
 * This testsuite include all the testcase classes for map validation
 * @author s_shehna
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ TestMap.class, TestMapFiles.class})
public class TestSuite_Map {

}
