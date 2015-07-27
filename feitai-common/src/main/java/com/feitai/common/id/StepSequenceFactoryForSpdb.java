package com.feitai.common.id;

public class StepSequenceFactoryForSpdb extends NonBlockingSequenceFactory {
	private int step;
	private long hi;
	private int lo;

	public StepSequenceFactoryForSpdb() {
		this.step = 10;
		this.hi = 0L;
		this.lo = (this.step + 1);
	}

	public void setStep(int i) {
		this.step = i;
		this.lo = (this.step + 1);
	}

	protected synchronized long internalGenerate(String range, int count) {
		if (this.lo > this.step) {
			long hival = super.internalGenerate(range, this.step) - 1L;
			this.lo = 1;
			this.hi = hival;
			if (this.log.isDebugEnabled()) {
				this.log.debug("new hi[type=" + this.type + ",step="
						+ this.step + "]: " + hival);
			}
		}

		return (this.hi + this.lo++);
	}
}