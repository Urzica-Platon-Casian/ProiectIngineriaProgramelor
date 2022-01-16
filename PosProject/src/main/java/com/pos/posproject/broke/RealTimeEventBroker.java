package com.pos.posproject.broke;

import comp.pos.posproject.publish.BrowserWindow;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import service.PositionEvent;

/**
 *
 * @author Rori
 */

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class RealTimeEventBroker {
    private ConcurrentLinkedQueue<BrowserWindow> browsers = new ConcurrentLinkedQueue<>();
    private ConcurrentHashMap<String, String> toValidate = new ConcurrentHashMap<>();

    public void onBrowserRequest(@Observes BrowserWindow browserWindow) {
        System.err.println("RealTimeEventBroker#onBrowserRequest");
        browsers.add(browserWindow);
     
    }

    public void onNewEvent(@Observes PositionEvent message) {
        //info
        System.err.println("RealTimeEventBroker#onNewEvent...msg=" + message);
        
        if (message.getMsg().startsWith("RequestValidate")) {
            toValidate.put(message.getId(), message.getMsg());
        } else if (message.getMsg().startsWith("Done")){
            toValidate.remove(message.getId());
        }
        updateBrowsers();

    }

    private void updateBrowsers() {
        for (BrowserWindow browserWindow : browsers) {
            try {
                String rez = "";
                rez = toValidate.values().stream().collect(Collectors.joining("<BR>"));
                browserWindow.sendAndCommit(rez);
            } finally {
                browsers.remove(browserWindow);
            }
        }
    }
}
