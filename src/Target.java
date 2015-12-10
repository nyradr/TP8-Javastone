
/**
 * Cibles possible pour un effet
 */
public enum Target {
	ALLPLAYER		(0),	// cible tous les joueurs
	PLAYER			(1),	// cible le joueur	
	ADV				(2),	// cible l'adversaire
	ALLCREA			(3),	// cible toutes les creatures
	PLAYERCREA		(4),	// cible les creatures du joueur
	ADVCREA			(5),	// cible les creatures de l'adversaire
	ALL				(6),	// cible toutes les creature set les joueurs
	CHOSEALL		(7),	// choisis une creature ou un joueur
	CHOSEPLAYER		(8),	// chosis parmis les creatures du joueur
	CHOSEADV		(9),	// choisis parmis les creatures de l'adversaire
	CREAADVMAXDMG	(10),	// cible la creature adversaire avec le plus de pv
	SELF			(11);	// cible la creature à l'origine de l'effet
	
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
