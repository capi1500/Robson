package robson.lang.keywords;

import com.google.gson.annotations.SerializedName;
import robson.lang.base.Expresion;
import robson.lang.base.Value;
import robson.lang.environment.Scope;

import java.util.Objects;

public class Assignment extends Expresion{
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
	public boolean equals(Object o){
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		if(!super.equals(o))
			return false;
		
		Assignment that = (Assignment)o;
		
		if(local != that.local)
			return false;
		if(!Objects.equals(name, that.name))
			return false;
		return Objects.equals(value, that.value);
	}
	
	@Override
	public int hashCode(){
		int result = super.hashCode();
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (value != null ? value.hashCode() : 0);
		result = 31 * result + (local ? 1 : 0);
		return result;
	}
	
	@Override
	public String preetyPrint(String prefix){
		StringBuilder stringBuilder = new StringBuilder();
		if(local)
			stringBuilder.append("local ");
		stringBuilder.append(name);
		stringBuilder.append(" = ");
		stringBuilder.append(value.preetyPrint(prefix));
		return stringBuilder.toString();
	}
}
