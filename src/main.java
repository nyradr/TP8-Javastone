
public class main {
	public static void main(String [] a){
		GamePanel panel = new GamePanel();
		
		int i = panel.menu("title", new String[]{"it1", "it2"});
		
		System.out.println("----\n" + i);
		
		
	}
}
