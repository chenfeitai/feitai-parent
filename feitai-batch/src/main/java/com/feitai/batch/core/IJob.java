package com.feitai.batch.core;

import com.feitai.common.exception.FtException;

public interface IJob {
	public void execute() throws FtException;
}
