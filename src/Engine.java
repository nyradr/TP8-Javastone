import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.zip.CheckedOutputStream;

/**
 * Gestionaire d'effets
 */
public class Engine {

	private CardType		type;		// type de la carte
	private Declancheur		decl;		// declancheur de l'effet
	private Target 			qui; 		// cible de d'effet
	private String[]		args;		//argument
	

	public Engine(CardType type, Declancheur decl, Target qui, String[] args) {
		this.type = type;
		this.qui = qui;
		this.decl = decl;
		this.args = args;
	}

	/**
	 * @return type de la carte
	 */
	public CardType getType() {
		return this.type;
	}
	
	public Declancheur getDecl(){
		return this.decl;
	}

	/**
	 * @return cible de la carte
	 */
	public Target getTarget() {
		return qui;
	}

	/**
	 * @return arguments
	 */
	public String[] getArgs() {
		return args;
	}
	
	/**
	 * Choisis un effet dans le cas de plusieurs effet possible
	 * @param str
	 * @return
	 */
	private static String choose(String str){
		List<String> possibles = new ArrayList<String>();
		String poss = "";
		
		for(int i = 0; i < str.length(); i++){
			if(str.charAt(i) == '|'){
				if(poss != "")
					possibles.add(poss);
				poss = "";
			}else
				poss += str.charAt(i);
		}
		
		if(poss != "")
			possibles.add(poss);
		
		if(possibles.size() > 0){
			Random rand = new Random();
			return possibles.get(rand.nextInt(possibles.size()));
		}
		
		return null;
	}

	/**
	 * Extrait un effet depuis une String
	 * 
	 * @param effect
	 * @return
	 */
	public static Engine extractEngine(String effect) {
		List<String> args = new ArrayList<String>();
		Engine engine = null;
		String arg = "";
		int i = 0;
		boolean ctn = true;

		while (i < effect.length() && ctn) {
			char c = effect.charAt(i);

			if (c == ';' || c == ' ') {
				ctn = c != ';';

				if (!arg.equals("")) {
					args.add(arg);
				}
				arg = "";
			} else
				arg += c;
			i++;
		}
		if (ctn)
			args.add(arg);

		if (args.size() > 3) {
			String[] cmdargs = new String[args.size() - 2];

			for (i = 3; i < args.size(); i++)
				cmdargs[i - 3] = choose(args.get(i));

			engine = new Engine(CardType.fromString(args.get(0)), Declancheur.fromString(args.get(1)), Target.fromString(args.get(2)), cmdargs);
		}
		return engine;
	}

	/**
	 * Extrait touts les effets d'une String
	 * 
	 * @param effects
	 * @return
	 */
	public static List<Engine> extractEffects(String effects) {
		List<Engine> eff = new ArrayList<Engine>();

		String nstr = effects;
		Engine eng = null;

		while ((eng = extractEngine(nstr)) != null) {
			eff.add(eng);

			int isep = nstr.indexOf(";");
			if (isep >= 0)
				nstr = nstr.substring(isep + 1);
			else
				nstr = "";
		}

		return eff;
	}

	public String toString() {
		String str = this.type + " " + this.decl + " " + this.qui;

		for (String s : this.args)
			str += " " + s;
		return str;
	}
}
