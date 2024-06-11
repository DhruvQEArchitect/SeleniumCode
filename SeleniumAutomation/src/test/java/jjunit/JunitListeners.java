package jjunit;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

/**
 * This class shows how we can use listeners in junit tests by extending a RunListener class
 */
public class JunitListeners extends RunListener {

    @Override
    public void testStarted(Description description) throws Exception {
        System.out.println("Test started: " + description.getMethodName());
    }

    @Override
    public void testFinished(Description description) throws Exception {
        System.out.println("Test finished: " + description.getMethodName());
    }

    @Override
    public void testFailure(Failure failure) throws Exception {
        System.out.println("Test failure: " + failure.getMessage());
    }
}
