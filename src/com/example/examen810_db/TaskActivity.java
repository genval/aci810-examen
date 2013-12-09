package com.example.examen810_db;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.examen.R;

public class TaskActivity extends ListActivity {
	
	public static final int REQUEST_CODE_ADD_TASK = 1;
	public static final int REQUEST_CODE_UPDATE_TASK = 2;
	
	public static final String EXTRA_TASK = "task";
	public static final String EXTRA_REMOVE = "remove";
	
	DatePicker dia;
	Button boton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task);
		
		dia =(DatePicker)findViewById(R.id.dpDia);
		boton = (Button)findViewById(R.id.btButton);
		
		boton.setOnClickListener(new OnClickListener(){
	

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				final int mes = dia.getMonth();
				final int uno = 1;
				Toast.makeText(getBaseContext(), "El dia seleccionado es:"+
					(mes + uno) + "/" + dia.getDayOfMonth() +
					"/" + dia.getYear(), Toast.LENGTH_LONG).show();
			}
			
		
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.task, menu);
		return true;
	}

}
