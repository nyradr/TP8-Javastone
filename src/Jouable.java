import java.util.List;
import java.util.stream.Collectors;

public abstract class Jouable extends Drawable{

	protected String name;
	protected List<Engine> effets;
	protected String fileName;
	
	public String getName(){
		return this.name;
	}
	
	public List<Engine> getEffets(){
		return this.effets;
	}
	
	public List<Engine> getEffets(Declancheur event){
		return this.effets.stream().filter(x -> x.getDecl() == event).collect(Collectors.toList());
	}
	
	public String getFileName(){
		return fileName;
	}
}
