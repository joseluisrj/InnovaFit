package com.example.innovafit;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;


public class EditarPerfilActivity extends AppCompatActivity {

    private static final String TAG = "EditarPerfilActivity";
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private Spinner combo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        getIncomingIntent();
        getIncomingDate();


    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        if(getIntent().hasExtra("EXTRA_NOMBBRES") && getIntent().hasExtra("EXTRA_APELLIDOS") &&
                getIntent().hasExtra("EXTRA_MAIL") && getIntent().hasExtra("EXTRA_PHONE") &&
                getIntent().hasExtra("EXTRA_BIRTHDAY") && getIntent().hasExtra("EXTRA_GENDER")){

            Log.d(TAG, "getIncomingIntent: found intent extras.");

            String nombres = getIntent().getStringExtra("EXTRA_NOMBBRES");
            String apellidos = getIntent().getStringExtra("EXTRA_APELLIDOS");
            String mail = getIntent().getStringExtra("EXTRA_MAIL");
            String phone = getIntent().getStringExtra("EXTRA_PHONE");
            String birthday = getIntent().getStringExtra("EXTRA_BIRTHDAY");
            String gender = getIntent().getStringExtra("EXTRA_GENDER");

            TextView edtApellidos = findViewById(R.id.edtApellidos);
            TextView edtNombres = findViewById(R.id.edtNombres);
            TextView edtMail = findViewById(R.id.edtMail);
            TextView edtPhone = findViewById(R.id.edtPhone);
            TextView edtBirthday = findViewById(R.id.edtBirthday);
            TextView edtGender = findViewById(R.id.edtGender);


            edtApellidos.setText(String.valueOf(apellidos));
            edtNombres.setText(String.valueOf(nombres));
            edtMail.setText(String.valueOf(mail));
            edtPhone.setText(phone);
            edtBirthday.setText(birthday);
            edtGender.setText(gender);
            getIncomingGender(gender);

        }
    }

    private void getIncomingDate() {

        final TextView mDisplayDate = findViewById(R.id.edtBirthday);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        EditarPerfilActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                //String date = month + "/" + day + "/" + year;
                String date = year + "-" + twoDigits(month) + "-" + twoDigits(day);
                mDisplayDate.setText(date);
            }
        };

    }

    private String twoDigits(int n) {
        return (n<=9) ? ("0"+n) : String.valueOf(n);
    }

    private void getIncomingGender(String id) {
        System.out.println("ID================="+id);
        LinkedHashMap<String, String> mapData = new LinkedHashMap<String, String>();
        int position;

        mapData.put("", "Seleccione");
        mapData.put("M", "Masculino");
        mapData.put("F", "Femenino");

        if (id.equals("M"))
            position = 1;
        else if (id.equals("F"))
            position = 2;
        else
            position = 0;

        LinkedHashMapAdapter<String, String> adaptador = new LinkedHashMapAdapter<String, String>(this,    R.layout.spinner_item, mapData);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        combo = (Spinner) findViewById(R.id.spinner);
        combo.setAdapter(adaptador);
        combo.setSelection(position);

        /*ArrayList<Genero> generoList = new ArrayList<>();
        //Add gender
        generoList.add(new Genero("M", "Masculino"));
        generoList.add(new Genero("F", "Femenino"));
        //fill data in spinner
        ArrayAdapter<Genero> adapter = new ArrayAdapter<Genero>(this, R.layout.spinner_item, generoList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        combo = (Spinner) findViewById(R.id.spinner);
        combo.setAdapter(adapter);
        combo.setSelection(adapter.getPosition(new Genero("M", "Masculino")));*/


        combo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
                //Toast.makeText(EditarPerfilActivity.this, "Seleccionado: " + cursos[position], Toast.LENGTH_LONG).show();
                combo.setSelection(position);
                Map.Entry<String, String> item = (Map.Entry<String, String>) combo.getSelectedItem();
                TextView edtIdGender = findViewById(R.id.edtIdGender);
                edtIdGender.setText(item.getKey());
                //Genero genero = (Genero) parent.getSelectedItem();
                //Toast.makeText(EditarPerfilActivity.this, "Seleccionado: " + item.getKey() + " " + item.getValue(), Toast.LENGTH_LONG).show();
            }

            public void onNothingSelected(AdapterView<?> parent) {
                //Toast.makeText(EditarPerfilActivity.this, "No ha seleccionado",    Toast.LENGTH_LONG).show();
            }
        });


    }

    public void modificar(){

        TextView edtNombres = findViewById(R.id.edtNombres);
        TextView edtApellidos = findViewById(R.id.edtApellidos);
        TextView edtMail = findViewById(R.id.edtMail);
        TextView edtPhone = findViewById(R.id.edtPhone);
        TextView edtBirthday = findViewById(R.id.edtBirthday);
        TextView edtIdGender = findViewById(R.id.edtIdGender);

        String nombres = edtNombres.getText().toString();
        String apellidos = edtApellidos.getText().toString();
        String mail = edtMail.getText().toString();
        String phone = edtPhone.getText().toString();
        String birthday = edtBirthday.getText().toString();
        String gender = edtIdGender.getText().toString();


        UsuarioDAO usuarioDAO = new UsuarioDAO(getBaseContext());
        try {
            usuarioDAO.modificar(nombres, apellidos, mail, phone, birthday, gender);

            SharedPreferences prefs = getSharedPreferences("PREFERENCIAS",
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("nombres", nombres);
            editor.putString("apellidos", apellidos);
            editor.commit();

            Toast toast= Toast.makeText(getApplicationContext(), "Los datos se guardaron correctamente", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();

        } catch (DAOException e) {
            Log.i("EditarPerfilActivity", "====> " + e.getMessage());
        }
    }

    public void editarPerfil(View view) {
        modificar();
        Intent intent = new Intent(EditarPerfilActivity.this, ActivityThree.class);
        //EditText editText = (EditText) findViewById(R.id.editText);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
