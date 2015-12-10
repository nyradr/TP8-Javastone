/**
 * Ajout du système de fichier
 */
public class Carte extends Jouable {
	private CardType type; // type de la carte
	private int manaCost; // cout en mana

	private int maxelem; // nombre max d'occurences de la carte dans le deck

	/**
	 * Crée une nouvelle carte
	 * 
	 * @param type
	 *            : type de la carte
	 * @param manaCost
	 *            cout en mana
	 * @param name
	 *            nom de la carte
	 * @param decr
	 *            description de la carte
	 * @param effect
	 *            effet de la carte
	 */
	public Carte(CardType type, int manaCost, String name, String descr, String effect) {
		this.type = type;
		this.manaCost = manaCost;
		this.name = name;
		this.effets = Engine.extractEffects(effect);
		this.descr = descr;
	}

	/**
	 * Cree une nouvelle carte depuis un fichier
	 * 
	 * @param name
	 *            nom de la carte
	 */
	public Carte(String name) throws Exception {
		String[] lines = FileManager.loadFile(FileManager.FOLD_CART, name);
		
		this.fileName = name;
		this.name = lines[0];
		this.descr = lines[1];
		this.effets = Engine.extractEffects(lines[2]);
		this.manaCost = Integer.parseInt(lines[3]);
		this.maxelem = Integer.parseInt(lines[4]);
	}

	/**
	 * @return description utilisateur de la carte
	 */
	public String getDescr() {
		return this.descr;
	}

	/**
	 * @return cout en mana
	 */
	public int getMana() {
		return this.manaCost;
	}

	/**
	 * @return nombre max d'occurence dans le deck
	 */
	public int getMax() {
		return this.maxelem;
	}

	public String toString() {
		String str = "[" + this.manaCost + "] ";
		str += this.name + " : " + this.descr;
		return str;
	}

	@Override
	public void draw(DrawingPanel printer) {
		printer.getPrinter().print("[" + this.getMana() + "]");
		printer.getPrinter().print(this.getName() + " : " + this.getDescr());
	}
}
