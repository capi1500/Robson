package instructions;

import com.google.gson.annotations.SerializedName;

public abstract class Instruction{
	@SerializedName("typ")
	protected String type;
	
	public String getType(){
		return type;
	}
	
	public abstract Object calculate();
}
