package com.feitai.common.id;


public class StepSequenceFactory extends NonBlockingSequenceFactory {
	private int step = 10;

	private long hi = 0L;
	private int lo = this.step + 1;

	public void setStep(int i) {
		this.step = i;
		this.lo = (this.step + 1);
	}

	protected synchronized long internalGenerate(String range, int count) {
		if (this.lo > this.step) {
			long hival = super.internalGenerate(range, this.step) - 1L;
			this.lo = 1;
			this.hi = hival;
			this.log.info("new hi[type=" + this.type + ",step=" + this.step
					+ "]: " + hival);
		}
		return (this.hi + this.lo++);
	}
}