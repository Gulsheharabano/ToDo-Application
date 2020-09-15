package com.todoproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		request.getRequestDispatcher("index.html").include(request, response);
		
		String uid=request.getParameter("userid");
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String gender=request.getParameter("gender");
		String dob=request.getParameter("dob");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String confirmpass=request.getParameter("confirmpass");
		String state=request.getParameter("activestatus");
		String rdate=request.getParameter("regdate");
		String role=request.getParameter("role");
		
		int r = new Random().nextInt(999999);
		OtpVerify.getOtp(r);
		OtpVerify.getData(uid,fname,lname,gender,dob, email, password, confirmpass,state,rdate,role);
			
		EmailNotifiaction.sendotpemail(email, String.valueOf(r));
		response.sendRedirect("otpverification.html");
		
		out.close();
	}
	
}
