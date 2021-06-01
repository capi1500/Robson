package instructions;

import com.google.gson.annotations.SerializedName;

public class If extends Instruction{
	@SerializedName("warunek")
	Instruction predicate;
	
	@SerializedName("blok_prawda")
	Instruction ifTrue;
	
	@SerializedName("blok_false")
	Instruction ifFalse;
	
	@Override
	public Object calculate(){
		return ((boolean)predicate.calculate() ? ifTrue : ifFalse).calculate();
	}
}
