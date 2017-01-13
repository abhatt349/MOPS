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
	int retInt = 0;
	for (int i = 0; i < cards.length()-1; i += 2) {
	    retInt += Deck.value(cards.substring(i, i+1));
	}
	return retInt;
    }
	
}

