import cs1.Keyboard;
public class Driver {
    public static void menu() {
	System.out.println("Blackjack Menu");
	System.out.println("n:new game");
	System.out.println("h:help");
	System.out.println("q:quit");
	System.out.print("user: ");
    }
    
    public static String response() {
	String input = Keyboard.readString();
	if(input.equals("n")) {
	    return ""; //merged ai menu
	}
	else if (input.equals("h")) {
	    System.out.println("help message here");
	    System.out.print("------------------\nuser: ");
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
	System.out.println("driver: how many ai's would you like to add? input an integer between 1 and 5 inclusive");
	System.out.print("user: ");
	int input = Keyboard.readInt();
	if(input == Integer.MIN_VALUE) {
	    System.out.println("driver: invalid number. please input and integer between 1 and 5 inclusive");
	    ai();
	} else {
	    return input;
	}
	return 0;
    }
    public static void main(String[] args) {
	menu();
	if(response().equals("q")) return;
	int n = ai();
	ArrayList<AI> u = new ArrayList<AI>(n);
	Blackjack b = new Blackjack(u);
	b.game();
    }
}
	
