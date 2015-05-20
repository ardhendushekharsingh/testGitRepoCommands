package com.otv;

import java.text.ParseException;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.otv.job.TestJob;

/**
 * @author onlinetechvision.com
 * @since 17 Sept 2011
 * @version 1.0.0
 *
 */
public class JobScheduler {
	
	
	//This is a test to check git commit.
	
	public static void main(String[] args) {
		
		try {
			
			// specify the job' s details..
			JobDetail job = JobBuilder.newJob(TestJob.class)
			    .withIdentity("testJob")
			    .build();
			
			// specify the running period of the job
			/*Trigger trigger = TriggerBuilder.newTrigger()
			      .withSchedule(  
	                    SimpleScheduleBuilder.simpleSchedule()
	                    .withIntervalInSeconds(30)
	                    .repeatForever())  
                             .build();  */
			CronTrigger cronTrigger=null;
			try {
				cronTrigger = TriggerBuilder.newTrigger()
				        .withIdentity("crontrigger","crontriggergroup1")
				        .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?"))
				        .build();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//schedule the job
			SchedulerFactory schFactory = new StdSchedulerFactory();
			Scheduler sch = schFactory.getScheduler();
	    	sch.start();	    	
	    	sch.scheduleJob(job, cronTrigger);		
		
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
}
