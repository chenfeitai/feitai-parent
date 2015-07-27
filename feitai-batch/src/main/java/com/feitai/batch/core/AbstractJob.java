package com.feitai.batch.core;

import jodd.util.StringUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Scheduler;
import org.quartz.TriggerKey;
import org.quartz.impl.triggers.CronTriggerImpl;

import com.feitai.common.exception.FtException;

public abstract class AbstractJob implements IJob{

	protected Log log = LogFactory.getLog(AbstractJob.class);
	
	private Scheduler scheduler;
	
	public abstract void execute() throws FtException;

	public void resetCronExpression(String cronExpression,String triggerName,String group) throws FtException{
		try {
			CronTriggerImpl trigger = (CronTriggerImpl) scheduler
					.getTrigger(new TriggerKey(triggerName , group));
			String origCronExpression = trigger.getCronExpression();

			log.info(trigger.getCronExpression());
			if (!StringUtil.equals(cronExpression, origCronExpression)) {
				trigger.setCronExpression(cronExpression);
				scheduler.rescheduleJob(new TriggerKey(triggerName, group),
						trigger);
			}
		} catch (Exception e) {
			FtException ft = new FtException("B001",e,new Object[]{group,triggerName,cronExpression});
			throw ft;
		}
	}
	
	public Scheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}
}
