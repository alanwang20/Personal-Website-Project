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
 * Servlet implementation class UpdateFormServlet
 */
@WebServlet("/update")
public class UpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateFormServlet() {
        super();
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get the bookID
        int bookId = Integer.parseInt(request.getParameter("bookID"));
        
        // create dbHelper class
        BookDBHelper bdb = new BookDBHelper();
        
        // Use method to get the book data
        Book book = bdb.doReadOne(bookId);
            
        // pass Book and control to the updateForm.jsp
        request.setAttribute("book", book);
        
        String url = "/updateForm.jsp";
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
	}

}
