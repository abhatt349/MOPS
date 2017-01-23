import java.util.ArrayList;
public class Blackjack {
    public int bet = 0;
    public Deck _d = new Deck(1);
    public Dealer d = new Dealer(); //hmmmm
    public Player p = new Player(100);
    public int b = 0;
    public ArrayList<AI> u;
    
    public Blackjack (ArrayList<AI> ais) {
	u = ais;
    }

    public void game() {
	System.out.println( "===============================================" );
	System.out.println( "BlackJack" );
	System.out.println( "-----------------------------------------------" );
	System.out.println( "Dealer stands on all 17");
	System.out.println( "Blackjacks pay 3:2");
	System.out.println( "AI's bet 5 by default");
	System.out.println( "Cards are represented by face value + suit" );
	System.out.println( "             e.g. Ah for the ace of hearts" );
	//System.out.println( "X means a card is facedown" );
	System.out.println( "Only moves implemented are hit and stand" );
	//System.out.println( "type help for help and quit to quit at any time" );
	boolean moneyLeft = true;
	while(moneyLeft&&(p.input("blackjack: play a round? type y or n:\nplayer: ").equals("y"))) { //boolean short circuiting to the rescue!
	    //int bet = Integer.parseInt(p.input("(doesnt do anything) bet:")); 
	    System.out.println("blackjack: round start");
	    round();
	    if (p.money < 5) {
		moneyLeft = false;
	    }
	    p.reset();
	    for(int i = 0; i < u.size(); i++) {
		//System.out.println(u);
		//System.out.println(u.get(i));
		//System.out.println(i);
		u.get(i).reset();
	    }
	    d.reset();
	}
	if (!moneyLeft) {
	    System.out.println("blackjack: You ran out of money! Thanks for playing!");
	}
     }

    public String round() {	    
	_d.shuffle();
	System.out.println("blackjack: You have "+p.money+" dollars");
        bet = Integer.parseInt(p.input("blackjack: Enter a bet between 5 and "+p.money+"\nplayer: "));
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
	System.out.println("\nblackjack: turn start");
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
	}
	int hits = u.get(n).move(_d.undealt(),d.sum());
	for(int i = 0; i < hits; i++) {
	    u.get(n).add(_d.deal());
	    System.out.println("ai " + n + ": hit");
	    u.get(n).print();
	    if(u.get(n).sum() == 21) {
		System.out.println("ai " + n + ": stand");
		return;
	    }	
	    else if(u.get(n).sum() > 21) {
		u.get(n).money = u.get(n).money - 5;
		System.out.println("ai " + n + ": bust");
		return;
	    }
	}
	System.out.println("ai " + n + ": stand");
	return;
    }

    public String check() {
	if (p.sum() == 21) {
	    System.out.println("blackjack: blackjack! you win");
	    p.money += bet*3/2;
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
	    if(u.get(i).sum() > d.sum()) {
		System.out.println("blackjack: ai " + i + " wins.");
		u.get(i).money += 10;
		return;
	    }
	    else if(u.get(i).sum() == d.sum()) {
		System.out.println("blackjack: ai " + i + " tied with the dealer.");
		u.get(i).money += 5;
		return;
	    }
	    else if(u.get(i).sum() < d.sum()) {
		System.out.println("blackjack: ai " + i + " loses.");
		return;		
	    }
	}
    }

    public static void main(String[] args) {
	//none
    }
}
