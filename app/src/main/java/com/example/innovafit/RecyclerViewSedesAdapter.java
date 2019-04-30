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

public class RecyclerViewSedesAdapter extends RecyclerView.Adapter<RecyclerViewSedesAdapter.MyViewHolder> {

    private static final String TAG = "RVSedesAdapter";

    private List<Sede> mSedesList;
    private Context mContext;
    private Activity mActivity;

    public RecyclerViewSedesAdapter(Context context, List<Sede> sedesList) {
        this.mSedesList = sedesList;
        mContext = context;
    }

    @Override
    public RecyclerViewSedesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sede_fila, parent, false);

        return new RecyclerViewSedesAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewSedesAdapter.MyViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Sede sede = mSedesList.get(position);
        holder.nombre.setText(sede.getNombre());
        holder.direccion.setText(sede.getDireccion());


        holder.relative_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + mSedesList.get(position));

                //Toast.makeText(mContext, String.valueOf(mSedesList.get(position).getIdSede()), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, MapaBasicoActivity.class);

                intent.putExtra("id_sede", mSedesList.get(position).getIdSede());
                intent.putExtra("nombre_sede", mSedesList.get(position).getNombre());
                intent.putExtra("marcador_1", mSedesList.get(position).getMarcador1());
                intent.putExtra("marcador_2", mSedesList.get(position).getMarcador2());

                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mSedesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView nombre, direccion;
        public RelativeLayout relative_layout;

        public MyViewHolder(View view) {
            super(view);
            nombre = (TextView) view.findViewById(R.id.nombreSede);
            direccion = (TextView) view.findViewById(R.id.direccionSede);
            relative_layout = view.findViewById(R.id.relative_layout_sede);
        }
    }
}

