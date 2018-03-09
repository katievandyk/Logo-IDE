## Refactoring Discussion

#### Duplication Refactoring
In the commands, there were some code duplications, mostly because a lot of commands are somewhat similar. For example, If and IfElse perform different actions, but have a block of around 6 lines of code that is the same. To refactor this situation and other issues with commands, we created new abstract classes that would contain callable methods by the subclasses.

#### Checklist Refactoring
There are a bunch of magic numbers in our project as of now. While some of them need to be changed, we think that the magic numbers in the Command objects don't need to be refactored, since they are used only once in a validation method, and since one would have to go through every single command and create a final variable.
Another checklist item of note is the single purpose rule. Our project has a bunch of long methods to solve complicated tasks, but we should be seperating these into shorter, more modular, and easier to read methods.
Finally, the program tells us to make constructors on some of our methods and to replace instance variables with local variables. We agree with this, since local variables keep infrmation within the specific class and operation.

#### General Refactoring
In the code smells section, most of the errors are minor, and come from the fact that we replace an existing list with a new list within each command. The tool tells us to create a new variable instead of replacing the current one. While this may have been a good idea to fix, it's so minor that it's not worth our time to go through each command and insert new code.
Finally, we encounter a bunch of java note errors because while we catch and handle errors, we don't log them. Also, the tool wants us to put comments in methods that have no code inside of them, which will be done.