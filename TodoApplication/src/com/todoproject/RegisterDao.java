package com.todoproject;

import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegisterDao {

	public static int save(String userid, String firstname, String lastname, String gender, String dob, String email, String password,
			String confirmpass, String activestatus,String regdate,String role) {
		int status = 0;
		Connection con= null;
		PreparedStatement pst1 = null;
		PreparedStatement ps= null;
		ResultSet rs = null;
		java.sql.Date sqlDOB = Formatter.getSqlDate(dob);
		try {
			con = ConProvider.getConnection();
			pst1 = con.prepareStatement("select max(userid)+1 from user");
			rs = pst1.executeQuery();
			String user_id = "";
			while (rs.next()) 
			{
				user_id = rs.getString(1);
			}
			
			ps = con.prepareStatement("insert into user(userid,firstname,lastname,gender,dob,email,password,confirmpass,activestatus,regdate,role) values(?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, user_id.toString());
			ps.setString(2, firstname);
			ps.setString(3, lastname);
			ps.setString(4, gender);
			ps.setDate(5, sqlDOB);
			ps.setString(6, email);
			ps.setString(7, password);
			ps.setString(8, confirmpass);
			ps.setString(9, "yes");
			ps.setDate(10,Formatter.getCurrentDate());
			ps.setString(11,"user");

			status = ps.executeUpdate();
			
		} 
		catch(SQLIntegrityConstraintViolationException e)
		{
			//System.out.println("Your mailid already register");
			return 100;
		}
		catch (Exception e) {
			System.out.println(e);
		}
		finally {
			try {
				rs.close();
				pst1.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return status;
	}
	public static List<UserBean> getAllRecords(){
		List<UserBean> list=new ArrayList<UserBean>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			con=ConProvider.getConnection();
			ps=con.prepareStatement("select * from user where role='user'");
			rs=ps.executeQuery();
			//java.sql.Date sqlDOB = Formatter.getSqlDate(dob);
			while(rs.next()){
				UserBean bean=new UserBean();
				bean.setUserid(rs.getInt(1));
				bean.setFirstname(rs.getString(2));
				bean.setLastname(rs.getString(3));
				bean.setGender(rs.getString(4));
				bean.setDob(rs.getDate(5));
				bean.setEmail(rs.getString(6));
				bean.setPassword(rs.getString(7));
				bean.setConfirmpass(rs.getString(8));
				bean.setActivestatus(rs.getString(9));
				bean.setRdate(rs.getDate(10));
				bean.setRole(rs.getString(11));
				list.add(bean);
			}
			
		}catch(Exception ex){System.out.println(ex);}
		finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
		return list;
	}
	
	 
}
