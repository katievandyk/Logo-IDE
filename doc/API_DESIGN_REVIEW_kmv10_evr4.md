API Review (evr4, kmv10)
=======
### Part 1:
* What about your API/design is intended to be flexible?
	* Katie: Our internal interpreter API is designed to support new types of commands, through the abstract command superclass and its subclasses (which sort commands by type). Our internal graphical API supports the addition of new types of graphics and stylistic effects. Our external graphical API supports adding features such as multiple ‘turtle windows’, and additional panels to support new types of user input. Finally, our external interpreter API supports the addition of transmitting new types of commands to the turtle object, and even processing information to features beyond the turtle object (such as window color, or any changeable part of the GUI).
	* Erik: Our back end internal API will be able to support new types of commands that may be added after basic implementation. It will also be able to support different definitions of the mover that draws across the screen, in case the user may like to pick a different image for the turtle, etc...It will also support flexibility in its parsing of commands to be able to implement commands of multiple different languages, which will be selected by the user in the front end of the project.

* How is your API/design encapsulating your implementation decisions? 
	* Katie: Our internal interpreter API hides the details of how command structures are executed. Our external interpreter API hides how commands are parsed and actions for the turtle object are generated (as a string is inputted, and an arraylist of actions are returned to the external graphical API). Our internal graphical API hides how screens and their features (buttons, panels, text fields, graphics) are generated, and only allows the user to change these through pre-defined channels. Our external graphical API hides how commands are taken from the text field and passed to the internal graphical API, and how errors passed into it are handled.
	* Erik: Similarly, our back end internal API will hide the implementations of the command parsing and the actual implementations of the commands to move the turtle displayed in the GUI from the user. The user will only be able to see the names of the methods that implement the commands, rather than seeing the logic of how these commands are implemented by the project.
* What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
	*  Katie: My api (the internal interpreter API) encounters the error of invalid commands input by the user (which will become evident in the parser class and command object classes). We plan to handle them by throwing them up to the external interpreter API, and then to the frontend for display. 
	* Erik: The back end internal API will throw errors for input commands of invalid format. These errors will be encountered in the back end of the project and will be passed to the front end where an error message will be displayed to the user, explaining why the error occurred.
* Why do you think your API/design is good (also define what your measure of good is)? 
	* Katie: I think the internal interpreter API is good because it is structured to handle a variety of different commands, through the use of an abstract command superclass/inheritance. It also allows for a variable number of instructions to be parsed at once by using an arraylist to hold the action events for the turtle, GUI styling, and other features.
	* I think this API design is good because it makes the code in the back end much more organized and readable. Any person can look at the superinterface and determine what methods get implemented in its subclasses. From this they can easily determine how to add new subclasses that could add extra functionality to the program (such as adding new commands).
 
### Part 2:
* How do you think Design Patterns are currently represented in the design or could be used to help improve the design?
	* Katie: The parser design models the example shown in class, and we use the controller feature of the model-view-controller design model to pass our instruction information to the frontend. 
	* Erik: We don’t currently implement any design patterns but perhaps a model-view-controller design model would be helpful to add to our project as well in order to pass the command implementation to the front end.
* What feature/design problem are you most excited to work on? 
	* Katie: I’m most excited to work on passing the instructions to the turtle, and seeing the turtle execute the instructions, especially using conditional structures like for loops and if statements. 
* Erik:  I’m most excited to work on writing the various command classes and figuring out  how to implement them with the values gained from parsing the input commands.
* What feature/design problem are you most worried about working on? 
	* I’m worried about how to deal with the actual turtle object and implementing the commands to update it in the GUI. I think it might be difficult creating an object that represents the turtle image and the path that it creates each time its moves. I also think it might be difficult to determine all the methods that must be encompassed in the Turtle class that will be needed for implementing the commands.
* Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).
	* Erik: adding a command to hide the turtle, adding a command to change the line color, implementing a command that is written in German, adding a new image for the turtle, moving the turtle outside of the boundaries of the scene
	* Katie: adding a new type of command to resize the turtle, adding a new turtle object, receiving an invalid command from the user, executing a movement command to move the turtle outside of the screen

