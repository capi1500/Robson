package robson.lang.operators;

import utils.TypeCheck;

import java.util.Arrays;

public class Modulo extends TwoArgumentOperator{
	@Override
	protected Number operation(Object e1, Object e2){
		TypeCheck.assertType(e1, Number.class);
		TypeCheck.assertType(e2, Number.class);
		
		Number n1 = (Number)e1, n2 = (Number)e2;
		if(n1.doubleValue() != n1.longValue())
			throw new RuntimeException("Object has type " + e1.getClass().getName() + ", " + Arrays.toString(new Class[]{Byte.class, Short.class, Integer.class, Long.class}) + " required");
		if(n2.doubleValue() != n2.longValue())
			throw new RuntimeException("Object has type " + e2.getClass().getName() + ", " + Arrays.toString(new Class[]{Byte.class, Short.class, Integer.class, Long.class}) + " required");
		
		return n1.longValue() % n2.longValue();
	}
	
	@Override
	public String prettyPrint(String prefix){
		return "(" + arg1.prettyPrint(prefix) + " % " + arg2.prettyPrint(prefix) + ")";
	}
}
