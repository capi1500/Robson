package robson.lang.operators;

import robson.lang.base.Value;
import robson.lang.environment.Scope;
import utils.TypeCheck;

public class And extends BinaryOperator{
	@Override
	protected Boolean operation(Boolean e1, Boolean e2){
		return e1 && e2;
	}
	
	@Override
	public String prettyPrint(String prefix){
		return arg1.prettyPrint(prefix) + " & " + arg2.prettyPrint(prefix);
	}
}
