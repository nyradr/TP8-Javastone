
public interface IEngineTarget {
	/**
	 * Effet d'invocation
	 * @param name nom de la carte à invoquer
	 * @throws Exception
	 */
	public Creature eng_invoke(String name, String carteFileName) throws Exception;
	
	/**
	 * Applique un buff
	 * @param pv buff en pv, ou dommage si negatif
	 * @param dmg buff de dommages
	 * @throws Exception
	 */
	public void eng_buff(int pv, int dmg) throws Exception;
	
	/**
	 * Pioche une carte dans le deck<br>
	 * Si negatif on pioche le nombre de creature ennemis
	 * @param nbr
	 * @throws Exception
	 */
	public void eng_deck(int nbr) throws Exception;
	
	/**
	 * Renvois la creature dans son deck
	 * @param c creature à renvoyer
	 * @throws Exception
	 */
	public String eng_renv() throws Exception;
}
