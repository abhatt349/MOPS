import cs1.Keyboard;
import java.util.ArrayList;
public class Player {
    public ArrayList<Card> cards;
    public int money;
    public Player(int val) {
	cards = new ArrayList<Card>(2);
	money = val;
    }
    public String input(String prompt) {
	System.out.print(prompt + " ");
	return Keyboard.readWord();
    }
    public void add(Card card) {
	cards.add(card);
    }
    public void print() {
	System.out.println("player: "+cards);
    }
    public int sum() {
	int retInt = 0;
	for (int i = 0; i < cards.size(); i++) {
	    retInt += cards.get(i).value;
	}
	boolean ace = false;
	for (int i = 0;i<cards.size();i++) {
	    if (cards.get(i).value == 11) {
		ace = true;
	    }
	}
	if (retInt>21 && ace) {
	    retInt -= 10;
	}
	return retInt;
    }
    public void reset() {
	cards.clear();
    }
    public int bet() {
	return 0;
    }
}
