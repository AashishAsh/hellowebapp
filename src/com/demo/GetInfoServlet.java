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
 * Servlet implementation class GetInfoServlet
 */
@WebServlet(name = "getinfo", urlPatterns = { "/getinfo" })
public class GetInfoServlet extends HttpServlet {
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
		
		String id = request.getParameter("userid");
		
		HttpSession session = request.getSession(true);
		String currentUserRole = session.getAttribute("currentUserRole").toString();
		
		try{
			PreparedStatement ps = con.prepareStatement("select * from users where username=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			out.println("<body text='green'>");
			out.println("<div style='text-align:right'><a href='logout'>Logout</a></div>");
			while(rs.next()) {
				String userId = rs.getString(1);
				if(currentUserRole.equals("Admin")) {
					out.println("<a href='create'>Create User</a>");
					out.println("<a href='edit?id='" + userId + "'>" + userId + "==>" + rs.getString(2) + "</a>");
				}
				else {
					out.println(rs.getString(1) + "-->" + rs.getString(2));
					PreparedStatement ps1 = con.prepareStatement("select * from users where username=?");
					ps1.setString(1, id);
					ResultSet rs1 = ps1.executeQuery();
				}
			}
			out.println("</body>");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
