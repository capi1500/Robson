package robson.lang.operators;

import com.google.gson.annotations.SerializedName;
import robson.lang.base.Expresion;

import java.util.Objects;

public abstract class TwoArgumentOperator extends Expresion{
	@SerializedName(value = "argument1", alternate = {"arg1"})
	protected Expresion arg1;
	@SerializedName(value = "argument2", alternate = {"arg2"})
	protected Expresion arg2;
	
	@Override
	public boolean equals(Object o){
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		if(!super.equals(o))
			return false;
		
		TwoArgumentOperator that = (TwoArgumentOperator)o;
		
		if(!Objects.equals(arg1, that.arg1))
			return false;
		return Objects.equals(arg2, that.arg2);
	}
	
	@Override
	public int hashCode(){
		int result = super.hashCode();
		result = 31 * result + (arg1 != null ? arg1.hashCode() : 0);
		result = 31 * result + (arg2 != null ? arg2.hashCode() : 0);
		return result;
	}
}
