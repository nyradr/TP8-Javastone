import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**	Représente un deck de cartes
 * 	Un deck est une pile de carte
 */
public class Deck {
	
	ArrayList<Carte> deck;	//pile de cartes, le haut de la pile est la fin de la liste
	
	int nmax;				//nombre maximal d'elements
	
	/**	Constructeur
	 * 	@param n : nombre max de cartes
	 */
	public Deck(int n){
		this.deck = new ArrayList<Carte>(n);
		this.nmax = n;
	}
	
	/**	Indique si le deck est vide
	 * 	@return true si le deck est vide
	 */
	public boolean isEmpty(){
		return this.deck.size() == 0;
	}
	
	/**	Indique si le deck est plein
	 * 	Un deck est plein quand le nombre de carte qu'il contient est egal au nombre max de cartes authoriser
	 * 	@return	true si le deck est plein
	 */
	public boolean isFull(){
		return ! (this.deck.size() < this.nmax);
	}
	
	/**	Ajoute une carte au deck
	 * 	@param c	: carte à ajouter
	 */
	public void addCard(Carte c){
		if(this.deck.size() < this.nmax)
			this.deck.add(c);
	}
	
	/**	Mélange le deck alèatoirement
	 */
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
	
	/**	Pioche la carte situer au sommet du deck 
	 * 	@return carte piocher ou null si le deck est vide
	 */
	public Carte pioche(){
		int it = this.deck.size() -1;
		Carte c = this.deck.get(it);
		this.deck.remove(it);
		
		return c;
	}
}
