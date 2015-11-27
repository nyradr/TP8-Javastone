

/**
 * Ajout du système de fichier
 */
public class Carte {
	public static final int TYPE_INVOKE = 0;	//invoque un créature
	public static final int TYPE_BUFF = 1;		//applique un buff (modifie caractéristiques d'une créature)
	public static final int TYPE_DAMAGE = 2;	//inflige des dégats (creature ou joueur)
	public static final int TYPE_DECK = 3;		//Acces au paquet de carte
	
	private int type;			//type de la carte (contantes de nom TYPE_*)
	private int manaCost;		//cout en mana
	
	private String name;		//nom de la carte
	private String descr;		//description utilisateur de la carte
	
	private String effect;		//effets de la carte
	private int maxelem;		//nombre max d'occurences de la carte dans le deck
	
	/**
	 * Crée une nouvelle carte
	 * @param type : type de la carte
	 * @param manaCost cout en mana
	 * @param name nom de la carte
	 * @param decr description de la carte
	 * @param effect effet de la carte
	 */
	public Carte(int type, int manaCost, String name, String descr, String effect){
		this.type = type;
		this.manaCost = manaCost;
		this.name = name;
		this.effect = effect;
		this.descr = descr;
	}
	
	/**
	 * Cree une nouvelle carte depuis un fichier
	 * @param name nom de la carte
	 */
	public Carte(String name){
		String [] lines = FileReader.loadFile(FileReader.FOLD_CART, name);
		
		try{
			//TODO
		}catch(Exception e){
			
		}
	}
	
	public void play(){
		
	}
}
