import java.util.ArrayList;
import cs1.Keyboard;
public class Blackjack {
    public int bet = 0;
    public Deck _d = new Deck(1);        //we decided that in order to easily facilitate card counting, we'd only let the user play with one deck
    public Dealer d = new Dealer(); 
    public Player p = new Player(100);
    public int b = 0;
    public ArrayList<AI> u;
    
    public Blackjack (ArrayList<AI> ais) {
	u = ais;                                //takes the ArrayList of AI's initialized in class Driver
    }

    public void game() {                        //the actual game itself
	System.out.println( "===============================================" );
	System.out.println( "BlackJack" );
	System.out.println( "-----------------------------------------------" );
	System.out.println( "Dealer stands on all 17");
	System.out.println( "Blackjacks pay 3:2");
	System.out.println( "AI's bet 5 by default");
	System.out.println( "Cards are represented by face value + suit" );
	System.out.println( "             e.g. Ah for the ace of hearts" );
	//System.out.println( "X means a card is facedown" );           this became unnecessary because we decided to leave all cards face up
	System.out.println( "Only moves implemented are hit and stand" );   //we ran out of time, so we couldn't implement the other options
	//System.out.println( "type help for help and quit to quit at any time" );   we decided to create a menu for this at the beginning, to keep things more organized
	boolean moneyLeft = true;
	String nextround = p.input("blackjack: play a round? type y or n:\nplayer: "); //boolean short circuiting to the rescue! 
	while(moneyLeft&&(nextround.equals("y"))) {
	    //int bet = Integer.parseInt(p.input("(doesnt do anything) bet:")); we decided to add betting into a different place
	    System.out.println("blackjack: round start");
	    round();                    //calls round(), which plays one round of blackjack
	    if (p.money < 5) {          //makes sure you have enough money to play
		moneyLeft = false;
	    }
	    p.reset();
	    for(int i = 0; i < u.size(); i++) {
		//System.out.println(u);
		//System.out.println(u.get(i));    diagnostic print statements
		//System.out.println(i);
		u.get(i).reset();
	    }
	    d.reset();
	    nextround = p.input("blackjack: play a round? type y or n:\nplayer: ");
	}
	if (!moneyLeft) {               //in the case that you don't have enought money, the game end
	    System.out.println("blackjack: You ran out of money! Thanks for playing!");
	}
     }

    public String round() {	    
	//_d.shuffle();            we later decided to make Deck automatically shuffle itself         
	System.out.println("blackjack: You have "+p.money+" dollars");
	System.out.print("blackjack: Enter a bet between 5 and "+p.money+"\nplayer: ");
        bet = Keyboard.readInt();
	while(bet < 0 || bet > p.money) {   //checks to make sure that the bet is a valid amount
	    System.out.println("blackjack: invalid bet amount. try again");
	    bet = Keyboard.readInt();       //asks for input again
	}
	p.money -= bet;                     //puts bet into the pool
	
	for(int i = 0; i < 2; i++) {             //deals 2 cards to everyone
	    d.add(_d.deal());
	    for(int a = 0; a < u.size(); a++) {
		u.get(a).add(_d.deal());
	    }
	    p.add(_d.deal());
	}
	System.out.println("-----------------------------------------------");
	p.print();
	for(AI i : u) {       //prints the state of everybody on the table
	    i.print();
	}
	d.print();
	String c = check();            //checks to see if round should end
	//if(!c.equals("c")) return c; //checks to see if round should end
	String p = player();                   //player takes their turn
	//if(!p.equals("c")) return p; //checks to see if round should end
	for(int i = 0; i < u.size(); i++) {    //AI's take their turns
	    ai(i);
	}
	String dd = dealer();
	//if(!dd.equals("c")) return dd;
	return end(c,p,dd);           //calls end() to resolve the round
    }

    public String player() {           //player's turn
	System.out.println("\nblackjack: turn start (type hit or stand)");   //asks what player wants to do
	String in = p.input("player:");
	while(in.equals("hit")) {
	    p.add(_d.deal());
	    p.print();                //reprints state of player
	    String c = check();       //checks if round should end
	    if(!c.equals("c")) return c;
	    in = p.input("player:");
	}
	if(in.equals("stand")) {
	    p.print();
	    return "c";
	}
	else {
	    System.out.println("blackjack: that is not a move option. try again");
	    return player();
	}
    }

