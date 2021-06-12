package robson.lang.operators;

import com.google.gson.annotations.SerializedName;
import robson.lang.base.Expresion;

public abstract class OneArgumentOperator implements Expresion{
	@SerializedName(value = "argument", alternate = {"arg"})
	protected Expresion arg;
}
