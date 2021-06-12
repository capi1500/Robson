package robson.lang.operators;

import robson.lang.base.Value;
import robson.lang.environment.Scope;
import utils.TypeCheck;

public class Not extends OneArgumentOperator{
	@Override
	public Value calculate(Scope scope) throws RuntimeException{
		Object v = arg.calculate(scope).getValue();
		TypeCheck.assertType(v, Boolean.class);
		return new Value(!((Boolean)v));
	}
	
	@Override
	public String toString(){
		return "Not{" + "arg=" + arg + '}';
	}
}
