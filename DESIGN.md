
# DESIGN.md
## SLogo Team 01
Brandon Dalla Rosa, Eric Fu, Martin Muenster, Katherine Van Dyk

### High Level Design Goals
Our project has is designed to have three main components; the front end, the back end, and the controller as an intermediary between the both. This allows the front and back end to have no direct contact, allowing the controller to validate and pass data to the desired classes. The front end takes in user input and preferences, and sends the desired data to the controller using the external API. The controller holds this data and sends it to the back end, where the data is parsed and converted into a series of commands. These commands are converted into states for the turtle to use to update, and a list of states is sent from the back end back to the controller. The controller then holds the states and sends them to the front end, where the turtle view visually shows the desired preferences and changes.

This design allows for the project to have clear-cut external and internal APIs for both the front and the back end, while creating a clean design for the Slogo project. It is an adaptation of the MVC design pattern, in which there is a model, a view, and a controller for the project.

  

### New Features
#### Add New Language
To add a new language, a new resource file must be created in the format LanguageName.properties, detailing the translation of all commands. Then, it will automatically show up as an option in the UI.

#### Add New Command
To add a new command, language files must be edited so that it includes a line with '<CommandClassName> = <CommandLanguageString>'. Additionally, an entry must be made in the 'CommandChildrenNumbers' to specify the number of arguments the new command takes in the format '<CommandClassName> = <# arguments>', and in the 'CommandTypes' file to specify which folder the command class is contained in in the format '<CommandClassName> = <folder name>'. When a command class is created, it must extend the Command class at least, and if necessary, any other abstract command class. It must express its behavior in the implemented methods-- most importantly the ‘execute’ and ‘getReturnValue’ methods. After the contents of the command are implemented, it should function in the UI.  

#### Add New Frontend Feature
##### Add a Component Within a Panel
To add a component within a Panel, navigate into the existing Panel class. You can use the existing Factory classes created within the Panel superclass to create a styled Button, Chooser, or Text field of your choice. Simply add the action that you want this component to complete using currently imported objects, or within the MainScreen, add a new input to the Panel class that could be toggled/changed by the new feature.

##### Add a New Panel
To add an entire Panel to a currently existing screen (e.g. the Main Screen), simply create a new Panel subclass and implement the construct() method, which returns a fully populated Pane. Use the existing Factory classes to add smaller components to your Panel.

To attach the new Panel to the main screen, create an instance of it in the MainScreen class. To position the panel, within the initializeBorderPane() method of the MainScreen() class, stick it in one of the positions within the existing BorderPane (e.g., right, center, bottom). Margins are automatically handled by this method. To stick multiple Panels within the same child of the BorderPane, create an HBox/VBox with the desired components. 

##### Add a New Screen
To add a new Screen (meaning a pop-up screen), simply add a new class to the Screen package and create the screen features, including the stage to attach the screen to. Correspondingly, to make the screen pop up, connect a button to it on the MainScreen. Have this button, when clicked, launch a new instance of the desired screen.

If I could alter this design to make adding a new screen easier, I would add a Screen superclass that, in its constructor, made a Stage and centered it on the Screen, as well as an abstract 'construct' method. This way, the user would only have to extend this class and implement the Construct method to add features




### Justifications
#### When Commands Were to be Parsed
One design decision that we made was between having the commands be compiled as they were being parsed or having the commands be compiled after parsing was done. Compiling the commands in the parser would have made recursion and the "To" command much easier to implement. It would also simplify the code for values and string variable or custom command names, since the parse would know what to look for as arguments while it was parsing. Therefore strings and numbers wouldn't have to be put into command types such as "StringVar" or "Value" commands, which would have simplified the command classes. On the other hand, Compiling commands in the parser would have lead to very messy, confusing code within the parser. It also is less intuitive and would require a lot of exceptions for certain commands that wouldn't function well within that framework, such as grouping. Having command compiling outside of the parser makes some commands less intuitive. For example, we had to treat numbers, string variables, and parentheses/brackets as separate commands. 

