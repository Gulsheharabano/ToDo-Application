package com.todoproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewTodoList")
public class ViewTodoList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static List<AddTaskBean> list;
	String[] taskcomplete =CompleteTaskServlet.taskcomplete;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
				
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>View Accountant</title>");
		out.println("<link rel='stylesheet' href='resources/bootstrap.min.css'/>");
		out.println("<link rel='stylesheet' href='style.css'/>");
		out.println("</head>");
		out.println("<body>");
		request.getRequestDispatcher("userHome.html").include(request, response);
		out.println("<div class='container'>");
		out.print("<h1>View Todolist</h1>");
		list=AddTaskDao.getAllRecords();
		out.println("<form action='complete' method='get'>");
		out.println("<table class='table table-bordered table-striped'>");
		out.print("<tr><th></th><th>Task Name</th><th>Description</th><th>Status</th><th>Edit</th><th>Delete</th>");
		int uid=LoginServlet.currentid;
		
		for(AddTaskBean bean:list){
			if(uid==bean.getUserid())
			{
			out.print("<tr><td><input type='checkbox' name='taskcomplited' value='"+bean.getTaskid()+"' /></td><td>"+bean.getTaskname()+"</td><td>"+bean.getDescription()+"</td><td>"+bean.getTaskstatus()+"</td><td><a href='EditTaskServlet?id="+bean.getTaskid()+"'>Edit</a></td><td><a href='DeleteTaskServlet?id="+bean.getTaskid()+"'>Delete</a></td></tr>");
			}
		}
		out.println("</table>");
		out.println("<br>");
		out.println("<button type='submit' class='btn btn-success'>Complete Task</button>");
		out.println("</form>");
		out.println("</div>");
		
		out.println("</body>");
		out.println("</html>");
//		request.getRequestDispatcher("ViewTodoList2").forward(request, response);
		out.close();
	}

}
