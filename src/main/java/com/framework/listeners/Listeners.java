package com.framework.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

//ITestListener interface which implements Testng listeners
public class Listeners implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("***** Starting Test: "+ result.getName()+" *****");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("***** Test: "+result.getName()+" has passed *****");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("***** Test: "+result.getName()+" has failed *****");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("***** Test: "+result.getTestName()+" was skipped *****");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {
	}

}
