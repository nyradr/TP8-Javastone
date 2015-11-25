

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Lecteur de fichier
 *
 */
public class FileReader {
	public static final String FOLD_CREA = "crea";		//dossier pour les creatures
	public static final String FOLD_CART = "carte";		//dossier pour les cartes
	public static final String FOLD_DECK = "deck";		//dossier pour les decks
	
	/**
	 * Transforme une ArrayList de Strings en un tableau de Strings
	 * @param list ArrayList de Strings
	 * @return tableau de Strings
	 */
	private static String[] toArray(ArrayList<String> list){
		String[] lstr = new String[list.size()];
		
		for(int i = 0; i < list.size(); i++)
			lstr[i] = list.get(i);
		
		return lstr;
	}
	
	/**
	 * Lit un fichier situer dans un dossier d'extension le nom du dossier
	 * Lit toutes les lignes de ce fichier
	 * @param folder dossier
	 * @param name nom du fichier
	 * @return lignes lues
	 */
	public static String[] loadFile(String folder, String name){
		ArrayList<String> lines = new ArrayList<String>();
		
		File file = new File(folder + File.separator + name + "." + folder);
		try{
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			BufferedReader br = new BufferedReader(new InputStreamReader(bis));
			
			String line = null;
			
			while((line = br.readLine()) != null){
				lines.add(line);
			}
			return toArray(lines);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public static void main(String[] a){
		String[] t = loadFile("crea", "crea");
		
		if(t == null)
			return;
		
		for(String s : t)
			System.out.println(s);
	}
}
