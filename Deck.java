public class Deck {
    public String[] cards;
    public int cut;
    public int marker;
    public void Deck() {
	cards = new String[52];
	String[] vals = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
	String[] suits = {"d","c","h","s"};
	for (String val: vals) {
	    for (String suit: suits) {
		cards[i] = val+suit;
		i += 1;
	    }
	}
	cut = 0;
	marker = 0;
    }
    public void Deck(int num) {
	this();
	cards = new String[num*52];
    }
    public void shuffle() {
	int n = cards.length;
        for (int i = 0; i < n; i++) {
            int s = i + (int) (Math.random()*(n-i));
            String temp = cards[i];
            cards[i] = cards[s];
            cards[s] = temp;
        }
	cut = cards.length*3/4;
    }
    public String deal() {
	if (cut == marker) {
	    shuffle();
	    return deal();
	}
	marker +=1;
	return cards[marker];
    }
    public static int value(String card) {
	if (card.substring(0,1).equals("J") || card.substring(0,1).equals("Q") || card.substring(0,1).equals("K")) {
	    return 10;
	}
	if (card.substring(0,1).equals("A")) {
		return 11;
	}
	return Integer.parseInt(card.substring(0,1));
    }
}
