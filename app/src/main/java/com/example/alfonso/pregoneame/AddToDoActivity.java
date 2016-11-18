package com.example.alfonso.pregoneame;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;


import com.example.alfonso.pregoneame.ToDoItem.Tema;
public class AddToDoActivity extends AppCompatActivity {
	
	// 7 days in milliseconds - 7 * 24 * 60 * 60 * 1000
	private static final int SEVEN_DAYS = 604800000;

	private static final String TAG = "Lab-UserInterface";

	private static String timeString;
	private static String dateString;
	private static TextView dateView;
	private static TextView timeView;

	
	private Date mDate;
	private EditText mTitleText;
	private EditText mDesText;
	private RadioGroup mTemaRadioGroup;
	private RadioButton mDefaultTemaButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_todo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

		mTitleText = (EditText) findViewById(R.id.title);
		mDesText = (EditText) findViewById(R.id.Descripcion);
		mDefaultTemaButton = (RadioButton) findViewById(R.id.Otros);
		mTemaRadioGroup = (RadioGroup) findViewById(R.id.temaGroup);


		dateView = (TextView) findViewById(R.id.date);
		timeView = (TextView) findViewById(R.id.time);

		// Set the default date and time

		setDefaultDateTime();

		// OnClickListener for the Date button, calls showDatePickerDialog() to show
		// the Date dialog

		final Button datePickerButton = (Button) findViewById(R.id.date_picker_button);
		datePickerButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDatePickerDialog();
			}
		});

		// OnClickListener for the Time button, calls showTimePickerDialog() to show
		// the Time Dialog

		final Button timePickerButton = (Button) findViewById(R.id.time_picker_button);
		timePickerButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showTimePickerDialog();
			}
		});

		// OnClickListener for the Cancel Button, 

		final Button cancelButton = (Button) findViewById(R.id.cancelButton);
		cancelButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				log("Entered cancelButton.OnClickListener.onClick()");

				//TODO - Implement onClick(). 

Intent data=new Intent();
setResult(RESULT_CANCELED, data);
				finish();
			}
		});

		//OnClickListener for the Reset Button

		final Button resetButton = (Button) findViewById(R.id.resetButton);
		resetButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				log("Entered resetButton.OnClickListener.onClick()");

				//TODO - Reset data fields to default values

			mTitleText.setText("");
			mDesText.setText("");
				mTemaRadioGroup.check(mDefaultTemaButton.getId());
				setDefaultDateTime();
			
			}
		});

		// OnClickListener for the Submit Button
		// Implement onClick().
		
		final Button submitButton = (Button) findViewById(R.id.submitButton);
		submitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				log("Entered submitButton.OnClickListener.onClick()");

				// Gather ToDoItem data  
				
				//TODO - Get Tema
Tema tema = getTema();

				//TODO -  Title
	String title = mTitleText.getText().toString();
				//TODO -  DEscripcion
				String des = mDesText.getText().toString();

				//TODO - Date
				String fullDate = dateString + " " + timeString;

				//TODO - Package ToDoItem data into an Intent

				Intent data = new Intent();
				ToDoItem.packageIntent(data, title, des,tema, fullDate);
				//TODO - return data Intent and finish			


				setResult(RESULT_OK, data);
				finish();
				
			}
		});
	}

    private void showDatePickerDialog() {
        //TODO - Create a Date Picker Dialog and show it

DialogFragment dateD = new DatePickerFragment();
		dateD.show(getFragmentManager(),"datepicker");
    }

    private void showTimePickerDialog() {
        //TODO - Create a Time Picker Dialog and show it
		DialogFragment timeD = new TimePickerFragment();
		timeD.show(getFragmentManager(),"timepicker");

    }

	// Do not modify below here
	
	// Use this method to set the default date and time
	
	private void setDefaultDateTime() {

		// Default is current time + 7 days
		mDate = new Date();
		mDate = new Date(mDate.getTime() + SEVEN_DAYS);

		Calendar c = Calendar.getInstance();
		c.setTime(mDate);

		setDateString(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
				c.get(Calendar.DAY_OF_MONTH));

		dateView.setText(dateString);

		setTimeString(c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),
				c.get(Calendar.MILLISECOND));

		timeView.setText(timeString);
	}

	private static void setDateString(int year, int monthOfYear, int dayOfMonth) {

		// Increment monthOfYear for Calendar/Date -> Time Format setting
		monthOfYear++;
		String mon = "" + monthOfYear;
		String day = "" + dayOfMonth;

		if (monthOfYear < 10)
			mon = "0" + monthOfYear;
		if (dayOfMonth < 10)
			day = "0" + dayOfMonth;

		dateString = year + "-" + mon + "-" + day;
	}

	private static void setTimeString(int hourOfDay, int minute, int mili) {
		String hour = "" + hourOfDay;
		String min = "" + minute;

		if (hourOfDay < 10)
			hour = "0" + hourOfDay;
		if (minute < 10)
			min = "0" + minute;

		timeString = hour + ":" + min + ":00";
	}

	// DialogFragment used to pick a ToDoItem deadline date

	public static class DatePickerFragment extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {

			// Use the current date as the default date in the picker

			final Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);

			// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
							  int dayOfMonth) {
			setDateString(year, monthOfYear, dayOfMonth);

			dateView.setText(dateString);
		}

	}

	// DialogFragment used to pick a ToDoItem deadline time

	public static class TimePickerFragment extends DialogFragment implements
			TimePickerDialog.OnTimeSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {

			// Use the current time as the default values for the picker
			final Calendar c = Calendar.getInstance();
			int hour = c.get(Calendar.HOUR_OF_DAY);
			int minute = c.get(Calendar.MINUTE);

			// Create a new instance of TimePickerDialog and return
			return new TimePickerDialog(getActivity(), this, hour, minute,
					true);
		}

		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			setTimeString(hourOfDay, minute, 0);

			timeView.setText(timeString);
		}
	}

	private void log(String msg) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Log.i(TAG, msg);
	}
	private Tema getTema() {

		switch (mTemaRadioGroup.getCheckedRadioButtonId()) {
			case R.id.Deporte: {
				return Tema.Deporte;
			}
			case R.id.Cultura: {
				return Tema.Cultura;
			}

			case R.id.Festejo: {
				return Tema.Festejo;
			}
			default: {
				return Tema.Otros;
			}
		}
	}

}
