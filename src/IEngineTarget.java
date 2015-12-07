
public interface IEngineTarget {
	public void eng_invoke(String name) throws Exception;
	
	public void eng_buff(int pv, int dmg) throws Exception;
	
	public void eng_deck(int nbr) throws Exception;
}
