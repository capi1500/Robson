package robson.lang.operators;

import utils.NumericalOperations;

public class Subtract extends ArithmeticOperator{
	@Override
	protected Number operation(Number e1, Number e2){
		return NumericalOperations.subtract(e1, e2);
	}
	
	@Override
	public String prettyPrint(String prefix){
		return "(" + arg1.prettyPrint(prefix) + " - " + arg2.prettyPrint(prefix) + ")";
	}
}
