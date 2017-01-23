import cs1.keyboard;
public class Driver {
    public static String menu() {
	System.out.println("Blackjack Menu");
	System.out.println("n:new game");
	System.out.println("h:help");
	System.out.println("q:quit");
	System.out.print("user: ");
    }
    
    public static void response() {
	String input = Keyboard.readString();
	if(input.equals("n")) {
	    return; //merged ai menu
	}
	else if (input.equals("h")) {
	    System.out.println("help message here");
	    System.out.println("------------------\nuser: ");
	    response();
	}
	else if (input.equals("q")) {
	    System.out.println("driver: quitting.");
	    System.exit();
	}
	else {
	    System.out.println("driver: invalid response. try again with a menu option");
	    System.out.println("-----------");
	    menu();
	    response();
	}
    }
    public static int ai() {
	System.out.println("driver: how many ai's would you like to add? input an integer between 1 and 5 inclusive");
	System.out.print("user: ");
	String input = Keyboard.readString();
	if(input == Integer.MIN_VALUE) {
	    System.out.println("driver: invalid number. please input and integer between 1 and 5 inclusive");
	    ai();
	} else {
	    return input;
	}
    }
    public static void main(String[] args) {
	response();
	int n = ai();
	AI[] u = new AI[n];
    }
}
	