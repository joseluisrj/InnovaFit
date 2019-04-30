package com.example.innovafit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewClasesAdapter extends RecyclerView.Adapter<RecyclerViewClasesAdapter.MyViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private List<Clase> mClasesList;
    private Context mContext;
    private Activity mActivity;

    public RecyclerViewClasesAdapter(Activity activity, Context context, List<Clase> clasesList) {
        this.mClasesList = clasesList;
        mContext = context;
        mActivity = activity;
    }

    @Override
    public RecyclerViewClasesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.clase_fila, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewClasesAdapter.MyViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Clase clase = mClasesList.get(position);
        holder.hora.setText(clase.getHoraInicio() + " " + clase.getHoraFin());
        holder.nombre.setText(clase.getNombre());
        holder.entrenador.setText(clase.getEntrenador());
        holder.asistente.setText(clase.getStock() + " / " + clase.getCantidad());

        holder.relative_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + mClasesList.get(position));

                Toast.makeText(mContext, String.valueOf(mClasesList.get(position).getIdClase()), Toast.LENGTH_SHORT).show();

                ReservaDAO reservaDAO = new ReservaDAO(mActivity.getBaseContext());
                SharedPreferences prefs = mActivity.getSharedPreferences("PREFERENCIAS", Context.MODE_PRIVATE);
                Integer idMembresia = prefs.getInt("id_membresia", 0);
                System.out.println("MEMBRESIA EN ADAPTER CLASE============="+idMembresia);
                int count = 0;

                try {
                    count = reservaDAO.buscar(mClasesList.get(position).getFecha()).size();

                } catch (DAOException e) {
                    Log.i("buscarClasePorSedeFecha", "====> " + e.getMessage());
                }

                if (count > 0) {

                    Toast.makeText(mContext, "Existe una reserva para esta clase", Toast.LENGTH_SHORT).show();

                } else {

                    Intent intent = new Intent(mContext, ReserveActivity.class);
                    intent.putExtra("id_clase", mClasesList.get(position).getIdClase());
                    intent.putExtra("nombre", mClasesList.get(position).getNombre());
                    intent.putExtra("entrenador", mClasesList.get(position).getEntrenador());
                    intent.putExtra("fecha", mClasesList.get(position).getFecha());
                    intent.putExtra("hora_inicio", mClasesList.get(position).getHoraInicio());
                    intent.putExtra("hora_fin", mClasesList.get(position).getHoraFin());
                    intent.putExtra("cantidad", mClasesList.get(position).getCantidad());
                    intent.putExtra("stock", mClasesList.get(position).getStock());
                    intent.putExtra("nombre_sede", mClasesList.get(position).getSede().getNombre());

                    //intent.putExtra("image_name", mImageNames.get(position));
                    mContext.startActivity(intent);

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mClasesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView hora, nombre, entrenador, asistente;
        public RelativeLayout relative_layout;

        public MyViewHolder(View view) {
            super(view);
            hora = (TextView) view.findViewById(R.id.hora);
            nombre = (TextView) view.findViewById(R.id.nombre);
            entrenador = (TextView) view.findViewById(R.id.entrenador);
            asistente = (TextView) view.findViewById(R.id.asistente);
            relative_layout = view.findViewById(R.id.relative_layout);
        }
    }

}

