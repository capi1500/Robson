package robson.lang.operators;

import utils.NumericalOperations;

public class Multiply extends ArithmeticOperator{
	@Override
	protected Number operation(Number e1, Number e2){
		return NumericalOperations.multiply(e1, e2);
	}
	
	@Override
	public String toString(){
		return "Multiply{" + "arg1=" + arg1 + ", arg2=" + arg2 + '}';
	}
}
