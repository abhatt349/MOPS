import cs1.Keyboard;
import java.util.ArrayList;
public class Driver {
    public static void menu() {
	System.out.println("Blackjack Menu");
	System.out.println("n:new game");
	System.out.println("h:help");
	System.out.println("q:quit");
	System.out.print("player: ");
    }
    
    public static String response() {
	String input = Keyboard.readString();
	if(input.equals("n")) {
	    return ""; //merged ai menu
	}
	else if (input.equals("h")) {
	    System.out.println("The goal of blackjack is to beat the dealer's hand without going over 21.");
	    System.out.println("Face cards are worth 10. Aces are worth 1 or 11, whichever makes a better hand.");
	    System.out.println("Each player starts with two cards.");
	    System.out.println("To 'hit' is to ask for another card.");
	    System.out.println("To 'stand' is to hold your total and end your turn.");
	    System.out.println("If you go over 21 you bust, and the dealer wins regardless of the dealer's hand.");
	    System.out.println("If you are dealt 21 from the start (Ace & 10), you got a blackjack.");
	    System.out.println("Blackjack means you win 1.5 the amount of your bet.");
	    System.out.println("Dealer will hit until his/her cards total 17 or higher.");
	    System.out.print("------------------\nuser: ");
	    menu();
	    return response();
	}
	else if (input.equals("q")) {
	    System.out.println("driver: quitting.");
	    return "q";
	}
	else {
	    System.out.println("driver: invalid response. try again with a menu option");
	    System.out.println("-----------");
	    menu();
	    return response();
	}
	//return "???";
    }
    public static int ai() {
	System.out.println("driver: Enter a number of AI's to add (between 0 and 5 inclusive)");
	System.out.print("player: ");
	int input = Keyboard.readInt();
	if(input < 0 || input > 5) {
	    System.out.println("driver: invalid number. please input an integer between 1 and 5 inclusive");
	    return ai();
	}
	return input;
    }
    public static void main(String[] args) {
	menu();
	if(response().equals("q")) return;
	int n = ai();
	ArrayList<AI> u = new ArrayList<AI>(n);
	for(int i = 0; i < n; i++) {
	    AI aa = new AI(i);
	    u.add(aa);
	}
	Blackjack b = new Blackjack(u);
	b.game();
    }
}
	
