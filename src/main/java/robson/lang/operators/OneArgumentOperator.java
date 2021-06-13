package robson.lang.operators;

import com.google.gson.annotations.SerializedName;
import robson.lang.base.Expresion;

import java.util.Objects;

public abstract class OneArgumentOperator extends Expresion{
	@SerializedName(value = "argument", alternate = {"arg"})
	protected Expresion arg;
	
	@Override
	public boolean equals(Object o){
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		if(!super.equals(o))
			return false;
		
		OneArgumentOperator that = (OneArgumentOperator)o;
		
		return Objects.equals(arg, that.arg);
	}
	
	@Override
	public int hashCode(){
		int result = super.hashCode();
		result = 31 * result + (arg != null ? arg.hashCode() : 0);
		return result;
	}
}
