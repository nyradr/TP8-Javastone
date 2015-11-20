
/**	Représente une créature
 */
public class Creature {
	private int life;				//point de vie restant
	private int damage;				//attaque
	
	private boolean guardian;		//indique s'il est un guardien
	// private boolean [others]
	
	/**
	 * Contructeur basique
	 * @param life : points de vie initiaux
	 * @param damage : attaque initiale
	 * @param guardian : guardien?
	 */
	public Creature(int life, int damage, boolean guardian){
		this.life = life;
		
		if(damage < 0)
			this.damage = 0;
		else
			this.damage = damage;	
		
		this.guardian = guardian;		
	}
	
	/**
	 * Charge la creature depuis un fichier
	 * @param nom nom de la creature
	 */
	public Creature(String nom){
		
	}
	
	/**
	 * Donne les pv restant
	 * @return le nombre de pv restant
	 */
	public int getLife(){
		return this.life;
	}
	
	/**
	 * Donne l'attaque courante
	 * @return attaque
	 */
	public int getDamage(){
		return  this.damage;
	}
	
	/**
	 * indique si la creature est morte
	 * @return true si elle est morte
	 */
	public boolean isDead(){
		return this.life <= 0;
	}
	
	/**
	 * indique si la creature est un guardien
	 * @return true si elle est un guardien
	 */
	public boolean isGuardian(){
		return this.guardian;
	}
	
	/**
	 * La creature subit une attaque de valDamage
	 * @param valDamage valeur de l'attaque
	 */
	public void takeDamage(int valDamage){
		this.life -= valDamage;
	}
	
	/**
	 * Applique un buff à la creature
	 * Si les arguments sont negatifs il n'agit d'un debuff
	 * @param buffLife buff de vie
	 * @param buffDamage buff d'attaque
	 */
	public void buffCreature(int buffLife, int buffDamage){
		this.life += buffLife;
		this.damage += buffDamage;
	}
	
	
}
