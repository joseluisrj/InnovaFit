package com.example.innovafit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DetalleNotifActivity extends AppCompatActivity {

    private static final String TAG = "DetalleNotifActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_notif);

        getIncomingIntent();
    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        if(getIntent().hasExtra("titulo") && getIntent().hasExtra("detalle")) {

            Log.d(TAG, "getIncomingIntent: found intent extras.");

            String titulo = getIntent().getStringExtra("titulo");
            String detalle = getIntent().getStringExtra("detalle");;

            TextView txtTitulo = findViewById(R.id.textTitulo);
            TextView txtMensaje = findViewById(R.id.textMensaje);


            txtTitulo.setText(titulo);
            txtMensaje.setText(detalle);

        }
    }
}
