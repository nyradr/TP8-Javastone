
/**
 * Cibles possible pour un effet
 */
public enum EngineTarget {
	ALL(0), // cible tous les joueurs
	PLAYER(1), // cible le joueur courant
	ADV(2); // cible l'adversaire

	private int val;

	private EngineTarget(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}

	public static EngineTarget fromString(String s) {
		int v = Integer.parseInt(s);
		for (EngineTarget t : EngineTarget.values())
			if (t.val == v)
				return t;
		return null;
	}
}
