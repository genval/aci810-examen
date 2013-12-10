package com.example.examen810_db;



import com.example.examen.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


import com.example.aci810_db.db.MyAppDataSource;
import com.example.aci810_db.model.Task;

public class TasksActivity extends Activity {
	private MyAppDataSource ds;
	private Task taskToUpdate;
	DatePicker dia;
	Button boton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tasks);
		setupActionBar();

		
		dia =(DatePicker)findViewById(R.id.dpDia);
		boton = (Button)findViewById(R.id.btButton);
		
		boton.setOnClickListener(new OnClickListener(){	

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				final int mes = dia.getMonth();
				final int uno = 1;
				Toast.makeText(getBaseContext(), "El dia seleccionado es:"+
				dia.getYear()+ "/" + dia.getDayOfMonth()+"/"+ (mes + uno)  
					, Toast.LENGTH_LONG).show();
			}
					
		});
		
		ds = new MyAppDataSource(this);
	    ds.open();
	    
	    Intent i = this.getIntent();
	    
	    if(i.hasExtra(TaskActivity.EXTRA_TASK))
	    {
	    	Task t = (Task) i.getSerializableExtra(TaskActivity.EXTRA_TASK);
	    	
	    	EditText nameTask = (EditText) this.findViewById(R.id.nameTask);
	    	nameTask.setText(t.getTaskName());
			
			EditText descriptionTask = (EditText) this.findViewById(R.id.descriptionTask);
			descriptionTask.setText(t.getTaskDescription());
			
			DatePicker dpDia = (DatePicker) this.findViewById(R.id.dpDia);
			dpDia.updateDate(dia.getYear(),dia.getMonth() ,dia.getDayOfMonth());
			
			Button saveButton = (Button) this.findViewById(R.id.saveButton);
			saveButton.setText("Update");
			
			Button deleteButton = (Button) this.findViewById(R.id.deleteButton);
			deleteButton.setVisibility(Button.VISIBLE);
			
			this.setTitle("Update Task");
			
			this.taskToUpdate = t;
	    }
	    else
	    {
	    	Button saveButton = (Button) this.findViewById(R.id.saveButton);
	    	saveButton.setText("Create");
	    	
	    	Button deleteButton = (Button) this.findViewById(R.id.deleteButton);
	    	deleteButton.setVisibility(Button.VISIBLE);
	    	
	    	this.setTitle("Create Task");
	    	
	    	this.taskToUpdate = null;
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
		getMenuInflater().inflate(R.menu.tasks, menu);
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
		EditText taskName = (EditText) this.findViewById(R.id.nameTask);
		String nameTask = taskName.getText().toString();
		
		EditText descriptionTask = (EditText) this.findViewById(R.id.descriptionTask);
		String taskDescription = descriptionTask.getText().toString();
		
		DatePicker date = (DatePicker) this.findViewById(R.id.dpDia);
		int day = date.getDayOfMonth();
		int month = date.getMonth();
		int year = date.getYear();
		
		if(nameTask.isEmpty() || taskDescription.isEmpty() || date.isEnabled())
		{
			Toast.makeText(this, "Complete the form before saving", Toast.LENGTH_LONG).show();
			return;
		}
		
		Task t = null;
		
		if(this.taskToUpdate != null)
		{
			t = ds.updateTask(this.taskToUpdate, nameTask, taskDescription, date);
		}
		else
		{
			t = ds.createTask(nameTask, taskDescription, date);
		}
		
		Intent i = new Intent();
		i.putExtra(TaskActivity.EXTRA_TASK, t);
		i.putExtra(TaskActivity.EXTRA_REMOVE, false);
		this.setResult(RESULT_OK, i);
		
		this.finish();
	}
	
	public void onDeleteButtonClicked(View view) {
		
		Task p = ds.deleteTask(this.taskToUpdate);
		
		Intent i = new Intent();
		i.putExtra(TaskActivity.EXTRA_TASK, p);
		i.putExtra(TaskActivity.EXTRA_REMOVE, true);
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
