package robson.lang.operators;

import utils.NumericalOperations;

public class SmallerEqual extends RelationalOperator{
	@Override
	protected Boolean operation(Number e1, Number e2){
		return NumericalOperations.smallerEqual(e1, e2);
	}
	
	@Override
	public String toString(){
		return "LessEqual{" + "arg1=" + arg1 + ", arg2=" + arg2 + '}';
	}
}
