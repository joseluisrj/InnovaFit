package com.example.innovafit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class ReserveActivity extends AppCompatActivity {

    private static final String TAG = "ReserveActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);
        getIncomingIntent();
    }

    public void reservar(View view) {
        insertar();
        Intent intent = new Intent(ReserveActivity.this, ActivityOne.class);
        //EditText editText = (EditText) findViewById(R.id.editText);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        if(getIntent().hasExtra("id_clase") && getIntent().hasExtra("nombre") &&
                getIntent().hasExtra("entrenador") && getIntent().hasExtra("fecha") &&
                getIntent().hasExtra("hora_inicio") && getIntent().hasExtra("hora_fin") &&
                getIntent().hasExtra("cantidad") && getIntent().hasExtra("stock") &&
                getIntent().hasExtra("nombre_sede")){

            Log.d(TAG, "getIncomingIntent: found intent extras.");

            int idClase = getIntent().getIntExtra("id_clase", 0);
            String nombre = getIntent().getStringExtra("nombre");
            String entrenador = getIntent().getStringExtra("entrenador");
            String fecha = getIntent().getStringExtra("fecha");
            String horaInicio = getIntent().getStringExtra("hora_inicio");
            String horaFin = getIntent().getStringExtra("hora_fin");
            int cantidad = getIntent().getIntExtra("cantidad", 0);
            int stock = getIntent().getIntExtra("stock", 0);
            String nombreSede = getIntent().getStringExtra("nombre_sede");

            TextView idClass = findViewById(R.id.idClass);
            TextView classStock = findViewById(R.id.classStock);
            TextView classCount = findViewById(R.id.classCount);
            TextView className = findViewById(R.id.className);
            TextView classTrainer = findViewById(R.id.classTrainer);
            TextView classCalendar = findViewById(R.id.classCalendar);
            TextView classTime = findViewById(R.id.classTime);
            TextView classMap = findViewById(R.id.classMap);
            TextView classNum = findViewById(R.id.classNum);

            idClass.setText(String.valueOf(idClase));
            classStock.setText(String.valueOf(stock));
            classCount.setText(String.valueOf(cantidad));
            className.setText(nombre);
            classTrainer.setText(entrenador);
            classCalendar.setText(fecha);
            classTime.setText(horaInicio + " - " + horaFin);
            classMap.setText(nombreSede);
            classNum.setText(stock + " / " + cantidad);
        }
    }

    public void insertar(){

        TextView idClass = findViewById(R.id.idClass);
        TextView classStock = findViewById(R.id.classStock);
        TextView classCount = findViewById(R.id.classCount);


        String idClase = idClass.getText().toString();
        String claseStock = classStock.getText().toString();
        String claseCantidad = classCount.getText().toString();

        SharedPreferences prefs = getSharedPreferences("PREFERENCIAS", Context.MODE_PRIVATE);
        Integer idMembresia = prefs.getInt("id_membresia", 0);
        String fechaInicio = prefs.getString("fecha_inicio", "");
        String fechaFin = prefs.getString("fecha_fin", "");
        Integer stock = prefs.getInt("stock", 0);
        Integer cantidad = prefs.getInt("cantidad", 0);

        if (Integer.parseInt(claseStock) >= Integer.parseInt(claseCantidad)) {
            Toast toast= Toast.makeText(getApplicationContext(), "La clase no cuenta con cupos disponibles", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
            return;
        }

        if (stock >= cantidad) {
            Toast toast= Toast.makeText(getApplicationContext(), "No cuenta con clases disponibles en su membresía", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
            return;
        }


        ReservaDAO reservaDAO = new ReservaDAO(getBaseContext());
        ClaseDAO claseDAO = new ClaseDAO(getBaseContext());
        MembresiaDAO membresiaDAO = new MembresiaDAO((getBaseContext()));
        try {
            reservaDAO.insertar(Integer.parseInt(idClase), idMembresia);
            claseDAO.actualizarStock(Integer.parseInt(idClase));
            membresiaDAO.actualizarStock(idMembresia);

            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("stock", stock + 1 );
            editor.commit();


            Toast toast= Toast.makeText(getApplicationContext(), "La reserva se registró correctamente", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();

        } catch (DAOException e) {
            Log.i("RegisterActivity", "====> " + e.getMessage());
        }
    }
}
