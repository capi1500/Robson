package robson.lang.base;

import robson.lang.base.Value;
import robson.lang.environment.Scope;

public interface Expresion{
	Value calculate(Scope scope) throws RuntimeException;
}
