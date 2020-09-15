package com.todoproject;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateEditServlet")
public class UpdateEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("etname");
		String email = request.getParameter("etdescription");

		AddTaskBean bean = new AddTaskBean(id, name, email);
		AddTaskDao.update(bean);
		
		request.getRequestDispatcher("ViewTodoList").forward(request, response);
		
		out.close();
	}

}
