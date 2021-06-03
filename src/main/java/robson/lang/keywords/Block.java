package robson.lang.keywords;

import com.google.gson.annotations.SerializedName;
import robson.lang.base.Value;
import robson.lang.environment.Scope;
import robson.interfaces.Expresion;

import java.util.Arrays;

public class Block implements Expresion{
	@SerializedName(value = "instrukcje", alternate = {"instructions"})
	private Expresion[] instructions;
	
	@Override
	public Value calculate(Scope scope) throws RuntimeException{
		Value value = new Value(new Integer(0));
		for(Expresion i : instructions){
			value = i.calculate(scope);
		}
		return value;
	}
	
	@Override
	public String toString(){
		return "Block{" + "instructions=" + Arrays.toString(instructions) + '}';
	}
}
