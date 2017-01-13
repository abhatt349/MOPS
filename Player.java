import cs1.Keyboard;

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
    public int sum() {
	int retInt = 0;
	for (int i = 0; i < cards.length()-1; i += 2) {
	    retInt += Deck.value(cards.substring(i, i+1));
	}
	return retInt;
    }
}
