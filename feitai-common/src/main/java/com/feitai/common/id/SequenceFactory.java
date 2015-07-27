package com.feitai.common.id;

public abstract interface SequenceFactory extends IdFactory {
	public abstract Object generate(int paramInt);

	public abstract Object generate(String paramString);

	public abstract Object generate(String paramString, int paramInt);
}