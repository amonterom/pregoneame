package com.example.alfonso.pregoneame;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    //COmponentes del layout
    private TextView textViewBienvenida;
    private TextView textViewIntroduccion;
    private TextView textViewCodigoPostal;
    private EditText editTextCodigoPostalResult;
    private Button buttonAceptar;
    private ImageView imageViewPregon;

    private void inicializarComponentes() {
        textViewBienvenida = (TextView) findViewById(R.id.textViewBienvenida);
        textViewIntroduccion = (TextView) findViewById(R.id.textViewIntroduccion);
        textViewCodigoPostal = (TextView) findViewById(R.id.textViewCodigoPostal);
        editTextCodigoPostalResult = (EditText) findViewById(R.id.editTextCodigoPostalResult);
        imageViewPregon = (ImageView) findViewById(R.id.imageViewPregon);
        imageViewPregon.setImageResource(R.drawable.pregon);
        buttonAceptar = (Button) findViewById(R.id.buttonAceptar);

        buttonAceptar.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
               //Crear un nuevo Inten e ir a la otra pantalla
            }
        });


    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarComponentes();
    }
}