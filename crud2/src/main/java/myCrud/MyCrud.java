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
import java.sql.Statement;


/**
 * Servlet implementation class MyCrud
 */
public class MyCrud extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyCrud() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String contact = request.getParameter("contact");
		
		out.println("<table border='1'><tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Contact</th></tr>");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbCrud","root","Root");
			PreparedStatement ps = con.prepareStatement("insert into tblCrud(fname,lname,contact) values (?,?,?)");
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setString(3, contact);
			int i = ps.executeUpdate();
			
			if(i > 0) {
				out.println("Registered Successfully!");
			}
			
			Statement stmt = con.createStatement();
			ResultSet rSet = stmt.executeQuery("select * from tblCrud");
			while(rSet.next()) {
				out.println("<tr><td>");
				out.println(rSet.getString(1));
				out.println("</td>");
				out.println("<td>");
				out.println(rSet.getString(2));
				out.println("</td>");
				out.println("<td>");
				out.println(rSet.getString(3));
				out.println("</td>");
				out.println("<td>");
				out.println(rSet.getString(4));
				out.println("</td>");
				out.println("<td>");
				out.println("<button name='edit'><a href='UpdateForm?id="+rSet.getString(1)+"'>Edit</a></button>");
				out.println("</td>");
				out.println("<td>");
				out.println("<button name='delete'><a href='Delete?id="+rSet.getString(1)+"'>Delete</a></button>");
				out.println("</td>");
				out.println("</tr>");
				
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
