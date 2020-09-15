package com.todoproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewUserList")
public class ViewUserList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter out=response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>View User list</title>");
		out.println("<link rel='stylesheet' href='resources/bootstrap.min.css'/>");
		out.println("<link rel='stylesheet' href='style.css'/>");
		out.println("</head>");
		out.println("<body>");
		request.getRequestDispatcher("adminHome.html").include(request, response);
		out.println("<div class='container'>");
		out.print("<h1>View Todolist</h1>");
	
		List<UserBean> list=RegisterDao.getAllRecords();
		//out.println("<form action='ActiveDeactiveServle' method='get'>");
		out.println("<table class='table table-bordered table-striped'>");
		out.print("<tr><th>User Id</th><th>User Name</th><th>Status</th><th>Active/Deactive User</th>");
		
		for(UserBean bean:list){
			out.print("<tr><td>"+bean.getUserid()+"</td><td>"+bean.getFirstname()+" "+bean.getLastname()+"</td><td>"+bean.getActivestatus()+"</td><td><a href='ActiveDeactiveServlet?id="+bean.getUserid()+"'>Active/Deactive</a></td></tr>");
			
		}
		out.println("</table>");
		//out.println("</form>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}

}
