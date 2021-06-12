package robson.lang.keywords;

import com.google.gson.annotations.SerializedName;
import robson.lang.base.Expresion;
import robson.lang.base.Value;
import robson.lang.environment.Scope;
import utils.TypeCheck;

public class While implements Expresion{
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
			if((Boolean)predicateValue == false)
				break;
			expresion.calculate(nestedScope);
		}
		return new Value(0);
	}
	
	@Override
	public String toString(){
		return "While{" + "predicate=" + predicate + ", expresion=" + expresion + '}';
	}
}
