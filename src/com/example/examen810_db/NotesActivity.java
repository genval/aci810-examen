package com.example.examen810_db;


import com.example.examen.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.aci810_db.db.MyAppDataSource;
import com.example.aci810_db.model.Note;

public class NotesActivity extends Activity {
	private MyAppDataSource ds;
	private Note noteToUpdate;
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notes);
		setupActionBar();

		
		ds = new MyAppDataSource(this);
	    ds.open();
	    
	    Intent i = this.getIntent();
	    
	    
	    if(i.hasExtra(NoteActivity.EXTRA_NOTE))
	    {
	    	Note n = (Note) i.getSerializableExtra(NoteActivity.EXTRA_NOTE);
	    	
	    	EditText nameNote = (EditText) this.findViewById(R.id.nameNote);
	    	nameNote.setText(n.getNoteName());
			
			EditText descriptionNote = (EditText) this.findViewById(R.id.descriptionNote);
			descriptionNote.setText(n.getNoteDescription());
			
			
			Button saveButton = (Button) this.findViewById(R.id.saveButton);
			saveButton.setText("Update");
			
			Button deleteButton = (Button) this.findViewById(R.id.deleteButton);
			deleteButton.setVisibility(Button.VISIBLE);
			
			this.setTitle("Update Note");
			
			this.noteToUpdate = n;
	    }
	    else
	    {
	    	Button saveButton = (Button) this.findViewById(R.id.saveButton);
	    	
	    	saveButton.setText("Create");
	    	
	    	Button deleteButton = (Button) this.findViewById(R.id.deleteButton);
	    	deleteButton.setVisibility(Button.VISIBLE);
	    	
	    	this.setTitle("Create Note");
	    	
	    	this.noteToUpdate = null;
	    }
	    
	}
	
	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notes, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onSaveButtonClicked(View view) { 
		EditText noteName = (EditText) this.findViewById(R.id.nameNote);
		String nameNote = noteName.getText().toString();
		
		EditText descriptionNote = (EditText) this.findViewById(R.id.descriptionNote);
		String noteDescription = descriptionNote.getText().toString();
		
		
		if(nameNote.isEmpty() || noteDescription.isEmpty())
		{
			Toast.makeText(this, "Complete the form before saving", Toast.LENGTH_LONG).show();
			return;
		}
		
		Note n = null;
		
		if(this.noteToUpdate != null)
		{
			n = ds.updateNote(this.noteToUpdate, nameNote, noteDescription);
		}
		else
		{
			
		    n = ds.createNote(nameNote, noteDescription); 
		
			
		}
		
		Intent i = new Intent();
		i.putExtra(NoteActivity.EXTRA_NOTE, n);
		i.putExtra(NoteActivity.EXTRA_REMOVE, false);
		this.setResult(RESULT_OK, i);
		
		this.finish();
	}
	
	public void onDeleteButtonClicked(View view) {
		
		Note n = ds.deleteNote(this.noteToUpdate);
		
		Intent i = new Intent();
		i.putExtra(NoteActivity.EXTRA_NOTE, n);
		i.putExtra(NoteActivity.EXTRA_REMOVE, true);
		this.setResult(RESULT_OK, i);
		
		this.finish();
	}
	
	@Override
	protected void onResume() {
		ds.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		ds.close();
		super.onPause();
	}

}
