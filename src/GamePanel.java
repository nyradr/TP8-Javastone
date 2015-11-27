import java.io.Console;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;


public class GamePanel {

	private Joueur joueur1;
	private Joueur joueur2;
	
	Scanner scan;
	
	public GamePanel(){
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
		
	}
	
	private void initPlayer(Joueur player){
		player = new Joueur();
		
		
	}
	
	public void startGame(){
		initPlayer(this.joueur1);
		initPlayer(this.joueur2);
		
		while(!this.joueur1.isDead() && !this.joueur2.isDead()){
			playerTurn(this.joueur1);
			playerTurn(this.joueur2);
		}
	}
	
	
}
