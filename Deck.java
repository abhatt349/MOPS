public class Deck {
    public String[] cards;
    public int cut;
    public int marker;

    public void Deck(int num) {
	cards = new String[num*52];
	int i = 0;
	for (String val: {"A","2","3","4","5","6","7","8","9","10","J","Q","K"}) {
	    for (String suit: {"d","c","h","s"}) {
		cards[i] = val+suit;
		i += 1;
	    }
	}
	cut = 0;
	marker = 0;
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
}
