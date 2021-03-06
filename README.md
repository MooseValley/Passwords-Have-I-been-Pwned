# Passwords - Have I been Pwned

* Source:  Passwords__Have_I_Been_Pwned.java
* Author:  Mike "Moose" OMalley
* Email:   Moose UNDERSCORE Software AT yahoo DOT com DOT au
* WEB:     https://rebrand.ly/MoosesSoftware
* Last Updated:    24-Jan-2021
* License: MIT License.
           Free for any person, company, or business to use.
           This comment block at the top of this file *must* remain intact.

* Description:  Check if any passwords are on the Have I Been Pwned (HIBP) web site.
* Requirements: Java Development Kit (JDK) installed on your machine.
                http://www.oracle.com/technetwork/java/javase/downloads/index.html


## Step 1: Download
* Make sure the **Java Development Kit (JDK)** installed on your machine,
http://www.oracle.com/technetwork/java/javase/downloads/index.html
* Download the "Passwords - Have I been Pwned" source code. i.e. this Github Repo.
* Click the green "**Code**" button above and select "**Download ZIP**",
and unzip the download to a new folder.  e.g. c:\Passwords\


## Step 2: Setup
* **Recommended:** do a test Compile and Run with the original source code first,
just to make sure Java is setup correctly on your machine.
* Edit: Passwords__Have_I_Been_Pwned.java
* Scroll down and enter your passwords in the PASSWORDS_ARRAY.
* Examples included below.  Enter your passwords in double quotes and comma separated.
* Save the changes.


## Step 3: Compile and Run

* Open a command prompt and navigate to the directory where the "Passwords__Have_I_Been_Pwned.java"
source code is.

* Compile and then run Java source code.

To compile, enter this command:
```
   javac Passwords__Have_I_Been_Pwned.java
```

To run, enter this command:
```
   java Passwords__Have_I_Been_Pwned
```

Or, for Windows users, I have included a .BAT command files:
```
   Passwords__Have_I_Been_Pwned__Compile.bat
   Passwords__Have_I_Been_Pwned__Run_JAR_and_Show_Console_Window.bat
```
which contains the compile and run commands.

Just double click on each BAT file to compile and run the java program.

NOTE: you may need to edit the directory paths in the .BAT file
to suit your Java installation.


## Sample Output:

```
---------------------------------------
Have I Been Pwned Password Check:
---------------------------------------

 ID  Pwned? Password
  1.   Y    abc123
  2.   Y    P@ssw0rd
  3.   Y    password
  4.   Y    password1
  5.   Y    p@ssword
  6.   N    abcd1234.zxc

*** WARNING: YOU HAVE BEEN PWNED 5 times !!! :(
```

Any questions, comments, feedback, please let me know.  :)

 Mike "Moose" OMalley
____________________________________________________
Moose's Software Valley - Established July, 1996.
* Email:  Moose UNDERSCORE Software AT yahoo DOT com DOT au
* WEB: https://rebrand.ly/MoosesSoftware
____________________________________________________

