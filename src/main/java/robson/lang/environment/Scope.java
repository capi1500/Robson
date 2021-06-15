package robson.lang.environment;

import robson.lang.base.Function;
import robson.lang.base.Value;

public class Scope extends Environment{
	private Scope parent;
	private GlobalScope globalScope;
	
	@Override
	public Value getVariable(String name) throws RuntimeException{
		if(containsVariable(name))
			return super.getVariable(name);
		else if(parent != null)
			return parent.getVariable(name);
		return globalScope.getVariable(name);
	}
	
	@Override
	public Function getFunction(String name) throws RuntimeException{
		if(containsFunction(name))
			return super.getFunction(name);
		else if(parent != null)
			return parent.getFunction(name);
		return globalScope.getFunction(name);
	}
	
	public Scope(Scope parent){
		this.parent = parent;
		this.globalScope = parent.getGlobalScope();
	}
	
	public Scope(GlobalScope globalScope){
		this.globalScope = globalScope;
	}
	
	public GlobalScope getGlobalScope(){
		return globalScope;
	}
	
	@Override
	public String toString(){
		return "Scope{" + "variables=" + variables + ", functions=" + functions + ", parent=" + (parent == null ? globalScope : parent) + '}';
	}
}
