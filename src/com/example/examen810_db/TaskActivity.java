package com.example.examen810_db;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import com.example.examen.R;

public class TaskActivity extends ListActivity {
	
	public static final int REQUEST_CODE_ADD_TASK = 1;
	public static final int REQUEST_CODE_UPDATE_TASK = 2;
	
	public static final String EXTRA_TASK = "task";
	public static final String EXTRA_REMOVE = "remove";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task);		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.task, menu);
		return true;
	}
	
	public void onAddButtonClicked(View view){
		Intent i = new Intent(this, TasksActivity.class);
		this.startActivityForResult(i, REQUEST_CODE_ADD_TASK);
	}

}
