
/**
 * Cibles possible pour un effet
 */
public enum EngineTarget {
	ALL		(0),
	PLAYER	(1),
	ADV		(2);
	
	private int val;
	
	private EngineTarget(int val){
		this.val = val;
	}
	
	public int getVal(){
		return val;
	}
	
	public static EngineTarget fromString(String s){
		int v = Integer.parseInt(s);
		for(EngineTarget t : EngineTarget.values())
			if(t.val == v)
				return t;
		return null;
	}
}
