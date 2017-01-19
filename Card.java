public class Card {
    public int value;
    public String name;
    public static String[] values {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
    public static String[] suits = {"c","d","h","s"};
    public Card(v) {
	value = v;
	name = values[v%14] + suits[v/14];
    }
}