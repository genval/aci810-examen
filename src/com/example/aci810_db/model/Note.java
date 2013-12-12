package com.example.aci810_db.model;

import java.io.Serializable;


public class Note implements Serializable {

	public static final long serialVersionUID = 7526472295622776147L;
	
	private long id;
	private String noteName;
	private String noteDescription;

	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNoteName() {
		return noteName;
	}
	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}
	public String getNoteDescription() {
		return noteDescription;
	}
	public void setNoteDescription(String noteDescription) {
		this.noteDescription = noteDescription;
	}

	@Override
	  public String toString() {
	    return this.noteName;
	  }

}
