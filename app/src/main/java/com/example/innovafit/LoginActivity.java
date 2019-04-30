package com.example.innovafit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private String flagLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void openRegister(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        //EditText editText = (EditText) findViewById(R.id.editText);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void contacto(View view) {
        String phone = "+51945080767";
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
        startActivity(intent);
    }

    public void login(View view) {
        validar();
        if (this.getFlagLogin() != null && this.getFlagLogin() != "") {
            Intent intent = new Intent(LoginActivity.this, ActivityOne.class);
            //EditText editText = (EditText) findViewById(R.id.editText);
            //String message = editText.getText().toString();
            //intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        }
    }

    public void validar(){

        EditText mail = (EditText) findViewById(R.id.mail);
        EditText password = (EditText) findViewById(R.id.password);

        String usuario = mail.getText().toString();
        String clave = password.getText().toString();

        UsuarioDAO usuarioDAO = new UsuarioDAO(getBaseContext());
        ClaseDAO claseDAO = new ClaseDAO(getBaseContext());
        SedeDAO  sedeDAO = new SedeDAO(getBaseContext());
        TipoMembresiaDAO tipoMembresiaDAO = new TipoMembresiaDAO(getBaseContext());
        MembresiaDAO membresiaDAO = new MembresiaDAO(getBaseContext());
        try {
            //sedeDAO.insertar();
            //Log.i("LoginActivity", "====> " + claseDAO.buscarClasePorSedeFecha());
            //tipoMembresiaDAO.insertar();
            //membresiaDAO.insertar();
/*
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "06:00 am", "07:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "1");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "07:00 am", "08:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "1");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "08:00 am", "09:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "1");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "09:00 am", "10:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "1");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "10:00 am", "11:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "1");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "11:00 am", "12:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "1");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "12:00 pm", "13:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "1");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "13:00 pm", "14:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "1");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "14:00 pm", "15:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "1");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "15:00 pm", "16:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "1");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "16:00 pm", "17:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "1");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "17:00 pm", "18:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "1");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "18:00 pm", "19:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "1");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "19:00 pm", "20:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "1");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "20:00 pm", "21:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "1");

            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "06:00 am", "07:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "2");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "07:00 am", "08:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "2");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "08:00 am", "09:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "2");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "09:00 am", "10:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "2");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "10:00 am", "11:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "2");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "11:00 am", "12:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "2");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "12:00 pm", "13:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "2");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "13:00 pm", "14:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "2");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "14:00 pm", "15:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "2");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "15:00 pm", "16:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "2");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "16:00 pm", "17:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "2");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "17:00 pm", "18:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "2");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "18:00 pm", "19:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "2");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "19:00 pm", "20:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "2");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "20:00 pm", "21:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "2");

            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "06:00 am", "07:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "3");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "07:00 am", "08:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "3");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "08:00 am", "09:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "3");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "09:00 am", "10:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "3");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "10:00 am", "11:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "3");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "11:00 am", "12:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "3");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "12:00 pm", "13:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "3");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "13:00 pm", "14:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "3");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "14:00 pm", "15:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "3");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "15:00 pm", "16:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "3");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "16:00 pm", "17:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "3");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "17:00 pm", "18:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "3");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "18:00 pm", "19:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "3");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "19:00 pm", "20:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "3");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-02", "20:00 pm", "21:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "3");



            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "06:00 am", "07:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "1");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "07:00 am", "08:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "1");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "08:00 am", "09:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "1");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "09:00 am", "10:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "1");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "10:00 am", "11:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "1");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "11:00 am", "12:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "1");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "12:00 pm", "13:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "1");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "13:00 pm", "14:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "1");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "14:00 pm", "15:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "1");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "15:00 pm", "16:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "1");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "16:00 pm", "17:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "1");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "17:00 pm", "18:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "1");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "18:00 pm", "19:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "1");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "19:00 pm", "20:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "1");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "20:00 pm", "21:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "1");

            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "06:00 am", "07:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "2");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "07:00 am", "08:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "2");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "08:00 am", "09:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "2");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "09:00 am", "10:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "2");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "10:00 am", "11:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "2");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "11:00 am", "12:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "2");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "12:00 pm", "13:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "2");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "13:00 pm", "14:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "2");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "14:00 pm", "15:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "2");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "15:00 pm", "16:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "2");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "16:00 pm", "17:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "2");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "17:00 pm", "18:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "2");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "18:00 pm", "19:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "2");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "19:00 pm", "20:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "2");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "20:00 pm", "21:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "2");

            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "06:00 am", "07:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "3");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "07:00 am", "08:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "3");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "08:00 am", "09:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "3");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "09:00 am", "10:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "3");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "10:00 am", "11:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "3");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "11:00 am", "12:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "3");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "12:00 pm", "13:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "3");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "13:00 pm", "14:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "3");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "14:00 pm", "15:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "3");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "15:00 pm", "16:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "3");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "16:00 pm", "17:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "3");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "17:00 pm", "18:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "3");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "18:00 pm", "19:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "3");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "19:00 pm", "20:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "3");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-03", "20:00 pm", "21:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "3");

            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-04", "06:00 am", "07:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "1");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-04", "07:00 am", "08:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "1");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-04", "08:00 am", "09:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "1");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-04", "09:00 am", "10:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "1");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-04", "10:00 am", "11:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "1");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-04", "11:00 am", "12:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "1");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-04", "12:00 pm", "13:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "1");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-04", "13:00 pm", "14:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "1");

            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-04", "06:00 am", "07:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "2");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-04", "07:00 am", "08:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "2");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-04", "08:00 am", "09:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "2");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-04", "09:00 am", "10:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "2");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-04", "10:00 am", "11:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "2");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-04", "11:00 am", "12:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "2");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-04", "12:00 pm", "13:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "2");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-04", "13:00 pm", "14:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "2");

            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-04", "06:00 am", "07:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "3");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-04", "07:00 am", "08:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "3");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-04", "08:00 am", "09:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "3");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-04", "09:00 am", "10:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "3");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-04", "10:00 am", "11:00 am", Integer.parseInt("15"), Integer.parseInt("0"), "3");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-04", "11:00 am", "12:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "3");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-04", "12:00 pm", "13:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "3");
            claseDAO.insertar("Clase Dual Training System", "Trainer 1", "2019-05-04", "13:00 pm", "14:00 pm", Integer.parseInt("15"), Integer.parseInt("0"), "3");
*/
            for (Sede i: sedeDAO.buscar()) {

                System.out.println ("idSede==="+i.getIdSede());
                System.out.println ("nombre==="+i.getNombre());
                System.out.println ("direccion==="+i.getDireccion());
                System.out.println ("mar1==="+i.getMarcador1());
                System.out.println ("mar2==="+i.getMarcador2());
                System.out.println ("mar3==="+i.getMarcador3());
                System.out.println ("estado==="+i.getEstado());

            }

            for (Clase i: claseDAO.buscarClasePorSedeFecha()) {

                System.out.println ("idClase==="+i.getIdClase());
                System.out.println ("nombre==="+i.getNombre());
                System.out.println ("entrenador==="+i.getEntrenador());
                System.out.println ("fecha==="+i.getFecha());
                System.out.println ("horaIni==="+i.getHoraInicio());
                System.out.println ("horaFin==="+i.getHoraFin());
                System.out.println ("Cantidad==="+i.getCantidad());
                System.out.println ("stock==="+i.getStock());
                System.out.println ("sede==="+i.getSede().getIdSede());

            }

            for (Membresia i: membresiaDAO.buscar()) {

                System.out.println ("idMembresia==="+i.getIdMembresia());
                System.out.println ("nombre==="+i.getNombre());
                System.out.println ("fechaInicio==="+i.getFechaInicio());
                System.out.println ("fechaFin==="+i.getFechaFin());
                System.out.println ("stock==="+i.getStock());
                System.out.println ("tipo==="+i.getTipoMembresia().getIdTipo());
                System.out.println ("usuaeio==="+i.getUsuario().getIdUsuario());
                System.out.println ("Cantidad==="+i.getTipoMembresia().getCantidad());

            }

            usuarioDAO.validar(usuario, clave);

            if (usuarioDAO.getMensaje() != "") {
                Toast toast = Toast.makeText(getApplicationContext(), usuarioDAO.getMensaje(), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            } else {

                this.setFlagLogin(usuario);

                Membresia membresia = membresiaDAO.obtener(usuario);

                    System.out.println ("idClase==="+membresia.getIdMembresia());
                    System.out.println ("nombre==="+membresia.getNombre());
                    System.out.println ("entrenador==="+membresia.getFechaInicio());
                    System.out.println ("fecha==="+membresia.getFechaFin());
                    System.out.println ("Cantidad==="+membresia.getTipoMembresia().getCantidad());
                    System.out.println ("stock==="+membresia.getStock());

                SharedPreferences prefs = getSharedPreferences("PREFERENCIAS",
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("id_usuario", usuarioDAO.getUsuario().getIdUsuario());
                editor.putString("clave", usuarioDAO.getUsuario().getClave());
                editor.putString("nombres", usuarioDAO.getUsuario().getNombres());
                editor.putString("apellidos", usuarioDAO.getUsuario().getApellidos());
                editor.putString("id_sede", Constante.SEDE);
                editor.putString("nombre_sede", Constante.NOMBRE_SEDE);
                editor.putInt("id_membresia", membresia.getIdMembresia());
                editor.putString("fecha_inicio", membresia.getFechaInicio());
                editor.putString("fecha_fin", membresia.getFechaFin());
                editor.putInt("stock", membresia.getStock());
                editor.putInt("cantidad", membresia.getTipoMembresia().getCantidad());
                editor.commit();

            }
        } catch (DAOException e) {
            Log.i("LoginActivity", "====> " + e.getMessage());
        }
    }

    public String getFlagLogin() {
        return flagLogin;
    }

    public void setFlagLogin(String flagLogin) {
        this.flagLogin = flagLogin;
    }
}
