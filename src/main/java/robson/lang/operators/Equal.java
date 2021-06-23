package robson.lang.operators;

import utils.TypeCheck;

public class Equal extends TwoArgumentOperator{
	@Override
	protected Boolean operation(Object e1, Object e2){
		if(TypeCheck.isType(e1, Number.class) && TypeCheck.isType(e2, Number.class))
			return ((Number)e1).doubleValue() == ((Number)e2).doubleValue();
		return e1.equals(e2);
	}
	
	@Override
	public String prettyPrint(String prefix){
		return arg1.prettyPrint(prefix) + " == " + arg2.prettyPrint(prefix);
	}
}
