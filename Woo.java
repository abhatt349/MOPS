import cs1.Keyboard;
import java.util.ArrayList;
public class Woo {
    public static void menu() { //first menu encountered when they enter the game
	System.out.println("Blackjack Menu");
	System.out.println("n:new game");  //start a game
	System.out.println("h:help");      //instructions
	System.out.println("q:quit");      //exit
	System.out.print("player: ");      
    }
    
    public static String response() {         //asks for input
	String input = Keyboard.readString();
	if(input.equals("n")) {               //if they want to start a game
	    return ""; //merged ai menu       //exits response()
	}
	else if (input.equals("h")) {         //if they ask for instructions
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
	    menu();                   //reprints menu options
	    return response();        //loops back around to menu
	}
	else if (input.equals("q")) { //exits
	    System.out.println("driver: quitting.");
	    return "q";
	}
	else {                        //accounts for user incompetence
	    System.out.println("driver: invalid response. try again with a menu option");
	    System.out.println("-----------");
	    menu();                   //reprints menu options
	    return response();        //loops back around to menu
	}
	//return "???";
    }
    public static int ai() {          //asks user how many AI's they want to play with
	System.out.println("driver: Enter a number of AI's to add (between 0 and 5 inclusive)");
	System.out.print("player: ");
	int input = Keyboard.readInt();
	if(input < 0 || input > 5) {  //accounts for user incompetence
	    System.out.println("driver: invalid number. please input an integer between 1 and 5 inclusive");
	    return ai();              //loops back around, asking for input again
	}
	return input;                 //returns user-inputted number
    }
    public static void main(String[] args) {
	menu();                                  //displays menu
	if(response().equals("q")) return;       //exits if user wants to quit
	int n = ai();
	ArrayList<AI> u = new ArrayList<AI>(n);  //creates ArrayList of AI's
	for(int i = 0; i < n; i++) {             //fills ArrayList
	    AI aa = new AI(i);
	    u.add(aa);
	}
	Blackjack b = new Blackjack(u);
	b.game();                                //calls game runner
    }
}
	
