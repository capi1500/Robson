package robson.lang.operators;

import utils.NumericalOperations;

public class Divide extends ArithmeticOperator{
	@Override
	protected Number operation(Number e1, Number e2){
		return NumericalOperations.divide(e1, e2);
	}
	
	@Override
	public String toString(){
		return "Divide{" + "arg1=" + arg1 + ", arg2=" + arg2 + '}';
	}
}
