package com.example.examen810_db;



import com.example.examen.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class TasksActivity extends Activity {
	private MyAppDataSource ds;
	private Task taskToUpdate;
	DatePicker dia;
	Button boton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tasks);
		
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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tasks, menu);
		return true;
	}
	
	public void onDeleteButtonClicked(View view) {
		
		Task p = ds.deletePerson(this.taskToUpdate);
		
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
