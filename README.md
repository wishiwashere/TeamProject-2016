#Wish I Was Here

Android application designed and built by Eiren McLoughlin and Laura Pigott, as part of the Team Project module of the Creative Multimedia B.Sc (Hons) degree course in Limerick Institute of Technology, Clonmel, Co. Tipperary.

'Wish I Was Here' is the no pay holiday application, which allows users to take pictures of themselves in different locations around the world. This application is now available for download on the [Google Play Store](https://play.google.com/store/apps/details?id=processing.test.wish_i_was_here&ah=vSa3i6qQw1bEdd0nt5kwAgY1DjA)

Our aim is to make the world a little bit smaller, one click at a time.

###Personal Portfolios
_Eiren McLoughlin_: [eiren.projects.ie](www.eiren.projects.ie)  
_Laura Pigott_: [pigottlaura.com](www.pigottlaura.com)


###Development
This application was developed in Android Studio, through the following APIs and libraries
* [Processing](https://processing.org/)
	* The majority of the code for this application was creating through the Processing library
* [Ketai](http://ketai.org/)
	* Accessing the device camera
	* Accessing the accelerometer sensor of the device
	* Triggering the keyboard of the device (for text input)
* [Fabric](https://fabric.io/)
	* Handling OAuth Twitter logins
* [Twitter4J](http://twitter4j.org/)
	* Sending tweets from the app
* [Google Maps Geocoding API](https://developers.google.com/maps/documentation/geocoding/intro)
	* To lookup the latitude/longitude of a location, based on a user's search terms
* [Google Street View Image API](https://developers.google.com/maps/documentation/streetview/)
	* To request the background images of locations
  
  
###Application Functionalities
* Camera
	* Reading in camera input
	* Switching between front and rear cameras on a device
* Green Screen Keying
	* If a user stands in front of a green background (such as a green screen, or grass) the background will be removed from the image, so that they appear as though they are standing in front of the Google Street View image environment instead
	* All of the code for green screen keying was custom created for this app
	* The functionality required to complete green screen keying is quite intensive, and causes a heavy load on the device. A [green screen keying web server](https://github.com/wishiwashere/TeamProject-2016_webserver) was created to take some of the burden off the device. Images from the device camera were sent to the server using custom written HTTP POST requests, but due to the significant time delays this caused (up to 30 seconds per frame) it was decided that this was not a viable solution
* Image Orientations
	* It is possible to take images in both landscape and portrait orientations
	* Turning the device between landscape/portrait modes will result in the icons turning to reflect this, and the ratio of the Google Street View Image's size being recalculated to fill the screen
* Saving to Device Storage
	* Creating a Wish I Was Here directory to store images taken in the app
	* Saving user preferences to the device. The defaults for these user preferences are generated from our [remote resources](https://github.com/wishiwashere/wishiwashere.github.io)
* Travelling to locations
	* A series of random locations are available within the app
		* These are sourced from a our [remote resources](https://github.com/wishiwashere/wishiwashere.github.io) and so will be updated frequently to offer new and exciting locations to users
	* Users can also search for a specific location on the search screen of the app 
* Interactive Locations
	* When viewing a location, it is possible to "look around" the environment by swiping the screen
	* In portrait mode, it is also possible to enable the shake movement functionality, which allows users to look up and down in the environment using the accelerometer of their device
* Sharing Images
	* While logged in, users can send out their images to their Twitter accounts
	* Once an image has been saved, it can then be shared with any compatible app on the user's device using sharing intents
* Favourites
	* By default, users are provided with a series of "favourite" locations
	* Locations can be added, removed and visited within the app
	* All favourites are stored locally on the user's device 
* Managing the new Marshmallow (Android 6.0) Permission Requests
	* On device's running the new Marshmallow Android version, users will be prompted to allow the application to access the device camera and storage
	* Device's running older Android versions will not receive this request, as the permissions will be given to the application once the user accepts them when downloading the app from the [Google Play Store](https://play.google.com/store/apps/details?id=processing.test.wish_i_was_here&ah=vSa3i6qQw1bEdd0nt5kwAgY1DjA)
  
###Deployment
Since the beginning of the project, weekly releases have been made to the [Google Play Store](https://play.google.com/store/apps/details?id=processing.test.wish_i_was_here&ah=vSa3i6qQw1bEdd0nt5kwAgY1DjA), as per our release schedule. In total, 28 releases have been made to date. Details of these can be found on our [release](https://github.com/wishiwashere/TeamProject-2016/releases) page
* 9 Production Releases
* 4 Beta Releases
* 15 Alpha Releases

	
###Creating an App Interface through Processing
As this application was originally developed using Processing 3.0, the entire app interface was custom created to imitate the following functionalities:
* Text Inputs
	* Triggering the device keyboard
	* Accepting input from keypresses on a soft keyboard
	* Restricting the maximum allowed characters when necessary (i.e. when writing a tweet)
	* Constraining the text to appear as though it were in a text box (i.e. to make the text wrap as opposed to going off screen)
	* Hiding password input (which is no longer a part of the app, as logins are now handled using OAuth redirects)
* Icons which
	* Trigger functions
	* Link to other screens in the app
	* Link to external websites
	* Can detect if they were clicked on
* Screens
	* Scrolling of screens and screen elements
* Toggling Buttons 
	* For displaying whether a functionality is turned off/on	
	
	
###Class Structure API
The following is a visual representation of the class structure of the Wish I Was Here application, which demonstrates the methods and inheritance upon which it is built
![Wish I Was Here Class Structure API](https://github.com/wishiwashere/TeamProject-2016/blob/master/Android_Studio/WishIWasHere_ClassStructure.png "Wish I Was Here Class Structure API")
