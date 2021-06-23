package robson.lang.base;

import com.google.gson.annotations.SerializedName;
import robson.lang.environment.Scope;

import java.util.Objects;

public abstract class Expresion{
	@SerializedName(value = "typ", alternate = {"type"})
	private String type;
	
	public abstract Value calculate(Scope scope) throws RuntimeException;
	
	@Override
	public boolean equals(Object o){
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		
		Expresion expresion = (Expresion)o;
		
		return Objects.equals(type, expresion.type);
	}
	
	@Override
	public int hashCode(){
		return type != null ? type.hashCode() : 0;
	}
	
	public abstract String prettyPrint(String prefix);
}
