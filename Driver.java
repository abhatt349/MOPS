
import cs1.keyboard;
public class Driver {
    public static String menu() {
	System.out.println("Blackjack Menu");
	System.out.println("n:new game");
	System.out.println("h:help");
	System.out.println("q:quit");
    }
    
    public static void response() {
	String input = Keyboard.readString();
	if(input.equals("n")) {
	    //send to ai menu
	}
	else if (input.equals("h")) {
	    System.out.println("paste your stuff here aryan");
	}
	else if (input.equals("q")) {
	    System.out.println("quitting.");
	    return;
	}
	else {
	    System.out.println("That is not a valid response. Please pick one of the options from the menu.");
	    System.out.println("-----------");
	    menu();
	    response();
	}
    }
    public static void main(String[] args) {
	response();
    }
}
	