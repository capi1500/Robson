package robson.lang.base;

import com.google.gson.annotations.SerializedName;
import robson.lang.environment.Scope;

import java.util.Arrays;
import java.util.Objects;

public class FunctionCall extends Expresion{
	@SerializedName(value = "nazwa", alternate = {"name"})
	private String name;
	@SerializedName(value = "argumenty", alternate = {"args"})
	private Expresion[] arguments;
	
	@Override
	public Value calculate(Scope scope) throws RuntimeException{
		return scope.getFunction(name).call(scope, arguments);
	}
	
	@Override
	public boolean equals(Object o){
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		if(!super.equals(o))
			return false;
		
		FunctionCall that = (FunctionCall)o;
		
		if(!Objects.equals(name, that.name))
			return false;
		// Probably incorrect - comparing Object[] arrays with Arrays.equals
		return Arrays.equals(arguments, that.arguments);
	}
	
	@Override
	public int hashCode(){
		int result = super.hashCode();
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + Arrays.hashCode(arguments);
		return result;
	}
}
