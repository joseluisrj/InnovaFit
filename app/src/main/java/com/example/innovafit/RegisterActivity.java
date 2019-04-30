package com.example.innovafit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void register(View view) {
        insertar();
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        //EditText editText = (EditText) findViewById(R.id.editText);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void insertar(){

        EditText name = (EditText) findViewById(R.id.name);
        EditText surname = (EditText) findViewById(R.id.surname);
        EditText mail = (EditText) findViewById(R.id.mail);
        EditText password = (EditText) findViewById(R.id.password);

        String nombres = name.getText().toString();
        String apellidos = surname.getText().toString();
        String correo = mail.getText().toString();
        String contrasena = password.getText().toString();

        UsuarioDAO usuarioDAO = new UsuarioDAO(getBaseContext());
        UsuarioRolDAO usuarioRolDAO = new UsuarioRolDAO(getBaseContext());
        MembresiaDAO membresiaDAO = new MembresiaDAO(getBaseContext());
        RolDAO rolDAO = new RolDAO(getBaseContext());
        TipoMembresiaDAO tipoMembresiaDAO = new TipoMembresiaDAO(getBaseContext());
        SedeDAO sedeDAO = new SedeDAO(getBaseContext());

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("usuario", correo)
                .addFormDataPart("clave", contrasena)
                .addFormDataPart("nombres", nombres)
                .addFormDataPart("apellidos", apellidos)
                .build();

        Request request = new Request.Builder()
                .url("http://innovafit.atwebpages.com/index.php/usuario")
                .post(requestBody)
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

                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast toast= Toast.makeText(getApplicationContext(), "Se insertó correctamente en la nube", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
                            toast.show();
                        }
                    });

                }
            }
        });

        try {
            //dao.eliminarTodos();
            //sedeDAO.insertar("San Borja", "Avenida Aviacion 2345", "-12.0914980", "-77.0029060", "marcador_3", "1");
            //sedeDAO.insertar("San Isidro", "Avenida Paseo de la Republica 3211", "-12.0960720", "-77.0242481", "marcador_3", "1");
            //sedeDAO.insertar("Surco", "Avenida Caminos del Inca 1404", "-12.1243648", "-76.9844720", "marcador_3", "1");
            //rolDAO.insertar("Administrador");
            //rolDAO.insertar("Estudiante");
            //tipoMembresiaDAO.insertar("Plan Mensual 2019", new BigDecimal("240"), Integer.parseInt("12"), "1");

            usuarioDAO.insertar(nombres, apellidos, correo, contrasena);
            usuarioRolDAO.insertar(correo, Constante.ROL_ESTUDIANTE);
            membresiaDAO.insertar("Abril 2019", "2019-04-01", "2019-06-01", Integer.parseInt("0"), Integer.parseInt("1"), correo);






            Toast toast= Toast.makeText(getApplicationContext(), "Se insertó correctamente", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();

            name.setText("");
            surname.setText("");
            mail.setText("");
            password.setText("");
        } catch (DAOException e) {
            Log.i("RegisterActivity", "====> " + e.getMessage());
        }
    }
}
