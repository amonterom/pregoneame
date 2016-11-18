package com.example.alfonso.pregoneame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
