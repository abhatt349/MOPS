import cs1.Keyboard;
import java.util.ArrayList;
public class Player {
    public ArrayList<String> cards;
    public int money;
    public Player(int val) {
	cards = new ArrayList<String>();
	money = val;
    }
    public String input(String prompt) {
	System.out.println(prompt);
	return Keyboard.readWord();
    }
    public void add(String card) {
	cards.add(card);
    }
    public void print() {
	System.out.println("Player money: "+money+"\nCards: "+cards);
    }
    public int sum() {
	int retInt = 0;
	for (int i = 0; i < cards.size(); i++) {
	    retInt += Deck.value(cards.get(i));
	}
	return retInt;
    }
    public void reset() {
	cards.clear();
    }
}
