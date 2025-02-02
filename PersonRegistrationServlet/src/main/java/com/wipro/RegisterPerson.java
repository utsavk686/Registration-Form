package com.wipro;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterPerson
 */
@WebServlet("/RegisterPerson")
public class RegisterPerson extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pid = Integer.parseInt(request.getParameter("pid"));
		String pname = request.getParameter("pname");
		String country = request.getParameter("country");
		PrintWriter out = response.getWriter();
		
		try {
//			PrintWriter out = response.getWriter();
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wipro?useSSL=false","root","root");
			
			PreparedStatement ps = connection.prepareStatement("INSERT into person VALUES(?,?,?)");
			
			ps.setInt(1, pid);
			ps.setString(2, pname);
			ps.setString(3,  country);
			
			int x = ps.executeUpdate();
			if(x>0) {
				 RequestDispatcher rsd = request.getRequestDispatcher("/welcome.html");
	              rsd.forward(request, response);
			}else {
				out.print("Please Try Again!");
			}
			connection.close();
		}catch(Exception e) {
			out.print(e.getMessage());
		}
		
		
	}

}
