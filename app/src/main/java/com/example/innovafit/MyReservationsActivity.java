package com.example.innovafit;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyReservationsActivity extends AppCompatActivity {

    private static final String TAG = "ActivityOne";


    private List<Reserva> mReservaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservations);

        SharedPreferences prefs = getSharedPreferences("PREFERENCIAS", Context.MODE_PRIVATE);
        Integer idMembresia = prefs.getInt("id_membresia", 0);

        initReservas(idMembresia);

    }


    private void initReservas(int idMembresia){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        ReservaDAO dao = new ReservaDAO(getBaseContext());
        try {
            mReservaList = dao.buscar(idMembresia);

        } catch (DAOException e) {
            Log.i("buscarClasePorSedeFecha", "====> " + e.getMessage());
        }

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recycler_view_reservas);
        RecyclerViewReservationsAdapter adapter = new RecyclerViewReservationsAdapter(MyReservationsActivity.this, mReservaList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MyReservationsActivity.this));
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new DividerItemDecoration(ActivityOne.this, LinearLayoutManager.VERTICAL));
    }
}
