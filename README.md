# slogo
Test
A development environment that helps users write SLogo programs.

## Authors

Katie Van Dyk, Martin Muenster, Brandon Dalla Rosa, Eric Fu

## Dates

Start date:February 15

End Date: March 9

Number of Hours worked: 100

## Roles

Katie: Creating frontend panels and panes, including new tabs and views, adding functionality to buttons and writing controller hierarchy to connect frontend to backend. Also wrote functionality to load/save command files and workspace preference files.

Brandon: Responsible for creation and animation of turtle object, frontend logic and development of frontend. 

Martin: Creating command objects that compile the instructions, building necessary components for the commands (dictionaries), and designing an object to pass the results of the commands to the turtle.

Eric: Parsing the input from files and the command line, and creating the command tree to pass to the controller.

## Resources used
### Katie Resources: 
Tabs:
* https://stackoverflow.com/questions/17018562/how-to-create-tabs-with-icons-in-javafx
* http://www.java2s.com/Code/Java/JavaFX/AddTabtoTabPane.htm

Writing to text file:
* http://java-buddy.blogspot.com/2012/05/save-file-with-javafx-filechooser.html

Making list of integers w/o for loop:
* https://stackoverflow.com/questions/10242380/how-can-i-generate-a-list-or-array-of-sequential-integers-in-java

JavaFX File Chooser:
* https://docs.oracle.com/javafx/2/ui_controls/file-chooser.htm

JavaFX Color Picker: 
* https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/ColorPicker.html

CSS Reference Guide:
* https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html

In class Animation example 

## Files used to test project and errors handled

  Example files to run can be found in the data/examples directory. To upload workspace preferences, use the open button in the application window to navigate to the src/resources/savedWorkspaces directory. User-saved workspaces can also be found here.

## Information about using the program

To run commands, insert valid slogo syntax into the user input and hit enter or click run. Only the “Tell” command will create new turtles. Calling “Tell” with a single integer ID that doesn’t exist will create as many new turtles as there are undefined IDs below the inputed ID and set the turtles with these IDs active. Calling tell with more that one input will create one new turtle per ID input if that ID is undefined. “Ask” will not create new turtles.

For ambiguous grouping, when it may not make sense to have a group with a given command, our code is designed to execute that first command only. We decided to design our code this way to prevent unexpected crashed and to reduce the number of unexpected events within the simulation.

Our team decided to implement recursion using the “Define” command. Our parser parses and creates a command before it is executed; therefore, without a messy workaround, it would be impossible to get the required number of input arguments for a custom command when that same command is called. By using “Define” the parser knows how many inputs a custom command requires, and therefore is able to create a valid command hierarchy. If the user never defines a custom command before a recursive function is created, they will receive an error.

For an input like “set :x 10 fd set :x + :x 10” we decided that all turtles should move a different distance. More generally, we decided that commands should be run for each turtle, since it makes sense that turtles shouldn’t all abide to a single execution of a command. In this way, we were able to implement turtles each moving a random distance, and turtles acting based on their ID.

 
## Decisions, assumptions, or simplifications made

We assume that all recursive functions define themselves before their implementation is given. We also simplified so that only the “Tell” command will create new turtles.

On the frontend, we assumed that once a palette was chosen, its color reigned over user-picked background colors (which can only be set temporarily). We also assumed that the setPalettes command set the background color of the indexed palette. 

Finally, we assumed that the turtle would just not moved if given a command that would make it go out of bounds.

## Extra features included
#### Basic Extensions
##### Front end
* Multiple tabs, user can switch between them, add and exit from them
* Shows current state of a turtle (i.e., its ID, position, heading) and its pen (i.e., up/down, color, thickness) updated while the code is running.
* Palettes of images and colors with their associates numeric values that can be referred to within SLogo commands 
* Click to toggle which turtles are currently active (active turtles should be graphically distinct from inactive ones)
* Can move current turtle(s) graphically (FD, BK, LT, and RT by a fixed amount)  
* Can change the pen's current properties graphically (up/down, thickness, color)
* User can save and load preferences for each workspace tab, including workspace attributes like turtle image, background color, pen color/size, etc.

##### Back end
* Turtle recognizes additional commands  [recognize these additional commands]
* Users can save (and later read) a file of valid SLogo functions/variables  
    Thus the user can develop a library of work which, when loaded, are added to the set of user-defined commands that can be entered interactively.

#### Challenging Extensions

##### Front end
*  Turtles' movement is animated. User can control how fast the turtle animates when it performs  
* User can pause, undo/redo and reset turtle's actions.

##### Back end
* Grouping: allow variable number of parameters to procedures where appropriate by using parentheses (e.g.,  `sum`,  `difference`,  `product`,  `and`,  `or`, etc.)
* Recursion: allows for recursive command calls

## Impressions of assignment
A challenging but rewarding assignment. Challenged us to think about hierarchies beyond traditional inheritance hierarchies, like composition, and allowed us to refine our design skills by using internal/external APIs.
