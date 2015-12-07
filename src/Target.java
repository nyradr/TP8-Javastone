
/**
 * Cibles possible pour un effet
 */
public enum Target {
	ALLPLAYER		(0), 	// cible tous les joueurs
	PLAYER			(1), 	// cible le joueur courant
	ADV				(2), 	// cible l'adversaire
	CREA			(3),	// cible une creature
	CREAPLAYER		(4),	// cible une creature du joueur
	CREAADV			(5),	// cible une creature de l'adversaire
	ALLCREA			(6),	// cible toutes les creatures
	ALLCREAPLAYER	(7),	// cible toutes les creatures du joueur
	ALLCREAADV		(8);	// cible toutes les creatures de l'adversaire
	
	private int val;

	private Target(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}

	public static Target fromString(String s) {
		int v = Integer.parseInt(s);
		for (Target t : Target.values())
			if (t.val == v)
				return t;
		return null;
	}
}
