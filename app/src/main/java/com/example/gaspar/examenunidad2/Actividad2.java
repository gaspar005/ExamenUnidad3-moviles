package com.example.gaspar.examenunidad2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Actividad2 extends AppCompatActivity {



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
    int sumaJug1 =0;
    int sumaJug2 =0;

    //Control de los 10 tiros
    int control10Tiros = 0;

    //control del jugador
    boolean jug1 = true;

    //ARRAYS PARA DIBUJAR CIRCULOS
    ArrayList<Float> arrayX = new ArrayList();
    ArrayList<Float> arrayY = new ArrayList();

    //controlar el contado
    boolean contador = true;


    public String mensaje1="";
    public String mensaje2 ="";
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

    public void popUp()
    {
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle(mensaje1);
        dialogo1.setMessage(mensaje2);
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {


            }
        });
        /*dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });*/
        dialogo1.show();
    }

    //se crea el canvas
    class Lienzo extends View
    {
        public Lienzo(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            puntoOrigenX = ancho/2;
            puntoOrigenY = alto/2;
            pOrigenTouchX =0;
            pOrigenTouchY = 0;
            distancia = 0;


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

            //se dibuja la suma de los puntos del jugador 1
            canvas.drawText("Jugador 1: "+Integer.toString(sumaJug1),factorX*3,factorX*3,pincel1);

            //se dibuja la suma de los puntos del jugador 1
            canvas.drawText("Jugador 2: "+Integer.toString(sumaJug2),ancho-(factorX*7),factorX*3,pincel1);

            //se dibujan los circulos del toque
            for (int x = 0; x<control10Tiros;x++)
            {
                canvas.drawCircle(arrayX.get(x),arrayY.get(x),10,pincel1);
            }

        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            //return super.onTouchEvent(event);
            pOrigenTouchX = event.getX();
            pOrigenTouchY = event.getY();

            contador = true;

            if(event.getAction() == MotionEvent.ACTION_DOWN)
            {
                if(control10Tiros<10)
                {
                    //se agregan los valores de las coordenadas de los circulos del toque
                    arrayX.add(pOrigenTouchX);
                    arrayY.add(pOrigenTouchY);

                    //se calcula la distancia con el teorema de pitagoras
                    float a = (pOrigenTouchY -puntoOrigenY);
                    float b = (pOrigenTouchX - puntoOrigenX);
                    distancia = (float) Math.sqrt((a*a)+(b*b));

                    for (int x = 0; x<10;x++)
                    {
                        if (distancia > (factorX *x) && distancia< (factorX*(x+1)) )
                        {
                            if(jug1)
                            {
                                sumaJug1+=10-x;
                            }else
                            {
                                sumaJug2+=10-x;
                            }


                        }
                    }

                }else
                {


                    if (jug1)
                    {
                        mensaje1 = "Juego terminado jugador 1";
                        mensaje2 = "Continua el siguiente jugador";
                        jug1 = false;
                    }else
                    {
                        mensaje1 ="Bien hecho!! juego terminado";

                        if (sumaJug1>sumaJug2)
                        {
                            mensaje2 = "Jugador 1: " + sumaJug1+" puntos"+"\n"+
                                       "Jugador 2: " + sumaJug2+" puntos" +"\n"+
                                       "El ganador es: Jugador 1 con "+sumaJug1 +" puntos";
                        }else if(sumaJug1<sumaJug2)
                        {
                            mensaje2 = "Jugador 1: " + sumaJug1+" puntos"+"\n"+
                                       "Jugador 2: " +sumaJug2 +" puntos"+"\n"+
                                       "El ganador es: Jugador 2 con "+sumaJug2 +" puntos";
                        }else if(sumaJug1==sumaJug2)
                        {
                            mensaje2 = "Jugador 1: " + sumaJug1+" puntos"+"\n"+
                                    "Jugador 2: " +sumaJug2 +" puntos"+"\n"+
                                    "Hubo un empate!!";
                        }

                        jug1=true;
                        sumaJug1=0;
                        sumaJug2=0;

                    }

                    arrayY.clear();
                    arrayX.clear();
                    control10Tiros=0;
                    popUp();
                    contador = false;
                }
                if (contador)
                {
                    control10Tiros++;
                }

            }

            invalidate();
            return true;
        }


    }
}
