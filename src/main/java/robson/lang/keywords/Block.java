package robson.lang.keywords;

import com.google.gson.annotations.SerializedName;
import robson.lang.base.Value;
import robson.lang.environment.Scope;
import robson.lang.base.Expresion;

import java.util.Arrays;

public class Block extends Expresion{
	@SerializedName(value = "instrukcje", alternate = {"instructions"})
	private Expresion[] instructions;
	
	@Override
	public Value calculate(Scope scope) throws RuntimeException{
		Scope nestedScope = new Scope(scope);
		Value value = new Value(0);
		for(Expresion i : instructions){
			value = i.calculate(nestedScope);
		}
		return value;
	}
	
	@Override
	public boolean equals(Object o){
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		if(!super.equals(o))
			return false;
		
		Block block = (Block)o;
		
		// Probably incorrect - comparing Object[] arrays with Arrays.equals
		return Arrays.equals(instructions, block.instructions);
	}
	
	@Override
	public int hashCode(){
		int result = super.hashCode();
		result = 31 * result + Arrays.hashCode(instructions);
		return result;
	}
	
	@Override
	public String preetyPrint(String prefix){
		StringBuilder out = new StringBuilder();
		out.append("{\n");
		for(Expresion expresion : instructions){
			out.append(prefix + "    " + expresion.preetyPrint(prefix + "    ") + "\n");
		}
		out.append(prefix + "}");
		return out.toString();
	}
}
