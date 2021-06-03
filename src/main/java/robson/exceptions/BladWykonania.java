package robson.exceptions;

public class BladWykonania extends Exception{
	public BladWykonania(){
	}
	
	public BladWykonania(String message){
		super(message);
	}
	
	public BladWykonania(String message, Throwable cause){
		super(message, cause);
	}
	
	public BladWykonania(Throwable cause){
		super(cause);
	}
	
	public BladWykonania(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
