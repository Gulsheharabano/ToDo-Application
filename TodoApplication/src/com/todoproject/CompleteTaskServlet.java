package com.todoproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/complete")
public class CompleteTaskServlet extends HttpServlet{
	
	static String taskcomplete[];
	List<AddTaskBean> list;

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out=response.getWriter();
		taskcomplete=request.getParameterValues("taskcomplited");
		
		CompleteUpdate();
		
		   response.setContentType("text/html");
		   out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>View List</title>");
			out.println("<link rel='stylesheet' href='resources/bootstrap.min.css'/>");
			out.println("<link rel='stylesheet' href='style.css'/>");
			out.println("</head>");
			out.println("<body>");
			request.getRequestDispatcher("userHome.html").include(request, response);
			out.println("<div class='container'>");
			out.print("<h1 style=\"text-align: center;\">View Todolist</h1>");
			
			list=AddTaskDao.getAllRecords();
			out.println("<form action='complete' method='get'>");
			out.println("<table class='table table-bordered table-striped'>");
			out.print("<tr><th></th><th>Task Name</th><th>Description</th><th>Status</th><th>Edit</th><th>Delete</th>");
			
			for(AddTaskBean bean:ViewTodoList.list){
				out.print("<tr><td><input type='checkbox' name='taskcomplited' value='"+bean.getTaskid()+"' /></td><td>"+bean.getTaskname()+"</td><td>"+bean.getDescription()+"</td><td>"+bean.getTaskstatus()+"</td><td><a href='Edittodotask.html?id="+bean.getTaskid()+"'>Edit</a></td><td><a href='DeleteTaskServlet?id="+bean.getTaskid()+"'>Delete</a></td></tr>");
				
			}
			out.println("</table>");
			
			out.println("<br>");
			out.println("<button type='submit' class=\"btn btn-success\">Complete Task</button>");
			
			out.println("</form>");
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");
			request.getRequestDispatcher("ViewTodoList").forward(request, response);
			out.close();
		
	}
	
	public static void CompleteUpdate() {
		Connection con=null;
		Statement stm=null;
		ResultSet rs=null;
		try {
			con = ConProvider.getConnection();
			stm=con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			
			rs=stm.executeQuery("select * from tasklist");

			while (rs.next()) {

				int tid = rs.getInt("taskid");
				boolean flag = false;
				for (int i = 0; i < taskcomplete.length; i++) {
					if (tid == Integer.parseInt(taskcomplete[i])) {
						flag = true;
						break;
					}
				}
				if (flag) {
					rs.updateString(4, "completed");
					rs.updateRow();
				}
			}
		}
		catch (Exception ex) {
			System.out.println(ex);
		}
		finally {
			try {
				rs.close();
				stm.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    AddTaskDao.getAllRecords();
	}
	

}
