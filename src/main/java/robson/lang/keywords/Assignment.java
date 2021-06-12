package robson.lang.keywords;

import com.google.gson.annotations.SerializedName;
import robson.lang.base.Expresion;
import robson.lang.base.Value;
import robson.lang.environment.Scope;

public class Assignment implements Expresion{
	@SerializedName(value = "nazwa", alternate = {"name"})
	private String name;
	@SerializedName(value = "wartosc", alternate = {"value"})
	private Expresion value;
	@SerializedName(value = "lokalna", alternate = {"local"})
	private boolean local = false;
	
	@Override
	public Value calculate(Scope scope) throws RuntimeException{
		Value out = value.calculate(scope);
		
		if(local)
			scope.add(name, out);
		else
			scope.getGlobalScope().add(name, out);
		
		return out;
	}
	
	@Override
	public String toString(){
		return "Assignment{" + "name='" + name + '\'' + ", value=" + value + ", local=" + local + '}';
	}
}
