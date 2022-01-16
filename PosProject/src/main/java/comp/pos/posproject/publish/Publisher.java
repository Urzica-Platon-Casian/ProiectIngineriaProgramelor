package comp.pos.posproject.publish;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rori
 */
@WebServlet(name = "Publisher", urlPatterns = {"/Publisher"})
public class Publisher extends HttpServlet {

    @Inject
    Event<BrowserWindow> listeners;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        //System.out.println("Publisher doGet reqURI:"+request.getRequestURI());
        AsyncContext async = request.startAsync();
        listeners.fire(new BrowserWindow(async));
    }
}
