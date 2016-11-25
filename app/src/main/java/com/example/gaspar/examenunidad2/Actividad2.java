package com.example.gaspar.examenunidad2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Actividad2 extends AppCompatActivity {

    List<color> colores = new ArrayList<color>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);

        crearColores();

        LinearLayout layout1 = (LinearLayout) findViewById(R.id.layout_tiro);
        Lienzo fondo = new Lienzo(this);
        layout1.addView(fondo);
    }

    private void crearColores() {
        colores.add(new color(22,204,49));
        colores.add(new color(171,204,22));
        colores.add(new color(204,108,22));
        colores.add(new color(204,116,22));
        colores.add(new color(204,80,22));
        colores.add(new color(97,105,39));
        colores.add(new color(159,39,193));
        colores.add(new color(95,39,193));
        colores.add(new color(39,188,193));
        colores.add(new color(255,0,0));

    }

    //se crea el canvas
    class Lienzo extends View
    {
        public Lienzo(Context context) {
            super(context);
        }


        float factorX =0;
        float radio = 0;
        float coordTextY =0;
        float coordTextX = 0;


        int ancho,alto;
        //SE CALCULA EL PUNTO TE ORIGEN
        float puntoOrigenX;
        float puntoOrigenY;

        //PUNTO DE ORIGEN TOUCH
        float pOrigenTouchX = 0, pOrigenTouchY = 0;
        //distancia
        float distancia;

        //suma de los puntos
        int suma;
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            puntoOrigenX = ancho/2;
            puntoOrigenY = alto/2;
            pOrigenTouchX =0;
            pOrigenTouchY = 0;
            distancia = 0;
            suma=0;

            factorX =0;
            radio = 0;
            coordTextY =0;
            coordTextX = 0;

            //SE OBTIENE EL ALTO Y ANCHO DE LA PANTALLA
            ancho = this.getMeasuredWidth();
            alto = this.getMeasuredHeight();

            //se calcula el factor x
            if(ancho<alto)
            {
                factorX = (ancho/2)/10;
            }else
            {
                factorX = (alto/2)/10;
            }


            //SE DEFINE UN OBJETO PINCEL
            Paint pincel1 = new Paint();
            pincel1.setStrokeWidth(3);
            pincel1.setAntiAlias(true);
            pincel1.setStyle(Paint.Style.FILL);
            pincel1.setTextSize(15);

            //se calcula el radio
            coordTextY = alto/2;
           if(ancho<alto)
            {
                radio = ancho/2;

            }else
            {
                radio = alto/2;
                coordTextX = radio+factorX;
                //coordTextY = ancho/2;
            }

            //se dibujan los 10 circulos
            for (int x=0; x<10;x++)
            {
                pincel1.setColor(Color.rgb(colores.get(x).getR(),colores.get(x).getG(),colores.get(x).getB()));

                canvas.drawCircle(ancho/2,alto/2,radio,pincel1);
                radio -= factorX;

            }

            //se dibujan los numeros
            pincel1.setTextSize(25);
            pincel1.setColor(Color.BLACK);
            for (int x = 0; x<10;x++)
            {
                canvas.drawText(Integer.toString(x+1),coordTextX,coordTextY,pincel1);
                coordTextX +=factorX;
            }

            //se dibuja la suma de los puntos
            canvas.drawText(Integer.toString(suma),100,100,pincel1);

        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            //return super.onTouchEvent(event);
            pOrigenTouchX = event.getX();
            pOrigenTouchY = event.getY();

            if(event.getAction() == MotionEvent.ACTION_DOWN)
            {

                float a = (pOrigenTouchY -puntoOrigenY);
                float b = (pOrigenTouchX - puntoOrigenX);
                distancia = (float) Math.sqrt((a*a)+(b*b));

                for (int x = 0; x<10;x++)
                {
                    if (distancia > (factorX *x) && distancia< (factorX*(x+1)) )
                    {
                        suma+=x+1;
                        Toast.makeText(getApplicationContext(),Integer.toString(suma),Toast.LENGTH_SHORT).show();
                        //algo
                    }
                }
                /*if(distancia<=30)
                {

                }*/
            }

            return true;
        }


    }
}
