import java.util.ArrayList;

import print.color.Ansi.Attribute;
import print.color.Ansi.BColor;
import print.color.Ansi.FColor;

/**
 * Représente une créature DONE
 */
public class Creature extends Jouable implements IEngineTarget{
	private int life; 		// point de vie restant
	private int damage; 	// attaque

	private boolean guardian; // indique s'il est un guardien
	private boolean tired;
	
	private String carteFileName;

	/**
	 * Contructeur basique
	 * 
	 * @param life
	 *            : points de vie initiaux
	 * @param damage
	 *            : attaque initiale
	 * @param guardian
	 *            : guardien?
	 */
	public Creature(String name, int life, int damage, boolean guardian, String effets) {
		this.life = life;
		this.name = name;

		if (damage < 0)
			this.damage = 0;
		else
			this.damage = damage;

		this.guardian = guardian;
		this.effets = Engine.extractEffects(effets);
		this.tired = true;
	}

	/**
	 * Charge la creature depuis un fichier
	 * 
	 * @param nom
	 *            nom de la creature
	 */
	public Creature(String nom, String parentCarte) throws Exception {
		loadFromFile(nom);
		this.carteFileName = parentCarte;
		this.tired = true;
	}
	
	private void loadFromFile(String nom) throws Exception{
		String[] lines = FileManager.loadFile(FileManager.FOLD_CREA, nom);

		this.fileName = nom;
		this.name = lines[0];
		this.damage = Integer.parseInt(lines[1]);
		this.life = Integer.parseInt(lines[2]);

		this.guardian = lines[3].charAt(0) == '1';
		try{
			this.effets = Engine.extractEffects(lines[4]);
			this.descr = lines[5];
		}catch(Exception e){
			this.effets = new ArrayList<Engine>();
		}
	}

	/**
	 * @return nom de la creature
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return le nombre de pv restant
	 */
	public int getLife() {
		return this.life;
	}

	/**
	 * Donne l'attaque courante
	 * 
	 * @return attaque
	 */
	public int getDamage() {
		return this.damage;
	}
	
	/**
	 * Indique si la creature est fatiguer
	 * @return
	 */
	public boolean getFatigue(){
		return this.tired;
	}
	
	/**
	 * Definis la fatigue de la creature
	 * @param f
	 */
	public void setFatigue(boolean f){
		this.tired = f;
	}
	
	/**
	 * indique si la creature est morte
	 * 
	 * @return true si elle est morte
	 */
	public boolean isDead() {
		return this.life <= 0;
	}

	/**
	 * indique si la creature est un guardien
	 * 
	 * @return true si elle est un guardien
	 */
	public boolean isGuardian() {
		return this.guardian;
	}

	/**
	 * La creature subit une attaque de valDamage
	 * 
	 * @param valDamage
	 *            valeur de l'attaque
	 */
	public void takeDamage(int valDamage) {
		this.life -= valDamage;
	}

	/**
	 * Applique un buff à la creature Si les arguments sont negatifs il n'agit
	 * d'un debuff
	 * 
	 * @param buffLife
	 *            buff de vie
	 * @param buffDamage
	 *            buff d'attaque
	 */
	public void buffCreature(int buffLife, int buffDamage) {
		this.life += buffLife;
		this.damage += buffDamage;
	}

	public String toString() {
		return this.name + "," + this.damage + "/" + this.life;
	}

	@Override
	public void draw(DrawingPanel printer) {
		
		printer.getPrinter().print(this.getName());
		printer.getPrinter().print(" " + this.getDamage() + "/" + this.getLife());
		if(this.descr != null)
			printer.getPrinter().print(" " + this.descr);
		
		if(this.tired)
			printer.getPrinter().print(" est fatiguer", Attribute.BOLD, FColor.YELLOW, BColor.BLACK);
	}

	@Override
	public Creature eng_invoke(String name, String carteFileName) throws Exception {
		if(name.length() == 0)
			loadFromFile(name);
		return null;
	}

	@Override
	public boolean eng_buff(int pv, int dmg) throws Exception {
		buffCreature(pv, dmg);
		return pv < 0;
	}

	@Override
	public void eng_deck(int nbr) throws Exception {
		throw new Exception();	//non implementer
	}

	@Override
	public String eng_renv() throws Exception {
		if(this.carteFileName == null)
			throw new Exception();
		else
			this.life = 0;
		return this.carteFileName;
	}
}
