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

public class SeleccionarSedeActivity extends AppCompatActivity {

    private static final String TAG = "SeleccionarSedeActivity";

    private List<Sede> mSedeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_sede);

        initSedes();

    }

    private void initSedes(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        SedeDAO sedeDAO = new SedeDAO(getBaseContext());
        try {
            mSedeList = sedeDAO.buscar();

        } catch (DAOException e) {
            Log.i("buscarClasePorSedeFecha", "====> " + e.getMessage());
        }

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recycler_view_seleccionar_sede);
        SeleccionarSedeAdapter adapter = new SeleccionarSedeAdapter(SeleccionarSedeActivity.this, mSedeList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(SeleccionarSedeActivity.this));
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new DividerItemDecoration(ActivityOne.this, LinearLayoutManager.VERTICAL));
    }

}
