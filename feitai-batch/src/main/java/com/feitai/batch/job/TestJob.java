package com.feitai.batch.job;

import com.feitai.batch.core.AbstractJob;
import com.feitai.common.exception.FtException;


public class TestJob extends AbstractJob{
	public void execute() throws FtException{
		System.out.println("hello world!");
		this.resetCronExpression("* 0/2 * * * ?" , "TestTrigger" , "Test");
	}
}
