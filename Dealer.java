import cs1.Keyboard;
import java.util.ArrayList;
public class Dealer {
    public ArrayList<String> cards;
    public Dealer() {
	cards = new ArrayList<String>(2);
    }
    public void add(String card) {
	cards.add(card);
    }
    public void print() {
	System.out.println("dealer: "+cards.toString());
    }
    public int sum() {
	int retInt = 0;
	for (int i = 0; i < cards.size(); i++ ) {
	    retInt += Deck.value(cards.get(i));
	}
	return retInt;
    }
    public void reset() {
	cards.clear();
    }
}

