package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestMap.class, TestMapFiles.class, TestPlayer.class, TestReinforcement.class })
public class TestSuite {

}
