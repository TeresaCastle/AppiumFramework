### NOTE: Framework and README are incomplete and are in the process of being restructured

# Appium Framework for Android

This branch includes the appium framework for Android Apps and Browsers. Please see the README on the main branch for installation and setup instructions. 
https://github.com/madison-vincent/DemoAppiumFramework#readme

### Project Outline
[main > java > com > framework > base] This is where the majority of the framework lives. The Base class is where we set up the Appium Driver and give Appium all of the settings it needs to run automation on a mobile app. The BrowserBase class is where we set up the Driver and settings for mobile browsers. The Common class contains methods that are commonly used in the project, but not specific to a certain area or class. 

[main > java > com < framework > listeners] This is where the configuration lives for TestNG and the report that is generated with testing results. You can use the AssertionLogging class to change what happens when assertions pass or fail during a test. You can use the Listeners class to change what happens when tests start, end, pass, fail, are skipped, etc. I'd recommend just leaving the ExtentReporterNG class alone. 

[main > java > com < framework > pageObjects] This is where page objects live. Page objects are elements within the app, such as a button, that you want to interact with. We keep them separate from the tests so that we can reference the same object in multiple places without having redundant code.
If you are unfamiliar with the page object model check out this link: https://www.browserstack.com/guide/page-object-model-in-selenium

[main > resources] This is where your mobile chromedriver lives and where your app lives, assuming you are installing the app through the project with an .apk file. If you want to open an app that is already installed on the emulator/device there is code you can look at in the Base class for reference. 

[test > java] This is where the test classes live. You can create additional packages/folders in this location to organize your tests by area, or however you want. You will find example tests in the classes within this folder. 

[test-output > Reports] 

[test-output > Screenshots]

[.gitignore]

[pom.xml]

[mobile.xml and browser.xml]

### Setting up for general testing

 1. Right click on the pom.xml > Maven > Reload Project
 3. You can now add the .app and .apk files to the project. I recommend moving them into src/main/resources within the project.
 4. Naviage to src>main>java>com>framework>global.properties and open the file.
 5. Follow the provided instructions in the file to update the fields where needed:
    a. Project Name (Used for saving screenshots and reports to the right location) <br>
    b. IP (Your IP Address is required to initialize the drivers and interact with appium) <br>
    c. App Directory (Where the apps are located in the project)<br>
    e. Android Device (Name of the device you want to use in Android Studio uiautomatoor) <br>
    f. Android App (Name of the app you want to use for Android Automation <br>
    g. mobileBrowser (The name of the browser you want to use for Android) <br>
    
## Usage

### Writing Tests

Common class contains commonly used methods

#### Example Tests
There are 3 example tests already in the framework that you can use as an outline for writing tests. 
Note: Android's uiautomatorviewer typically does not work with the Appium server running. Stop the server before opening uiautomatorviewer

### Running Tests

1. Ensure Android Studio or Xcode are open, depending on which platform you are currently testing
2. Open the simulator/emulator. It must be open for Appium to recognize it. If a physical device is plugged in it will likely use that, but the global properties file may still need to be updated to include the right device name and id
3. Ensure the Appium server is running
4. You should now be able to run tests. It should default to running using TestNG. <br>
   a. You can run tests by right clicking the class and selecting "Run" or by right clicking the "mobile.xml" or "browser.xml" file and clicking "run". The testng file will executes all tests as detailed within the file. This is the best way to execute your entire suite of tests as well as particular groups, packages, classes etc. 
6. Test output will appear in the IDE as well as the Appium Server logs. 

### Test Reports

A report will be generated automatically once tests have been ran that have failed/passed. You can open it by right clicking the report > Open In > Browser > Select browser. The report is found at the following location by default:

test-output > Reports > htmlreport.html

Screenshots are not being pulled into the report at this time, but may in the future. Screenshots should still be captured upon assertion failures and should appear at test-output>Screenshots

## Contributing

Please clone the repository and create a repository specific to your project to ensure that it remains untouched. If something in the framework should be changed please create a Pull Request and assign Madison Vincent as a reviewer for approval. 

## Support

If you need any help or have any questions/concerns please reach out to Madison Vincent for help (madison.vincent@wundermanthompson.com)
