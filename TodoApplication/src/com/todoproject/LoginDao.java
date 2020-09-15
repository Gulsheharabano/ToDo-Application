package com.todoproject;
import java.sql.*;
public class LoginDao {

	public static boolean validate(String email,String password){
		boolean status=false;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection con1=null;
		PreparedStatement ps1=null;
		ResultSet rs1=null;
		try{
			con=ConProvider.getConnection();
			ps=con.prepareStatement("select * from user where email=? and password=? and activestatus=? and role=?");
			ps.setString(1,email);
			ps.setString(2,password);
			ps.setString(3,"yes");
			ps.setString(4,"user");
			rs=ps.executeQuery();
			
			if(rs.next()){
				LoginServlet.currentid=rs.getInt("userid");
				LoginServlet.getstat("user");
				status=true;
			}
			else 
			{
				con1=ConProvider.getConnection();
				ps1=con.prepareStatement("select * from user where email=? and password=? and activestatus=? and role=?");
				ps1.setString(1,email);
				ps1.setString(2,password);
				ps1.setString(3,"yes");
				ps1.setString(4,"admin");
				rs1=ps1.executeQuery();
			
				if(rs1.next()){
					LoginServlet.currentid=rs1.getInt("userid");
					LoginServlet.getstat("admin");
					status=true;
				}
			
			}
			
		}catch(Exception e){System.out.println(e);}
		/*finally {
			try {
				rs.close();
				rs1.close();
				ps.close();
				ps1.close();
				con.close();
				con1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}*/
		
		return status;
	}
}
