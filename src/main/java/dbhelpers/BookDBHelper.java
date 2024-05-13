/**
 * 
 */
package dbhelpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Book;

/**
 *  A class for methods to work with our Book database
 */
public class BookDBHelper {
    private Connection connection;

    public BookDBHelper() {
        connection = MyDbConnection.getConnection();
    }
    
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public ResultSet doReadAll(){

        // Create a query to select all of the books
        String query = "SELECT bookID, title, author, pages FROM books";

        ResultSet results = null;
        try {
        // set up a preparedstatement to hold and implement the query
            PreparedStatement ps = this.connection.prepareStatement(query);
            
            // implement the query and get the results
            
            results = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // return the resultset
        return results;
    }

    // Return a String that holds contents of the resultset as a displayable html table
    
    public String getHTMLTable(ResultSet results){
        String table ="";
        table += "<table border=1>\n";

        try {
            while(results.next()) {

                // Consider: Why are we creating Book objects with these results, rather 
                // than just printing the results of the query directly?
                // (It helps us validate our data.)

                Book book = new Book(
                        results.getInt("bookID"),
                        results.getString("title"),
                        results.getString("author"),
                        results.getInt("pages")
                        );

                // Consider: Could this table row code be refactored to be part of Book?
                // Would that be a good idea or not?

                table +="<tr>";
                table +="\t<td>";
                table += book.getTitle();
                table +="</td>";
                table +="<td>";
                table += book.getAuthor();
                table +="</td>";
                table +="<td>";
                table += book.getPages();
                table +="</td>";
                table +="\n\t<td>";
                
                // We made changes to the Delete servlet, so that it can't be accessed via 'GET'
                // Thus, this HTML needs to change as well. 
                // We'll create a small form that POSTs instead.
                
                table += "<form action=\"update\" method=\"post\">";
                table += "<input type=\"hidden\" name=\"bookID\" value=\"" + book.getBookID() + "\">";
                table += "<input type=\"submit\" value=\"Update\">";
                table += "</form>";
                
                table += "<form action=\"delete\" method=\"post\">";
                table += "<input type=\"hidden\" name=\"bookID\" value=\"" + book.getBookID() + "\">";
                table += "<input type=\"submit\" value=\"Delete\">";
                table += "</form>";
                
                // Consider adding behavior that might make this more user friendly:
                // a) adding an "Are you sure?" Javascript popup.
                // b) adding a success message to the reloaded page.
                
                table +="</td>\n";
                
                table +="</tr>\n";

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        table += "</table>";
        return table;
    }

	public void doDelete(int bookID) {
		String query = "DELETE FROM books WHERE bookID = ?";
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1,  bookID); // 1 stands for the first question mark
			ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
		}
		
	}

	public void doAdd(Book book) {
		 String query = "INSERT INTO books (title, author, pages) values (?, ?, ?)";

	        try {
	            PreparedStatement ps = connection.prepareStatement(query);

	            ps.setString(1, book.getTitle());
	            ps.setString(2, book.getAuthor());
	            ps.setInt(3, book.getPages());

	            ps.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	
	public Book doReadOne(int bookId) {
        String query = "SELECT * FROM books WHERE bookID = ?";

        Book book = null;

        try {
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, bookId);
            ResultSet results = ps.executeQuery();

            results.next();

            // What if book isn't found? Is an exception thrown?
            // Is it okay to return null from this refactored method?

            book = new Book(
                    results.getInt("bookID"),
                    results.getString("title"),
                    results.getString("author"),
                    results.getInt("pages")
                    );

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book;
    }

    

    public void doUpdate(Book book){
        String query = "UPDATE books SET title=?, author=?, pages=? WHERE bookID=?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getPages());
            ps.setInt(4, book.getBookID());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

