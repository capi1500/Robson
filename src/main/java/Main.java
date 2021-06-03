import robson.Robson;
import robson.exceptions.BladWykonania;
import robson.exceptions.NieprawidlowyProgram;

import java.io.File;

public class Main {
	public static void main(String[] args) {
		Robson script = new Robson();
		try{
			script.fromJson("src/main/resources/test.json");
			System.out.println(script.wykonaj());
			
			script.fromJson("src/main/resources/ang.json");
			System.out.println(script.wykonaj());
			
			//script.fromJson("src/main/resources/gcd.json");
			//System.out.println(script.wykonaj());
			
			script.fromJson("src/main/resources/test1.json");
			System.out.println(script.wykonaj());
			
			script.fromJson("src/main/resources/test2.json");
			System.out.println(script.wykonaj());
			
			script.fromJson("src/main/resources/fib.json");
			System.out.println(script.wykonaj("fibonaci", 5));
			System.out.println(script.wykonaj("fibonaci", 8));
			System.out.println(script.wykonaj("fibonaci", 3));
			System.out.println(script.wykonaj("fibonaci", 5));
			System.out.println(script.wykonaj("fibonaci", 8));
			System.out.println(script.wykonaj("fibonaci", 3));
			
			script.fromJson("src/main/resources/table.json");
			System.out.println(script.wykonaj("tablica"));
		}
		catch(NieprawidlowyProgram e){
			e.printStackTrace();
		}
		catch(BladWykonania e){
			e.printStackTrace();
		}
	}
}
