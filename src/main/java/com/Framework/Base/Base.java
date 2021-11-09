package com.Framework.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import com.Framework.Listeners.AssertionLogging;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import pageObjects.Android.AppHomePageObjects;
import pageObjects.Browser.AndroidPageObjects;
import pageObjects.Browser.iOSPageObjects;

public class Base {

    public static AppiumDriverLocalService service;
    public static AppiumDriver driver;
    public static AssertionLogging softAssert = new AssertionLogging();
    public static AppHomePageObjects android = new AppHomePageObjects();
    public static pageObjects.IOS.AppHomePageObjects iOS = new pageObjects.IOS.AppHomePageObjects();
    public static AndroidPageObjects androidBrowser = new AndroidPageObjects();
    public static iOSPageObjects iOSBrowser = new iOSPageObjects();

//    static {
//        try {
//            driver = getPlatform();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    //@Parameters({"Platform"})
    @BeforeSuite()
    public void getPlatform(String platform) throws IOException, InterruptedException {
        platform = "android";
        if(Objects.equals(platform, "android")) {AndroidBase.capabilities();}
        else if (Objects.equals(platform, "ios")){iOSBase.capabilities();}
    }

}


