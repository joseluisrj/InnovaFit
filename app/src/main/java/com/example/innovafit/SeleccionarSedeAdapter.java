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

public class SeleccionarSedeAdapter extends RecyclerView.Adapter<SeleccionarSedeAdapter.MyViewHolder> {

    private static final String TAG = "RVSelSedesAdapter";

    private List<Sede> mSedesList;
    private Context mContext;
    private Activity mActivity;

    public SeleccionarSedeAdapter(Context context, List<Sede> sedesList) {
        this.mSedesList = sedesList;
        mContext = context;
    }

    @Override
    public SeleccionarSedeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.seleccionar_sede_fila, parent, false);

        return new SeleccionarSedeAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SeleccionarSedeAdapter.MyViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Sede sede = mSedesList.get(position);
        holder.nombre.setText(sede.getNombre());


        holder.relative_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + mSedesList.get(position));

                //Toast.makeText(mContext, String.valueOf(mSedesList.get(position).getIdSede()) + " " + mSedesList.get(position).getNombre(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, ActivityOne.class);

                intent.putExtra("id_sede_sel", mSedesList.get(position).getIdSede());
                intent.putExtra("nombre_sede_sel", mSedesList.get(position).getNombre());

                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mSedesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView nombre;
        public RelativeLayout relative_layout;

        public MyViewHolder(View view) {
            super(view);
            nombre = (TextView) view.findViewById(R.id.nombreSelSede);
            relative_layout = view.findViewById(R.id.relative_layout_sel_sede);
        }
    }
}

