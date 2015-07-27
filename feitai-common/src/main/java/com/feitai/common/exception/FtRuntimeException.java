package com.feitai.common.exception;

import jodd.util.StringUtil;

import org.springframework.core.NestedCheckedException;

import com.feitai.common.core.MessageSupport;
import com.feitai.common.core.Messageable;

public class FtRuntimeException extends NestedCheckedException implements Messageable {

	private static final long serialVersionUID = 2763605130168831962L;
	
	private static final String DEFAULT_MESSAGE_KEY = "E998";

private MessageSupport messageSupport = new MessageSupport();
	
	public FtRuntimeException(){
		super(DEFAULT_MESSAGE_KEY);
		this.messageSupport.setMessageKey(DEFAULT_MESSAGE_KEY);
	}
	
	public FtRuntimeException(String errorCode) {
		super(errorCode);
		if(StringUtil.isEmpty(errorCode)){
			this.messageSupport.setMessageKey(DEFAULT_MESSAGE_KEY);
		}else{
			this.messageSupport.setMessageKey(errorCode);
		}
	}
	
	public FtRuntimeException(String errorCode, Object[] args) {
		super(errorCode);
		if (StringUtil.isEmpty(errorCode))
			this.messageSupport.setMessageKey(DEFAULT_MESSAGE_KEY);
		else
			this.messageSupport.setMessageKey(errorCode);
		this.messageSupport.setArgs(args);
	}
	
	public FtRuntimeException(String errorCode, Throwable paramThrowable) {
		super(errorCode, paramThrowable);
		if (StringUtil.isEmpty(errorCode))
			this.messageSupport.setMessageKey(DEFAULT_MESSAGE_KEY);
		else
			this.messageSupport.setMessageKey(errorCode);
	}
	
	public FtRuntimeException(String errorCode, Throwable paramThrowable, Object[] args) {
		super(errorCode, paramThrowable);
		if (StringUtil.isEmpty(errorCode))
			this.messageSupport.setMessageKey(DEFAULT_MESSAGE_KEY);
		else
			this.messageSupport.setMessageKey(errorCode);
		this.messageSupport.setArgs(args);
	}
	
	public FtRuntimeException(Throwable paramThrowable) {
		super("", paramThrowable);
		this.messageSupport.setMessageKey(DEFAULT_MESSAGE_KEY);
	}
	public boolean hasDefaultMessage() {
		return this.messageSupport.hasDefaultMessage();
	}

	public void setDefaultMessage(String paramString) {
		this.messageSupport.setDefaultMessage(paramString);
	}


	public String getDefaultMessage() {
		return null;
	}

	public String toString() {
		StringBuffer local = new StringBuffer(super.toString());
		if (this.messageSupport.getArgs() != null) {
			local.append("  Message:");
			Object[] arrayOfObject = this.messageSupport.getArgs();
			for (int i = 0; i < arrayOfObject.length; ++i)
				local.append(arrayOfObject[i]).append(" ");
		}
		return local.toString();
	}

	public String getMessageKey() {
		return this.messageSupport.getMessageKey();
	}

	public Object[] getArgs() {
		return this.messageSupport.getArgs();
	}

	public void setArgs(Object[] paramArrayOfObject) {
		this.messageSupport.setArgs(paramArrayOfObject);
	}
	
}
