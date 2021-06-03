package robson.lang.operators;

import robson.lang.base.Value;
import robson.lang.environment.Scope;
import utils.type_traits.TypeCheck;

public class Modulo extends TwoArgumentOperator{
	@Override
	public Value calculate(Scope scope) throws RuntimeException{
		Object v1 = arg1.calculate(scope).getValue(), v2 = arg2.calculate(scope).getValue();
		TypeCheck.assertInteger(v1);
		TypeCheck.assertInteger(v2);
		return new Value(((Integer)v1) % ((Integer)v2));
	}
}
