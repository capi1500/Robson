package robson.lang.base;

import com.google.gson.annotations.SerializedName;
import robson.lang.environment.Scope;

import java.util.Arrays;

public class Function implements Expresion{
	@SerializedName(value = "nazwa", alternate = {"name"})
	private String name;
	@SerializedName(value = "argumenty", alternate = {"args"})
	private String[] argumentNames;
	@SerializedName(value = "instrukcja", alternate = {"instruction"})
	private Expresion body;
	@SerializedName(value = "lokalna", alternate = {"local"})
	private boolean local = false;
	
	transient private Scope baseScope;
	
	@Override
	public Value calculate(Scope scope) throws RuntimeException{
		
		if(local){
			scope.add(name, this);
			baseScope = new Scope(scope);
		}
		else{
			scope.getGlobalScope().add(name, this);
			baseScope = new Scope(scope.getGlobalScope());
		}
		
		return new Value(new Integer(0));
	}
	
	public Value call(Scope scope, Expresion... args) throws RuntimeException{
		if(argumentNames.length != args.length)
			throw new RuntimeException("Invalid function call: " + name + " requires " + argumentNames.length + " arguments, " + args.length + " provided");
		
		Scope callScope = new Scope(baseScope);
		
		for(int i = 0; i < args.length; i++)
			callScope.add(argumentNames[i], args[i].calculate(scope));
		
		return body.calculate(callScope);
	}
	
	@Override
	public String toString(){
		return "Function{" + "name='" + name + '\'' + ", argumentNames=" + Arrays.toString(argumentNames) + ", body=" + body + '}';
	}
}
