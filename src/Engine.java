import java.util.ArrayList;
import java.util.List;

/**
 * Gestionaire d'effets
 */
public class Engine {
	
	private CardType type;		//type de la carte
	private EngineTarget qui;	//cible de d'effet
	private String [] args;		//arguments eventuels
	
	public Engine(CardType type, EngineTarget qui, String [] args){
		this.type = type;
		this.qui = qui;
		this.args = args;
	}
	
	public CardType getType(){
		return this.type;
	}
	
	public EngineTarget getTarget(){
		return qui;
	}
	
	public String [] getArgs(){
		return args;
	}
	
	
	/**
	 * Extrait un effet depuis une String
	 * @param effect
	 * @return
	 */
	public static Engine extractEngine(String effect){
		List<String> args = new ArrayList<String>();
		Engine engine = null;
		String arg = "";
		int i = 0;
		boolean ctn = true;
		
		while(i < effect.length() && ctn){
			char c = effect.charAt(i);
			
			if(c == ';' || c == ' '){
				ctn = c != ';';
				
				if(!arg.equals("")){
					args.add(arg);
				}
				arg = "";
			}else
				arg += c;
			i++;
		}
		if(ctn)
			args.add(arg);
		
		if(args.size() > 2){
			String [] cmdargs = new String[args.size() -2];
			
			for(i = 2; i < args.size(); i++)
				cmdargs[i -2] = args.get(i);
			
			engine = new Engine(
					CardType.fromString(args.get(0)),
					EngineTarget.fromString(args.get(1)),
					cmdargs);
		}
		return engine;
	}
	
	/**
	 * Extrait touts les effets d'une String
	 * @param effects
	 * @return
	 */
	public static List<Engine> extractEffects(String effects){
		List<Engine> eff = new ArrayList<Engine>();
		
		String nstr = effects;
		Engine eng = null;
		
		while((eng = extractEngine(nstr)) != null){
			eff.add(eng);
			
			int isep = nstr.indexOf(";");
			if(isep >= 0)
				nstr = nstr.substring(isep +1);
			else
				nstr = "";
		}
		
		return eff;
	}
	
	public String toString(){
		String str = this.type + " " + this.qui;
		
		for(String s : this.args)
			str += " " + s;
		return str;
	}
}
