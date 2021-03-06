Boolean learningModeOn = true;

String favText = "Tap on the star to save your current location to Favourites";
String homeText = "Tap here to return home";
String shakeMovementText = "Tap here to enable movement. By tilting your phone forwards and backwards you can look up and down. Or swiping left or right you can turn around in your location";
String captureText = "Tap here to take a picture";
String switchCameraText = "Tap here to switch camera views";

PImage bgImage;
float rectX = 275;
float rectY = 20;

float distance = dist(rectX, rectY, mouseX, mouseY);

void setup() {
  size(640, 360);
  bgImage = loadImage("london.jpg");
}

void draw() {
  background(bgImage);
  noStroke();

  if (learningModeOn == true) {
    //For the text box
    fill(0, 255, 0);
    rect(width/32, height/16, width/4, height/6);
    rect(width/1.4, height/16, width/4, height/6);
    rect(width/32, height/1.3, width/4, height/4.8);
    rect(width/2.8, height/1.3, width/4, height/6);
    rect(width/1.4, height/1.3, width/4, height/6);

    //For the text
    fill(255, 0, 0);
    text(favText, width/24.5, height/14.2, width/4, height/6);         
    text(homeText, width/1.38, height/14.2, width/4, height/6); 
    text(shakeMovementText, width/24.5, height/1.29, width/4, height/4.8); 
    text(captureText, width/2.7, height/1.29, width/4, height/6);
    text(switchCameraText, width/1.38, height/1.29, width/4, height/6); 

    //for learning mode box
    fill(48, 2, 125, 100);
  }
  if (mousePressed) {
    learningModeOn = false;
  /* if((mouseX > 225 && mouseX < 275)&& (mouseY > 15 && mouseY < 25)){
     println("clicky  click");
     learningModeOn = true;
    }*/
    // println("mosue pressed");
  }

  if (learningModeOn == false) {
    // println("Learning mode was set to false");
    fill(0);
    rect(rectX, rectY, 50, 50);
    fill(0, 0, 0, 0);
  }
  rect(0, 0, 640, 360);
}