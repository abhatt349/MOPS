# MOPS
##Aryan Bhatt + Haiyao Liu APCS1 Final Project: Blackjack
###Launch Instructions:
The driver file that will run our project is called *Woo.java*.
In order to run it, you must first compile with the command `javac Woo.java` from our home directory, MOPS.
Then, once it is compiled, run the file with `java Woo` to start the actual game.
###Description
Our project is a simple blackjack game that will allow users to play a typical shoe-game of blackjack from the command line (singleplayer) either 1 on 1 with the dealer or with a chosen number of AI’s. For example, the player can choose to play alongside 3 other AI players, or by themselves.
The game will allow for all of the typical blackjack commands/actions (bet, hit, stand, split, double down, insurance, surrender).
Cards will be represented by their number (name for face cards) and a letter {c,d,h,s} to represent suits (clubs, diamonds, hearts, spades, respectively).
Player can request for the current state of the game at any time (a summary will be printed out).
New players will start with $100.
The player will be able to set how many decks to be used (1-6), how many AI’s will play in the game, and how the dealer plays (stand on all 17 or soft 17).
AI’s will have difficulty levels that change how well they can count cards (how many played cards the AI can remember).
