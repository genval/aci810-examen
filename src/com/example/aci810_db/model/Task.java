package com.example.aci810_db.model;

import java.io.Serializable;

public class Task implements Serializable {

	public static final long serialVersionUID = 7526472295622776147L;
	
	private long id;
	private String taskName;
	private String taskDescription;
	private String date;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	  public String toString() {
	    return this.taskName;
	  }

}
