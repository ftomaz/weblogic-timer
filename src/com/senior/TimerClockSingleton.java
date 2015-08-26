package com.senior;

import java.util.Collection;
import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

@Startup
@Singleton
@LocalBean
public class TimerClockSingleton {


    @Resource
    TimerService service;
    
    public TimerClockSingleton() {
        System.out.println("TimerClockSingleton constructor " + service);
    }
    
    
    public TimerService getTimer() {
        return service;
    }


    @PostConstruct
    @Lock(LockType.WRITE)
    public void postConstruct() {
        System.out.println("************ postConstruct.....");
    }

    @Timeout
    @Lock(LockType.READ)
    public void onTimeout(Timer timer) {
        System.out.println("************ onTimeOut.....");
    }


    public String checkTimerStatus() {
        Timer timer = null;
        Collection<Timer> timers = service.getTimers();
        Iterator<Timer> iterator = timers.iterator();
        while (iterator.hasNext()) {
            timer = iterator.next();
            return ("Timer will expire after " + 
                timer.getTimeRemaining() + " milliseconds.");
        }
        return ("No timer found");
    }    

}
