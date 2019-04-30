package com.example.innovafit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by User on 4/15/2017.
 */

public class ActivityTwo extends AppCompatActivity {

    private static final String TAG = "ActivityTwo";

    private List<Notificacion> mNotificacionList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        initNotificaciones();

        //TextView title = (TextView) findViewById(R.id.activityTitle2);
        //title.setText("This is ActivityTwo");

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_arrow:
                        Intent intent0 = new Intent(ActivityTwo.this, MainActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.ic_android:
                        Intent intent1 = new Intent(ActivityTwo.this, ActivityOne.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_books:

                        break;

                    case R.id.ic_center_focus:
                        Intent intent3 = new Intent(ActivityTwo.this, ActivityThree.class);
                        startActivity(intent3);
                        break;

                    case R.id.ic_backup:
                        Intent intent4 = new Intent(ActivityTwo.this, ActivityFour.class);
                        startActivity(intent4);
                        break;
                }


                return false;
            }
        });
    }

    private void initNotificaciones(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");
/*
        NotificacionDAO dao = new NotificacionDAO(getBaseContext());
        try {
            //dao.insertar();
            mNotificacionList = dao.buscar();

        } catch (DAOException e) {
            Log.i("buscarClasePorSedeFecha", "====> " + e.getMessage());
        }

        initRecyclerView();
*/
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://innovafit.atwebpages.com/index.php/notificaciones")
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
                        Notificacion notificacion = new Notificacion();
                        notificacion.setIdNotificacion(Integer.parseInt((String) x.get("idNotificacion")));
                        notificacion.setTitulo((String) x.get("titulo"));
                        notificacion.setDetalle((String) x.get("detalle"));
                        notificacion.setFecha((String) x.get("fecha"));
                        notificacion.setEstado((String) x.get("estado"));

                        mNotificacionList.add(notificacion);
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
        RecyclerView recyclerView = findViewById(R.id.recycler_view_notificacion);
        NotificacionesAdapter adapter = new NotificacionesAdapter(ActivityTwo.this, mNotificacionList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ActivityTwo.this));
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new DividerItemDecoration(ActivityOne.this, LinearLayoutManager.VERTICAL));
    }

}
