package com.example.innovafit;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.model.ModelLoader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PerfilActivity extends AppCompatActivity {

    ImageView imageView1, imageView2;
    String pathToFile;
    File photoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        getIncomingIntent();

        imageView2 = findViewById(R.id.tomar_foto);
        if (Build.VERSION.SDK_INT >= 23){
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        }
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchPictureTakerAction();
            }
        });

        imageView1 = findViewById(R.id.foto_perfil);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if ( requestCode == 1){
                Bitmap bitmap = BitmapFactory.decodeFile(pathToFile);
                imageView1.setImageBitmap(bitmap);
                uploadFile(photoFile);

            }
        }
    }

    private void dispatchPictureTakerAction () {
        Intent takePic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePic.resolveActivity(getPackageManager()) != null) {
            photoFile = null;
            photoFile = createPhotoFile();


            if (photoFile != null) {
                pathToFile = photoFile.getAbsolutePath();
                Uri photoURI = FileProvider.getUriForFile(PerfilActivity.this, "com.example.innovafit.fileprovider", photoFile);
                takePic.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePic, 1);
            }

        }
    }

    private File createPhotoFile() {
        String name = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = null;
        try{
            image = File.createTempFile(name, ".jpeg", storageDir);
        }
        catch (Exception e){
            Log.d("myLog", "Excep : " + e.toString());
        }

        return  image;
    }

    private void getIncomingIntent(){

        Usuario modelo = this.obtenerUsuario();

        TextView nombreCompleto = findViewById(R.id.textNombres);
        TextView nombres = findViewById(R.id.txtNombres);
        TextView apellidos = findViewById(R.id.txtApellidos);
        TextView mail = findViewById(R.id.txtMail);
        TextView phone = findViewById(R.id.txtPhone);
        TextView birthday = findViewById(R.id.txtBirthday);
        TextView idGender = findViewById(R.id.txtIdGender);
        TextView gender = findViewById(R.id.txtGender);

        nombreCompleto.setText(modelo.getNombres() + " " + modelo.getApellidos());
        nombres.setText(modelo.getNombres());
        apellidos.setText(modelo.getApellidos());
        mail.setText(modelo.getIdUsuario());
        phone.setText(modelo.getTelefono());
        birthday.setText(modelo.getFechaNacimiento());
        idGender.setText(modelo.getSexo());
        gender.setText(modelo.getSexo()!= null?modelo.getSexo().equals(Constante.ID_HOMBRE)?Constante.HOMBRE:Constante.MUJER:"");
    }

    public Usuario obtenerUsuario() {

        SharedPreferences prefs = getSharedPreferences("PREFERENCIAS", Context.MODE_PRIVATE);
        String idUsuario = prefs.getString("id_usuario", "");

        UsuarioDAO usuarioDAO = new UsuarioDAO(getBaseContext());
        Usuario modelo = new Usuario();

        try {
            modelo = usuarioDAO.obtener(idUsuario);
        } catch (DAOException e) {
            Log.i("PerfilActivity", "====> " + e.getMessage());
        }

        return modelo;

    }

    public void editarPerfil(View view) {
        Intent intent = new Intent(PerfilActivity.this, EditarPerfilActivity.class);
        //EditText editText = (EditText) findViewById(R.id.editText);

        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);

        TextView nombres = findViewById(R.id.txtNombres);
        TextView apellidos = findViewById(R.id.txtApellidos);
        TextView mail = findViewById(R.id.txtMail);
        TextView phone = findViewById(R.id.txtPhone);
        TextView birthday = findViewById(R.id.txtBirthday);
        TextView idGender = findViewById(R.id.txtIdGender);

        String txtNombres = nombres.getText().toString();
        String txtApellidos = apellidos.getText().toString();
        String txtMail = mail.getText().toString();
        String txtPhone = phone.getText().toString();
        String txtBirthday = birthday.getText().toString();
        String txtIdGender = idGender.getText().toString();

        intent.putExtra("EXTRA_NOMBBRES", txtNombres);
        intent.putExtra("EXTRA_APELLIDOS", txtApellidos);
        intent.putExtra("EXTRA_MAIL", txtMail);
        intent.putExtra("EXTRA_PHONE", txtPhone);
        intent.putExtra("EXTRA_BIRTHDAY", txtBirthday);
        intent.putExtra("EXTRA_GENDER", txtIdGender);

        startActivity(intent);
    }

    public void uploadFile(File file){

        SharedPreferences prefs = getSharedPreferences("PREFERENCIAS", Context.MODE_PRIVATE);
        String usuario = prefs.getString("id_usuario", "");

        Log.i("fileNme====>", file.getName());

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("usuario", usuario)
                .addFormDataPart("imagen", file.getName(), RequestBody.create(MediaType.parse("image/jpeg"),file))
                .build();

        Request request = new Request.Builder()
                .url("http://innovafit.atwebpages.com/index.php/uploadFile")
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
                            Toast toast= Toast.makeText(getApplicationContext(), "Se insertó correctamente", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
                            toast.show();
                        }
                    });

                }
            }
        });

    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
}
