# API_REVIEW_mlo11_bkd8_jks38

## Review squad
Marcus Oertle (mlo11)
Brandon Dalla Rosa (bkd8)
Kelley Scroggs (jks38)

## Part 1
### Part 1 - Marcus Oertle
1.  What about your API/design is intended to be flexible?
    * My API/design is flexible because it fits the needs of the SLOGO program well as it allows every sectino of the program to communicate in a streamlined, standardized way. If a feature needs to be added, the framework will already be in place for the communication that the feature requires to happen.
2.  How is your API/design encapsulating your implementation decisions?
    * It allows each part of the visualization to be handled separately. The turtle displayer can be its own class completely separate from the toolbar and again separate from the prompt, etc. This is due to the strong focus on communication between parts of the project we made.
3.  What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
    * Since I am in charge of displaying errors in the first place to the user as the front-end guy, if something goes wrong on the backend and I get an invalid input into my front-end class methods, I can simply display something along the lines of "something went wrong!" to the user. Ideally, that error message would never show in practice as the back-end wouldn't bug out. The only other exceptions are invalid user commands which are handled by the back-end and I'm simply told what error I need to display a message for.
4.  Why do you think your API/design is _good_ (also define what your measure of good is)?
    * My API design is good because every portion of the project is it easy to learn and talk about, it should (in theory) lead to easily readable code as there will be less large chunks of code and more function calls, and will definitey allow for extension if new features need to be added. I'm not sure how "hard to misuse" it is though, I think it definitely could be misued rather easily. Perhaps someone might misunderstand the purpose behind some of the objects and think they are self-updating when they are actually not.

### Part 1 - Brandon Dalla Rosa
1.  What about your API/design is intended to be flexible?
    * We are intending to make our API methods general, but at the same time well fitted to the needs of the general Slogo program. By this I mean that the methods in the API will focus more on the general flow of data in the program, rather than the exact functionality of the classes. As long as the data being sent between the components of the program is the same, then the API should be flexible enough to encompass any changes within the various components. The classes themselves can change and fit any number of different extensions of the program, as long as the same basic data is being sent between the different program components.
2.  How is your API/design encapsulating your implementation decisions?
    * Our API design focuses strongly on the transmission of pertinent program data that needs to be shared between the same or different components. It focuses on the general data that the program needs to transfer, rather than small specific details that the classes may need. This way, even if the program has to change, it will still be a Slogo program and then therefore will be able to use the same API for the general data flow.
3.  What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
    * My part of the program is the front end design, and controller design. The error cases that will be encountered will be related to the files being read in for various program labels and preset data. Additionally, there will be a few errors which will be detected by the command line. These will be unrelated to parsing, but more along the lines of the format of the data, such as an empty string. These errors will be handled by describing the error to the user in a displayable message and then halting the process which encountered the error.
4.  Why do you think your API/design is _good_ (also define what your measure of good is)?
    * We believe our API design is good because the methods are well split up. The external API handles the data transfer between the various components, while the internal API handles the interactions within the components. The data being transferred will be general to the function of the program so that it will not be required to be changed later on when changing various aspects of the program. This allows the API to be easy to understand and flexible, while unchanging through progress, which is good.

### Part 1 - Kelley Scroggs
1.  What about your API/design is intended to be flexible?
    * Our API design is inteded to support features including constructing the graphical user interface of the program, creating buttons, listening to user input events, and displaying error messages to the user. If at any point, someone wishes to change how these features are constructred or displayed, our front end API will provide a easy to learn method for extension and/or modification
2.  How is your API/design encapsulating your implementation decisions?
    * We plan to have our API depend on some sort of CSS style sheet to design the UI components of the graphical user interface, in order to hide the style decisions from the program. This part of the program will not need to know anything about the backend calculations behind moving the turtle to display the image in the UI
3.  What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
    * The error cases we expect to handle in this part of the API are those that lead to an incorrect display of the program, for example the turtle moving out of range of the screen. We plan to handle them by alerting the user through a pop-up notification
4.  Why do you think your API/design is _good_ (also define what your measure of good is)?
    * The definition of a good API is something that hits all of the design goals: easy to learn, leads to readable code, hard to misuse, and provides for extension. I believe that my API is good because it will have all of those aspects. The API should be easy to learn and hard to misuse because it will have functions that are responsible for limited but important aspects of the programs operation. It will provide for extension by allowing the front end team and easy way to change the program in light of new specificiations 
    
## Part 2
1.  How do you think Design Patterns are currently represented in the design or could be used to help improve the design?
    * Design pattern are currently represented in the design as the flow of data and currently we have it so there are 3 main components: the front-end, back-end, and controller to bridge the gap between them. The internal API's for each of these will deal with the communication between classes within components, while the external will be dealing with communication between the different components. We can better improve this design by allowing the methods of the API to be more flexible to handle various changes in the program without actually having to change themselves.
2.  What feature/design problem are you most excited to work on?
    * We are most excited to work on the turtle and figure out the best way to make a fluent and easy-to-modify interface for modifying the turtle's graphics.
3.  What feature/design problem are you most worried about working on?
    * We are most worried about working on making the GUI and visual aspects of the program very flexible and adjustable for any desired screen size and configuration.
4.  Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).
    * Below:

### Use Cases
#### Case 1
    * User manually adjusts the relative size of the components of the GUI
The GUI segments would automatically adjust based on the new size of the component being changed. This would be done through methods notifying the GUI that the size of one component is no longer the same.
#### Case 2
    * User attempts to select an invalid file for the initial configuration of the program
The invalid file would be recognized as invalid, and a user error message would be displayed. The message would contain information about the reason the file was invalid, so the user would be able to select a correct file.
#### Case 3
    * User attempts to run code (presses enter or enter button) without actually entering any code into the terminal
The GUI displays a warning message telling the user that they must enter code first.
#### Case 4
    * User inputs code that moves the turtle out of the screen's bounds. 
The turtle would realize that its new location is out of the screen's view, and therefore it would set its location to wrap around the viewable screen.
#### Case 5
    * User changes the language of the simulation via GUI object
The external API for the front end would then send the desired language through to the back-end or controller, and then notify them that the language had been changed at the same time.