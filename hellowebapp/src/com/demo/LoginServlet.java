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
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "login", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String userName = request.getParameter("uname");
		String passWord = request.getParameter("pword");
		
		try{
			PreparedStatement ps = con.prepareStatement("select id,username,email,country from users where username=? and password=?");
			ps.setString(1, userName);
			ps.setString(2, passWord);
			System.out.println("Trying to login...");
			ResultSet rs = ps.executeQuery();
			if(!rs.next()) {
				out.println("<body text='red'>");
				out.println("<h3>Invalid credentials...</h3>");
				out.println("</body>");
			}
			else {
				out.println("<body text='blue'>");
				out.println("<h3>Welcome to the Dashboard...</h3>");
				out.println("</body>");
				/*HttpSession session = request.getSession(true);
				session.setAttribute("currentUserRole",rs.getString(3));
				response.sendRedirect("dashboard");*/
				//Retrieve by column name
	          /*  int id  = rs.getInt("id");
	            String username = rs.getString("username");
	            String email = rs.getString("email");
	            String country = rs.getString("country");
	            
	            //Display values
	            out.println("ID: " + id + "<br>");
	            out.println(", Age: " + username + "<br>");
	            out.println(", First: " + email + "<br>");
	            out.println(", Last: " + country + "<br>");
			*/
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
