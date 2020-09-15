package com.todoproject;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddTaskServlet")
public class AddTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		request.getRequestDispatcher("userHome.html").include(request, response);
		
		String tid=request.getParameter("taskid");
		String name=request.getParameter("tname");
		String desc=request.getParameter("tdescription");
		String tstatus=request.getParameter("taskstatus");
		String uid=request.getParameter("userid");
		String tdate=request.getParameter("taskdate");
		
		int status=AddTaskDao.save(tid,name,desc,tstatus,uid,tdate);
		if(status>0){
			out.print("<p>You are task successfully added!</p>");
		}
		out.close();
	}

}
