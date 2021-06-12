package utils;

public class NumericalOperations{
	public static Number add(Number a, Number b){
		if(a instanceof Double || b instanceof Double){
			return a.doubleValue() + b.doubleValue();
		}
		else if(a instanceof Float || b instanceof Float){
			return a.floatValue() + b.floatValue();
		}
		else if(a instanceof Long || b instanceof Long){
			return a.longValue() + b.longValue();
		}
		else{
			return a.intValue() + b.intValue();
		}
	}
	
	public static Number subtract(Number a, Number b){
		if(a instanceof Double || b instanceof Double){
			return a.doubleValue() - b.doubleValue();
		}
		else if(a instanceof Float || b instanceof Float){
			return a.floatValue() - b.floatValue();
		}
		else if(a instanceof Long || b instanceof Long){
			return a.longValue() - b.longValue();
		}
		else{
			return a.intValue() - b.intValue();
		}
	}
	
	public static Number multiply(Number a, Number b){
		if(a instanceof Double || b instanceof Double){
			return a.doubleValue() * b.doubleValue();
		}
		else if(a instanceof Float || b instanceof Float){
			return a.floatValue() * b.floatValue();
		}
		else if(a instanceof Long || b instanceof Long){
			return a.longValue() * b.longValue();
		}
		else{
			return a.intValue() * b.intValue();
		}
	}
	
	public static Number divide(Number a, Number b){
		if(a instanceof Double || b instanceof Double){
			return a.doubleValue() / b.doubleValue();
		}
		else if(a instanceof Float || b instanceof Float){
			return a.floatValue() / b.floatValue();
		}
		else if(a instanceof Long || b instanceof Long){
			return a.longValue() / b.longValue();
		}
		else{
			return a.intValue() / b.intValue();
		}
	}
	
	public static Boolean smaller(Number a, Number b){
		if(a instanceof Double || b instanceof Double){
			return a.doubleValue() < b.doubleValue();
		}
		else if(a instanceof Float || b instanceof Float){
			return a.floatValue() < b.floatValue();
		}
		else if(a instanceof Long || b instanceof Long){
			return a.longValue() < b.longValue();
		}
		else{
			return a.intValue() < b.intValue();
		}
	}
	
	public static Boolean smallerEqual(Number a, Number b){
		if(a instanceof Double || b instanceof Double){
			return a.doubleValue() <= b.doubleValue();
		}
		else if(a instanceof Float || b instanceof Float){
			return a.floatValue() <= b.floatValue();
		}
		else if(a instanceof Long || b instanceof Long){
			return a.longValue() <= b.longValue();
		}
		else{
			return a.intValue() <= b.intValue();
		}
	}
	
	public static Boolean greater(Number a, Number b){
		if(a instanceof Double || b instanceof Double){
			return a.doubleValue() > b.doubleValue();
		}
		else if(a instanceof Float || b instanceof Float){
			return a.floatValue() > b.floatValue();
		}
		else if(a instanceof Long || b instanceof Long){
			return a.longValue() > b.longValue();
		}
		else{
			return a.intValue() > b.intValue();
		}
	}
	
	public static Boolean greaterEqual(Number a, Number b){
		if(a instanceof Double || b instanceof Double){
			return a.doubleValue() >= b.doubleValue();
		}
		else if(a instanceof Float || b instanceof Float){
			return a.floatValue() >= b.floatValue();
		}
		else if(a instanceof Long || b instanceof Long){
			return a.longValue() >= b.longValue();
		}
		else{
			return a.intValue() >= b.intValue();
		}
	}
}
