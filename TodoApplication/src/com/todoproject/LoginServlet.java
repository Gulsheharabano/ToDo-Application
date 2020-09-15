package com.todoproject;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	static int currentid;
	static String stat;
	
	public static void getstat(String x) {
		stat=x;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		request.getRequestDispatcher("index.html").include(request, response);

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(LoginDao.validate(email, password))
		{
			if(stat.equals("user"))
			{
				request.getSession().setAttribute("user", "true");
				request.getSession().setAttribute("email", email);	        
				int user=LoginServlet.currentid;			
				response.sendRedirect("userHome.html");
			}
			else
			{
				request.getSession().setAttribute("admin", "true");
				request.getSession().setAttribute("email", email);	        
				//int user=LoginServlet.currentid;			
				response.sendRedirect("adminHome.html");
			}
			
		}
		else
		{
			request.getRequestDispatcher("errorlogin.html").include(request, response);
		}	
		out.close();
	}

}
