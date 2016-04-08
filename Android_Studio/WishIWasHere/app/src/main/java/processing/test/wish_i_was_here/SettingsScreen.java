package processing.test.wish_i_was_here;

import processing.core.*;

public class SettingsScreen extends Screen {
    private Sketch sketch;

    public Icon learningModeIcon;
    public Icon autoSaveIcon;

    // Creating a public constructor for the SettingsScreen class, so that
    // an instance of it can be declared in the main sketch
    public SettingsScreen(Sketch _sketch) {

        // Passing the color parametre to the super class (Screen), which will in
        // turn call it's super class (Rectangle) and create a rectangle with the
        // default values i.e. fullscreen, centered etc.
        super(_sketch);

        sketch = _sketch;

        String autoSaveToggleSwitchImage;
        if(sketch.autoSaveModeOn){
            autoSaveToggleSwitchImage = "toggleSwitchOnIconImage.png";
        }
        else{
            autoSaveToggleSwitchImage = "toggleSwitchOffIconImage.png";
        }

        String learningModeToggleSwitchImage;
        if(sketch.learningModeOn){
            learningModeToggleSwitchImage = "toggleSwitchOnIconImage.png";
        }
        else{
            learningModeToggleSwitchImage = "toggleSwitchOffIconImage.png";
        }
        // Creating the icon/s for this screen, using locally scoped variables, as these
        // icons will be only ever be referred to from the allIcons array. Setting their
        // x, and y, based on percentages of the width and height (where icon positioning variables
        // are used, these were defined in the main sketch. Not passing in any width or height, so as
        // to allow this icon to be set to the default size in the Icon class of the app . Passing
        // in a colour value of white. Passing in a name for the icon, followed by a boolean to choose
        // whether this name should be displayed on the icon or not. Finally, passing in a linkTo
        // value of the name of the screen they will later link to. The title arguments, as well
        // as the linkTo argument, are optional
        Icon homeIcon = new Icon(sketch, sketch.iconRightX, sketch.iconTopY, sketch.loadImage("homeIconImage.png"), "Home", false, "HomeScreen");
        learningModeIcon = new Icon(sketch, sketch.iconRightX * 0.9, sketch.iconCenterY * 0.5, sketch.smallIconSize * 1.8, sketch.smallIconSize * 0.9, sketch.loadImage(learningModeToggleSwitchImage), "Learning mode switch", false, "_switchLearningMode");
        autoSaveIcon = new Icon(sketch, sketch.iconRightX * 0.9, sketch.iconCenterY * 0.8, sketch.smallIconSize * 1.8, sketch.smallIconSize * 0.9, sketch.loadImage(autoSaveToggleSwitchImage), "Auto-save switch", false, "_switchAutoSave");

        if(TwitterLoginActivity.twitterLoggedIn) {
            Icon twitterAccountIcon = new Icon(sketch, sketch.iconCenterX, sketch.iconCenterY * 1.2, sketch.largeIconSize, sketch.largeIconSize, sketch.loadImage("twitterAccountIconOnImage.png"), "Twitter", true, "Below", "_checkTwitterLogin");
            Icon[] allIcons = {homeIcon, learningModeIcon, autoSaveIcon, twitterAccountIcon};
            // Calling the setScreenIcons() method of this screen's super class (Screen). This passes
            // the temporary allIcons array to the screenIcons array of the Screen class so that they
            // can be looped through by the showScreen() method, and methods inherited from the Icon
            // class (such as showIcon and checkMouseOver) can be called on them from within this array.
            // This reduces the need for each screen to have to loop through it's icons, or call the
            // same method on multiple icons.
            this.setScreenIcons(allIcons);
        } else {
            Icon[] allIcons = {homeIcon, learningModeIcon, autoSaveIcon};
            // Calling the setScreenIcons() method of this screen's super class (Screen). This passes
            // the temporary allIcons array to the screenIcons array of the Screen class so that they
            // can be looped through by the showScreen() method, and methods inherited from the Icon
            // class (such as showIcon and checkMouseOver) can be called on them from within this array.
            // This reduces the need for each screen to have to loop through it's icons, or call the
            // same method on multiple icons.
            this.setScreenIcons(allIcons);
        }

        // Setting the title of this screen. The screenTitle variable was also declared in this
        // class's super class (Screen), so that it can be accessed when showing the screen
        // (i.e can be displayed as the header text of the page). If no screenTitle were set,
        // then no header text will appear on this page
        this.setScreenTitle("Settings");
    }

    // Creating a public showScreen method, which is called by the draw() funciton whenever this
    // screen needs to be displayed
    public void showScreen() {

        // Calling the super class's (Screen) drawScreen() method, to display each of this screen's
        // icons. This method will then in turn call it's super class's (Rectangle) method, to
        // generate the size and background of the screen
        this.drawScreen();

        // Adding text to go beside the icon that will be toggled in order label each setting for the users convenience
        this.addText("Learning Mode", "LEFT", sketch.iconLeftX, sketch.iconCenterY * 0.5);
        this.addText("Autosave Image", "LEFT", sketch.iconLeftX, sketch.iconCenterY * 0.8);
    }
}