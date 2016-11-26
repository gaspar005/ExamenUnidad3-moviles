package com.example.gaspar.examenunidad2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnActividad2,btnActividad1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnActividad2 = (Button)findViewById(R.id.btnAct2);
        btnActividad2.setOnClickListener(this);

        btnActividad1 =(Button)findViewById(R.id.btnAct1);
        btnActividad1.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btnAct1:
                Intent intent1 = new Intent(this,Actividad1.class);
                startActivity(intent1);
                break;
            case R.id.btnAct2:
                Intent intent = new Intent(this,Actividad2.class);
                startActivity(intent);
                break;

        }

    }
}
