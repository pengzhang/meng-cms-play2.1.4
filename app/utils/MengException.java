package utils;

import play.Logger;
import play.i18n.Messages;

public class MengException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public boolean success;
	public String errorno;
	public String message;
	
	public MengException(){
		super();
	}
	public MengException(String errorno){
		super(Messages.get(errorno, ""));
		Logger.info(Messages.get(errorno, ""));
		this.success = false;
		this.errorno = errorno;
		this.message = Messages.get(errorno, "");
	}
	
	
}
