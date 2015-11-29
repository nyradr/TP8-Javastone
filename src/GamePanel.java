import java.util.Scanner;


public class GamePanel {

	private Joueur joueur1;
	private Joueur joueur2;
	
	Scanner scan;
	DrawingPanel draw;
	
	public GamePanel(){
		this.joueur1 = new Joueur();
		this.joueur2 = new Joueur();
		
		this.scan = new Scanner(System.in);
		this.draw = new DrawingPanel();
	}
	
	/**
	 * Dessine un menu
	 * @param title titre du menu
	 * @param items items du menu
	 */
	private void drawMenu(String title, String[] items){
		System.out.println(title + " :\n");
		
		for(int i = 0; i < items.length; i++){
			String str = "\t" + i;
			if(i < 10)
				str += " ";
			str += "- " + items[i];
			
			System.out.println(str);
		}
	}
	
	/**
	 * dessine un menu est gère l'entrée
	 * @param title titre du menu
	 * @param items items du menu
	 * @return valeur de l'items selectionner
	 */
	public int menu(String title, String [] items){
		int val = -1;
		
		do{
			drawMenu(title, items);
			
			try{
				val = Integer.parseInt(scan.nextLine());
				
				if(val >= items.length)
					throw new Exception();
			}catch(Exception e){
				val = -1;
				System.out.println("Input error");
			}
		}while(val < 0);
		
		return val;
	}
	
	/**
	 * Obtient l'instace Joueur du joueur adverse
	 * @param player joueur courant
	 * @return son adversaire
	 */
	private Joueur getAdversaire(Joueur player){
		if(player == this.joueur1)
			return joueur2;
		return joueur1;
	}
	
	/**
	 * Invoque une creature
	 * @param crea
	 * @param player
	 */
	private void applyInvoke(String crea, Joueur player){
		Creature c = new Creature(crea);
		player.invoke(c);
	}
	
	/**
	 * Applique un buff à la cible
	 * @param dmg
	 * @param life
	 * @param player
	 * @param target
	 */
	private void applyBuff(int dmg, int life, Joueur player, int target){
		if(target < player.getPlateau().size())
			player.getPlateau().get(target).buffCreature(life, dmg);
		
		//TODO erreur target
	}
	
	/**
	 * Inflique des dégats à la cible
	 * @param dmg
	 * @param player
	 * @param target
	 */
	private void applyDmg(int dmg, Joueur player, int target){
		if(target < player.getPlateau().size())
			player.getPlateau().get(target).takeDamage(dmg);
		
		//TODO erreurs target
	}
	
	/**
	 * Fait piocher n carte au joueur
	 * @param n
	 * @param player
	 */
	private void applyDeck(int n, Joueur player){
		for(int i = 0; i < n; i++)
			player.pioche();
	}
	
	/**
	 * Applique l'effet au joueur
	 * @param e effet
	 * @param player joueur
	 * @param target cible(dans le cas d'un BUFF ou d'un DMG)
	 */
	private void applyEngineOn(Engine e, Joueur player, int target){
		switch (e.getType()) {
		case INVOKE:
			applyInvoke(e.getArgs()[0], player);
			break;
		
		case BUFF:
			applyBuff(
					Integer.parseInt(e.getArgs()[0]),
					Integer.parseInt(e.getArgs()[1]),
					player, target);
			break;
			
		case DAMAGE:
			applyDmg(Integer.parseInt(e.getArgs()[0]), player, target);
			break;
			
		case DECK:
			applyDeck(Integer.parseInt(e.getArgs()[0]), player);
			break;
			
		default:
			break;
		}
	}
	
	/**
	 * Interprete l'effet d'une carte
	 * @param c carte à interpreter
	 * @param player joueur courant
	 */
	private void interpret(Carte c, Joueur player, int target){
		for(Engine e : Engine.extractEffects(c.getEffect())){
			switch(e.getTarget()){
			case ALL:
				applyEngineOn(e, player, target);
				applyEngineOn(e, getAdversaire(player), target);
				break;
			
			case PLAYER:
				applyEngineOn(e, player, target);
				break;
				
			case ADV:
				applyEngineOn(e, getAdversaire(player), target);
				break;
			}
		}
	}
	
	
	private void playerTurn(Joueur player){
		player.newTurn();
		
		System.out.println("Debut de votre tour...");
		
		//Adversaire
		System.out.println("Adversaire:");
		draw.drawPlayer(getAdversaire(player), true);
		
		//Joueur:
		System.out.println("Vous:");
		draw.drawPlayer(player, false);
		
		switch (menu("Que voullez vous faire", new String[]{"Jouer une carte", "Attaquer avec une creature", "Rien faire"})) {
		case 0:
			String[] strcartes = new String[player.getMain().size()];
			for(int i = 0; i < strcartes.length; i++)
				strcartes[i] = player.getMain().get(i).toString();
			
			int cartejouer = menu("Quelle carte jouez vous", strcartes);
			
			
			
			break;

		case 1:
			//TODO : Attaquer 
			break;
			
		default:
			System.out.println("Vous n'avez rien fait");
			break;
		}
	}
	
	private void initPlayer(Joueur player){
		System.out.println("Initialisation :");
		
		if (menu("Deck", new String[]{"Depuis un fichier", "Creation manuelle"}) == 0) {
			System.out.println("depuis un deck");
		}else{
			System.out.println("manuelle");
		}
	}
	
	public void startGame(){
		//initPlayer(this.joueur1);
		//initPlayer(this.joueur2);
		
		//while(!this.joueur1.isDead() && !this.joueur2.isDead()){
			playerTurn(this.joueur1);
			//playerTurn(this.joueur2);
		//}
	}
	
	
}
