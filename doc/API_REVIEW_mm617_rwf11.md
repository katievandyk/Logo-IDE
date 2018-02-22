## API Review
##### Martin Muenster (mm617) & Ryan Fu (rwf)

### Part 1
1. Ryan's design is plannin to make the design flexible by creating a command superclass, so adding classes would be easy. My design is flexible for the same reasons, although I don't know whether I'm going to use an interface or an abstract class for the Commands.

2. Ryan's classes have their own information about the commands, not accessable to other classes. My command classes will encapsulate their behavior inside an execute method, so that external classes don't have access to the specific implementation of the command. This makes the API hard to misuse.

3. Ryan's possible exceptions include out of bounds exceptions, for exceptionally high values and invalid turle object. My exceptions include invalid argument type, invalid number of arguments, & invalid instruction type.

4. I think my API design is good because it is very simple and understandable, since the only important public method is the execute method. Ryan's API is good because it is flexible enough for additional commands to be added, and it is simple and understandable since functionality is split by class.


### Part 2
1. As of now, we're using a factory method design pattern in the CommandBuider class to build command objects. We're also using the command pattern for use with our commands. I'm alos planning to implement the iterator pattern in some of my commands. Ryan is using the factory method to create commands as well, and is thinking about implementing the command pattern.

2. I'm most excited to work on implementing the execute method in the commands and figuring out how to create a list of instructions that can be communicated to the turtle. Ryan is most excited about working on his command builder.

3. I'm most worried about inter-command communication; for example, whether a command has to look inside instructions to determine if one of its child commands is a number. I'm also worried about how the command will have access to the variable and command dictionaries. Ryan is most worried about the functionalty for each command class, sice it will be difficult to have implementation with the turtle without having the command encapsulated.

4. Use Cases
* the command builder creates a nested command of the type 'fd 50', and the forward command must execute and get the value of the number command.
* a nested command object exists with multiple parameters at the same level. How do you preserve the order of the resulting instrucions?
* a sum command is executed and must return a number, not an actual instruction.
* a command is executed that does not exist.
* a variable command must be executed and return the value of that variable to its parent command.