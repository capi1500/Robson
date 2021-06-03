package robson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import robson.lang.base.Value;
import robson.lang.environment.GlobalScope;
import robson.lang.environment.Scope;
import robson.exceptions.BladWykonania;
import robson.exceptions.NieprawidlowyProgram;
import robson.interfaces.Expresion;
import robson.loader.Deserializer;
import robson.loader.ValueDeserializer;
import utils.type_traits.TypeCheck;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;

public class Robson{
	private Expresion script;
	private final GlobalScope globalScope = new GlobalScope();
	
	public void fromJson(String filename) throws NieprawidlowyProgram{
		Gson gson = new GsonBuilder()
							.registerTypeAdapter(Expresion.class, new Deserializer())
							.registerTypeAdapter(Value.class, new ValueDeserializer())
							.create();
		
		try{
			script = gson.fromJson(new FileReader(filename), Expresion.class);
		}
		catch(FileNotFoundException | JsonIOException | JsonSyntaxException e){
			throw new NieprawidlowyProgram(e);
		}
		
		System.out.println(script.toString());
		
		try{
			Scope scope = new Scope(globalScope);
			script.calculate(scope);
		} catch(RuntimeException e){
			throw new NieprawidlowyProgram(e);
		}
	}
	
	public void toJson(String filename){
		Gson gson = new GsonBuilder()
							.setPrettyPrinting()
							.serializeNulls()
							.registerTypeAdapter(Expresion.class, new Deserializer())
							.create();
		
		try{
			PrintStream file = new PrintStream(filename);
			file.println(gson.toJson(script));
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public void toJava(String filename){
		
	}
	
	public double wykonaj() throws BladWykonania{
		Scope scope = new Scope(globalScope);
		try{
			Object out = script.calculate(scope).getValue();
			if(TypeCheck.isDouble(out))
				return (double)out;
			return ((Integer)out).doubleValue();
		} catch(RuntimeException e){
			throw new BladWykonania(e);
		}
	}
	
	public Object wykonaj(String name, Object... args) throws BladWykonania{
		Scope scope = new Scope(globalScope);
		try{
			Expresion[] exprArgs = new Expresion[args.length];
			for(int i = 0; i < args.length; i++)
				exprArgs[i] = new Value(args[i]);
			
			return scope.getFunction(name).call(scope, exprArgs);
		} catch(RuntimeException e){
			throw new BladWykonania(e);
		}
	}
}
