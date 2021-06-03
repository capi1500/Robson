package robson.lang.environment;

import robson.lang.base.Function;
import robson.lang.base.Value;

import java.util.HashMap;
import java.util.Map;

public class Environment{
	protected final Map<String, Value> variables = new HashMap<>();
	protected final Map<String, Function> functions = new HashMap<>();
	
	public Value getVariable(String name) throws RuntimeException{
		if(!variables.containsKey(name))
			throw new RuntimeException("variable " + name + " not found in the environment");
		return variables.get(name);
	}
	
	public Function getFunction(String name) throws RuntimeException{
		if(!functions.containsKey(name))
			throw new RuntimeException("function " + name + " not found in the environment");
		return functions.get(name);
	}
	
	public boolean containsVariable(String name){
		return variables.containsKey(name);
	}
	
	public boolean containsFunction(String name){
		return functions.containsKey(name);
	}
	
	public void add(String name, Value value){
		variables.put(name, value);
	}
	
	public void add(String name, Function function){
		functions.put(name, function);
	}
}
