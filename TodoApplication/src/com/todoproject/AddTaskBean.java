package com.todoproject;

public class AddTaskBean {
	int taskid;
	String taskname;
	String description;
	String taskstatus;
	int userid;
	
	public AddTaskBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AddTaskBean(int taskid, String taskname, String description, String taskstatus, int userid) {
		super();
		this.taskid = taskid;
		this.taskname = taskname;
		this.description = description;
		this.taskstatus = taskstatus;
		this.userid = userid;
	}
	public AddTaskBean(int taskid,String taskname, String description) {
		super();
		this.taskid = taskid;
		this.taskname = taskname;
		this.description = description;
	}
	public int getTaskid() {
		return taskid;
	}
	public void setTaskid(int taskid) {
		this.taskid = taskid;
	}
	public String getTaskname() {
		return taskname;
	}
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTaskstatus() {
		return taskstatus;
	}
	public void setTaskstatus(String taskstatus) {
		this.taskstatus = taskstatus;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}

}
