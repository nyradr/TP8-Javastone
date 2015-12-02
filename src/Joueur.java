
import java.util.ArrayList;
import java.util.List;

/**
 * Classe joueur TODO
 *
 */
public class Joueur {

	private String name; // nom du joueur

	private Deck deck; // deck
	private ArrayList<Carte> main; // cartes en main
	private int mainmax; // nombre de cartes maximales en main

	private ArrayList<Creature> plateau; // Creatures sur le terrain

	private int mana; // mana actuelle
	private int manamax; // mana maximale

	/**
	 * Nouveau joueur
	 */
	public Joueur(String name) {
		this.name = name;

		this.deck = new Deck(30);
		this.main = new ArrayList<Carte>();
		this.plateau = new ArrayList<Creature>();

		this.plateau.add(new Creature("Joueur", 30, 0, false));

		this.mainmax = 10;

		this.mana = 0;
		this.manamax = 10;
	}

	/**
	 * @return vie restante
	 */
	public int getLife() {
		return this.plateau.get(0).getLife();
	}

	/**
	 * @return mana
	 */
	public int getMana() {
		return this.mana;
	}

	/**
	 * @return mana maximale
	 */
	public int getManaMax() {
		return this.mainmax;
	}

	/**
	 * @return nom du joueur
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name
	 *            change le nom du joueur
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Indique si le joueur est mort
	 * 
	 * @return
	 */
	public boolean isDead() {
		return getPlayerInstance().isDead();
	}

	/**
	 * Retourne la créature représentant le joueur
	 * 
	 * @return
	 */
	public Creature getPlayerInstance() {
		return this.plateau.get(0);
	}

	/**
	 * @return retourne le Deck du joueur
	 */
	public Deck getDeck() {
		return this.deck;
	}

	/**
	 * @return retourne la main du joueur
	 */
	public List<Carte> getMain() {
		return this.main;
	}

	/**
	 * @return retourne le plateur du jeu. L'element 0 représente la créature
	 *         Joueur
	 */
	public List<Creature> getPlateau() {
		return this.plateau;
	}

	/**
	 * @return indique si le joueur à un guardien sur le plateau
	 */
	public boolean asGuardien() {
		boolean guard = false;

		for (int i = 1; i < this.plateau.size() && !guard; i++)
			guard = this.plateau.get(i).isGuardian();

		return guard;
	}

	/**
	 * Applique les règles d'un noveau tour(mana, ...) au joueur
	 */
	public void newTurn() {
		if (this.mana < this.manamax)
			this.mana++;

		for (int i = 1; i < this.plateau.size(); i++) {
			if (this.plateau.get(i).isDead())
				this.plateau.remove(i);
		}
	}

	/**
	 * Fait procher au joueur une carte et l'ajoute à sa main<br>
	 * Si la main du joueur est pleine la carte est suprimer
	 */
	public void pioche() {
		Carte c = this.deck.pioche();
		if (c != null) {
			if (this.main.size() < this.mainmax)
				this.main.add(c);
		}
	}

	/**
	 * Le joueur joue la carte c, reduction du mana et la carte est retirer de
	 * la main
	 * 
	 * @param c
	 */
	public void playCard(Carte c) {
		this.mana -= c.getMana();
		this.main.remove(c);
	}

	/**
	 * Ajoute une creature au terrain
	 * 
	 * @param c
	 */
	public void invoke(Creature c) {
		this.plateau.add(c);
	}
}