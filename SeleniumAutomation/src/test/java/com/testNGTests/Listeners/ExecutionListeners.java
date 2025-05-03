package com.testNGTests.Listeners;

import org.testng.IExecutionListener;

/**
 * IExecutionListener interface contains two methods onExecutionStart and onExecutionFinish which
 * can be used to check the execution start and end time
 */
public class ExecutionListeners implements IExecutionListener {

    @Override
    public void onExecutionStart() {
        System.out.println("Execution started at: " + System.currentTimeMillis());
    }

    @Override
    public void onExecutionFinish() {
        System.out.println("Execution finished at: " + System.currentTimeMillis());
    }
}
