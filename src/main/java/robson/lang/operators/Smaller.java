package robson.lang.operators;

import robson.lang.base.Value;
import robson.lang.environment.Scope;
import utils.type_traits.TypeCheck;

public class Smaller extends TwoArgumentOperator{
	@Override
	public Value calculate(Scope scope) throws RuntimeException{
		Object v1 = arg1.calculate(scope).getValue(), v2 = arg2.calculate(scope).getValue();
		TypeCheck.assertNumerical(v1);
		TypeCheck.assertNumerical(v2);
		if(TypeCheck.isInteger(v1) && TypeCheck.isInteger(v2))
			return new Value(((Integer)v1) < ((Integer)v2));
		return new Value(((Double)v1) < ((Double)v2));
	}
	
	@Override
	public String toString(){
		return "Smaller{" + "arg1=" + arg1 + ", arg2=" + arg2 + '}';
	}
}
