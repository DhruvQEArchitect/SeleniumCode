package jjunit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * This is the way to define a test suite in junit by using @RunWith and @Suite.SuiteClasses annotations
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({JunitSanityTests.class, JunitSmokeTests.class})
public class JunitTestSuite {
}
