package mk.ukim.finki.lab1.web.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.util.HashMap;

@WebListener
public class BookContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        HashMap<String, Integer> bookContextCount = new HashMap<>();
        ServletContext context = sce.getServletContext();
        context.setAttribute("bookReservationCount", bookContextCount);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
        sce.getServletContext().removeAttribute("bookReservationCount");

    }
}

