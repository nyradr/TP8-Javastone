public class Carte {
	public static final int TYPE_INVOKE = 0;	//invoque un créature
	public static final int TYPE_BUFF = 1;		//applique un buff (modifie caractéristiques d'une créature)
	public static final int TYPE_DAMAGE = 2;	//inflige des dégats (creature ou joueur)
	public static final int TYPE_DECK = 3;		//Acces au paquet de carte
	
	private int type;
	private int manaCost;
	
	private String name;
	private String descr;
	
	private String effect;
	
	public Carte(int type, int manaCost, String name, String decr, String effect){
		this.type = type;
		this.manaCost = manaCost;
		this.name = name;
		this.effect = effect;
	}
	
	public void play(){
		
	}
	
}
