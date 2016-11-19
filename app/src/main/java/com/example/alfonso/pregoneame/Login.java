package com.example.alfonso.pregoneame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Login extends AppCompatActivity {
    //COmponentes del layout
    private TextView textViewIs;
    private TextView textViewUser;
    private TextView textViewCon;
    private EditText editTextUserResult;
    private EditText editTextConResult;
    private Button buttonAceptar;
    private Button buttonHome;
    private ImageView imageViewPregon;
    private static final String FILE_NAME = "Login.txt";
    private static final String TAG = "Lab-UserInterface";
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textViewIs = (TextView) findViewById(R.id.textViewIs);

        textViewUser = (TextView) findViewById(R.id.textViewUser);

        textViewCon = (TextView) findViewById(R.id.textViewCon);

        editTextUserResult = (EditText) findViewById(R.id.editTextUserResult);

        editTextConResult = (EditText) findViewById(R.id.editTextConResult);
        imageViewPregon = (ImageView) findViewById(R.id.imageViewPregon);
        imageViewPregon.setImageResource(R.drawable.pregon);
        buttonAceptar = (Button) findViewById(R.id.buttonAceptar);

        buttonAceptar.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(editTextUserResult.getText().toString().equals("amonterom") && editTextConResult.getText().toString().equals("28101994")) {
                    Intent acti2 = new Intent(Login.this, ToDoManagerActivity.class);
                    startActivity(acti2);
                }
                else{

                    Toast toast = Toast.makeText(getApplicationContext(),"Usuario o contraseña incorrecta", Toast.LENGTH_LONG);
                    toast.show();

                }
            }
        });

        buttonHome = (Button) findViewById(R.id.buttonHome);
        buttonHome.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent acti = new Intent(Login.this, MainActivity.class);
                startActivity(acti);

            }
        });
    }




}
