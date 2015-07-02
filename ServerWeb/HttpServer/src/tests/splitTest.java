package tests;

public class splitTest {

	public static void main(String[] args) {
		
		String string = "ciao.come.stai??";
		String[] stringParts = string.split("\\.");
		for (String string2 : stringParts) {
			System.out.println(string2);
		}
		
	}
	
}
