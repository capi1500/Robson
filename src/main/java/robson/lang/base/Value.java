package robson.lang.base;

import com.google.gson.annotations.SerializedName;
import robson.lang.environment.Scope;

public class Value implements Expresion{
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
	public String toString(){
		return "Value{" + "value=" + value + '}';
	}
	
	@Override
	public boolean equals(Object o){
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		
		Value value1 = (Value)o;
		
		return value != null ? value.equals(value1.value) : value1.value == null;
	}
}
