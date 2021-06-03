package robson.loader;

import com.google.gson.*;
import com.google.gson.internal.LazilyParsedNumber;
import robson.lang.base.Value;

import java.lang.reflect.Type;

public class ValueDeserializer implements JsonDeserializer<Value>{
	@Override
	public Value deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException{
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		
		JsonElement element;
		if(jsonObject.has("wartosc"))
			element = jsonObject.get("wartosc");
		else if(jsonObject.has("value"))
			element = jsonObject.get("value");
		else
			throw new JsonParseException("Invalid Value, value not found");
		
		if(element.isJsonPrimitive()){
			String value = element.getAsString();
			
			if(value.equals("false") || value.equals("true"))
				return new Value(Boolean.parseBoolean(value));
			try{
				return new Value(Integer.parseInt(value)); }
			catch(NumberFormatException e){ }
			try{
				return new Value(Double.parseDouble(value));
			}
			catch(NumberFormatException e){ }
			return new Value(value);
		}
		return new Value(jsonDeserializationContext.deserialize(jsonElement, Object.class));
	}
}
