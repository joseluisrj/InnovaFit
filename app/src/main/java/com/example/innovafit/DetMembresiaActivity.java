package com.example.innovafit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.math.BigDecimal;

public class DetMembresiaActivity extends AppCompatActivity {

    private static final String TAG = "DetMembresiaActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_det_membresia);

        getIncomingIntent();
    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        if(getIntent().hasExtra("id_membresia") && getIntent().hasExtra("nombre") &&
                getIntent().hasExtra("fecha_inicio") && getIntent().hasExtra("fecha_fin") &&
                getIntent().hasExtra("costo_mem") && getIntent().hasExtra("stock") &&
                getIntent().hasExtra("sesiones")){

            Log.d(TAG, "getIncomingIntent: found intent extras.");

            int idMembresia = getIntent().getIntExtra("id_mebresia", 0);
            String nombre = getIntent().getStringExtra("nombre");
            String fechaInicio = getIntent().getStringExtra("fecha_inicio");
            String fechaFin = getIntent().getStringExtra("fecha_fin");
            String costo = getIntent().getStringExtra(" costo_mem");
            int stock = getIntent().getIntExtra("stock", 0);
            int sesiones = getIntent().getIntExtra("sesiones", 0);

            System.out.println("costo================="+costo);

            TextView detIdMembership = findViewById(R.id.det_id_mem);
            TextView detNombre = findViewById(R.id.det_nombre_mem);
            TextView detFechaInicio = findViewById(R.id.det_fecha_inicio);
            TextView detFechaFin = findViewById(R.id.det_fecha_fin);
            TextView detCosto = findViewById(R.id.det_costo);
            TextView detSesiones = findViewById(R.id.det_sesiones);

            detIdMembership.setText(String.valueOf(idMembresia));
            detNombre.setText(nombre);
            detFechaInicio.setText(fechaInicio);
            detFechaFin.setText(fechaFin);
            detCosto.setText(costo);
            detSesiones.setText(stock + " / " + sesiones);

        }
    }
}
