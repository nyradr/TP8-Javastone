import print.color.ColoredPrinter;

/**
 * Ajout du système de fichier
 */
public class Carte extends Drawable {
	private CardType type; // type de la carte
	private int manaCost; // cout en mana

	private String name; // nom de la carte
	private String descr; // description utilisateur de la carte

	private String effect; // effets de la carte
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
		this.effect = effect;
		this.descr = descr;
	}

	/**
	 * Cree une nouvelle carte depuis un fichier
	 * 
	 * @param name
	 *            nom de la carte
	 */
	public Carte(String name) throws Exception {
		String[] lines = FileReader.loadFile(FileReader.FOLD_CART, name);

		this.name = lines[0];
		this.descr = lines[1];
		this.effect = lines[2];
		this.manaCost = Integer.parseInt(lines[3]);
		this.maxelem = Integer.parseInt(lines[4]);
	}

	/**
	 * @return nom de la carte
	 */
	public String getName() {
		return this.name;
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
	 * @return effects
	 */
	public String getEffect() {
		return this.effect;
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
	public void draw(ColoredPrinter printer) {
		printer.print("[" + this.getMana() + "]");
		printer.print(this.getName() + " : " + this.getDescr());
	}
}
