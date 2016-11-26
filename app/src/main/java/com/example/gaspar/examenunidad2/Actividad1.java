package com.example.gaspar.examenunidad2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Actividad1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_actividad_1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);

        int id = item.getItemId();

        switch (id)
        {
            case R.id.agregaCotizacion:
                //Toast.makeText(this,"hola",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,addCotizacion.class);
                startActivity(intent);
                break;
        }

        return true;
    }
}
