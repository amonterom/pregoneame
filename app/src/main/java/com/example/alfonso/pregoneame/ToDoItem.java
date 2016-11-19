package com.example.alfonso.pregoneame;

import android.content.Intent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

// Do not modify 

public class ToDoItem {

	public static final String ITEM_SEP = System.getProperty("line.separator");
	public enum Tema {
		Deporte,Cultura,Festejo,Otros
	};



	public final static String TITLE = "title";
	public final static String DESCRIPCION = "descripcion";
	public final static String TEMA = "tema";
	public final static String DATE = "date";
	//public final static String ELIMINAR = "eliminar";

	public final static String FILENAME = "filename";

	public final static SimpleDateFormat FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss", Locale.US);

	private String mTitle = new String();
	private Tema mTema = Tema.Otros;
	private String mDes = new String();
	private Date mDate = new Date();

	ToDoItem(String title,String desc,Tema tema, Date date) {
		this.mTitle = title;
		this.mDes = desc;
		this.mTema = tema;
		this.mDate = date;
	}

	// Create a new ToDoItem from data packaged in an Intent

	ToDoItem(Intent intent) {

		mTitle = intent.getStringExtra(ToDoItem.TITLE);
		mDes = intent.getStringExtra(ToDoItem.DESCRIPCION);
		mTema = Tema.valueOf(intent.getStringExtra(ToDoItem.TEMA));


		try {
			mDate = ToDoItem.FORMAT.parse(intent.getStringExtra(ToDoItem.DATE));
		} catch (ParseException e) {
			mDate = new Date();
		}
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}


	public String getDescripcion() {
		return mDes;
	}

	public void setDescripcion(String desc) {
		mDes = desc;
	}

	public Tema getTema() {
		return mTema;
	}

	public void setTema(Tema tema) {
		mTema = tema;
	}






	public Date getDate() {
		return mDate;
	}

	public void setDate(Date date) {
		mDate = date;
	}

	// Take a set of String data values and 
	// package them for transport in an Intent

	public static void packageIntent(Intent intent, String title,String des,Tema tema,
									  String date) {

		intent.putExtra(ToDoItem.TITLE, title);

		intent.putExtra(ToDoItem.DESCRIPCION, des);
		intent.putExtra(ToDoItem.TEMA, tema.toString());
		intent.putExtra(ToDoItem.DATE, date);
	
	}

	public String toString() {
		return mTitle + ITEM_SEP + mDes + ITEM_SEP + mTema + ITEM_SEP
				+ FORMAT.format(mDate);
	}

	public String toLog() {
		return "Title:" + mTitle + ITEM_SEP + "Descripcion:" + mDes
			+ ITEM_SEP + "Tema:" + mTema + ITEM_SEP  + "Date:"
				+ FORMAT.format(mDate);
	}

}
