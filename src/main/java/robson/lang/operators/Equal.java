package robson.lang.operators;

import robson.lang.base.Value;
import robson.lang.environment.Scope;

public class Equal extends TwoArgumentOperator{
	@Override
	public Value calculate(Scope scope) throws RuntimeException{
		return new Value(arg1.calculate(scope).equals(arg2.calculate(scope)));
	}
	
	@Override
	public String preetyPrint(String prefix){
		return arg1.preetyPrint(prefix) + " == " + arg2.preetyPrint(prefix);
	}
}
