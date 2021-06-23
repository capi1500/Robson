package robson;

import com.google.gson.*;
import robson.lang.base.Value;
import robson.lang.environment.GlobalScope;
import robson.lang.environment.Scope;
import robson.exceptions.BladWykonania;
import robson.exceptions.NieprawidlowyProgram;
import robson.lang.base.Expresion;
import robson.loader.*;
import utils.TypeCheck;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Objects;

public class Robson{
	private Expresion script;
	private final GlobalScope globalScope = new GlobalScope();
	
	public void fromJson(String filename) throws NieprawidlowyProgram{
		Gson gson = new GsonBuilder()
							.registerTypeAdapter(Expresion.class, new ExpresionDeserializer())
							.create();
		
		try{
			FileReader reader = new FileReader(filename);
			script = gson.fromJson(reader, Expresion.class);
			reader.close();
		}
		catch(IOException | JsonIOException | JsonSyntaxException e){
			throw new NieprawidlowyProgram(e);
		}
		
		try{
			globalScope.clear();
			
			Scope scope = new Scope(globalScope);
			script.calculate(scope);
		} catch(RuntimeException e){
			throw new NieprawidlowyProgram(e);
		}
	}
	
	public void toJson(String filename){
		Gson gson = new GsonBuilder()
							.setPrettyPrinting()
							.create();
		try{
			PrintStream file = new PrintStream(filename);
			file.println(gson.toJson(script));
			file.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public void toJava(String filename){
		try{
			Gson gson = new GsonBuilder().create();
			
			String tmp = gson.toJson(script);
			StringBuilder program = new StringBuilder();
			
			tmp.chars().forEach(c -> {
				if(c == '"'){
					program.append('\\');
					program.append('"');
				}
				else 
					program.append((char)c);
			});
			
			
			PrintStream file = new PrintStream(filename);
			file.println("import robson.Robson;\n" +
						 "import robson.exceptions.BladWykonania;\n" +
						 "import robson.exceptions.NieprawidlowyProgram;\n" +
						 "\n" +
						 "import java.io.IOException;\n" +
						 "import java.io.PrintStream;\n" +
						 "import java.nio.file.Files;\n" +
						 "import java.nio.file.Path;\n" + 
						 "\n" + 
						 "public class Main{\n" + 
						 "\tpublic static void main(String[] args){\n" + 
						 "\t\ttry{\n" + 
						 "\t\t\tString program = \"" + new String(program) + "\";\n" + 
						 "\t\t\tPrintStream printer = new PrintStream(\"tmp.json\");\n" + 
						 "\t\t\tprinter.println(program);\n" + 
						 "\t\t\tprinter.close();\n" + 
						 "\t\t\t\n" + 
						 "\t\t\tRobson script = new Robson();\n" + 
						 "\t\t\tscript.fromJson(\"tmp.json\");\n" + 
						 "\t\t\tSystem.out.println(script.wykonaj());\n" + 
						 "\t\t\t\n" + 
						 "\t\t\tFiles.delete(Path.of(\"tmp.json\"));\n" + 
						 "\t\t} catch(IOException | NieprawidlowyProgram | BladWykonania e){\n" + 
						 "\t\t\te.printStackTrace();\n" + 
						 "\t\t}\n" + 
						 "\t}\n" + 
						 "}\n"
			);
			file.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public double wykonaj() throws BladWykonania{
		Scope scope = new Scope(globalScope);
		try{
			Object out = script.calculate(scope).getValue();
			TypeCheck.assertType(out, Number.class);
			return ((Number)out).doubleValue();
		}
		catch(RuntimeException e){
			throw new BladWykonania(e);
		}
	}
	
	public Object wykonaj(String name, Number... args) throws BladWykonania{
		Scope scope = new Scope(globalScope);
		try{
			Expresion[] exprArgs = new Expresion[args.length];
			for(int i = 0; i < args.length; i++)
				exprArgs[i] = new Value(args[i]);
			
			return scope.getFunction(name).call(scope, exprArgs).getValue();
		}
		catch(RuntimeException e){
			throw new BladWykonania(e);
		}
	}
	
	@Override
	public boolean equals(Object o){
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		
		Robson robson = (Robson)o;
		
		return Objects.equals(script, robson.script);
	}
	
	@Override
	public int hashCode(){
		return script != null ? script.hashCode() : 0;
	}
	
	@Override
	public String toString(){
		return script.prettyPrint("");
	}
}
