import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.w3c.dom.Attr;

import print.color.Ansi.Attribute;
import print.color.Ansi.BColor;
import print.color.Ansi.FColor;
import print.color.ColoredPrinter;

/**
 * Class gérant les affichages Affichages avec JCDP
 */
public class DrawingPanel {

	private ColoredPrinter printer; // gestionaire d'affichage
	private Scanner scan; // gestion des entrées

	public DrawingPanel() {
		printer = new ColoredPrinter.Builder(1, false).foreground(FColor.WHITE).background(BColor.BLACK).build();
		scan = new Scanner(System.in);
	}

	/**
	 * Passe à la ligne suivante
	 */
	public void newLine() {
		printer.print("\n");
	}

	/**
	 * Affiche l'iterateur du menu
	 * 
	 * @param i
	 */
	public void drawMenuIterator(int i) {
		String s = Integer.toString(i);

		if (i < 10)
			s += " ";
		s += "- ";
		printer.print("\t" + s);
	}

	/**
	 * @return obtient le gestionaire d'affichages
	 */
	public ColoredPrinter getPrinter() {
		return this.printer;
	}

	/**
	 * Dessine un menu d'object dessinables
	 * 
	 * @param items
	 */
	public void drawMenu(Drawable[] items) {
		for (int i = 0; i < items.length; i++) {
			drawMenuIterator(i);
			items[i].draw(this);
			newLine();
		}
	}

	/**
	 * Dessine un menu de Strings
	 * 
	 * @param items
	 */
	public void drawMenu(String[] items) {
		for (int i = 0; i < items.length; i++) {
			drawMenuIterator(i);
			printer.println(items[i]);
		}
	}

	/**
	 * Affiche le titre du menu
	 * 
	 * @param title
	 */
	public void drawMenuTitle(String title) {
		printer.println(title);
	}

	/**
	 * Gère les entrée du menu<br>
	 * les entrées sont comprisent entre 0 et max
	 * 
	 * @param max
	 * @return
	 */
	public int menuInput(int max) {
		int val = -1;
		try {
			val = Integer.parseInt(scan.nextLine());

			if (val < 0 || val >= max)
				throw new Exception();
		} catch (Exception e) {
			this.error("Entrée invalide");
			val = -1;
		}

		return val;
	}

	/**
	 * Gère un menu d'elements dessinables
	 * 
	 * @param title
	 *            titre
	 * @param items
	 *            elements
	 * @return valeur selectioner
	 */
	public int menu(String title, Drawable[] items) {
		int val = -1;

		drawMenuTitle(title);

		do {
			drawMenu(items);
			val = menuInput(items.length);
		} while (val < 0);

		return val;
	}

	/**
	 * Gère un menu de strings
	 * 
	 * @param title
	 *            titre
	 * @param items
	 *            elements
	 * @return valeur selectioner
	 */
	public int menu(String title, String[] items) {
		int val = -1;

		drawMenuTitle(title);

		do {
			drawMenu(items);
			val = menuInput(items.length);
		} while (val < 0);

		return val;
	}

	public void buildDeck(Deck deck) {
		printer.println("Contruction d'un deck:\n");
		String[] nomcarteDispo = FileManager.listFiles(FileManager.FOLD_CART);
		List<Carte> cartesDispo = new ArrayList<Carte>();

		printer.println("Nom du deck (Attention par pure fleme du developpeur si un deck porte déja ce nom il seras remplacer par ce nouveau deck)");
		String nomDeck = scan.nextLine();		
		
		for (int i = 0; i < nomcarteDispo.length; i++) {
			try {
				Carte c = new Carte(nomcarteDispo[i]);
				cartesDispo.add(c);
			} catch (Exception e) {
				printer.println("Erreur : impossible de charger la carte");
			}
		}
		
		for (int i = 0; i < deck.getMax(); i++) {
			int choix = menu("Il vous reste " + (deck.getMax() - deck.size()) + " cartes à placer", cartesDispo.toArray(new Carte[0]));
			try {
				Carte c = new Carte(cartesDispo.get(choix).getFileName());
				
				if(c.getMax() > deck.occurInDeck(c))
					deck.addCard(c);
				else
					printer.println("Le nombre maximum de cette carte est deja atteint");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		try{
			deck.save(nomDeck);
		}catch(Exception e){
			printer.println("Une erreur c'est produite lors de la creation du fichier");
		}
	}

	public boolean loadDeck(Deck deck) {
		printer.println("Chargement d'un deck existant :\n");

		try {
			String[] deckDispo = FileManager.listFiles(FileManager.FOLD_DECK);
			deck.loadFile(deckDispo[menu("Decks disponibles", deckDispo)]);
			return true;
		} catch (Exception e) {
			printer.println("Erreur : Impossible de charger le deck");
			return false;
		}
		
		
	}

	public void error(String mess){
		printer.print(mess, Attribute.BOLD, FColor.RED, BColor.BLACK);
		newLine();
	}
	
	/**
	 * Dessine la barre de mana
	 * 
	 * @param mana
	 *            valeur du mana
	 * @param max
	 *            mana maximal
	 */
	public void draw(int mana, int max) {
		printer.print("Mana [");

		for (int i = 0; i < max; i++) {
			if (i < mana)
				printer.print("X", Attribute.BOLD, FColor.BLUE, BColor.BLACK);
			else
				printer.print("_");
		}
		printer.print("] ");
		printer.print(mana, Attribute.BOLD, FColor.BLUE, BColor.BLACK);
		newLine();
	}

	/**
	 * Affiche un joueur
	 * 
	 * @param player
	 * @param adv
	 *            true si on dessine l'adversaire. La main du joueur seras
	 *            cacher.
	 */
	public void draw(Joueur player, boolean adv) {
		printer.println("Points de vie : " + player.getLife() + " pv");

		draw(player.getMana(), player.getManaMax());
		printer.println("Le deck contient " + player.getDeck().size() + " carte(s)");

		printer.println("Main :");
		if (!adv) {
			for (Carte c : player.getMain()){
				printer.print("\t");
				c.draw(this);
				printer.print("\n");
			}
		} else {
			printer.println(player.getMain().size() + " carte(s) dans la main");
		}

		printer.println("Terrain :");
		for(Creature c : player.getCrea()){
			printer.print("\t");
			c.draw(this);
			printer.print("\n");
		}
	}

	/**
	 * Affiche une string
	 * 
	 * @param s
	 */
	public void draw(String s) {
		printer.print(s);
	}
}
