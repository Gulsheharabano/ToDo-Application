package com.todoproject;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditTaskServlet")
public class EditTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		int tid=Integer.parseInt(request.getParameter("id"));
		AddTaskBean d=AddTaskDao.getRecordById(tid);
		int taskid=d.getTaskid();
		String taskname=d.getTaskname();
		String task_desc=d.getDescription();

		out.println("<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<title></title>\r\n" + 
				"<meta charset=\"utf-8\">\r\n" + 
				"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
				"<link rel=\"stylesheet\"\r\n" + 
				"	href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\r\n" + 
				"<script\r\n" + 
				"	src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\r\n" + 
				"<script\r\n" + 
				"	src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>\r\n" + 
				"<style>\r\n" + 
				".inputform {\r\n" + 
				"	float: left;\r\n" + 
				"	width: 400px;\r\n" + 
				"	border: 1px solid pink;\r\n" + 
				"	border-radius: 10px;\r\n" + 
				"	padding: 10px;\r\n" + 
				"	background-color: #EAEAEA;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				".inputform table tr td input, textarea, select {\r\n" + 
				"	width: 200px;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"#gender1, #gender2 {\r\n" + 
				"	width: 10px;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"#submit {\r\n" + 
				"	margin-left: 100px;\r\n" + 
				"	width: 100px;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"#reset {\r\n" + 
				"	width: 100px;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				".design {\r\n" + 
				"	background-color: #000066;\r\n" + 
				"	color: white;\r\n" + 
				"	padding-left: 90px;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"td {\r\n" + 
				"	padding: 5px;\r\n" + 
				"}\r\n" + 
				"</style>\r\n" + 
				"</head>\r\n" + 
				"<body style=\"background-color: #b3b3ff\">\r\n" + 
				"<nav class=\"navbar navbar-inverse\">\r\n" + 
				"  <div class=\"container-fluid\">\r\n" + 
				"    <div class=\"navbar-header\">\r\n" + 
				"      <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#myNavbar\">\r\n" + 
				"        <span class=\"icon-bar\"></span>\r\n" + 
				"        <span class=\"icon-bar\"></span>\r\n" + 
				"        <span class=\"icon-bar\"></span>                        \r\n" + 
				"      </button>\r\n" + 
				"      <a class=\"navbar-brand\" href=\"#\"></a>\r\n" + 
				"    </div>\r\n" + 
				"    <div class=\"collapse navbar-collapse\" id=\"myNavbar\">\r\n" + 
				"      <ul class=\"nav navbar-nav\">\r\n" + 
				"        <li class=\"active\"><a href=\"userHome.html\">Home</a></li>\r\n" + 
				"        <li><a href=\"Addtodotask.html\">Add TodoList</a></li>\r\n" + 
				"        <li><a href=\"ViewTodoList\">View TodoList</a></li>\r\n" + 
				"      </ul>\r\n" + 
				"      <ul class=\"nav navbar-nav navbar-right\">\r\n" + 
				"     	<li class=\"active\"><a ></a></li>\r\n" + 
				"        <li><a href=\"LogoutServlet\"><span class=\"glyphicon glyphicon-log-in\"></span> Logout</a></li>\r\n" + 
				"      </ul>\r\n" + 
				"    </div>\r\n" + 
				"  </div>\r\n" + 
				"</nav>\r\n" + 
				"	<div class=\"row\">\r\n" + 
				"		<div class=\"col-md-4\"></div>\r\n" + 
				"		<div class=\"col-md-6\">\r\n" + 
				"			<div class=\"inputform \">\r\n" + 
				"				<h2 class=\"design\">Edit TodoTask</h2>\r\n" + 
				"				<br>\r\n" + 
				"				<form action=\"UpdateEditServlet\" method=\"get\">\r\n" + 
				"					<table>\r\n" + 
				"						<tr>\r\n" + 
				"							<td><input type=\"hidden\" name=\"id\" value='"+taskid+"' class=\"form-control\" /></td>\r\n" + 
				"						</tr>\r\n" + 
				"						<tr>\r\n" + 
				"							<td>Task Name</td>\r\n" + 
				"							<td><input type=\"text\" name=\"etname\" style=\"width: 260px;\"\r\n" + 
				"								 value='"+taskname+"' class=\"form-control\" /></td>\r\n" + 
				"						</tr>\r\n" + 
				"						<tr>\r\n" + 
				"							<td>Description</td>\r\n" + 
				"							<td><input name=\"etdescription\" value='"+task_desc+"' style=\"width: 260px; height: 100px;\" class=\"form-control\"></input></td>\r\n" + 
				"						</tr>\r\n" + 
				"						<tr>\r\n" + 
				"							<td colspan=\"2\"><input id=\"submit\" type=\"submit\" class=\"btn btn-info btn-md\" value=\"Update\"/> \r\n" + 
				"								<input id=\"reset\" type=\"reset\" class=\"btn btn-info btn-md\" value=\"Clear\" /></td>\r\n" + 
				"						</tr>\r\n" + 
				"					</table>\r\n" + 
				"				</form>\r\n" + 
				"			</div>\r\n" + 
				"		</div>\r\n" + 
				"	</div>\r\n" + 
				"</body>\r\n" + 
				"</html>");
		
		out.println("");
		out.close();
	}

}
