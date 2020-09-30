# User Guide
Duke is a task manager that runs on the Command Line Interface(CLI).

* [Quick Start]()
* [Features]() 
    * 1.[help]()
    * 2.[todo]()
    * 3.[deadline]()
    * 4.[event]()
    * 5.[list]()
    * 6.[done]()
    * 7.[delete]()
    * 8.[bye]()
* [Command Summary]()
##Quick Start
1. Ensure that you have Java 11 installed.
2. Download duke.jar and move it into a new folder.
3. Run command prompt and navigate to the new folder.
4. Run duke.jar using the following command: 
`java -jar duke.jar`
5. Type
`help` to view a list of available commands.
## Features 
### 1.`help` - Listing all available commands
Lists the available commands and usage

Example of usage: 

`help`
### 2. `todo` `deadline` `event` - Adding a task:  
Adds a task to be completed to Duke. Task type
supported includes `todo`, `deadline` and `event`.


Format:

`todo TASK_DESCRIPTION`

`deadline DEADLINE_DESCRIPTION /by WHEN`

`event EVENT_DESCRIPTION /at WHEN`

Example of usage: 

`todo borrow book` 

`deadline return book /by Sunday`

`event project meeting /at Mon 2-4pm`
### 3. `list` - Listing all tasks:
Lists all tasks currently stored in Duke.

Example of usage: 

`list`
### 4. `done` - Marking a task as done:
Completed tasks can be marked as done with a tick
Tasks will be marked with a cross by default.

Format:

`done INDEX`

Example of usage: 

`done 1`
### 5. `delete` - Deleting tasks
Deletes a user-specified task.

Format:

`delete INDEX`

Example of usage: 

`delete 1`
### 6. `find` - Finding tasks
Finds all tasks that contains a user-specified keyword.

Format:

`find KEYWORD`

Example of usage: 

`find book`

### 7. `bye` - Exiting the program
Ends the program when command `bye` is given.

Example of usage: 
`bye` 


##Command Summary
Command|Format|Example
-------|------|-------
help|`help`|`help`
todo|`todo TASK_DESCRIPTION`|`todo borrow book`
deadline|`deadline DEADLINE_DESCRIPTION /by WHEN`|`deadline return book /by Sunday`
event|`event EVENT_DESCRIPTION /at WHEN`|`event project meeting /at Mon 2-4pm`
list|`list`|`list`
done|`done INDEX`|`done 1`
delete|`delete INDEX`|`delete 1`
find|`find KEYWORD`|`find book`
bye|`bye` |`bye` 