    public String dealer() {       //dealer's turn
	System.out.println("");
	while(d.sum() < 17) {      //hit on 16 or below
	    d.add(_d.deal());
	    System.out.println("dealer: hit");
	    d.print();
	    String c = check();    //checks if round should end
	    if(!c.equals("c")) return c;
	}
	System.out.println("dealer: stand");  //stand on all 17 or above
	d.print();                //prints current state
	return "c";
    }
    
    public void ai(int n) {        //AI's turn
	System.out.println("\nai "+n+"'s turn");
	//System.out.println(n);
	//System.out.println(u.get(n));    diagnostic print statements
	//System.out.println(d.sum());
	//System.out.println(_d.undealt());
	if(d.sum() == 21 && u.get(n).sum() < 21) {
	    System.out.println("blackjack: ai "+n+" loses");    //checks for dealer blackjack
	    return;
	}
	int hits = u.get(n).move(_d.undealt(),d.sum());       //checks hit or stand
	for(int i = 0; i < hits; i++) {
	    u.get(n).add(_d.deal());
	    System.out.println("ai " + n + ": hit");
	    u.get(n).print();
	    if(u.get(n).sum() == 21) {
		System.out.println("ai " + n + ": stand");
		u.get(n).print();
		return;
	    }	
	    else if(u.get(n).sum() > 21) {
		u.get(n).money = u.get(n).money - 5;
		System.out.println("ai " + n + ": bust");
		return;
	    }
	}
	System.out.println("ai " + n + ": stand");
	u.get(n).print();
	return;
    }

    public String check() {            //method to check whether the round should immediately end
	if (p.sum() == 21) {           //the case of player blackjack
	    System.out.println("blackjack: blackjack! you win");
	    p.money += bet*5/2;
	    bet = 0;
	    System.out.println("blackjack: you now have "+p.money+" dollars");
	    return "bp";
	}
	else if (d.sum() == 21) {      //the case of dealer blackjack
	    System.out.println("blackjack: dealer has blackjack. you lose.");
	    bet = 0;
	    System.out.println("blackjack: you now have "+p.money+" dollars");
	    return "bd";
	}
	else if (p.sum() > 21) {       //the case of player bust
	    System.out.println("blackjack: bust. you lose.");
	    bet = 0;
	    System.out.println("blackjack: you now have "+p.money+" dollars");
	    return "pb";
	}
	else if (d.sum() > 21) {       //the case of dealer bust
	    System.out.println("blackjack: dealer bust. you win");
	    p.money += bet*2;
	    bet = 0;
	    System.out.println("blackjack: you now have "+p.money+" dollars");
	    return "db";
	}
	return "c";
    }

    public String end(String fc, String pl, String dl) {     //method to resolve round
	if(fc.equals("bd")) return "";
	compare();
	if(pl.equals("pb")) return "";
	if(p.sum() > d.sum()) {              //case of player win
	    System.out.println("blackjack: dealer has less. you win.");
	    p.money += bet*2;
	    bet = 0;
	    System.out.println("blackjack: you now have "+p.money+" dollars");
	    return "p";
	}
	else if(p.sum() == d.sum()) {        //case of tie
	    System.out.println("blackjack: tie");
	    p.money += bet;
	    bet = 0;
	    System.out.println("blackjack: you now have "+p.money+" dollars");
	    return "t";
	}
	else if(p.sum() < d.sum()) {        //case of dealer win
	    System.out.println("blackjack: dealer has more. you lose");
	    bet = 0;
	    System.out.println("blackjack: you now have "+p.money+" dollars");
	    return "d";
	}
	
	return "????";
    }
    
    public void compare() {             //checks whether AI's won or lost 
	for(int i = 0; i < u.size(); i++) {      //case of AI win
	    if(u.get(i).sum() > d.sum() && u.get(i).sum() < 22) {
		System.out.println("blackjack: ai " + i + " wins.");
		u.get(i).money += 10;
	    }
	    else if(u.get(i).sum() == d.sum()) {    //case of tie
		System.out.println("blackjack: ai " + i + " tied with the dealer.");
		u.get(i).money += 5;
	    }
	    else if(u.get(i).sum() < d.sum() && d.sum() < 22) {     //case of dealer win
		System.out.println("blackjack: ai " + i + " loses.");
	    }
	    System.out.println("blackjack: ai " + i + " has " + u.get(i).money + " dollars.");
	}
	return;
    }

    public static void main(String[] args) {
	//none
    }
}
