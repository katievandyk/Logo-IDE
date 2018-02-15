# Cell society API

# Simulation
## Internal
	The internal simulation API consists of methods that allow the user to create new extensions to the existing simulations. Included in this are methods in the Cell class such as getMyNeighbors and setMyNeighbors, where new internal implementations can be created. Within the grid class, an important internal method for the simulation API is updateCells, because only users dedicated to the simulation care about the implementation of how the grid updates the cells.

## External
	The external simulation API  consists of methods like RandomizedInitConfig, because while the implementation of this method has to do with the simulation, how it works has to do with the configuration. In this way, it helps users who may not be involved with the simulation.

# Configuration
## Internal
The internal configuration API consists of methods which allow the user to extend and specify the abilities of the XML parser and initialization. It allows for the configuration data to be accessed in more efficient and malleable means.  Included in this API are methods such as public XMLSaver() and public void save().
## External
	There is no external API for the configuration. There is only one XML related class, callled XMLsaver, which appears to save the current state of the grid. Inside is one method called save, which doesn’t return anything. There doesn’t appear to be any method for communication between the XML class and the classes that utilize the information from the files. 

# Visualization
## Internal
The Internal visualization API consists of features that can be extended-  such as constructors for new buttons, panels and windows. Some example methods include getRestart(), getPlaying() and getSave(), also also goHome() and getWindow(), which seem to navigate to different screens. The internal visualization API can be extended to create different types of buttons and screens according to the predefined format. 

## External 
The external visualization API consists of code that allows user inputs chosen in the frontend to be communicated with the backend, and backend simulation information to be displayed within the frontend. Example methods that fall under this API include the neighborSelectionReceivedProperty(), getToroiodalSelection() and getNeighborSelection(), as they allow the frontend to connect to simulation components.
