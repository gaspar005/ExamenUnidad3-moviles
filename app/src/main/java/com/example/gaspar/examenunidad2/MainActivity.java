package com.example.gaspar.examenunidad2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnActividad2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnActividad2 = (Button)findViewById(R.id.btnAct2);
        btnActividad2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btnAct2:
                Intent intent = new Intent(this,Actividad2.class);
                startActivity(intent);
                break;
        }

    }
}
