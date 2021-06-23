package robson.lang.operators;

import utils.TypeCheck;

public abstract class ArithmeticOperator extends TwoArgumentOperator{
	protected abstract Number operation(Number e1, Number e2);
	
	@Override
	protected Number operation(Object e1, Object e2){
		TypeCheck.assertType(e1, Number.class);
		TypeCheck.assertType(e2, Number.class);
		return operation((Number)e1, (Number)e2);
	}
}
