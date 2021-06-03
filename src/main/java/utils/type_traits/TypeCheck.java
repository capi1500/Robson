package utils.type_traits;

public class TypeCheck{
	public static void assertNumerical(Object object) throws RuntimeException{
		if(!isNumerical(object))
			throw new RuntimeException("Object has type " + object.getClass().getName() + ", Double or Integer required");
	}
	
	public static void assertInteger(Object object) throws RuntimeException{
		if(!isInteger(object))
			throw new RuntimeException("Object has type " + object.getClass().getName() + ", Integer required");
	}
	
	public static void assertBoolean(Object object) throws RuntimeException{
		if(!isBoolean(object))
			throw new RuntimeException("Object has type " + object.getClass().getName() + ", Boolean required");
	}
	
	public static boolean isNumerical(Object object){
		return isDouble(object) || isInteger(object);
	}
	
	public static boolean isDouble(Object object){
		return Double.class.isAssignableFrom(object.getClass());
	}
	
	public static boolean isInteger(Object object){
		return Integer.class.isAssignableFrom(object.getClass());
	}
	
	public static boolean isBoolean(Object object){
		return Boolean.class.isAssignableFrom(object.getClass());
	}
}
