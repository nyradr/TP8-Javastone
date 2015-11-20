public class Creature {
	private int life;
	private int damage;
	
	private boolean guardian;
	// private boolean [others]
	
	public Creature(int life, int damage, boolean guardian){
		this.life = life;
		
		if(damage < 0)
			this.damage = 0;
		else
			this.damage = damage;	
		
		this.guardian = guardian;		
	}
	
	public int getLife(){
		return this.life;
	}
	public int getDamage(){
		return  this.damage;
	}
	
	public boolean isDead(){
		return this.life <= 0;
	}
	public boolean isGuardian(){

		return this.guardian;
	}

	public void takeDamage(int valDamage){
		this.life -= valDamage;
	}
	public void buffCreature(int buffLife, int buffDamage){
		this.life += buffLife;
		this.damage += buffDamage;
	}
	
	
}
