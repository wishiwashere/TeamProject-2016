import java.lang.reflect.Method;

// Declaring global variables, which will contain the width and height of the device's
// display, so that these values can be reused throughout all classes (i.e. to calculate
// more dynamic position/width/height's so that the interface responds to different
// screen sizes
float appWidth;
float appHeight;

// Creating the icon positioning X and Y variables, which will be used globally to ensure that
// the icons on each page all line up with one another. These measurements are all based on percentages
// of the app's display, and are initialised in the setup function of this sketch

float iconLeftX;
float iconRightX;
float iconCenterX;
float iconTopY;
float iconBottomY;

// Creating a global array to store all of the app's screen's in, so that they can be looped
// through in the draw function, to test whether they should be displayed or not, based
// on the currentScreen variable (which holds the name of the screen that should be displayed)
Screen[] allScreens;

// Setting the default screen to be the HomeScreen, so that when the app is loaded,
// this is the first screen that is displayed. Since this global variable is available
// throughout the sketch (i.e. within all classes as well as the main sketch) we will
// use this variable to pass in the value of "iconLinkTo" when the icon is clicked on
// within the checkMouseOver() method of the Icon class. The variable will then be tested
// against each of the potential screen names (in the main sketch's draw function) to
// decide which sketch should have the showScreen() method called on it i.e. (which
// screen should be displayed
String currentScreen = "SearchFailedScreen";

void setup() {
  // Setting the size of the sketch (for testing purposes only, will eventually be dynamic)
  size(360, 640);

  // Initialising the appWidth and appHeight variable with the width and height of the device's
  // display, so that these values can be reused throughout all classes (i.e. to calculate
  // more dynamic position/width/height's so that the interface responds to different
  // screen sizes (for testing purposes, I am currently setting these to the width and height
  // of the sketch as the display size of my computer screen gets called when using displayWidth
  // and displayHeight
  appWidth = width;
  appHeight = height;

  // Initialising the icon positioning X and Y variables, which will be used globally to ensure that
  // the icons on each page all line up with one another. These measurements are all based on percentages
  // of the app's display width and height (as defined above
  iconLeftX = appWidth * 0.15;
  iconRightX = appWidth * 0.85;
  iconCenterX = appWidth * 0.5;
  iconTopY = appHeight * 0.085;
  iconBottomY = appHeight * 0.92;

  // Creating the allScreens array with a length of 7 (which will have to be adjusted each time a new
  // screen is added below). This array will be used in the draw function of this sketch to loop
  // through each screen, and test it's class name against the current screen that is required (i.e.
  // the String value stored in the currentScreen variable, which is set in the Icon class when 
  // an icon that links to another page is clicked on).
  allScreens = new Screen[7];
  
  // Creating the screens which will be used in this application. Setting a random background
  // colour for each of these screens so that transitions between them can be more obvious
  // (for testing purposes only). Not giving these screen's an instance name, instead storing them
  // in the allScreens array (as this is the only place they will ever be accessed from.
  allScreens[0] = new HomeScreen(#000000);
  allScreens[1] = new CameraLiveViewScreen();
  allScreens[2] = new FavouritesScreen(#CE04BA);
  allScreens[3] = new SettingsScreen(#2023A5);
  allScreens[4] = new AboutScreen (#30B727);
  allScreens[5] = new SearchTravelScreen(#E88121);
  allScreens[6] = new SearchFailedScreen(#F5C811);
}

void draw() {
  // Looping through all of the screens of the app, which have been stored in the 
  // allScreens array
   for(int s = 0; s < allScreens.length; s++){
     
     // Storing the class name of this screen in a temporary String variable, so 
     // that we can check if this class name matches with the current screen that
     // is required
     String thisScreen = allScreens[s].getClass().getName();
     
     // Checking if this screen's class name contains the String that is stored
     // in the currentScreen variable (which gets set in the Icon class when
     // and icon is clicked on
     if(thisScreen.indexOf(currentScreen) > -1){
       
       // Logging out the class name of this screen
       //println(thisScreen);
       
       // Calling the Screen method showScreen() to display the contents of the
       // current screen i.e. switching to this screen
       allScreens[s].showScreen();
     }
   }
}