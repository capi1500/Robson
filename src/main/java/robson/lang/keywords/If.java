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
		Scope nestedScope = new Scope(scope);
		if((Boolean)predicateValue)
			return onTrue.calculate(nestedScope);
		return onFalse.calculate(nestedScope);
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
}
