package com.todoproject;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/verification")
public class OtpVerify extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static int otp;
	static String uid;
	static String fname;
	static String lname;
	static String gender;
	static String dob;
	static String email;
	static String password;
	static String confirmpass;
	static String state;
	static String rdate;
	static String role;
	
	public static void getData(String userid, String firstname, String lastname, String gender1, String dob1, String email1, String password1,
			String confirmpass1, String activestatus,String regdate,String role1)
	{
		uid=userid;
		fname=firstname;
		lname=lastname;
		gender=gender1;
		dob=dob1;
		email=email1;
		password=password1;
		confirmpass=confirmpass1;
		state=activestatus;
		rdate=regdate;
		role=role1;
		
	}
	
	public static void getOtp(int i) {
		otp=i;
	}
       
   public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
	   PrintWriter out= response.getWriter();
	   String uotp=request.getParameter("otp");
	   if(otp==Integer.parseInt(uotp)) {
		   int t=RegisterDao.save(uid,fname,lname,gender,dob, email, password, confirmpass,state,rdate,role);
		   
		   if(t==100)
		   {
			  out.println("your mailid already register..!");
			  try {
				request.getRequestDispatcher("Registeration.html").include(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			}
		   }
		   else
		   {
			   response.sendRedirect("Login.html");
		   }  
	   }
	   else 
	   {
		   response.sendRedirect("otpwrong.html");
	   }
	   out.close();
   }

}
