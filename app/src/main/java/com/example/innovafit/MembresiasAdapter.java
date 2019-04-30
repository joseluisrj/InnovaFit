package com.example.innovafit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MembresiasAdapter extends RecyclerView.Adapter<MembresiasAdapter.MyViewHolder> {

    private static final String TAG = "RVMembresiasAdapter";

    private List<Membresia> mMembresiasList;
    private Context mContext;
    private Activity mActivity;

    public MembresiasAdapter(Context context, List<Membresia> membresiasList) {
        this.mMembresiasList = membresiasList;
        mContext = context;
    }

    @Override
    public MembresiasAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.membresia_fila, parent, false);

        return new MembresiasAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MembresiasAdapter.MyViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Membresia membresia = mMembresiasList.get(position);
        holder.titulo.setText(membresia.getNombre());
        holder.desde.setText(membresia.getFechaInicio());
        holder.hasta.setText(membresia.getFechaFin());
        holder.costo.setText(membresia.getTipoMembresia().getCosto().toString());
        holder.sesiones.setText(membresia.getStock() + " / " + membresia.getTipoMembresia().getCantidad());


        holder.relative_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + mMembresiasList.get(position));

                Toast.makeText(mContext, String.valueOf(mMembresiasList.get(position).getTipoMembresia().getCosto().toString()) + " " + mMembresiasList.get(position).getNombre(), Toast.LENGTH_SHORT).show();

                /*Intent intent = new Intent(mContext, ActivityOne.class);

                intent.putExtra("id_sede_sel", mSedesList.get(position).getIdSede());
                intent.putExtra("nombre_sede_sel", mSedesList.get(position).getNombre());

                mContext.startActivity(intent);*/

                Intent intent = new Intent(mContext, DetMembresiaActivity.class);

                intent.putExtra("id_membresia", mMembresiasList.get(position).getIdMembresia());
                intent.putExtra("nombre", mMembresiasList.get(position).getNombre());
                intent.putExtra("fecha_inicio", mMembresiasList.get(position).getFechaInicio());
                intent.putExtra("fecha_fin", mMembresiasList.get(position).getFechaFin());
                intent.putExtra("costo_mem", String.valueOf(mMembresiasList.get(position).getTipoMembresia().getCosto()));
                intent.putExtra("stock", mMembresiasList.get(position).getStock());
                intent.putExtra("sesiones", mMembresiasList.get(position).getTipoMembresia().getCantidad());

                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mMembresiasList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView titulo, desde, hasta, costo, sesiones;
        public RelativeLayout relative_layout;

        public MyViewHolder(View view) {
            super(view);
            titulo = (TextView) view.findViewById(R.id.titulo);
            desde = (TextView) view.findViewById(R.id.desde);
            hasta = (TextView) view.findViewById(R.id.hasta);
            costo = (TextView) view.findViewById(R.id.costo);
            sesiones = (TextView) view.findViewById(R.id.sesiones);
            relative_layout = view.findViewById(R.id.relative_layout_membership);
        }
    }
}

