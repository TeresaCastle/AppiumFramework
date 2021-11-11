package com.framework.listeners;

import com.framework.base.Common;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class AssertionLogging extends SoftAssert {

    @Override
    public void onAssertSuccess(IAssert<?> assertCommand) {
        //Printing the assertion description and letting the user know it passed
        System.err.println("Assertion: " + assertCommand.getMessage() + " <PASSED> ");
    }

    @Override
    public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
        //Taking a screenshot on failed assertions
        try {
            Common.getScreenshot(assertCommand.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Printing the assertion description and letting the user know it failed and why it failed
        String suffix =
                String.format(
                        "Expected [%s] but found [%s]",
                        assertCommand.getExpected().toString(), assertCommand.getActual().toString());
        System.err.println("Assertion: " + assertCommand.getMessage() + " <FAILED>. " + suffix);

    }
}
