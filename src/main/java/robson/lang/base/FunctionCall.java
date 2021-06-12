package robson.lang.base;

import com.google.gson.annotations.SerializedName;
import robson.lang.environment.Scope;

import java.util.Arrays;

public class FunctionCall implements Expresion{
	@SerializedName(value = "nazwa", alternate = {"name"})
	private String name;
	@SerializedName(value = "argumenty", alternate = {"args"})
	private Expresion[] arguments;
	
	@Override
	public Value calculate(Scope scope) throws RuntimeException{
		return scope.getFunction(name).call(scope, arguments);
	}
	
	@Override
	public String toString(){
		return "FunctionCall{" + "name='" + name + '\'' + ", arguments=" + Arrays.toString(arguments) + '}';
	}
}
