# SLogo Addition
Katherine Van Dyk (kmv10)

## Before

### Time Estimate
I estimate it to take between 2-2.5 hours to implement the Frontend extension. I believe that it will take 1-1.5 hours to create the new Panel and test it, and then another 0.5-1 hours to refactor and document my changes. I estimate that the most time will be spent on creating the ScrollPane and also figuring out the best way to pass the new image information to the Turtle.  

### Files Changed
I’ll have to change the MainScreen class in order to incorporate the new panel into the BorderPane, as I plan on making the new panel a substitute for the left side of the ‘current state’ panel at the bottom of the screen, which currently displays state information for only one turtle. I will also have to create a 'Frontend Turtle' interface, so that I can encapsulate unnecessary Turtle information from my new panel. I plan to make my panel a ScrollPane that includes a button for each Turtle. The button itself will contain the image of the Turtle, and clicking it will cause the Turtle’s ID and image to show up to the right of the panel, along with an ImageChooser, to change the image of the Turtle.  This information will be passed, using the Frontend interface, to the Turtle class, where it will be updated and reflected on the Turtle Panel using its existing 'changeImage' method. I can use the existing image properties file to give the user image options. I’ll likely have to change the default.css file too, to make a custom button to hold the Turtle. 