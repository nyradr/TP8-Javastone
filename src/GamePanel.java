import java.io.Console;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;


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
	
	private void playerTurn(Joueur player){
		player.newTurn();
		
		System.out.println("Debut de votre tour");
		System.out.println("Il vous reste " + player.getLife() + " pv");
		
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
		initPlayer(this.joueur1);
		//initPlayer(this.joueur2);
		
		//while(!this.joueur1.isDead() && !this.joueur2.isDead()){
			playerTurn(this.joueur1);
			//playerTurn(this.joueur2);
		//}
	}
	
	
}
