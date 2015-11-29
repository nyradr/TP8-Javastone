import java.util.Scanner;


public class GamePanel {

	private Joueur joueur1;
	private Joueur joueur2;
	
	Scanner scan;
	
	public GamePanel(){
		this.joueur1 = new Joueur();
		this.joueur2 = new Joueur();
		
		this.scan = new Scanner(System.in);
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
	
	private void drawPlayer(Joueur player){
		System.out.println("Points de vie " + player.getLife() + " pv");
		
		String s_mana = "";
		int m = 0;
		while(m < player.getManaMax()){
			if(m < player.getMana())
				s_mana += "X";
			else
				s_mana += "_";
			m++;
		}
		System.out.println("Mana [" + s_mana + "]");
		
		System.out.println("Main :");
		for(Carte c : player.getMain())
			System.out.println("\t" + c.toString());
		
		System.out.println("Terrain :");
		for(int i = 1; i < player.getPlateau().size(); i++)
			System.out.println(player.getPlateau().get(i).toString());
	}
	
	private Joueur getAdversaire(Joueur player){
		if(player == this.joueur1)
			return joueur2;
		return joueur1;
	}
	
	private void interpret(Carte c, Joueur player){
		
	}
	
	
	private void playerTurn(Joueur player){
		player.newTurn();
		
		System.out.println("Debut de votre tour...");
		
		//Adversaire
		System.out.println("Adversaire:");
		drawPlayer(getAdversaire(player));
		
		//Joueur:
		System.out.println("Vous:");
		drawPlayer(player);
		
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
