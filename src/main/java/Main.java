import robson.Robson;
import robson.exceptions.BladWykonania;
import robson.exceptions.NieprawidlowyProgram;

public class Main {
	public static void main(String[] args){
		Robson script = new Robson();
		try{
			script.fromJson("src/main/resources/fib.json");
			System.out.println(script.wykonaj("Fibonacci", 5));
			System.out.println(script.wykonaj("Fibonacci", 8));
			System.out.println(script.wykonaj("Fibonacci", 3));
			System.out.println(script.wykonaj("Fibonacci", 5));
			System.out.println(script.wykonaj("Fibonacci", 8));
			System.out.println(script.wykonaj("Fibonacci", 3));
			
			script.fromJson("src/main/resources/table.json");
			for(int i = 0; i < 5; i++)
				System.out.println(script.wykonaj("get", i));
		}
		catch(NieprawidlowyProgram | BladWykonania e){
			e.printStackTrace();
		}
	}
}
