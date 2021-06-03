package robson.exceptions;

public class NieprawidlowyProgram extends Exception{
	public NieprawidlowyProgram(){
	}
	
	public NieprawidlowyProgram(String message){
		super(message);
	}
	
	public NieprawidlowyProgram(String message, Throwable cause){
		super(message, cause);
	}
	
	public NieprawidlowyProgram(Throwable cause){
		super(cause);
	}
	
	public NieprawidlowyProgram(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
