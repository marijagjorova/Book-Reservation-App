package mk.ukim.finki.lab1.web.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import mk.ukim.finki.lab1.model.Book;
import mk.ukim.finki.lab1.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookListServlet", urlPatterns = "")
public class BookListServlet extends HttpServlet {

    private final SpringTemplateEngine templateEngine;
    private final BookService bookService;

    public BookListServlet(SpringTemplateEngine templateEngine, BookService bookService) {
        this.templateEngine = templateEngine;
        this.bookService = bookService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(request, response);

        String filterName = request.getParameter("filterName");
        String filterRatingStr = request.getParameter("filterRating");

        double filterRating = -1;
        try {
            if (filterRatingStr != null && !filterRatingStr.isEmpty()) {
                filterRating = Double.parseDouble(filterRatingStr);
            }
        } catch (NumberFormatException e) {

        }

        List<Book> books;
        if ((filterName == null || filterName.isEmpty()) && filterRatingStr == null) {
            books = this.bookService.listAll();
        } else {
            books = this.bookService.searchBooks(filterName, filterRating);
        }

        WebContext context = new WebContext(webExchange);
        context.setVariable("books", books);

        templateEngine.process("listBooks", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filterName = request.getParameter("filterName");
        String filterRating = request.getParameter("filterRating");

        String params = String.format("filterName=%s&filterRating=%s", filterName, filterRating);
        response.sendRedirect("/?" + params);
    }
}
