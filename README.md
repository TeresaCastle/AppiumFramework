# Appium Framework

The project contains a framework for running Appium. The baseFramework branch contains a mostly blank framework with 1 example test in each platform. The testingFramework branch contains more detailed testing examples to work from. Both contain detailed comments throughout the code. 

The Framework was made using IntelliJ on MacOS, so file path's mentioned in code may need to be adjusted

## Installation

### Requirements:

1. Typical IntelliJ setup for TestNG including Java, Maven, Selenium, TestNG, Node.js (dependencies should already be in the pom.xml file) etc.
    a. Currently the pom.xml file uses the following versions: Appium 7.5.1, Selenium 3.141.59, TestNG 7.4.0, Maven Surefire 3.0.0-M5
    b. If you have newer versions, update the version numbers in the pom.xml. Then right click on the pom.xml > Maven > Reload Project to load the dependencies 
3. Appium installation - I recommend the desktop client
4. XCode Installation (set up your preferred simulator)
5. Android Studio Installation (set up your preferred emulator)
6. If doing testing on desktop browsers, insure they are installed. Safari will require you to allow automation within the developer menu (enabled in advanced settings)

### Steps

Note: a great guide on Git exists at https://rogerdudler.github.io/git-guide/

1. Create a new repository in github for your project
2. Navigate to the repository. The page should be focused on Quick Setup and the bottom section should say "...or import code from another repository". Click "Import Code"
3. Paste in https://github.com/madison-vincent/DemoAppiumFramework.git and click "Begin Import"
5. The new repository should now be a copy of the Demo framework
6. Open the IDE and create a new project based on a Verified Code Source (Get form VCS on IntelliJ). Provide the URL to your new repository
7. Switch to the repo branch you want to view/work in. Run the following command (Replace baseFramework with testingFramework to checkout the branch with example tests):
 git checkout baseFramework
 git pull baseFramework
 8. The code should be in place

## Usage

## Contributing

Please clone the repository and create a repository specific to your project to ensure that it remains untouched. If something in the framework should be changed please create a Pull Request and assign Madison Vincent as a reviewer for approval. 

## Support

If you need any help or have any questions/concerns please reach out to Madison Vincent for help (madison.vincent@wundermanthompson.com)