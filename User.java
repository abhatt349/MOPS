public abstract class User {
    public ArrayList<Card> cards;
    public void add(Card newCard) {
	cards.add(newCard);
    }
    public abstract void print();
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
