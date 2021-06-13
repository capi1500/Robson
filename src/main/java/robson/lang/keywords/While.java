package robson.lang.keywords;

import com.google.gson.annotations.SerializedName;
import robson.lang.base.Expresion;
import robson.lang.base.Value;
import robson.lang.environment.Scope;
import utils.TypeCheck;

import java.util.Objects;

public class While extends Expresion{
	@SerializedName(value = "warunek", alternate = {"predicate"})
	private Expresion predicate;
	@SerializedName(value = "blok", alternate = {"expresion"})
	private Expresion expresion;
	
	@Override
	public Value calculate(Scope scope) throws RuntimeException{
		Scope nestedScope = new Scope(scope);
		while(true){
			Object predicateValue = predicate.calculate(scope).getValue();
			TypeCheck.assertType(predicateValue, Boolean.class);
			if(!((Boolean)predicateValue))
				break;
			expresion.calculate(nestedScope);
		}
		return new Value(0);
	}
	
	@Override
	public boolean equals(Object o){
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		if(!super.equals(o))
			return false;
		
		While aWhile = (While)o;
		
		if(!Objects.equals(predicate, aWhile.predicate))
			return false;
		return Objects.equals(expresion, aWhile.expresion);
	}
	
	@Override
	public int hashCode(){
		int result = super.hashCode();
		result = 31 * result + (predicate != null ? predicate.hashCode() : 0);
		result = 31 * result + (expresion != null ? expresion.hashCode() : 0);
		return result;
	}
}
