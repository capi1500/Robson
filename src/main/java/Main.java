import robson.Robson;
import robson.exceptions.BladWykonania;
import robson.exceptions.NieprawidlowyProgram;

public class Main{
	public static void main(String[] args){
		try{
			Robson script = new Robson();
			
			script.fromJson("src/main/resources/recurency.json");
			System.out.println(script);
			for(int i = 0; i <= 11; i++)
				System.out.println(script.wykonaj("szybkie_potegowanie", 2, i));
		} catch(NieprawidlowyProgram | BladWykonania e){
			e.printStackTrace();
		} catch(StackOverflowError e){
			System.err.println("Stack Overflow");
		}
	}
}
