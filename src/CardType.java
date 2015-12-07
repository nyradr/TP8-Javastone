import java.util.Random;

/**
 * Type possible d'une care
 */
public enum CardType {
	INVOKE	(0), // code pour l'invocation d'une creature
	BUFF	(1), // applique un buffer à une creature
	DAMAGE	(2), // applique un domage à une creature
	DECK	(3); // fait piocher dans le deck

	private int val;

	private CardType(int v) {
		val = v;
	}

	public int getVal() {
		return val;
	}

	public static CardType fromString(String s) {
		int v = Integer.parseInt(s);
		Random rand = new Random();

		for (CardType t : CardType.values())
			if (t.val == v)
				return t;
		return null;
	}
}
