package service;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 *
 * @author Rori
 */
@Interceptor
public class PositionInterceptor {
    @Inject
    Event<PositionEvent> message;

    @AroundInvoke
    public Object notifyBrowser(InvocationContext ctx) {
        Object[] parameters = ctx.getParameters();
        String id = parameters[0].toString();
        String name = parameters[1].toString();
        System.out.println("PositionInterceptor #ID =" + id + " Name=" + name);
        Boolean ok;
        try {
            ok = (Boolean) ctx.proceed();
        } catch (Exception e) {
            return null;
        }
        if (ok) {
            String methName = ctx.getMethod().getName();
            String msgEvt;
            PositionEvent e;
            if (methName.equals("addPosition")) {
                //HTML link: <a href="url">link text</a> 
                String url = "http://localhost:8080/PosProject/Cashier/Validate?id=" + id;
                String htmlLink = "<a href=\"" + url + "\"target=\"bbb\">" + name + "</a>";

                msgEvt = "RequestValidate => " + htmlLink;
                e = new PositionEvent(id, msgEvt);

            } else {//methName.equals("validate")
                msgEvt = "Done:" + id;
                e = new PositionEvent(id, msgEvt);
            }

            message.fire(e);
        }
        return ok;
    }
}
