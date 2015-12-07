
public enum Declancheur {
	PLAY	(0),	//se déclanche quand la carte est jouer
	MODIF	(1),	//se déclanche lors d'une modification
	DEGATS	(2);	//se déclanche lors d'un degats reçu
	
	private int val;

	private Declancheur(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}

	public static Declancheur fromString(String s) {
		int v = Integer.parseInt(s);
		for (Declancheur t : Declancheur.values())
			if (t.val == v)
				return t;
		return null;
	}
}
