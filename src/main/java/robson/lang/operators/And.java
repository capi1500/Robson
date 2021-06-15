package robson.lang.operators;

import robson.lang.base.Value;
import robson.lang.environment.Scope;
import utils.TypeCheck;

public class And extends TwoArgumentOperator{
	@Override
	public Value calculate(Scope scope) throws RuntimeException{
		Object v1 = arg1.calculate(scope).getValue(), v2 = arg2.calculate(scope).getValue();
		TypeCheck.assertType(v1, Boolean.class);
		TypeCheck.assertType(v2, Boolean.class);
		return new Value(((Boolean)v1) && ((Boolean)v2));
	}
	
	@Override
	public String preetyPrint(String prefix){
		return arg1.preetyPrint(prefix) + " & " + arg2.preetyPrint(prefix);
	}
}
