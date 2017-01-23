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
	while(moneyLeft&&(p.input("blackjack: play a round? type y or n:\nplayer: ").equals("y"))) { //boolean short circuiting to the rescue!
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
	}
	if (!moneyLeft) {               //in the case that you don't have enought money, the game end
	    System.out.println("blackjack: You ran out of money! Thanks for playing!");
	}
     }

    public String round() {	    
	//_d.shuffle();             
	System.out.println("blackjack: You have "+p.money+" dollars");
	System.out.println("blackjack: Enter a bet between 5 and "+p.money+"\nplayer: ");
        bet = Keyboard.readInt();
	while(bet < 0 || bet > p.money) {
	    System.out.println("blackjack: invalid bet amount. try again");
	    bet = Keyboard.readInt();
	}
	p.money -= bet;
	
	for(int i = 0; i < 2; i++) {
	    d.add(_d.deal());
	    for(int a = 0; a < u.size(); a++) {
		u.get(a).add(_d.deal());
	    }
	    p.add(_d.deal());
	}
	System.out.println("-----------------------------------------------");
	p.print();
	for(AI i : u) {
	    i.print();
	}
	d.print();
	String c = check();
	//if(!c.equals("c")) return c;
	String p = player();
	//if(!p.equals("c")) return p;
	for(int i = 0; i < u.size(); i++) {
	    ai(i);
	}
	String dd = dealer();
	//if(!dd.equals("c")) return dd;
	return end(c,p,dd);
    }

    public String player() {
	System.out.println("\nblackjack: turn start (type hit or stand)");
	while(!p.input("player:").equals("stand")) {
	    p.add(_d.deal());
	    p.print();
	    String c = check();
	    if(!c.equals("c")) return c;
	}
	p.print();
	return "c";
    }

    public String dealer() {
	System.out.println("");
	while(d.sum() < 17) {
	    d.add(_d.deal());
	    System.out.println("dealer: hit");
	    d.print();
	    String c = check();
	    if(!c.equals("c")) return c;
	}
	System.out.println("dealer: stand");
	d.print();
	return "c";
    }
    
    public void ai(int n) {
	System.out.println("\nai "+n+"'s turn");
	//System.out.println(n);
	//System.out.println(u.get(n));
	//System.out.println(d.sum());
	//System.out.println(_d.undealt());
	if(d.sum() == 21 && u.get(n).sum() < 21) {
	    System.out.println("blackjack: ai "+n+" loses");
	    return;
	}
	int hits = u.get(n).move(_d.undealt(),d.sum());
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

    public String check() {
	if (p.sum() == 21) {
	    System.out.println("blackjack: blackjack! you win");
	    p.money += bet*5/2;
	    bet = 0;
	    System.out.println("blackjack: you now have "+p.money+" dollars");
	    return "bp";
	}
	else if (d.sum() == 21) {
	    System.out.println("blackjack: dealer has blackjack. you lose.");
	    bet = 0;
	    System.out.println("blackjack: you now have "+p.money+" dollars");
	    return "bd";
	}
	else if (p.sum() > 21) {
	    System.out.println("blackjack: bust. you lose.");
	    bet = 0;
	    System.out.println("blackjack: you now have "+p.money+" dollars");
	    return "pb";
	}
	else if (d.sum() > 21) {
	    System.out.println("blackjack: dealer bust. you win");
	    p.money += bet*2;
	    bet = 0;
	    System.out.println("blackjack: you now have "+p.money+" dollars");
	    return "db";
	}
	return "c";
    }

    public String end(String fc, String pl, String dl) {
	if(fc.equals("bd")) return "";
	compare();
	if(pl.equals("pb")) return "";
	if(p.sum() > d.sum()) {
	    System.out.println("blackjack: dealer has less. you win.");
	    p.money += bet*2;
	    bet = 0;
	    System.out.println("blackjack: you now have "+p.money+" dollars");
	    return "p";
	}
	if(p.sum() == d.sum()) {
	    System.out.println("blackjack: tie");
	    p.money += bet;
	    bet = 0;
	    System.out.println("blackjack: you now have "+p.money+" dollars");
	    return "t";
	}
	if(p.sum() < d.sum()) {
	    System.out.println("blackjack: dealer has more. you lose");
	    bet = 0;
	    System.out.println("blackjack: you now have "+p.money+" dollars");
	    return "d";
	}
	
	return "????";
    }
    
    public void compare() {
	for(int i = 0; i < u.size(); i++) {
	    if(u.get(i).sum() > d.sum() && u.get(i).sum() < 22) {
		System.out.println("blackjack: ai " + i + " wins.");
		u.get(i).money += 10;
	    }
	    else if(u.get(i).sum() == d.sum()) {
		System.out.println("blackjack: ai " + i + " tied with the dealer.");
		u.get(i).money += 5;
	    }
	    else if(u.get(i).sum() < d.sum() && d.sum() < 22) {
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
