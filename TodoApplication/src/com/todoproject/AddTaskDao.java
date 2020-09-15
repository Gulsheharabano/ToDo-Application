package com.todoproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddTaskDao {
	public static int save(String taskid, String taskname, String description, String taskstatus, String userid, String taskdate) {
		int status = 0;
		Connection con=null;
		PreparedStatement pst1=null;
		ResultSet rs=null;
		try {
			con = ConProvider.getConnection();
			pst1 = con.prepareStatement("select max(taskid)+1 from tasklist");
			rs = pst1.executeQuery();
			String task_id = "";
			while (rs.next()) 
			{
				task_id = rs.getString(1);
			}
			int uid=LoginServlet.currentid;
			PreparedStatement ps = con.prepareStatement(
					"insert into tasklist(taskid,taskname,description,taskstatus,userid,taskdate) values(?,?,?,?,?,?)");
			ps.setString(1, task_id.toString());
			ps.setString(2, taskname);
			ps.setString(3, description);
			ps.setString(4, "uncomplite");
			ps.setInt(5, uid);
			ps.setDate(6,Formatter.getCurrentDate());

			status = ps.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
		finally {
			try {
				rs.close();
				pst1.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return status;
	}
	public static int update(AddTaskBean bean){
		int status=0;
		Connection con=null;
		PreparedStatement ps=null;
		try{
			con=ConProvider.getConnection();
			ps=con.prepareStatement("update tasklist set taskname=?,description=? where taskid=?");
			ps.setString(1,bean.getTaskname());
			ps.setString(2,bean.getDescription());
			ps.setInt(3,bean.getTaskid());
			
			status=ps.executeUpdate();
			con.close();
		}catch(Exception ex){System.out.println(ex);}
		finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return status;
	}	

	public static int delete(int id){
		int status=0;
		Connection con=null;
		PreparedStatement ps=null;
		try{
			con=ConProvider.getConnection();
			ps=con.prepareStatement("delete from tasklist where taskid=?");
			ps.setInt(1,id);
			status=ps.executeUpdate();
			con.close();
		}catch(Exception ex){System.out.println(ex);}
		finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return status;
	}

	public static List<AddTaskBean> getAllRecords(){
		List<AddTaskBean> list=new ArrayList<AddTaskBean>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			con=ConProvider.getConnection();
			ps=con.prepareStatement("select * from tasklist");
			rs=ps.executeQuery();
			while(rs.next()){
				AddTaskBean bean=new AddTaskBean();
				bean.setTaskid(rs.getInt(1));
				bean.setTaskname(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setTaskstatus(rs.getString(4));
				bean.setUserid(rs.getInt(5));
				list.add(bean);
			}
		}catch(Exception ex){System.out.println(ex);}
		finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	public static AddTaskBean getRecordById(int id){
		AddTaskBean bean=new AddTaskBean();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			con=ConProvider.getConnection();
			ps=con.prepareStatement("select * from tasklist where taskid=?");
			ps.setInt(1,id);
			rs=ps.executeQuery();
			while(rs.next()){
				bean.setTaskid(rs.getInt(1));
				bean.setTaskname(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setTaskstatus(rs.getString(4));
				bean.setUserid(rs.getInt(5));
			}
			con.close();
		}catch(Exception ex){System.out.println(ex);}
		finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return bean;
	}


}
