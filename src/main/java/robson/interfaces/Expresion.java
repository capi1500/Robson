package robson.interfaces;

import robson.lang.base.Value;
import robson.lang.environment.Scope;

public interface Expresion{
	Value calculate(Scope scope) throws RuntimeException;
}
