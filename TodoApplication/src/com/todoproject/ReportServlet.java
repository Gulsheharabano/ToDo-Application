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

@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static int new_user;
	static int active_user;
	static int new_task;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		Count();
		out.println(new_user);
		out.println(new_task);
		out.println(active_user);
		out.println("<!DOCTYPE HTML>");
		out.println("<html>"); 
		out.println("<head>");
		out.println("<meta charset='UTF-8'>"); 
		out.println("<script>");
		out.println("window.onload = function() {" +  
				"		var chart = new CanvasJS.Chart('chartContainer', {" + 
				"			animationEnabled : true," + 
				"			theme : 'light2', // \"light1\", \"light2\", \"dark1\", \"dark2\"\r\n" + 
				"			title : {" + 
				"				text : 'Last seven days report'" + 
				"			}," + 
				"			axisY : {" + 
				"				title : \"\"" + 
				"			}," + 
				"			data : [ {" + 
				"				type : 'column'," + 
				"				showInLegend : true," + 
				"				legendMarkerColor : \"grey\"," + 
				"				legendText : \"list of data\"," + 
				"				dataPoints : [" + 
				"					{ y:"+new_user+", label: \"New User\" }," + 
				"					{ y: "+active_user+",  label: \"Active User\" }," + 
				"					{ y: "+new_task+",  label: \"Total Work\" }" + 
				"				 ]" + 
				"			} ]" + 
				"		});" + 
				"		chart.render();" + 
				"	}" + 
				"</script>" + 
				"</head>" + 
				"<body>");
				try {
					request.getRequestDispatcher("adminHome.html").include(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				}
				out.println("	<div id=\"chartContainer\"" + 
				"		style=\"height: 370px; max-width: 920px; margin: 0px auto;\"></div>" + 
				"	<script src=\"canvasjs.min.js\"></script>" + 
				"</body>" + 
				"</html>");
				out.close();
	}

	public static void Count() {

		Statement stm = null;
		ResultSet rs = null;
		Connection con=null;

		try {
			con = ConProvider.getConnection();
			stm = con.createStatement();
			rs = stm.executeQuery("select * from user where regdate >= (curdate() - interval 7 day)");
			new_user = 0;
			active_user = 0;

			while (rs.next()) {
				new_user++;
				if (rs.getString("activestatus").equals("yes")) {
					active_user++;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		Statement stm1 = null;
		ResultSet rs1 = null;
		Connection con1=null;
		try {
			con1 = ConProvider.getConnection();

			stm1 = con1.createStatement();
			rs1 = stm1.executeQuery("select * from tasklist where taskdate >= (curdate() - interval 7 day)");
			new_task = 0;

			while (rs1.next()) {
				new_task++;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				rs1.close();
				stm1.close();
				con1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
