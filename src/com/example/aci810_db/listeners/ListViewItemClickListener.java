package com.example.aci810_db.listeners;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.example.examen810_db.TaskActivity;
import com.example.examen810_db.TasksActivity;
import com.example.aci810_db.model.Task;
import com.example.examen810_db.NoteActivity;
import com.example.examen810_db.NotesActivity;
import com.example.aci810_db.model.Note;


public class ListViewItemClickListener implements AdapterView.OnItemClickListener {

	private Activity activity;
	
	public ListViewItemClickListener(Activity activity) {
		this.activity = activity;
	}
	
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Object o =  parent.getItemAtPosition(position);
		
		if(o instanceof Task)
		{
			Task task = (Task)o;
			Intent i = new Intent(this.activity, TasksActivity.class);
			i.putExtra("task", task);
			this.activity.startActivityForResult(i, TaskActivity.REQUEST_CODE_UPDATE_TASK);			
		}
		
		
		else if(o instanceof Note)
		{
			Note note = (Note) o;
			Intent i = new Intent(this.activity, NotesActivity.class);
			i.putExtra("note", note);
			this.activity.startActivityForResult(i, NoteActivity.REQUEST_CODE_UPDATE_NOTE);			
		}
	}
	


}
