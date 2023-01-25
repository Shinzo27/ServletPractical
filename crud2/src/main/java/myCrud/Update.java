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


public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Update() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String contact = request.getParameter("contact");
		String id = request.getParameter("id");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbCrud","root","Root");
			PreparedStatement pStatement = con.prepareStatement("update tblCrud set fname=?, lname=?, contact=? where Id=?");
			pStatement.setString(1, fname);
			pStatement.setString(2, lname);
			pStatement.setString(3, contact);
			pStatement.setString(4, id);
			int i = pStatement.executeUpdate();
			
			if(i>0) {
				out.println("Updated Successfully!");
				out.println("<a href='Home.html'>Home</a>");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}



}
