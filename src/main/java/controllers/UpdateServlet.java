package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbhelpers.BookDBHelper;
import model.Book;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/updateBook")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get the form data and set up a Book object
        int bookID = Integer.parseInt(request.getParameter("bookID"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        int pages = Integer.parseInt(request.getParameter("pages"));

        // create a book object with values
        Book book = new Book();
        book.setBookID(bookID);
        book.setAuthor(author);
        book.setTitle(title);
        book.setPages(pages);

        // create an dbHelper object and use it to update the book
        BookDBHelper bdb = new BookDBHelper();
        bdb.doUpdate(book);

        // pass control on to the ReadServlet
        String url = "/read";

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
	}

}
