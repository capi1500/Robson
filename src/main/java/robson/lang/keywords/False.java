package robson.lang.keywords;

import robson.lang.base.Expresion;
import robson.lang.base.Value;
import robson.lang.environment.Scope;

public class False extends Expresion{
	@Override
	public Value calculate(Scope scope) throws RuntimeException{
		return new Value(Boolean.FALSE);
	}
	
	@Override
	public String prettyPrint(String indent){
		return "False";
	}
}