Furthermore, There also were a few exceptions that had to be made within the parser. For example, when the parser saw a "To" or "Make" command, it would have to somehow attach the name of the command or variable to the instance of the command it just made, a process that doesn't occur in any other command. The pros of this design include a more modular and flexible design. The fact that all of the commands are created before they are compiled allows for the ability to easily add or change a type of command. For example, our design could handle switching the syntax of adding. Instead of "+ 3 4", we could handle "3 + 4" through simple changes. This change is difficult for in-parser compiling because compiling a value before the operation is known does not perform the operation. I prefer compiling the commands outside of the parser. Although recursion was tricky for us (we had to use the "Define" command), it was definitely more intuitive and allowed me to complete the extension challenges much more easily.

#### State Object
The other design decision that we made was having the commands directly move the turtles vs. having an object that handles this communication instead. Had the commands had the ability to edit the turtle directly, compiling commands would be much more convinient. A "fd 50" command, for example, could simply add 50 to the turtles position. The biggest problem with this method is that every single command is able to directly edit the turtle. This creates problems with keeping the program closed, since a command could accidentally delete or corrupt a turtle's data. The other way to handle communication between commands and the turtles is to have an object that holds the states of the turtle. These states can be created as the commands are compiled, and can then be passed into the turtle, where the turtle can set its state to the states that it received. The benefits of this method are that the turtle class can stay as closed as possible, since the commands would have no knowledge of the turtle. Furthermore, when the commands have to change the color of the background, for example, they could put the change in this state object so that the view doesn't have to be known by every command. I prefer having the commands communicate indirectly with the turtles. It helps keep our code robust and ultimately allows us to add more features without opening our code up.

#### Controller Hierarchy
One of the extensions that led to what I believe to be one of our best design changes/decisions was creating multiple views. Initially, the Driver class created an instance of the Controller class, which handled creating an instance of the ViewController class (to display all features of the current turtle and the turtle itself), and also taking updating the Turtle state from user input. In implementation, this meant that the Controller class connected the components of the backend, such as by calling an instance of the parser, passing user input to the Parser, and creating Command object's from the Parser's input. In this Controller hierarchy, the View Controller also received an instance of the stage from the Driver, and mounted a new Scene to this Stage. With this design decision, mounting multiple views to the same Stage would be impossible, since a Tab pane would have to be created a level above the MainScreen.

To solve this issue, we could either maintain the same Controller hierarchy and simply create multiple stages for multiple views, or rearrange the Controllers so that the top level Controller mounted different instances of the Main Screen to the Stage, and then we would maintain one instance of the Tab Pane on the top of the screen. We decided to adjust the Control hierarchy by also creating a ModelController to handle parsing user input. This ModelController would then create an instance of the ViewController, which instead of handling the entire stage, handled creating the display for one Model (or one Turtle Panel).

I prefer the design that we ended up with because it makes the Control classes more modular and their responsibilities clear. It makes sense that the top-level controller would hold the Stage, and that the ModelController, which is located on the top-level of the backend, would handle connecting the backend components.

One change I would make to this design, however, is to make the components of the screen being swapped with a changed tab more modular. In the current design, the Controller swaps entire instances of the Model Controller between tabs- including parts of the turtle panel that remain the same between tabs- like buttons to move the turtle, workspace saving, etc. In a future iteration of the design, I would elect to create a scope of the model that contains only the state of the Turtle, its Panel, and a collection of its Dictionaries, and store this in a 'Model' object.

### Assumptions and Decisions

For ambiguous grouping, when it may not make sense to have a group with a given command, our code is designed to execute that first command only. We decided to design our code this way to prevent unexpected crashed and to reduce the number of unexpected events within the simulation.

Our team decided to implement recursion using the ‘Define’ command. Our parser parses and creates a command before it is executed; therefore, without a messy workaround, it would be impossible to get the required number of input arguments for a custom command when that same command is called. By using ‘Define’ the parser knows how many inputs a custom command requires, and therefore is able to create a valid command hierarchy. If the user never defines a custom command before a recursive function is created, they will receive an error.

For an input like ‘set :x 10 fd set :x + :x 10' we decided that all turtles should move a different distance. More generally, we decided that commands should be run for each turtle, since it makes sense that turtles shouldn’t all abide to a single execution of a command. In this way, we were able to implement turtles each moving a random distance, and turtles acting based on their ID.

On the frontend, we assumed that once a palette was chosen, its color reigned over user-picked background colors (which can only be set temporarily). We also assumed that the setPalettes command set the background color of the indexed palette.

Finally, we assumed that the turtle would just not moved if given a command that would make it go out of bounds.