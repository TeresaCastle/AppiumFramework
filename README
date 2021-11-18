### NOTE: Framework and README are incomplete and are in the process of being restructured

# Appium Framework for Android

This branch includes the appium framework for Android Apps and Browsers. Please see the README on the main branch for installation and setup instructions. 
https://github.com/madison-vincent/DemoAppiumFramework#readme

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

I recommend checking the README files for each platform branch to find platform specific information on writing tests
Note: Android's uiautomatorviewer typically does not work with the Appium server running. Stop the server before opening uiautomatorviewer

### Running Tests

1. Ensure Android Studio or Xcode are open, depending on which platform you are currently testing
2. Open the simulator/emulator. It must be open for Appium to recognize it. If a physical device is plugged in it will likely use that, but the global properties file may still need to be updated to include the right device name and id
3. Ensure the Appium server is running
4. You should now be able to run tests. It should default to running using TestNG. <br>
   a. You can run tests by right clicking the class and selecting "Run" or by right clicking the "mobile.xml" or "browser.xml" file and clicking "run". The testng file will executes all tests as detailed within the file. This is the best way to execute your entire suite of tests as well as particular groups, packages, classes etc. 
6. Test output will appear in the IDE as well as the Appium Server logs. 

### Test Reports

A report will be generated automatically once tests have been ran that have failed/passed. You can open it by right clicking the report > Open In > Browser > Select browser. The report is found at the following location b default:

test-output > Reports > htmlreport.html

Screenshots are not being pulled into the report at this time, but may in the future. Screenshots should still be captured upon assertion failures and should appear at test-output>Screenshots

## Contributing

Please clone the repository and create a repository specific to your project to ensure that it remains untouched. If something in the framework should be changed please create a Pull Request and assign Madison Vincent as a reviewer for approval. 

## Support

If you need any help or have any questions/concerns please reach out to Madison Vincent for help (madison.vincent@wundermanthompson.com)
