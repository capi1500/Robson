package robson.lang.base;

import com.google.gson.annotations.SerializedName;
import robson.lang.environment.Scope;
import robson.lang.keywords.Block;

import java.util.Arrays;
import java.util.Objects;

public class Function extends Expresion{
	@SerializedName(value = "nazwa", alternate = {"name"})
	private String name;
	@SerializedName(value = "argumenty", alternate = {"args"})
	private String[] argumentNames;
	@SerializedName(value = "instrukcja", alternate = {"instruction"})
	private Expresion body;
	@SerializedName(value = "lokalna", alternate = {"local"})
	private boolean local = false;
	
	transient private Scope baseScope;
	
	@Override
	public Value calculate(Scope scope) throws RuntimeException{
		
		if(local){
			scope.add(name, this);
			baseScope = new Scope(scope);
		}
		else{
			scope.getGlobalScope().add(name, this);
			baseScope = new Scope(scope.getGlobalScope());
		}
		
		return new Value(0);
	}
	
	public Value call(Scope scope, Expresion... args) throws RuntimeException{
		if(argumentNames.length != args.length)
			throw new RuntimeException("Invalid function call: " + name + " requires " + argumentNames.length + " arguments, " + args.length + " provided");
		
		Scope callScope = new Scope(baseScope);
		
		for(int i = 0; i < args.length; i++)
			callScope.add(argumentNames[i], args[i].calculate(scope));
		
		return body.calculate(callScope);
	}
	
	@Override
	public boolean equals(Object o){
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		if(!super.equals(o))
			return false;
		
		Function function = (Function)o;
		
		if(local != function.local)
			return false;
		if(!Objects.equals(name, function.name))
			return false;
		// Probably incorrect - comparing Object[] arrays with Arrays.equals
		if(!Arrays.equals(argumentNames, function.argumentNames))
			return false;
		return Objects.equals(body, function.body);
	}
	
	@Override
	public int hashCode(){
		int result = super.hashCode();
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + Arrays.hashCode(argumentNames);
		result = 31 * result + (body != null ? body.hashCode() : 0);
		result = 31 * result + (local ? 1 : 0);
		return result;
	}
	
	@Override
	public String preetyPrint(String prefix){
		StringBuilder out = new StringBuilder();
		if(local)
			out.append("local ");
		out.append("def " + name + "(");
		for(String arg : argumentNames){
			out.append(arg);
			if(!arg.equals(argumentNames[argumentNames.length - 1]))
				out.append(", ");
		}
		out.append(")");
		if(body.getClass() != Block.class)
			out.append("\n" + prefix + "    " + body.preetyPrint(prefix + "    "));
		else
			out.append(body.preetyPrint(prefix));
		return out.toString();
	}
}
