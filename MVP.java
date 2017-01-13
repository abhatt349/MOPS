public class MVP {
    public static Deck _d = new Deck(1,52);
    public static Dealer d = new Dealer(); //hmmmm
    public static Player p = new Player(100);
    //public static Player[] = {p,d};
    
    public void game() {
	while(player.input("play next round? y/n").equals("y")) {
	    int bet = Integer.parseInt(player.input("bet # - positive integers you can afford")); 
	    System.out.println("round start");
	    round();
	}
    }

    public String round() {	    
	for(int i = 0; i < 2; i++) {
	    d.add(_d.deal());
	    p.add(_d.deal());
	}
	player.print();
	dealer.print();
	if(!check().equals("c")) return check();
	player();
	if(!check().equals("c")) return check();
	dealer();
	end();
    }

    public void player() {
	System.out.println("turn start");
	while(!player.input("hit stand help quit").equals("stand")) {
	    p.add(_d.deal());
	    p.print();
	    if(!check().equals("c")) return;
	}
    }

    public void dealer() {
	System.out.println("dealer move");
	while(dealer.sum() < 17) {
	    d.add(_d.deal());
	    d.print();
	    if(!check().equals("c")) return;
	}
    }
    
    public String check() {
	if (player.sum() == 21) {
	    System.out.println("blackjack! you win");
	    return "bj";
	}
	else if (dealer.sum() == 21) {
	    System.out.println("dealer has blackjack. you lose.");
	    return "d";
	}
	else if (player.sum() > 21) {
	    System.out.println("bust. you lose.");
	    return "b";
	}
	else if (dealer.sum() > 21) {
	    System.out.println("dealer bust. you win");
	    return "db";
	}
	return "c";
    }
    
    /*
    public void turn() {
	d.print();
	p.print();
	player();
	dealer();
    }
    */
    public void player(); {
	String s = player.input("hit, stand, quit, help:");
	if (s.equals("help")) help();
	else if (s.equals("quit")) System.exit(0);
	else if (s.equals("hit")) {
	}
	else if (s.equals("stand")) {
	    
	}
	
    }

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