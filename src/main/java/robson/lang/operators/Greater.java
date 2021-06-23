package robson.lang.operators;

import utils.NumericalOperations;

public class Greater extends RelationalOperator{
	@Override
	protected Boolean operation(Number e1, Number e2){
		return NumericalOperations.greater(e1, e2);
	}
	
	@Override
	public String prettyPrint(String prefix){
		return arg1.prettyPrint(prefix) + " > " + arg2.prettyPrint(prefix);
	}
}
