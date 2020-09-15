package com.todoproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ActiveDeactiveServlet")
public class ActiveDeactiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static int uid;
	
	public static void ActiveUpdate() {
		String active = null;
		Connection con=null;
		Statement stm=null;
		ResultSet rs=null;
		try {
			con = ConProvider.getConnection();
			stm=con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			
			rs=stm.executeQuery("select * from user");

			while (rs.next()) {

				if (uid == rs.getInt("userid")) {
					
					active = rs.getString("activestatus");

					if (active.equals("yes"))
					{
						rs.updateString(9, "not");
						active="not";
					} 
					else 
					{
						rs.updateString(9, "yes");
						active="yes";
					}
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
 
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		uid=Integer.parseInt(request.getParameter("id"));
		ActiveUpdate();
		
		request.getRequestDispatcher("ViewUserList").forward(request, response);
		
		out.close();
	}

}
