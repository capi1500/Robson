package robson.lang.operators;

import robson.lang.base.Value;
import robson.lang.environment.Scope;
import utils.TypeCheck;

public abstract class ArithmeticOperator extends TwoArgumentOperator{
	protected abstract Number operation(Number e1, Number e2);
	
	@Override
	public Value calculate(Scope scope) throws RuntimeException{
		Value v1 = arg1.calculate(scope);
		Value v2 = arg2.calculate(scope);
		TypeCheck.assertType(v1.getValue(), Number.class);
		TypeCheck.assertType(v2.getValue(), Number.class);
		return new Value(operation((Number)v1.getValue(), (Number)v2.getValue()));
	}
}
