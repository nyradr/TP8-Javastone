import print.color.Ansi.Attribute;
import print.color.Ansi.BColor;
import print.color.Ansi.FColor;
import print.color.ColoredPrinter;

public class DrawingPanel {

	ColoredPrinter printer;

	public DrawingPanel(){
		printer = new ColoredPrinter.Builder(1, false).foreground(FColor.WHITE).background(BColor.BLACK).build();
	}
	
	public void newLine(){
		printer.print("\n");
	}
	
	public void drawCarte(Carte c){
		printer.print("[" + c.getMane() + "]", Attribute.BOLD, FColor.GREEN, BColor.WHITE);
		printer.print(c.getName() + " : " + c.getDescr(),
				Attribute.BOLD, FColor.WHITE, BColor.WHITE);
	}
	
	public void drawCreature(Creature c){
		printer.print(c.name());
		printer.print(c.getDamage() + "/" + c.getLife());
	}
	
	public void draw(String s){
		printer.print(s);
	}
}
