
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

/**
 * Représente un deck de cartes Un deck est une pile de carte
 */
public class Deck {

	private ArrayList<Carte> deck; // pile de cartes, le haut de la pile est la
									// fin de la liste

	private int nmax; // nombre maximal d'elements

	/**
	 * Constructeur
	 * 
	 * @param n
	 *            : nombre max de cartes
	 */
	public Deck(int n) {
		this.deck = new ArrayList<Carte>(n);
		this.nmax = n;
	}

	/**
	 * Charge un deck depuis un fichier
	 * 
	 * @param n
	 *            nombre max de cartes dans le deck
	 * @param file
	 *            fichier de deck à charger
	 */
	public Deck(int n, String file) throws Exception {
		for (String name : FileReader.loadFile(FileReader.FOLD_DECK, file))
			this.addCard(new Carte(name));
	}

	/**
	 * Indique si le deck est vide
	 * 
	 * @return true si le deck est vide
	 */
	public boolean isEmpty() {
		return this.deck.size() == 0;
	}

	/**
	 * Indique si le deck est plein Un deck est plein quand le nombre de carte
	 * qu'il contient est egal au nombre max de cartes authoriser
	 * 
	 * @return true si le deck est plein
	 */
	public boolean isFull() {
		return !(this.deck.size() < this.nmax);
	}

	/**
	 * @return le nombre de cartes restantes dans le deck
	 */
	public int size() {
		return deck.size();
	}

	/**
	 * @return obtient la taille maximale du deck
	 */
	public int getMax() {
		return nmax;
	}

	/**
	 * Ajoute une carte au deck
	 * 
	 * @param c
	 *            : carte à ajouter
	 */
	public void addCard(Carte c) {
		if (this.deck.size() < this.nmax)
			this.deck.add(c);
	}

	/**
	 * Mélange le deck alèatoirement
	 */
	public void generateAleat() {
		Collections.shuffle(this.deck);
	}

	/**
	 * Pioche la carte situer au sommet du deck
	 * 
	 * @return carte piocher ou null si le deck est vide
	 */
	public Carte pioche() {
		int it = this.deck.size() - 1;
		Carte c = null;

		if (it >= 0)
			c = this.deck.remove(it);

		return c;
	}

	/**
	 * Donne le nombre de fois que la carte apparait dans le deck
	 * 
	 * @param c
	 *            carte
	 * @return nombre d'occurences de la carte
	 */
	public int occurInDeck(Carte crt) {
		int occ = 0;

		for (Carte c : this.deck) {
			if (c.getName().equals(crt.getName()))
				occ++;
		}

		return occ;
	}
}
