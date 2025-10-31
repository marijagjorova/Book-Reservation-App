package mk.ukim.finki.lab1.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.lab1.model.BookReservation;
import mk.ukim.finki.lab1.service.BookReservationService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "BookReservationServlet", urlPatterns = "/bookReservation")
public class BookReservationServlet extends HttpServlet {

    private final SpringTemplateEngine templateEngine;
    private final BookReservationService bookReservationService;

    public BookReservationServlet(SpringTemplateEngine templateEngine, BookReservationService bookReservationService) {
        this.templateEngine = templateEngine;
        this.bookReservationService = bookReservationService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(request, response);

        String bookTitle = request.getParameter("bookTitle");
        String readerName = request.getParameter("readerName");
        String numberOfCopies = request.getParameter("numberOfCopies");

        WebContext context = new WebContext(webExchange);
        context.setVariable("readerName", readerName);
        context.setVariable("ipAddress", request.getRemoteAddr());
        context.setVariable("bookTitle", bookTitle);
        context.setVariable("numberOfCopies", numberOfCopies);

        templateEngine.process("reservationConfirmation.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String bookTitle = request.getParameter("bookTitle");
        String readerName = request.getParameter("readerName");
        String readerAddress = request.getParameter("readerAddress");
        int numberOfCopies = Integer.parseInt(request.getParameter("numCopies"));

        BookReservation bookReservation =
                bookReservationService.placeReservation(bookTitle, readerName, readerAddress, numberOfCopies);

        String params = String.format(
                "bookTitle=%s&readerName=%s&readerAddress=%s&numberOfCopies=%s",
                bookReservation.getBookTitle(),
                bookReservation.getReaderName(),
                bookReservation.getReaderAddress(),
                bookReservation.getNumberOfCopies()
        );

        response.sendRedirect("/bookReservation?" + params);
    }
}
