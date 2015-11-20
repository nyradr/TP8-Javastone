import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class Deck {
	
	ArrayList<Carte> deck;
	
	int nmax;
	
	public Deck(int n){
		this.deck = new ArrayList<Carte>(n);
		this.nmax = n;
	}
	
	public void addCard(Carte c){
		if(this.deck.size() < this.nmax)
			this.deck.add(c);
	}
	
	public void generateAleat(){
		ArrayList<Carte> newdeck = new ArrayList<Carte>(this.nmax);
		Random rand = new Random();
		
		for(int i = 0; i < this.deck.size(); i++){
			int it = rand.nextInt(this.deck.size());
			
			newdeck.add(this.deck.get(it));
			this.deck.remove(it);
		}
		
		this.deck = newdeck;
	}
	
}
