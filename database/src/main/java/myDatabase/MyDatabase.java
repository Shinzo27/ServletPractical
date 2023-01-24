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
import java.sql.*;




public class MyDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyDatabase() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String contact = request.getParameter("contact");
		
		out.println("<br>");
		out.println("<table border='1'><tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Contact</th><tr>");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbServlet","root","root");
			PreparedStatement ps = con.prepareStatement("insert into tblEmployee(fname,lname,contact) values (?,?,?)");
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setString(3, contact);
			int i = ps.executeUpdate();
			
			if(i>0)
			{
				out.println("Registered Successfully!");
			}
			
			Statement stmt = con.createStatement();
			ResultSet rs= stmt.executeQuery("select * from tblEmployee");
			while(rs.next()) {
				out.println("<form method='post' action='update'>");
				out.println("<input type='hidden' name='id' value='"+rs.getString(1)+"'>");
				out.println("<tr><td>");
				out.println(rs.getString(1));
				out.println("</td>");
				out.println("<td>");
				out.println(rs.getString(2));
				out.println("</td>");
				out.println("<td>");
				out.println(rs.getString(3));
				out.println("</td>");
				out.println("<td>");
				out.println(rs.getString(4));
				out.println("</td>");
				out.println("<td>");
				out.println("<button name='edit'><a href='edit'>Edit</a></button>");
				out.println("</td>");
				out.println("<td>");
				out.println("<button name='delete'><a href='delete'>Delete</a></button>");
				out.println("</td>");
				out.println("<tr>");
				out.println("</form>");
			}
			
			
		} catch(Exception e) {
			System.out.println(e);
		}
	}

}
