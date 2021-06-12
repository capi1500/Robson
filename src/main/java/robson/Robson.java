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
import java.io.PrintStream;

public class Robson{
	private Expresion script;
	private final GlobalScope globalScope = new GlobalScope();
	
	public void fromJson(String filename) throws NieprawidlowyProgram{
		Gson gson = new GsonBuilder()
							.registerTypeAdapter(Expresion.class, new ExpresionDeserializer())
							.create();
		
		try{
			script = gson.fromJson(new FileReader(filename), Expresion.class);
		}
		catch(FileNotFoundException | JsonIOException | JsonSyntaxException e){
			throw new NieprawidlowyProgram(e);
		}
		
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
}
