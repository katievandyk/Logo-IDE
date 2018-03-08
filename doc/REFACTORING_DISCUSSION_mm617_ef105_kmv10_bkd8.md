## Refactoring Discussion

#### Duplication Refactoring
In the commands, there were some code duplications, mostly because a lot of commands are similar, but slightly different. For example, If and IfElse perform different actions, but have a block of around 6 lines of code that is the same. To refactor this situation and other issues with commands, we created new abstract classes that would contain callable methods by the subclasses.

#### Checklist Refactoring


#### General Refactoring