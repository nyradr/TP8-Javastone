import java.util.ArrayList;
import java.util.List;

public abstract class Jouable extends Drawable{

	protected String name;
	protected List<Engine> effets;
	protected String descr;
	protected String fileName;
	
	public String getName(){
		return this.name;
	}
	
	public List<Engine> getEffets(){
		return this.effets;
	}
	
	/**
	 * Retourne les effets correcpondant au declancheur
	 * @param event
	 * @return
	 */
	public List<Engine> getEffets(Declancheur event){
		List<Engine> eff = new ArrayList<Engine>();
		
		for(Engine e : effets)
			if(e.getDecl() == event)
				eff.add(e);
		
		return eff;
	}
	
	public String getFileName(){
		return fileName;
	}
}
