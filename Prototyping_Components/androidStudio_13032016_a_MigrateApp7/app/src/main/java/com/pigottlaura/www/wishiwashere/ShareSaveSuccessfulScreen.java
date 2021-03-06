package com.pigottlaura.www.wishiwashere;

import processing.core.*;

public class ShareSaveSuccessfulScreen extends Screen {
    private PApplet p;

    // Creating a public constructor for the TemplateScreen class, so that
    // an instance of it can be declared in the main sketch
    public ShareSaveSuccessfulScreen(PApplet _p, PImage bgImage) {

        // Passing the color parametre to the super class (Screen), which will in
        // turn call it's super class (Rectangle) and create a rectangle with the
        // default values i.e. fullscreen, centered etc.
        super(_p, bgImage);

        // Setting the title of this screen. The screenTitle variable was also declared in this
        // class's super class (Screen), so that it can be accessed when showing the screen
        // (i.e can be displayed as the header text of the page). If no screenTitle were set,
        // then no header text will appear on this page
        this.setScreenTitle("");
    }

    // Creating a public showScreen method, which is called by the draw() funciton whenever this
    // screen needs to be displayed
    public void showScreen() {

        // Calling the super class's (Screen) drawScreen() method, to display each of this screen's
        // icons. This method will then in turn call it's super class's (Rectangle) method, to
        // generate the size and background of the screen
        this.drawScreen();
        this.addText("Your postcard", iconCenterX, appHeight * 0.1);
        this.addText("has been", iconCenterX, appHeight * 0.18);
        this.addText("successfully", iconCenterX, appHeight * 0.26);
        this.addText("shared & saved", iconCenterX, appHeight * 0.34);
        this.addImage(loadImage("sharingScreenImage.png"), appWidth/2, appHeight * 0.6, appWidth * 0.8, appWidth * 0.4);
    }
}