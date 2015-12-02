
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Lecteur de fichier
 *
 */
public class FileReader {
	public static final String FOLD_CREA = "crea"; // dossier pour les creatures
	public static final String FOLD_CART = "carte"; // dossier pour les cartes
	public static final String FOLD_DECK = "deck"; // dossier pour les decks

	/**
	 * Transforme une ArrayList de Strings en un tableau de Strings
	 * 
	 * @param list
	 *            ArrayList de Strings
	 * @return tableau de Strings
	 */
	private static String[] toArray(ArrayList<String> list) {
		String[] lstr = new String[list.size()];

		for (int i = 0; i < list.size(); i++)
			lstr[i] = list.get(i);

		return lstr;
	}

	/**
	 * Lit un fichier situer dans un dossier d'extension le nom du dossier Lit
	 * toutes les lignes de ce fichier
	 * 
	 * @param folder
	 *            dossier
	 * @param name
	 *            nom du fichier
	 * @return lignes lues
	 */
	public static String[] loadFile(String folder, String name) throws Exception {
		ArrayList<String> lines = new ArrayList<String>();

		File file = new File(folder + File.separator + name + "." + folder);
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		BufferedReader br = new BufferedReader(new InputStreamReader(bis));

		String line = null;

		while ((line = br.readLine()) != null) {
			lines.add(line);
		}

		br.close();

		return toArray(lines);
	}

	/**
	 * Liste les fichiers d'un dossier
	 * 
	 * @param folder
	 * @return
	 */
	public static String[] listFiles(String folder) {
		File dir = new File(folder);

		List<String> files = new ArrayList<String>();

		for (String s : dir.list()) {
			if (s.length() > 0)
				if (s.charAt(s.length() - 1) != '~') { // supression des
														// doublons et de
														// l'extension
					s = s.substring(0, s.indexOf('.'));
					files.add(s);
				}
		}

		return files.toArray(new String[0]);
	}
}
