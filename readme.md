

First and foremost, designing the models expecting that they are database compatible.

Assumptions:
1. The timestamp is given WITHOUT milliseconds.
2. There are only TWO types of vehicles. More vehicle types require direct edit to the code. Due to the first "hardcoded" input line, it is impossible to modularize this part

Compilation:
This project was run on JDK version 11.0.1.
Any imports used in this project was from this JDK version.
Simply run the "Main" file.
Change the input.txt file in order to change the starting cases. input.txt file should be one level above src root.