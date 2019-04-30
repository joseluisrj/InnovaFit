package com.example.innovafit;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapaBasicoActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG = "MapaBasicoActivity";

    private Integer id;
    private String nombre;
    private String marcador1;
    private String marcador2;

    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createChannel();
        setContentView(R.layout.activity_mapa_basico);

        getIncomingIntent();

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setTrafficEnabled(true);

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(Double.parseDouble(this.getMarcador1()), Double.parseDouble(this.getMarcador2())))
                .title(this.getNombre())
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
/*
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(-12.044956, -77.029831))
                .title("Palacio de Gobierno"));

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(-12.046661, -77.029544))
                .title("Catedral"));
*/
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(this.getMarcador1()), Double.parseDouble(this.getMarcador2())), 15));

    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        if(getIntent().hasExtra("id_sede") && getIntent().hasExtra("nombre_sede") &&
                getIntent().hasExtra("marcador_1") && getIntent().hasExtra("marcador_2")){

            Log.d(TAG, "getIncomingIntent: found intent extras.");

            int idSede = getIntent().getIntExtra("id_sede", 0);
            String nombre = getIntent().getStringExtra("nombre_sede");
            String marcador1 = getIntent().getStringExtra("marcador_1");
            String marcador2 = getIntent().getStringExtra("marcador_2");

            this.setId(idSede);
            this.setNombre(nombre);
            this.setMarcador1(marcador1);
            this.setMarcador2(marcador2);

        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarcador1() {
        return marcador1;
    }

    public void setMarcador1(String marcador1) {
        this.marcador1 = marcador1;
    }

    public String getMarcador2() {
        return marcador2;
    }

    public void setMarcador2(String marcador2) {
        this.marcador2 = marcador2;
    }

    private void createChannel() {
        // Notification channel should only be created for devices running Android API level 26+.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel chan1 = new NotificationChannel(
                    "default_channel_id",
                    "default_channel_id",
                    NotificationManager.IMPORTANCE_NONE);
            chan1.setLightColor(Color.TRANSPARENT);
            chan1.setLockscreenVisibility(Notification.VISIBILITY_SECRET);
            notificationManager.createNotificationChannel(chan1);
            Log.i("=========>", "Canal creado!!");

        }
    }

}
