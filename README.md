## Name
ChessMeister

## Description
This project is a complex and meaningful implementation of chess. This was created through the use of Java and JavaFX. It features both a working command line version, as well as a GUI implementation, which is the suggested way to play.

## Installation
Ensure that you have both Java and JavaFX installed on your machine. We used Java Azul Zulu and is what we suggest.

## Usage
In order to support use for this program:

First, open the folder "ChessMiester" as the project and then using IntelliJ creat a new configuration:

For command line experience:
Set the build and run to Java Azul and target file as ./controller/Driver.java.
Then, you're able to hit the run button and play chess.
        
For GUI:
Set the build and run to JavaFX and target the file as ./gui/controller/Main.java.
Then, you're able to run the program and play chess.


## How to Play:
For command line:
    
In order to play this game, the user will be prompted to enter in a move. A user will enter
the file and rank of the piece they want to move, following by the file and rank of the
destination. The user will then be told if the move is valid or not, and if it is valid, the
board is updated and the next player's turn begins. This is repeated until the game meets an
ending condition, such as draw.


For GUI:

In order to play this game, the user will be able to click on a piece, and then click on a
destination, or drag the piece to their desired location. Users can also right-click to
highlight valid moves for a piece. If the move is valid, the board will be updated and the
next player's turn will begin. This is repeated until the game meets an ending condition,
such as checkmate.

# Saving:
After the user ends the program, the states of the game are saved in the file user gave. These
are all strings representing each piece on the board, and the moves that occurred. This is
done in order to make sure that the game can be loaded and played again.

Along with this, there are many navigable features in the menu, including a functional saving
and loading of the game, tutorials in order to learn basic chess, and many more. Feel free to
explore the menu and see what you can find!

## Note:
This program is not perfect, and there are some bugs that we were not able to fix.
Simply put, we were at a disadvantage during this sprint and eventually hit a point
where we all needed to focus on finals, unable to do everything we wanted to.

## Authors and acknowledgment
Authors:
    Brady Smith
    Zachary Eanes
    Kaushal Patel
    Colton Brooks