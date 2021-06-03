package robson.lang.operators;

import robson.lang.base.Value;
import robson.lang.environment.Scope;
import utils.type_traits.TypeCheck;

public class And extends TwoArgumentOperator{
	@Override
	public Value calculate(Scope scope) throws RuntimeException{
		Object v1 = arg1.calculate(scope).getValue(), v2 = arg2.calculate(scope).getValue();
		TypeCheck.assertBoolean(v1);
		TypeCheck.assertBoolean(v2);
		return new Value(((Boolean)v1) && ((Boolean)v2));
	}
	
	@Override
	public String toString(){
		return "And{" + "arg1=" + arg1 + ", arg2=" + arg2 + '}';
	}
}
