public class AI {
    public static int money = 0;
    public static Card[] cards;
    public static int difficulty = 0;
    public AI () {
	money = 100;
	cards = new Card[2];
    }
    public static int sum() {
	int s = 0;
	for(Card i : cards) {
	    s += i.value;
	}
	return s;
    }
    public static int move(int[] undealt, int dsum) {
	int hitn = 0;
	int sum = sum();
	int[] hits = new int[3];
	for(int i = 0; i < 3000; i++) {
	    for (int j = 0; j < undealt.length; j++) {
		int s = j + (int) (Math.random()*(undealt.length-j));
		int temp = undealt[j];
		undealt[j] = undealt[s];
		undealt[s] = temp;
	    }
	    int tsum = sum;
	    int tdsum = dsum;
	    for (int k = 0; k < i%3; k++) {
		tsum += undealt[k];
	    }
	    int c = 0;
	    while(dsum < 17) {
		tdsum += undealt[undealt.length-c];
	    } 
	    if(tsum < 22 && (tsum > tdsum || dsum > 21)) {
		hits[i%3] += 1;
	    }
	}
	//int max = Math.max(hits[0],Math.max(hits[1],hits[2]));
	return hits[0] > (Math.max(hits[1],hits[2])) ? 0 : (hits[1] > hits[2] ? 1 : 2);
    }
}