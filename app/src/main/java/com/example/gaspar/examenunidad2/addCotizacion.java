package com.example.gaspar.examenunidad2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

public class addCotizacion extends AppCompatActivity implements View.OnClickListener {

    Button btnGuardar;
    DatePicker txtDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cotizacion);
        btnGuardar = (Button)findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(this);

        txtDate = (DatePicker) findViewById(R.id.datePicker);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btnGuardar:
                int dia = txtDate.getDayOfMonth();
                int mes = txtDate.getMonth();
                int anio = txtDate.getYear();

                //int x = (int) txtDate.getMaxDate();

                Toast.makeText(getApplicationContext(),Integer.toString(dia),Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
