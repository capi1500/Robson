import exceptions.BladWykonania;
import exceptions.NieprawidlowyProgram;
import instructions.Instruction;

public class Robson{
	private Instruction program;
	
	public void fromJson(String filename) throws NieprawidlowyProgram{
		
	}
	
	public void toJson(String filename){
		
	}
	
	public void toJava(String filename){
		
	}
	
	public double wykonaj() throws BladWykonania{
		return (double)program.calculate();
	}
}
