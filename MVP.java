public class MVP {
    public static Deck _d = new Deck(1);
    public static Dealer d = new Dealer(); //hmmmm
    public static Player p = new Player(100);
    //public static Player[] = {p,d};
    
    public static void game() {
	while(p.input("\nplay blackjack? y/n:").equals("y")) {
	    //int bet = Integer.parseInt(p.input("(doesnt do anything) bet:")); 
	    System.out.println("blackjack: round start");
	    round();
	    p.reset();
	    d.reset();
	}
    }

    public static String round() {	    
	_d.shuffle();
	for(int i = 0; i < 2; i++) {
	    d.add(_d.deal());
	    p.add(_d.deal());
	}
	p.print();
	d.print();
	String c = check();
	if(!c.equals("c")) return c;
<<<<<<< HEAD
	String p = player();
	if(!p.equals("c")) return p;
=======
	if (!player()) {return check();} 
>>>>>>> 287108064be7709cf8300dd2ed1de845b52f5681
	c = check();
	if(!c.equals("c")) return c;
	String dd = dealer();
	if(!dd.equals("c")) return dd;
	return end();
    }

<<<<<<< HEAD
    public static String player() {
=======
    public static boolean player() {
>>>>>>> 287108064be7709cf8300dd2ed1de845b52f5681
	System.out.println("\nblackjack: turn start");
	while(!p.input("player:").equals("stand")) {
	    p.add(_d.deal());
	    p.print();
<<<<<<< HEAD
	    String c = check();
	    if(!c.equals("c")) return c;
	}
	return "c";
=======
	    if(!check().equals("c")) return false;
	    else {return true;}
	}
	    return true;
>>>>>>> 287108064be7709cf8300dd2ed1de845b52f5681
    }

    public static String dealer() {
	System.out.println("");
	while(d.sum() < 17) {
	    d.add(_d.deal());
	    System.out.println("dealer: hit");
	    d.print();
	    String c = check();
	    if(!c.equals("c")) return c;
	}
	return "c";
    }
    
    public static String check() {
	if (p.sum() == 21) {
	    System.out.println("blackjack: blackjack! you win");
	    return "b";
	}
	else if (d.sum() == 21) {
	    System.out.println("blackjack: dealer has blackjack. you lose.");
	    return "d";
	}
	else if (p.sum() > 21) {
	    System.out.println("blackjack: bust. you lose.");
	    return "d";
	}
	else if (d.sum() > 21) {
	    System.out.println("blackjack: dealer bust. you win");
	    return "p";
	}
	return "c";
    }

    public static String end() {
	if(p.sum() > d.sum()) {
	    System.out.println("blackjack: dealer has less. you win.");
	    return "p";
	}
	if(p.sum() == d.sum()) {
	    System.out.println("blackjack: tie");
	    return "t";
	}
	if(p.sum() < d.sum()) {
	    System.out.println("blackjack: you lose");
	    return "d";
	}
	return "????";
    }
    
    /*
    public void turn() {
	d.print();
	p.print();
	player();
	dealer();
    }
    */

    public static void main(String[] args) {
	System.out.println( "===============================================" );
	System.out.println( "MVP BlackJack" );
	System.out.println( "-------------------------------------------" );
	System.out.println( "1 player 1 dealer, dealer stands on all 17" );
	System.out.println( "cards are represented by face value + suit" );
	System.out.println( "             e.g. Ah for the ace of hearts" );
	//System.out.println( "X means a card is facedown" );
	System.out.println( "only moves implemented are hit and stand" );
	//System.out.println( "type help for help and quit to quit at any time" );
	game();	
    }
}
