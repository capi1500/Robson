package robson.loader;

import com.google.gson.*;
import robson.lang.base.FunctionCall;
import robson.lang.base.Function;
import robson.lang.base.Value;
import robson.lang.base.Variable;
import robson.lang.base.Expresion;
import robson.lang.keywords.*;
import robson.lang.operators.*;

import java.lang.reflect.Type;

public class ExpresionDeserializer implements JsonDeserializer<Expresion>{
	@Override
	public Expresion deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException{
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		
		String t;
		if(jsonObject.has("typ"))
			t = jsonObject.get("typ").getAsString();
		else if(jsonObject.has("type"))
			t = jsonObject.get("type").getAsString();
		else
			throw new JsonParseException("Invalid Expresion, type not found");
		
		Class<?> c = switch(t){
			case "Funkcja", "Function" -> Function.class;
			case "Wywolanie", "Call" -> FunctionCall.class;
			case "Liczba", "Wartosc", "Value" -> Value.class;
			case "Zmienna", "Variable" -> Variable.class;
			case "Przypisanie", "Assignment" -> Assignment.class;
			case "Wez", "Access" -> Access.class;
			case "Blok", "Block" -> Block.class;
			case "Jesli", "Jezeli", "If" -> If.class;
			case "Dopoki", "While" -> While.class;
			case "Falsz", "False" -> False.class;
			case "Prawda", "True" -> True.class;
			case "Plus", "Add", "+" -> Add.class;
			case "Minus", "Subtract", "-" -> Subtract.class;
			case "Razy", "Multiply", "*" -> Multiply.class;
			case "Dzielenie", "Divide", "/" -> Divide.class;
			case "Modulo", "%" -> Modulo.class;
			case "I", "And", "&&" -> And.class;
			case "Lub", "Or", "||" -> Or.class;
			case "Nie", "Not", "!" -> Not.class;
			case "<" -> Smaller.class;
			case "<=" -> SmallerEqual.class;
			case ">" -> Greater.class;
			case ">=" -> GreaterEqual.class;
			case "==" -> Equal.class;
			default -> null;
		};
		
		if(c == null)
			throw new JsonParseException("Unknown type: " + t);
		
		return jsonDeserializationContext.deserialize(jsonElement, c);
	}
}
