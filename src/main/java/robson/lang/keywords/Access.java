package robson.lang.keywords;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;
import robson.lang.base.Expresion;
import robson.lang.base.Value;
import robson.lang.environment.Scope;
import utils.TypeCheck;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Access extends Expresion{
	@SerializedName(value = "instrukcja", alternate = {"instruction"})
	Expresion expresion;
	@SerializedName(value = "nazwa", alternate = {"name", "field", "pole"})
	String what;
	@SerializedName(value = "indeks", alternate = {"index"})
	Expresion id;
	
	@Override
	public Value calculate(Scope scope) throws RuntimeException{
		if(id != null && what != null)
			throw new RuntimeException("Cannot access from expresion, both index and field");
		if(id == null && what == null)
			throw new RuntimeException("Cannot access from expresion, index nor field not specified");
		
		Object object = expresion.calculate(scope).getValue();
		if(object.getClass().isArray()){
			if(id == null)
				throw new RuntimeException("Cannot access " + what + ", expresion is an array");
			
			Value tmp = id.calculate(scope);
			TypeCheck.isType(tmp.getValue(), Number.class);
			int id = ((Number)tmp.getValue()).intValue();
			
			int len = Array.getLength(object);
			if(id < 0 || len <= id)
				throw new RuntimeException("Cannot access " + id + " in an expresion, index out of bounds");
			return new Value(Array.get(object, id));
		}
		else if(object instanceof List){
			if(id == null)
				throw new RuntimeException("Cannot access " + what + ", expresion is an array");
			
			Value tmp = id.calculate(scope);
			TypeCheck.isType(tmp.getValue(), Number.class);
			int id = ((Number)tmp.getValue()).intValue();
			
			ArrayList<?> arr = (ArrayList<?>)object;
			if(id < 0 || arr.size() <= id)
				throw new RuntimeException("Cannot access " + id + " in an expresion, index out of bounds");
			return new Value(arr.get(id));
		}
		if(object instanceof LinkedTreeMap<?, ?>){
			if(what == null)
				throw new RuntimeException("Cannot access index " + id + ", expresion is not an array");
			
			@SuppressWarnings("unchecked")
			LinkedTreeMap<? extends String, ?> map = (LinkedTreeMap<? extends String, ?>)object;
			return new Value(map.get(what));
		}
		throw new RuntimeException("Cannot access index or field: unexpected error");
	}
	
	@Override
	public boolean equals(Object o){
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		if(!super.equals(o))
			return false;
		
		Access access = (Access)o;
		
		if(!Objects.equals(expresion, access.expresion))
			return false;
		if(!Objects.equals(what, access.what))
			return false;
		return Objects.equals(id, access.id);
	}
	
	@Override
	public int hashCode(){
		int result = super.hashCode();
		result = 31 * result + (expresion != null ? expresion.hashCode() : 0);
		result = 31 * result + (what != null ? what.hashCode() : 0);
		result = 31 * result + (id != null ? id.hashCode() : 0);
		return result;
	}
	
	@Override
	public String prettyPrint(String prefix){
		StringBuilder out = new StringBuilder();
		out.append(expresion.prettyPrint(prefix));
		if(id != null){
			out.append("[");
			out.append(id.prettyPrint(prefix));
			out.append("]");
		}
		else{
			out.append(".");
			out.append(what);
		}
		return out.toString();
	}
}
