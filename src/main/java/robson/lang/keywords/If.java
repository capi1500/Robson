package robson.lang.keywords;

import com.google.gson.annotations.SerializedName;
import robson.lang.base.Expresion;
import robson.lang.base.Value;
import robson.lang.environment.Scope;
import utils.TypeCheck;

import java.util.Objects;

public class If extends Expresion{
	@SerializedName(value = "warunek", alternate = {"predicate"})
	private Expresion predicate;
	@SerializedName(value = "blok_prawda", alternate = {"expresion_true"})
	private Expresion onTrue;
	@SerializedName(value = "blok_falsz", alternate = {"expresion_false"})
	private Expresion onFalse;
	
	@Override
	public Value calculate(Scope scope) throws RuntimeException{
		Object predicateValue = predicate.calculate(scope).getValue();
		TypeCheck.assertType(predicateValue, Boolean.class);
		
		if((Boolean)predicateValue)
			return onTrue.calculate(new Scope(scope));
		return onFalse.calculate(new Scope(scope));
	}
	
	@Override
	public boolean equals(Object o){
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		if(!super.equals(o))
			return false;
		
		If anIf = (If)o;
		
		if(!Objects.equals(predicate, anIf.predicate))
			return false;
		if(!Objects.equals(onTrue, anIf.onTrue))
			return false;
		return Objects.equals(onFalse, anIf.onFalse);
	}
	
	@Override
	public int hashCode(){
		int result = super.hashCode();
		result = 31 * result + (predicate != null ? predicate.hashCode() : 0);
		result = 31 * result + (onTrue != null ? onTrue.hashCode() : 0);
		result = 31 * result + (onFalse != null ? onFalse.hashCode() : 0);
		return result;
	}
	
	@Override
	public String preetyPrint(String indent){
		StringBuilder out = new StringBuilder();
		out.append("if(");
		out.append(predicate.preetyPrint(indent + "    "));
		out.append(")");
		if(onTrue.getClass() != Block.class)
			out.append("\n" + indent + "    " + onTrue.preetyPrint(indent + "    "));
		else 
			out.append(onTrue.preetyPrint(indent));
		out.append("\n" + indent + "else");
		if(onTrue.getClass() != Block.class)
			out.append("\n" + indent + "    " + onFalse.preetyPrint(indent + "    "));
		else
			out.append(onFalse.preetyPrint(indent));
		return out.toString();
	}
}
