package robson.lang.base;

import com.google.gson.annotations.SerializedName;
import robson.lang.environment.Scope;

import java.util.Objects;

public class Value extends Expresion{
	@SerializedName(value = "wartosc", alternate = {"value"})
	private Object value;
	
	@Override
	public Value calculate(Scope scope) throws RuntimeException{
		return this;
	}
	
	public Object getValue(){
		return value;
	}
	
	public Value(){
	}
	
	public Value(Object value){
		this.value = value;
	}
	
	@Override
	public boolean equals(Object o){
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		if(!super.equals(o))
			return false;
		
		Value value1 = (Value)o;
		
		return Objects.equals(value, value1.value);
	}
	
	@Override
	public int hashCode(){
		int result = super.hashCode();
		result = 31 * result + (value != null ? value.hashCode() : 0);
		return result;
	}
	
	@Override
	public String preetyPrint(String prefix){
		return value.toString();
	}
}
