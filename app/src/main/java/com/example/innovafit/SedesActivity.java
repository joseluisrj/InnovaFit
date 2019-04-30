package com.example.innovafit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class SedesActivity extends AppCompatActivity {

    private static final String TAG = "SedesActivity";

    private List<Sede> mSedeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sedes);

        initSedes();


    }

    public void initSedes(){

        Log.d(TAG, "initSedes: preparing bitmaps.");


        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://innovafit.atwebpages.com/index.php/sedes")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    String cadenaJson = response.body().string();
                    Log.i("====>", cadenaJson);

                    Gson gson = new Gson();
                    Type stringStringMap = new TypeToken<ArrayList<Map<String, Object>>>() { }.getType();

                    final ArrayList<Map<String, Object>> retorno = gson.fromJson(cadenaJson, stringStringMap);

                    for (Map<String, Object> x : retorno) {
                        Sede sede = new Sede();
                        sede.setIdSede(Integer.parseInt((String) x.get("idSede")));
                        sede.setNombre((String) x.get("nombre"));
                        sede.setDireccion((String) x.get("direccion"));
                        sede.setMarcador1((String) x.get("marcador1"));
                        sede.setMarcador2((String) x.get("marcador2"));
                        sede.setMarcador3((String) x.get("marcador3"));
                        sede.setEstado((String) x.get("estado"));

                        mSedeList.add(sede);
                    }

                    runOnUiThread(new Runnable() {
                        public void run() {

                            initRecyclerView();

                        }
                    });



                }
            }
        });


    }


    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recycler_view_sedes);
        RecyclerViewSedesAdapter adapter = new RecyclerViewSedesAdapter(SedesActivity.this, mSedeList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(SedesActivity.this));
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new DividerItemDecoration(ActivityOne.this, LinearLayoutManager.VERTICAL));
    }
}
