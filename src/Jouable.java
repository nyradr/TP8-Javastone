
public abstract class Jouable extends Drawable{

	protected String name;
	protected Engine effet;
	protected String fileName;
	
	public String getName(){
		return this.name;
	}
	
	public Engine getEffet(){
		return this.effet;
	}
	
	public String getFileName(){
		return fileName;
	}
}
