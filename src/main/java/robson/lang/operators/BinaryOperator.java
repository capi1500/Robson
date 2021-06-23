package robson.lang.operators;

import utils.TypeCheck;

public abstract class BinaryOperator extends TwoArgumentOperator{
	protected abstract Boolean operation(Boolean e1, Boolean e2);
	
	@Override
	protected Boolean operation(Object e1, Object e2){
		TypeCheck.assertType(e1, Boolean.class);
		TypeCheck.assertType(e2, Boolean.class);
		return operation((Boolean)e1, (Boolean)e2);
	}
}
