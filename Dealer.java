import cs1.Keyboard;
import Deck;
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
    public int sum() {
    }
	
}

