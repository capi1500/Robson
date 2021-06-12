package robson.lang.operators;

import com.google.gson.annotations.SerializedName;
import robson.lang.base.Expresion;

public abstract class TwoArgumentOperator implements Expresion{
	@SerializedName(value = "argument1", alternate = {"arg1"})
	protected Expresion arg1;
	@SerializedName(value = "argument2", alternate = {"arg2"})
	protected Expresion arg2;
}
