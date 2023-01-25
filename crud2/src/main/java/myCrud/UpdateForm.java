package myCrud;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class UpdateForm
 */
public class UpdateForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbCrud","root","Root");
			PreparedStatement pStatement = con.prepareStatement("select * from tblCrud where id=?");
			pStatement.setInt(1, id);
			ResultSet rSet = pStatement.executeQuery();
			out.print("<html>");
			out.print("<body>");
			while(rSet.next()) {
				out.print("<form action='Update' method='post'>");
				out.print("Id : <input type='text' name='id' value='"+id+"'><br><br>");
				out.print("First Name : <input type='text' name='fname' value='"+rSet.getString(2)+"'><br><br>");
				out.print("Last Name : <input type='text' name='lname' value='"+rSet.getString(3)+"'><br><br>");
				out.print("Contact : <input type='text' name='contact' value='"+rSet.getString(4)+"'><br><br>");
				out.print("<input type='submit' name='submit' value='update'><br><br>");
				out.print("</form>");
				out.print("</body>");
				out.print("</html>");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		
	}



}
