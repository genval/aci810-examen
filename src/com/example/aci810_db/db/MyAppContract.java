package com.example.aci810_db.db;

import android.provider.BaseColumns;

public final class MyAppContract {

	public MyAppContract() {
		// this empty constructor prevent accidentally instantiating the contract class
	}
	
	public static abstract class Tasks implements BaseColumns {
		public static final String TABLE_NAME = "tasks";
		public static final String COLUMN_NAME_TASK_NAME = "task_name";
		public static final String COLUMN_NAME_TASK_DESCRIPTION = "task_description";
		public static final String COLUMN_NAME_DATE = "date_task";
	}


}