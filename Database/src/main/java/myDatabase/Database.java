package myDatabase;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class Database
 */
public class Database extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Database() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String contact = request.getParameter("contact");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbservlet","root","Root");
			PreparedStatement ps = con.prepareStatement("insert into tblregister values (?,?,?)");
			ps.setString(1,fname);
			ps.setString(2,lname);
			ps.setString(3,contact);
			int i= ps.executeUpdate();
			
			if(i>0) {
				out.println("You are Registered Successfully!!");
			}
			
			
			
		}catch(Exception e){
			System.out.println(e);
		}
	}

}
