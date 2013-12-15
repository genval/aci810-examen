package com.example.aci810_db.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.aci810_db.db.MyAppContract.Tasks;
import com.example.aci810_db.db.MyAppContract.Notes;
import com.example.aci810_db.model.Task;
import com.example.aci810_db.model.Note;



public class MyAppDataSource {

	private MyAppDbHelper dbHelper;
	private SQLiteDatabase db;
	
	private String[] allColumns = {
		    Tasks._ID,
		    Tasks.COLUMN_NAME_TASK_NAME,
		    Tasks.COLUMN_NAME_TASK_DESCRIPTION
		    //Tasks.COLUMN_NAME_DATE
		    };

	public MyAppDataSource(Context context) {
		this.dbHelper = new MyAppDbHelper(context);
	}
	
	public void open() throws SQLException {
		this.db = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}

	public Task createTask(String taskName, String taskDescription/*, DatePicker date*/) {
		ContentValues values = new ContentValues();
		values.put(Tasks.COLUMN_NAME_TASK_NAME, taskName);
		values.put(Tasks.COLUMN_NAME_TASK_DESCRIPTION, taskDescription);		
		//values.put(Tasks.COLUMN_NAME_DATE, date.getYear()+"-"+date.getMonth()+"-"+date.getDayOfMonth()); 
		
	
		
	    long insertId = db.insert(Tasks.TABLE_NAME, null, values);
	    
	    Cursor c = db.query(
	    		Tasks.TABLE_NAME,
	    		this.allColumns, Tasks._ID + " = " + insertId, 
	    		null,
	    		null, 
	    		null, 
	    		null
	    	);
	    c.moveToFirst();
	    
	    Task t = cursorToTask(c);
	    c.close();
	    
	    return t;
	
	}
	
	public Task updateTask(Task t, String taskName, String taskDescription/*,DatePicker date*/) {
		ContentValues values = new ContentValues();
		values.put(Tasks.COLUMN_NAME_TASK_NAME, taskName);
		values.put(Tasks.COLUMN_NAME_TASK_DESCRIPTION, taskDescription);
		//values.put(Tasks.COLUMN_NAME_DATE, date.getYear()+date.getMonth()+ date.getDayOfMonth());   
		
		
		
	    db.update(Tasks.TABLE_NAME, values, Tasks._ID + " = " + t.getId(), null);
	    
	    t.setTaskName(taskName);
	    t.setTaskDescription(taskDescription);
	    //t.setDate(date);
	    
	    return t;
	}
	
	public List<Task> getTasks() {
	    List<Task> tasks = new ArrayList<Task>();
	    
	    String sortOrder = Tasks.COLUMN_NAME_TASK_NAME + " DESC";
	    
	    Cursor c = db.query(
	    		Tasks.TABLE_NAME,	// The table to query
			    this.allColumns,	// The columns to return
			    null,				// The columns for the WHERE clause
			    null,				// The values for the WHERE clause
			    null,				// don't group the rows
			    null,				// don't filter by row groups
			    sortOrder			// The sort order
		    );

	    c.moveToFirst();
	    while (!c.isAfterLast()) {
	      Task t = cursorToTask(c);
	      tasks.add(t);
	      c.moveToNext();
	    }
	    
	    // make sure to close the cursor
	    c.close();
	    
	    return tasks;
	}
	
	public Task deleteTask(Task t) {
	    long id = t.getId();
	    db.delete(Tasks.TABLE_NAME, Tasks._ID + " = " + id, null);
	    t.setId(0);
	    return t;
	}

	
	private Task cursorToTask(Cursor cursor) {
		Task t = new Task();
	    t.setId(cursor.getLong(0));//
	    t.setTaskName(cursor.getString(1));
	    t.setTaskDescription(cursor.getString(2));
	  //  t.setDate(cursor.get(3));
	    
	    return t;
	}
	
	
	//NOTE
	
	
	private String[] allColumns1 = {
		    Notes._ID,
		    Notes.COLUMN_NAME_NOTE_NAME,
		    Notes.COLUMN_NAME_NOTE_DESCRIPTION
		    };



	public Note createNote(String noteName, String noteDescription) {
		ContentValues values = new ContentValues();
		values.put(Notes.COLUMN_NAME_NOTE_NAME, noteName);
		values.put(Notes.COLUMN_NAME_NOTE_DESCRIPTION, noteDescription);		
		
		
	
		
	    long insertId = db.insert(Notes.TABLE_NAME, null, values);
	    
	    Cursor c = db.query(
	    		Notes.TABLE_NAME,
	    		this.allColumns1, Notes._ID + " = " + insertId, 
	    		null,
	    		null, 
	    		null, 
	    		null
	    	);
	    c.moveToFirst();
	    
	    Note n = cursorToNote(c);
	    c.close();
	    
	    return n;
	
	}
	
	public Note updateNote(Note n, String noteName, String noteDescription) {
		ContentValues values = new ContentValues();
		values.put(Notes.COLUMN_NAME_NOTE_NAME, noteName);
		values.put(Notes.COLUMN_NAME_NOTE_DESCRIPTION, noteDescription);   
		
		
		
	    db.update(Notes.TABLE_NAME, values, Notes._ID + " = " + n.getId(), null);
	    
	    n.setNoteName(noteName);
	    n.setNoteDescription(noteDescription);
	   
	    
	    return n;
	}
	
	public List<Note> getNotes() {
	    List<Note> notes = new ArrayList<Note>();
	    
	//String sortOrder = Notes.COLUMN_NAME_NOTE_NAME + " DESC";
	    
	    Cursor c = db.query(
	    		Notes.TABLE_NAME,	// The table to query
			    this.allColumns,			// The columns to return
			    null,				// The columns for the WHERE clause
			    null,				// The values for the WHERE clause
			    null,				// don't group the rows
			    null,				// don't filter by row groups
			    null			// The sort order
		    );

	    c.moveToFirst();
	    while (!c.isAfterLast()) {
	      Note n = cursorToNote(c);
	      notes.add(n);
	      c.moveToNext();
	    }
	    
	    // make sure to close the cursor
	    c.close();
	    
	    return notes;
	}
	
	public Note deleteNote(Note n) {
	    long id = n.getId();
	    db.delete(Notes.TABLE_NAME, Notes._ID + " = " + id, null);
	    n.setId(0);
	    return n;
	}

	
	private Note cursorToNote(Cursor cursor) {
		Note n = new Note();
	    n.setId(cursor.getLong(0));//
	    n.setNoteName(cursor.getString(1));
	    n.setNoteDescription(cursor.getString(2));
	    
	    return n;
	}
}
