package robson.lang.operators;

import robson.lang.base.Value;
import robson.lang.environment.Scope;
import utils.type_traits.TypeCheck;

public class Not extends OneArgumentOperator{
	@Override
	public Value calculate(Scope scope) throws RuntimeException{
		Object v = arg.calculate(scope).getValue();
		TypeCheck.assertBoolean(v);
		return new Value(!((Boolean)v));
	}
	
	@Override
	public String toString(){
		return "Not{" + "arg=" + arg + '}';
	}
}
