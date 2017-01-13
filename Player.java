import Keyboard.java;

public class Player {
    public String cards;
    public int money;
    public void Player(int val) {
	cards = "";
	money = val;
    }
    public String input(String prompt) {
	System.out.println(prompt);
	return Keyboard.readWord();
    }
    public void add(String card) {
	cards += card;
	cards += " ";
    }
    public void print() {
	System.out.println("Player money: "+money+"\nCards: "+cards);
    }
