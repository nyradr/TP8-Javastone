import print.color.Ansi.Attribute;
import print.color.Ansi.BColor;
import print.color.Ansi.FColor;
import print.color.ColoredPrinter;

/**
 * Class gérant les affichages
 * Affichages avec JCDP
 */
public class DrawingPanel {

	ColoredPrinter printer;

	public DrawingPanel(){
		printer = new ColoredPrinter.Builder(1, false).foreground(FColor.WHITE).background(BColor.BLACK).build();
	}
	
	/**
	 * Passe à la ligne suivante
	 */
	public void newLine(){
		printer.print("\n");
	}
	
	/**
	 * Dessine une carte<br>
	 * Ne passe pas à la ligne
	 * @param c carte à dessiner
	 */
	public void drawCarte(Carte c){
		printer.print("[" + c.getMana() + "]", Attribute.BOLD, FColor.GREEN, BColor.WHITE);
		printer.print(c.getName() + " : " + c.getDescr(),
				Attribute.BOLD, FColor.WHITE, BColor.WHITE);
	}
	
	/**
	 * Dessine une creature<br>
	 * Ne passe pas à la ligne
	 * @param c creature à dessiner
	 */
	public void drawCreature(Creature c){
		printer.print(c.name());
		printer.print(c.getDamage() + "/" + c.getLife());
	}
	
	/**
	 * Dessine la barre de mana
	 * @param mana valeur du mana
	 * @param max mana maximal
	 */
	public void drawMana(int mana, int max){
		printer.print("Mana [", Attribute.BOLD, FColor.BLUE, BColor.BLACK);
		
		for(int i = 0; i < max; i++){
			if(i < mana)
				printer.print("X", Attribute.BOLD, FColor.BLUE, BColor.BLACK);
			else
				printer.print("_", Attribute.BOLD, FColor.BLACK, BColor.BLACK);
		}
		printer.println("]", Attribute.BOLD, FColor.BLUE, BColor.BLACK);
	}
	
	/**
	 * Affiche un joueur
	 * @param player
	 * @param adv true si on dessine l'adversaire. La main du joueur seras cacher.
	 */
	public void drawPlayer(Joueur player, boolean adv){
		printer.println("Points de vie " + player.getLife() + " pv");
		
		drawMana(player.getMana(), player.getManaMax());
		
		printer.print("Main :");
		if(!adv){
			for(Carte c : player.getMain())
				printer.println("\t" + c.toString());
		}else{
			printer.println(player.getMain());
		}
		
		printer.println("Terrain :");
		for(int i = 1; i < player.getPlateau().size(); i++)
			printer.println(player.getPlateau().get(i).toString());
	}
	
	/**
	 * Affiche une string
	 * @param s
	 */
	public void draw(String s){
		printer.print(s);
	}
}
