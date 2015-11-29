import java.util.List;

public class main {
	public static void main(String [] a)
	{
		List<Engine> engs = Engine.extractEffects("0 1 2; 0 2 2");
		
		System.out.println(engs.size());
		
		for(Engine e : engs)
			System.out.println(e.toString());
	}
}
