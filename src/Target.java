
/**
 * Cibles possible pour un effet
 */
public enum Target {
	ALLPLAYER		(0),
	PLAYER			(1),
	ADV				(2),
	ALLCREA			(3),
	PLAYERCREA		(4),
	ADVCREA			(5),
	ALL				(6),
	CHOSEALL		(7),
	CHOSEPLAYER		(8),
	CHOSEADV		(9);
	
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
