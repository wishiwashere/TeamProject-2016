package processing.test.wish_i_was_here;

// Importing the Processing library, so this class can declare variables using Processing specific
// datatypes i.e. PImage objects.
import processing.core.*;

// Importing the java ArrayList class, so that the favourite tabs for this screen can be stored in
// an array which can be added to, removed from and updated.
import java.util.ArrayList;

// This class extends from the Screen class, which in turn extends from the Rectangle class, and so
// inherits methods and variables from both of these classes. This screen is displayed when users
// want to view or access their favourite locations, so they can quickly return to them.
public class FavouritesScreen extends Screen {

    // Creating a private variable to store the instance of the main sketch which will be passed into
    // the constructors of this class when they are initialised. The purpose of this variable is so that
    // we can access the Processing library, along with other global methods and variables of the main
    // sketch class, from within this class. Every reference to a Processing method/variable, or a public
    // method/variable of the main sketch, must be prefixed with this object while within this class.
    private Sketch sketch;

    // Creating a private boolean, which will be used to check if this page has be reloaded yet i.e.
    // so that when the user leaves this screen, it will be reset (using this class's resetScreen() method)
    // so that if the page was partially scrolled when they left it, it will reset when they come back to
    // this screen. Resetting this variable to false in the HomeScreen class.
    public Boolean loaded = false;

    // Declaring a private favTabs array, to store each of the favourite tabs we create,
    // so that they can be looped through in this class's showScreen() method, to display
    // the tabs, as well as checking if they are being clicked on.
    public ArrayList<FavouriteTab> favTabs;

    /*-------------------------------------- Constructor() ------------------------------------------------*/
    // Creating a public constructor for the class so that an instance of it can be declared in the main sketch
    public FavouritesScreen(Sketch _sketch) {

        // Passing the instance of the Sketch class, which was passed to constructor of this class, to the
        // super class (Screen), which will in turn pass it to it's super class (Rectangle). The purpose
        // of this variable is so that we can access the Processing library, along with other global methods
        // and variables of the main sketch class, from all other classes.
        super(_sketch);

        // Initialising this class's local sketch variable, with the instance which was passed to the
        // constructor of this class. The purpose of this variable is so that we can access the Processing
        // library, along with other global methods and variables of the main sketch class, from within
        // this class. Every reference to a Processing method/variable, or a public method/variable of
        // the main sketch, must be prefixed with this object while within this class.
        sketch = _sketch;

        // Creating the favTabs array to store each of the favourite tabs that will be created below
        this.favTabs = new ArrayList<FavouriteTab>();

        // Looping through the favourites array, so that we can create a new tab for
        // each favourite place
        for (int f = 0; f < sketch.favouriteLocationsData.length; f++) {

            // Creating temporary variables to store the data relating to this location from
            // the favourite locations xml file, so that they can be passed in to this favourite
            // tab's constructor. Parsing the heading and pitch data to floats, as they will have
            // been stored as strings in the XML file. The purpose of casting them back to floats is
            // that we want the user to be able to scroll around the location and therefore be able
            // to increment/decrement these values.
            String name = sketch.favouriteLocationsData[f].getString("name");
            String latLng = sketch.favouriteLocationsData[f].getString("latLng");
            float heading = Float.parseFloat(sketch.favouriteLocationsData[f].getString("heading"));
            float pitch = Float.parseFloat(sketch.favouriteLocationsData[f].getString("pitch"));

            // Creating a temporary variable to hold the new Favourite Tab, passing in the title
            // and location URL data for the current favourite, as well as the increment variable,
            // which will be used to space out the favourite tabs on the page within the constructor
            // of the FavouriteTab class (as it passes the y value to the super class, it multiplies
            // the y value by this number, so that it increases with each tab i.e. they are spaced
            // vertically down along the screen)
            FavouriteTab newFavTab = new FavouriteTab(sketch, name, latLng, heading, pitch, f);

            // Adding the new FavTab to this class's favTabs array, so that we can loop through them
            // in the showScreen() method, to display them, as well as checking if they are being
            // clicked on.
            favTabs.add(newFavTab);
        }

        // Creating the icon/s for this screen, using locally scoped variables, as these icons will be only
        // ever be referred to from the allIcons array. Setting their x, and y, based on percentages of the
        // width and height (where icon positioning variables are used, these were defined in the main sketch.
        // Not passing in any width or height, so as to allow this icon to be set to the default size in the
        // Icon class of the app. Passing in a name for the icon, followed by a boolean to choose whether this
        // name should be displayed on the icon or not. Finally, passing in a linkTo value of the name of the
        // screen or function they will later link to.
        Icon homeIcon = new Icon(sketch, sketch.iconRightX, sketch.iconTopY, sketch.loadImage("homeIconImage.png"), "Home", false, "HomeScreen");

        // Creating a temporary allIcons array to store the icon/s we have created above.
        Icon[] allIcons = {homeIcon};

        // Calling the setScreenIcons() method of this screen's super class (Screen). This passes
        // the temporary allIcons array to the screenIcons array of the Screen class so that they
        // can be looped through by the showScreen() method, and methods inherited from the Icon
        // class (such as showIcon and checkMouseOver) can be called on them from within this array.
        // This reduces the need for each screen to have to loop through it's icons, or call the
        // same method on multiple icons.
        this.setScreenIcons(allIcons);

        // Setting the title of this screen in this class's super class (Screen), so that it can be accessed
        // when showing the screen (i.e can be displayed as the header text of the page).
        this.setScreenTitle("Favourites");
    }

