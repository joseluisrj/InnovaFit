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

public class MembresiasActivity extends AppCompatActivity {

    private static final String TAG = "MembresiasActivity";
    private List<Membresia> mMembresiasList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membresias);

        initMembresia();

    }

    private void initMembresia(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        MembresiaDAO membresiaDAO = new MembresiaDAO(getBaseContext());
        SharedPreferences prefs = getSharedPreferences("PREFERENCIAS", Context.MODE_PRIVATE);
        String idUsuario = prefs.getString("id_usuario", "");
        try {
            mMembresiasList = membresiaDAO.buscarById(idUsuario);

        } catch (DAOException e) {
            Log.i("buscarClasePorSedeFecha", "====> " + e.getMessage());
        }

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recycler_view_membresias);
        MembresiasAdapter adapter = new MembresiasAdapter(MembresiasActivity.this, mMembresiasList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MembresiasActivity.this));
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new DividerItemDecoration(ActivityOne.this, LinearLayoutManager.VERTICAL));
    }
}
