import java.util.Scanner;

import print.color.Ansi.BColor;
import print.color.Ansi.FColor;
import print.color.ColoredPrinter;

/**
 * Class gérant les affichages
 * Affichages avec JCDP
 */
public class DrawingPanel {

	ColoredPrinter printer;		//gestionaire d'affichage
	Scanner scan;				//gestion des entrées

	public DrawingPanel(){
		printer = new ColoredPrinter.Builder(1, false).foreground(FColor.WHITE).background(BColor.BLACK).build();
		scan = new Scanner(System.in);
	}
	
	/**
	 * Passe à la ligne suivante
	 */
	public void newLine(){
		printer.print("\n");
	}
	
	/**
	 * Affiche l'iterateur du menu
	 * @param i
	 */
	public void drawMenuIterator(int i){
		String s = Integer.toString(i);
		
		if(i < 10)
			s += " ";
		s += "- ";
		printer.print(s);
	}
	
	/**
	 * Dessine un menu d'object dessinables
	 * @param items 
	 */
	public void drawMenu(Drawable [] items){
		for(int i = 0; i < items.length; i++){
			drawMenuIterator(i);
			items[i].draw(printer);
			newLine();
		}
	}
	
	/**
	 * Dessine un menu de Strings
	 * @param items
	 */
	public void drawMenu(String [] items){
		for(int i = 0; i < items.length; i++){
			drawMenuIterator(i);
			printer.println(items[i]);
		}
	}
	
	/**
	 * Affiche le titre du menu
	 * @param title
	 */
	public void drawMenuTitle(String title){
		printer.println(title);
	}
	
	/**
	 * Gère les entrée du menu<br>
	 * les entrées sont comprisent entre 0 et max
	 * @param max
	 * @return
	 */
	public int menuInput(int max){
		int val = -1;
		try{
			val = Integer.parseInt(scan.nextLine());
			
			if(val < 0 || val >= max)
				throw new Exception();
		}catch(Exception e){
			printer.println("Input error");
			val = -1;
		}
		
		return val;
	}
	
	/**
	 * Gère un menu d'elements dessinables
	 * @param title titre
	 * @param items elements
	 * @return valeur selectioner
	 */
	public int menu(String title, Drawable [] items){
		int val = -1;
		
		drawMenuTitle(title);
		
		do{
			drawMenu(items);
			val = menuInput(items.length);
		}while(val < 0);
		
		return val;
	}
	
	/**
	 * Gère un menu de strings
	 * @param title titre
	 * @param items elements
	 * @return valeur selectioner
	 */
	public int menu(String title, String [] items){
		int val = -1;
		
		drawMenuTitle(title);
		
		do{
			drawMenu(items);
			val = menuInput(items.length);
		}while(val < 0);
		
		return val;
	}
	
	/**
	 * Dessine la barre de mana
	 * @param mana valeur du mana
	 * @param max mana maximal
	 */
	public void draw(int mana, int max){
		printer.print("Mana [");
		
		for(int i = 0; i < max; i++){
			if(i < mana)
				printer.print("X");
			else
				printer.print("_");
		}
		printer.println("]");
	}
	
	/**
	 * Affiche un joueur
	 * @param player
	 * @param adv true si on dessine l'adversaire. La main du joueur seras cacher.
	 */
	public void draw(Joueur player, boolean adv){
		printer.println("Points de vie : " + player.getLife() + " pv");
		
		draw(player.getMana(), player.getManaMax());
		
		printer.println("Main :");
		if(!adv){
			for(Carte c : player.getMain())
				printer.println("\t" + c.toString());
		}else{
			printer.println(player.getMain().size() + " carte(s) dans la main");
		}
		
		printer.println("Terrain :");
		for(int i = 1; i < player.getPlateau().size(); i++)
			printer.println("\t" + player.getPlateau().get(i).toString());
	}
	
	/**
	 * Affiche une string
	 * @param s
	 */
	public void draw(String s){
		printer.print(s);
	}
}
