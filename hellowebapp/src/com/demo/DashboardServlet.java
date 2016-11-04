package com.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet(name = "dashboard", urlPatterns = { "/dashboard" })
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection con;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public void init() {
    	
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aug2016","root","aashishisaGOOD");
		} catch(Exception e) {
			e.printStackTrace();
		}
    
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		out.println("<body>");
		out.println("<div style='text-align:right'><a href='logout'>Logout</a></div>");
		out.println("<form method=get action=getinfo><select name='userid'>");
		
		try{
			PreparedStatement ps = con.prepareStatement("select username from users");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				out.println("<option>" + rs.getString(1) + "</option>");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		out.println("</select>");
		out.println("<input type=submit value='OK'>");
		out.println("</form></body>");
	}

}
