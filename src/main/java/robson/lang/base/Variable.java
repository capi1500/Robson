package robson.lang.base;

import com.google.gson.annotations.SerializedName;
import robson.lang.environment.Scope;

import java.util.Objects;

public class Variable extends Expresion{
	@SerializedName(value = "nazwa", alternate = {"name"})
	private String name;
	
	@Override
	public Value calculate(Scope scope) throws RuntimeException{
		return scope.getVariable(name);
	}
	
	@Override
	public boolean equals(Object o){
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		if(!super.equals(o))
			return false;
		
		Variable variable = (Variable)o;
		
		return Objects.equals(name, variable.name);
	}
	
	@Override
	public int hashCode(){
		int result = super.hashCode();
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}
}
