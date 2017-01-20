public class Deck {
    public static Card[] cards;
    public static int cut;
    public static int marker;
    public Deck() {
	cards = new Card[52];
	//String[] vals = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
	//String[] suits = {"d","c","h","s"};
	//int i = 0;
	/*for (String val: vals) {
	    for (String suit: suits) {
		cards[i] = val+suit;
		i += 1;
	    }
        }*/
	for(int i = 0; i < 52; i++) {
	    cards[i] = new Card(i);
	}
	cut = 0;
	marker = 0;
    }
    public Deck(int num) {
	this();
    }
    public static void shuffle() {
	int n = cards.length;
        for (int i = 0; i < n; i++) {
            int s = i + (int) (Math.random()*(n-i));
            Card temp = cards[i];
            cards[i] = cards[s];
            cards[s] = temp;
        }
	cut = cards.length*3/4;
    }
    public static String deal() {
	//make two stacks, dealt and used. reshuffle point only recombines undealt and used.
	
	marker++;
	return cards[marker-1];
    }
    public static int value(Card card) {
	/*if (card.substring(0,1).equals("J") || card.substring(0,1).equals("Q") || card.substring(0,1).equals("K")) {
	    return 10;
	}
	if (card.substring(0,1).equals("A")) {
		return 11;
	}
	return Integer.parseInt(card.substring(0,card.length()-1));*/
	return Card.value;
    }
    public static  void diag() {
	
	for (String i : cards) {
	    System.out.print(i + "  ");
	}
    }
}
