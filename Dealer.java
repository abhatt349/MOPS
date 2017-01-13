import Keyboard.java;

public class Dealer {
    public String cards;
    public void Dealer () {
	cards = "";
    }
    public void add(String card) {
	cards += card;
	cards += " ";
    }
    public void print() {
	System.out.println("Dealer money: "+money+"\nCards: "+cards);
    }
