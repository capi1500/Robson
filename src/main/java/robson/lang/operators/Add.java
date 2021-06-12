package robson.lang.operators;

import utils.NumericalOperations;

public class Add extends ArithmeticOperator{
	@Override
	protected Number operation(Number e1, Number e2){
		return NumericalOperations.add(e1, e2);
	}
	
	@Override
	public String toString(){
		return "Addition{" + "arg1=" + arg1 + ", arg2=" + arg2 + '}';
	}
}
