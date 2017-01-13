public class MVP {
    public static Deck _d = new Deck(1);
    public static Dealer d = new Dealer(); //hmmmm
    public static Player p = new Player(100);
    //public static Player[] = {p,d};
    
    public static void game() {
	while(p.input("play next round? y/n").equals("y")) {
	    int bet = Integer.parseInt(p.input("bet # - positive integers you can afford")); 
	    System.out.println("round start");
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
	if(!check().equals("c")) return check();
	player();
	if(!check().equals("c")) return check();
	dealer();
	return end();
    }

    public static void player() {
	System.out.println("turn start");
	while(!p.input("hit stand help quit").equals("stand")) {
	    p.add(_d.deal());
	    p.print();
	    if(!check().equals("c")) return;
	}
    }

    public static void dealer() {
	System.out.println("dealer move");
	while(d.sum() < 17) {
	    d.add(_d.deal());
	    d.print();
	    if(!check().equals("c")) return;
	}
    }
    
    public static String check() {
	if (p.sum() == 21) {
	    System.out.println("blackjack! you win");
	    return "b";
	}
	else if (d.sum() == 21) {
	    System.out.println("dealer has blackjack. you lose.");
	    return "d";
	}
	else if (p.sum() > 21) {
	    System.out.println("bust. you lose.");
	    return "d";
	}
	else if (d.sum() > 21) {
	    System.out.println("dealer bust. you win");
	    return "p";
	}
	return "c";
    }

    public static String end() {
	if(p.sum() > d.sum()) {
	    System.out.println("you win.");
	    return "p";
	}
	if(p.sum() == d.sum()) {
	    System.out.println("tie");
	    return "t";
	}
	if(p.sum() < d.sum()) {
	    System.out.println("you lose");
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
	System.out.println( "X means a card is facedown" );
	System.out.println( "only moves implemented are hit and stand" );
	System.out.println( "type help for help and quit to quit at any time" );
	game();	
    }
}