package com.example.innovafit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

/**
 * Created by User on 4/15/2017.
 */

public class ActivityOne extends AppCompatActivity {

    private static final String TAG = "ActivityOne";

    private List<Clase> mClaseList = new ArrayList<>();
    private String idSede;

    private HorizontalCalendar horizontalCalendar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        //TextView title = (TextView) findViewById(R.id.activityTitle1);
        //title.setText("This is ActivityOne");
        initSeleccionarSede();
        initSelSede();
        initCalender();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_arrow:
                        Intent intent0 = new Intent(ActivityOne.this, MainActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.ic_android:

                        break;

                    case R.id.ic_books:
                        Intent intent2 = new Intent(ActivityOne.this, ActivityTwo.class);
                        startActivity(intent2);
                        break;

                    case R.id.ic_center_focus:
                        Intent intent3 = new Intent(ActivityOne.this, ActivityThree.class);
                        startActivity(intent3);
                        break;

                    case R.id.ic_backup:
                        Intent intent4 = new Intent(ActivityOne.this, ActivityFour.class);
                        startActivity(intent4);
                        break;
                }


                return false;
            }
        });


    }

    private void initCalender() {

        SharedPreferences prefs = getSharedPreferences("PREFERENCIAS", Context.MODE_PRIVATE);
        idSede = prefs.getString("id_sede", "");

        /* end after 1 months from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        /* start 1 months ago from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        // Default Date set to Today.
        final Calendar defaultSelectedDate = Calendar.getInstance();

        horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .defaultSelectedDate(defaultSelectedDate)
                .build();

        Log.i("Default Date", DateFormat.format("yyyy-MM-dd", defaultSelectedDate).toString());

        //initRecyclerView(1,DateFormat.format("yyyy-MM-dd", defaultSelectedDate).toString());
        initClases(Integer.parseInt(idSede),DateFormat.format("yyyy-MM-dd", defaultSelectedDate).toString());

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                String selectedDateStr = DateFormat.format("yyyy-MM-dd", date).toString();

                Toast.makeText(ActivityOne.this, selectedDateStr + " selected!", Toast.LENGTH_SHORT).show();
                Log.i("onDateSelected", selectedDateStr + " - Position = " + position);

                initClases(Integer.parseInt(idSede),selectedDateStr);
            }

        });

    }

    private void initClases(int idSede, String fecha){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        ClaseDAO dao = new ClaseDAO(getBaseContext());
        //SedeDAO dao2 = new SedeDAO(getBaseContext());
        try {
            //dao2.insertar();
            //dao.insertar();
            mClaseList = dao.buscarClasePorSedeFecha(idSede, fecha);

        } catch (DAOException e) {
            Log.i("buscarClasePorSedeFecha", "====> " + e.getMessage());
        }

        initRecyclerView();
    }

    private void initSelSede() {
        SharedPreferences prefs = getSharedPreferences("PREFERENCIAS", Context.MODE_PRIVATE);
        String nombreSede = prefs.getString("nombre_sede", "");

        TextView selSede = findViewById(R.id.textSelSede);
        selSede.setText(String.valueOf(nombreSede));
    }

    public void SeleccionarSede(View view) {
        Intent intent = new Intent(ActivityOne.this, SeleccionarSedeActivity.class);
        //EditText editText = (EditText) findViewById(R.id.editText);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewClasesAdapter adapter = new RecyclerViewClasesAdapter(ActivityOne.this, ActivityOne.this, mClaseList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ActivityOne.this));
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new DividerItemDecoration(ActivityOne.this, LinearLayoutManager.VERTICAL));
    }

    public void initSeleccionarSede() {

        System.out.println("initSeleccionarSede===============");

        if(getIntent().hasExtra("id_sede_sel") && getIntent().hasExtra("nombre_sede_sel")) {

            String idSede = String.valueOf(getIntent().getIntExtra("id_sede_sel", 0));
            String nombreSede = getIntent().getStringExtra("nombre_sede_sel");

            System.out.println("id_sede_sel===============" + getIntent().getStringExtra("id_sede_sel"));
            System.out.println("nombre_sede_sel===============" +getIntent().getStringExtra("nombre_sede_sel"));

            SharedPreferences prefs = getSharedPreferences("PREFERENCIAS",
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("id_sede", idSede);
            editor.putString("nombre_sede", nombreSede);
            editor.commit();
        }
    }
}
