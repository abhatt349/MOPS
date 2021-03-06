import java.util.ArrayList;

public abstract class User {
    public String name;
    public ArrayList<Card> cards;
    public User() {
	cards = new ArrayList<Card>(2);
    }
    public void add(Card newCard) {
	cards.add(newCard);
    }
    public int sum() {
	int retInt = 0;
	for (int i = 0; i < cards.size(); i++) {
	    retInt += cards.get(i).value;
	}
	return retInt;
    }
    public void reset() {
	cards.clear();
    }
}
