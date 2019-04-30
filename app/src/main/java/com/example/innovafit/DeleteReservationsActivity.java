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
import android.widget.TextView;
import android.widget.Toast;

public class DeleteReservationsActivity extends AppCompatActivity {

    private static final String TAG = "DeleteReservatiActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_reservations);

        getIncomingIntent();
    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        if(getIntent().hasExtra("id_reserva") && getIntent().hasExtra("id_clase") &&
                getIntent().hasExtra("id_membresia") && getIntent().hasExtra("nombre") &&
                getIntent().hasExtra("entrenador") && getIntent().hasExtra("fecha") &&
                getIntent().hasExtra("hora_inicio") && getIntent().hasExtra("hora_fin") &&
                getIntent().hasExtra("nombre_sede")){

            Log.d(TAG, "getIncomingIntent: found intent extras.");

            int idReserva = getIntent().getIntExtra("id_reserva", 0);
            int idClase = getIntent().getIntExtra("id_clase", 0);
            int idMembresia = getIntent().getIntExtra("id_membresia", 0);
            String nombre = getIntent().getStringExtra("nombre");
            String entrenador = getIntent().getStringExtra("entrenador");
            String fecha = getIntent().getStringExtra("fecha");
            String horaInicio = getIntent().getStringExtra("hora_inicio");
            String horaFin = getIntent().getStringExtra("hora_fin");
            String nombreSede = getIntent().getStringExtra("nombre_sede");

            TextView idReserve = findViewById(R.id.idReserveDel);
            TextView idClass = findViewById(R.id.idClassDel);
            TextView idMembership = findViewById(R.id.idMembershipDel);
            TextView className = findViewById(R.id.reserveName);
            TextView classTrainer = findViewById(R.id.reserveTrainer);
            TextView classCalendar = findViewById(R.id.reserveCalendar);
            TextView classTime = findViewById(R.id.reserveTime);
            TextView classMap = findViewById(R.id.reserveMap);

            idReserve.setText(String.valueOf(idReserva));
            idClass.setText(String.valueOf(idClase));
            idMembership.setText(String.valueOf(idMembresia));
            className.setText(nombre);
            classTrainer.setText(entrenador);
            classCalendar.setText(fecha);
            classTime.setText(horaInicio + " - " + horaFin);
            classMap.setText(nombreSede);
        }
    }

    public void eliminar(View view) {
        delete();
        Intent intent = new Intent(DeleteReservationsActivity.this, ActivityThree.class);
        //EditText editText = (EditText) findViewById(R.id.editText);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void delete(){
        TextView idReserveDel = findViewById(R.id.idReserveDel);
        TextView idClassDel = findViewById(R.id.idClassDel);
        TextView idMembershipDel = findViewById(R.id.idMembershipDel);


        String idReserva = idReserveDel.getText().toString();
        String idClase = idClassDel.getText().toString();
        String idMembresia = idMembershipDel.getText().toString();

        SharedPreferences prefs = getSharedPreferences("PREFERENCIAS", Context.MODE_PRIVATE);
        Integer stock = prefs.getInt("stock", 0);

        ReservaDAO reservaDAO = new ReservaDAO(getBaseContext());
        ClaseDAO claseDAO = new ClaseDAO(getBaseContext());
        MembresiaDAO membresiaDAO = new MembresiaDAO((getBaseContext()));
        try {
            reservaDAO.eliminar(Integer.parseInt(idReserva));
            claseDAO.actualizarStockNeg(Integer.parseInt(idClase));
            membresiaDAO.actualizarStockNeg(Integer.parseInt(idMembresia));

            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("stock", stock - 1 );
            editor.commit();


            Toast toast= Toast.makeText(getApplicationContext(), "La reserva se eliminó correctamente", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();

        } catch (DAOException e) {
            Log.i("RegisterActivity", "====> " + e.getMessage());
        }
    }
}
