/**
 * Type possible d'une care
 */
public enum CardType {
	INVOKE	(0),
	BUFF	(1),
	DAMAGE	(2),
	DECK	(3);
	
	private int val;
	
	private CardType(int v){
		val = v;
	}
	
	public int getVal(){
		return val;
	}
	
	public static CardType fromString(String s){
		int v = Integer.parseInt(s);
		
		for(CardType t : CardType.values())
			if(t.val == v)
				return t;
		return null;
	}
}
