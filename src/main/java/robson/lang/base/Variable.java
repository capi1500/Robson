package robson.lang.base;

import com.google.gson.annotations.SerializedName;
import robson.lang.environment.Scope;
import robson.interfaces.Expresion;

public class Variable implements Expresion{
	@SerializedName(value = "nazwa", alternate = {"name"})
	private String name;
	
	@Override
	public Value calculate(Scope scope) throws RuntimeException{
		return scope.getVariable(name);
	}
	
	@Override
	public String toString(){
		return "Variable{" + "name='" + name + "'}";
	}
}
