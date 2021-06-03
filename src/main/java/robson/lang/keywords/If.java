package robson.lang.keywords;

import com.google.gson.annotations.SerializedName;
import robson.interfaces.Expresion;
import robson.lang.base.Value;
import robson.lang.environment.Scope;
import utils.type_traits.TypeCheck;

public class If implements Expresion{
	@SerializedName(value = "warunek", alternate = {"predicate"})
	private Expresion predicate;
	@SerializedName(value = "blok_prawda", alternate = {"expresion_true"})
	private Expresion onTrue;
	@SerializedName(value = "blok_falsz", alternate = {"expresion_false"})
	private Expresion onFalse;
	
	@Override
	public Value calculate(Scope scope) throws RuntimeException{
		Object predicateValue = predicate.calculate(scope).getValue();
		TypeCheck.assertBoolean(predicateValue);
		Scope nestedScope = new Scope(scope);
		if((Boolean)predicateValue)
			return onTrue.calculate(nestedScope);
		return onFalse.calculate(nestedScope);
	}
	
	@Override
	public String toString(){
		return "If{" + "predicate=" + predicate + ", onTrue=" + onTrue + ", onFalse=" + onFalse + '}';
	}
}