    /*-------------------------------------- showScreen() ------------------------------------------------*/

    // Creating a public showScreen method, which is called by the draw() funciton whenever this
    // screen needs to be displayed
    public void showScreen() {

        if (!this.loaded) {
            this.resetScreen();
        }

        // Calling the super class's (Screen) drawScreen() method, to display each of this screen's icons.
        // This method will then in turn call it's super class's (Rectangle) method, to generate the screen.
        // Calling this method after the above check has been done to see if the icons etc. on this screen have
        // been reset, so that the screen appears correctly, even on it's first load.
        this.drawScreen();

        // Looping through our array of favourite tabs, and calling the showFavourite() method (of
        // the FavouriteTab class) to display the tab on screen
        for (int f = 0; f < this.favTabs.size(); f++) {
            this.favTabs.get(f).showFavourite();
        }

        if (sketch.mousePressed) {

            float amountScrolled = scrollScreen();

            // Looping through each of the page icons, which are only being stored in an array within
            // this class so that they can be looped through to be repositioned (i.e. in every other
            // screen, these icons would be stored only in the super class, and not directly accessible
            // within the individual screen classes
            for (int i = 0; i < favTabs.size(); i++) {
                // Checking which direction the user scrolled
                if (sketch.pmouseY > sketch.mouseY) {
                    // The user has scrolled UP
                    // Setting the y position of the icon to it's current position, minus the amount scrolled i.e.
                    // moving the icon up the screen
                    this.favTabs.get(i).setY(this.favTabs.get(i).getY() - amountScrolled);
                } else {
                    // The user has scrolled DOWN
                    // Checking if the screen's y position is less than or equal to half of the height i.e. is
                    // so that the screen cannot be down any further once you reach the top
                    if (this.getY() <= (sketch.appHeight/2) - amountScrolled) {
                        // Setting the y position of the icon to it's current position, plus the amount scrolled i.e.
                        // moving the icon down the screen
                        this.favTabs.get(i).setY(this.favTabs.get(i).getY() + amountScrolled);
                    } else {
                        this.resetScreen();
                    }
                }
            }
        }
    }

    private void resetScreen(){
        // Resetting the position values of the element so on the screen every time the page is opened,
        // so that if a user leaves the screen half scrolled, it will still be reset upon their return

        // Resetting the screenTitleY position to it's original value (as it may have been
        // incremented if the about screen was scrolled
        sketch.screenTitleY = sketch.iconTopY;

        this.setY(sketch.appHeight / 2);
        this.getScreenIcons()[0].setY(sketch.iconTopY);

        for (int i = 0; i < favTabs.size(); i++) {
            favTabs.get(i).setY((float) ((i + 1) * sketch.appHeight * 0.25));
        }

        // Setting loaded to true, so that this block of code will only run once (each time this page
        // is opened). This value will be reset to false in the Icon class's checkMouseOver function,
        // when an icon that links to another page has been clicked.
        this.loaded = true;
        println("firstLoad");
    }

    /*-------------------------------------- get() and set() ------------------------------------------------*/

    public ArrayList<FavouriteTab> getFavTabs() {
        return favTabs;
    }
}