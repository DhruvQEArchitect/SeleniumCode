package com.testNGTests.Listeners;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.xml.XmlSuite;

import java.util.List;
import java.util.Map;

public class ReportingListeners implements IReporter {
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        for (ISuite suite : suites) {
            String suiteName = suite.getName();
            Map<String, ISuiteResult> map = suite.getResults();
            for (ISuiteResult iSuiteResult : map.values()) {
                System.out.println("Passed tests in suite: " + suiteName + " " + iSuiteResult.getTestContext().getPassedTests().size());
                System.out.println("Failed tests in suite: " + suiteName + " " + iSuiteResult.getTestContext().getFailedTests().size());
                System.out.println("Skipped tests in suite: " + suiteName + " " + iSuiteResult.getTestContext().getSkippedTests().size());
            }
        }
    }
}
