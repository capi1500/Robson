package robson.lang.environment;

public class GlobalScope extends Environment{
	@Override
	public String toString(){
		return "GlobalScope{" + "variables=" + variables + ", functions=" + functions + '}';
	}
}
