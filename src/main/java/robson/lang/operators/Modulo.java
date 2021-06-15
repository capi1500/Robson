package robson.lang.operators;

import robson.lang.base.Value;
import robson.lang.environment.Scope;
import utils.TypeCheck;

import java.util.Arrays;

public class Modulo extends TwoArgumentOperator{
	@Override
	public Value calculate(Scope scope) throws RuntimeException{
		Object v1 = arg1.calculate(scope).getValue(), v2 = arg2.calculate(scope).getValue();
		TypeCheck.assertType(v1, Number.class);
		TypeCheck.assertType(v2, Number.class);
		
		Number n1 = (Number)v1, n2 = (Number)v2;
		if(n1.doubleValue() != n1.longValue())
			throw new RuntimeException("Object has type " + v1.getClass().getName() + ", " + Arrays.toString(new Class[]{Byte.class, Short.class, Integer.class, Long.class}) + " required");
		if(n2.doubleValue() != n2.longValue())
			throw new RuntimeException("Object has type " + v2.getClass().getName() + ", " + Arrays.toString(new Class[]{Byte.class, Short.class, Integer.class, Long.class}) + " required");
		
		return new Value(n1.longValue() % n2.longValue());
	}
	
	@Override
	public String preetyPrint(String prefix){
		return arg1.preetyPrint(prefix) + " % " + arg2.preetyPrint(prefix);
	}
}
