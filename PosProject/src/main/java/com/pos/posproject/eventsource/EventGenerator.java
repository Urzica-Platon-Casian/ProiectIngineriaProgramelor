package com.pos.posproject.eventsource;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import service.PositionEvent;

/**
 *
 * @author Rori
 */
@Startup
@Singleton
public class EventGenerator extends HttpServlet {

   @Inject
    Event<PositionEvent> message;
    
    @Schedule(minute="*",second="*/5",hour="*")
    public void sendTime(){
        
        message.fire(new PositionEvent("-1", "Update"));
        //System.out.println("."+date);
    }

}
