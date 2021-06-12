package robson.lang.keywords;

import robson.lang.base.Expresion;
import robson.lang.base.Value;
import robson.lang.environment.Scope;

public class False implements Expresion{
	@Override
	public Value calculate(Scope scope) throws RuntimeException{
		return new Value(new Boolean(false));
	}
	
	@Override
	public String toString(){
		return "False{}";
	}
}
