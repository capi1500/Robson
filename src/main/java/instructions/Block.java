package instructions;

import com.google.gson.annotations.SerializedName;

public class Block extends Instruction{
	@SerializedName("instrukcje")
	private Instruction[] instructions;
	
	@Override
	public Object calculate(){
		Object out = 0;
		for(Instruction i : instructions)
			out = i.calculate();
		return out;
	}
}
