package robson.lang.keywords;

import robson.lang.base.Expresion;
import robson.lang.base.Value;
import robson.lang.environment.Scope;

public class True extends Expresion{
	@Override
	public Value calculate(Scope scope) throws RuntimeException{
		return new Value(Boolean.TRUE);
	}
	
	@Override
	public String prettyPrint(String indent){
		return "True";
	}
}
