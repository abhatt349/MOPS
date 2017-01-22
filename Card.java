public class Card {
    public int value;
    public String name;
    public static String[] values = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
    public static String[] suits = {"c","d","h","s"};
    public Card(int v) {
	value = v%13;
	if ((v%13)<= 7) {
	    value = Integer.parseInt(values[v%13]);
	}
	else if ((v%13) == 12) {
	    value = 11;
	}
	else {
	    value = 10;
	}
	name = values[v%13] + suits[v/13];
    }
    public String toString() {
	return name;
    }
    
}
