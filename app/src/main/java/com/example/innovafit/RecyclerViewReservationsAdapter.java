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

public class RecyclerViewReservationsAdapter extends RecyclerView.Adapter<RecyclerViewReservationsAdapter.MyViewHolder> {

    private static final String TAG = "RVReservationsAdapter";

    private List<Reserva> mReservasList;
    private Context mContext;
    private Activity mActivity;

    public RecyclerViewReservationsAdapter(Context context, List<Reserva> reservasList) {
        this.mReservasList = reservasList;
        mContext = context;
    }

    @Override
    public RecyclerViewReservationsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.reserva_fila, parent, false);

        return new RecyclerViewReservationsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewReservationsAdapter.MyViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Reserva reserva = mReservasList.get(position);
        holder.clase.setText(reserva.getClase().getNombre());
        holder.sede.setText(reserva.getClase().getSede().getNombre());
        holder.fecha.setText(reserva.getClase().getFecha());
        holder.hora.setText(reserva.getClase().getHoraInicio() + " - " + reserva.getClase().getHoraFin());
        holder.entrenador.setText(reserva.getClase().getEntrenador());

        holder.relative_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + mReservasList.get(position));

                //Toast.makeText(mContext, String.valueOf(mReservasList.get(position).getIdReserva()), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, DeleteReservationsActivity.class);

                intent.putExtra("id_reserva", mReservasList.get(position).getIdReserva());
                intent.putExtra("id_clase", mReservasList.get(position).getClase().getIdClase());
                intent.putExtra("id_membresia", mReservasList.get(position).getMembresia().getIdMembresia());
                intent.putExtra("nombre", mReservasList.get(position).getClase().getNombre());
                intent.putExtra("entrenador", mReservasList.get(position).getClase().getEntrenador());
                intent.putExtra("fecha", mReservasList.get(position).getClase().getFecha());
                intent.putExtra("hora_inicio", mReservasList.get(position).getClase().getHoraInicio());
                intent.putExtra("hora_fin", mReservasList.get(position).getClase().getHoraFin());
                intent.putExtra("nombre_sede", mReservasList.get(position).getClase().getSede().getNombre());

                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mReservasList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView clase, sede, fecha, hora, entrenador;
        public RelativeLayout relative_layout;

        public MyViewHolder(View view) {
            super(view);
            clase = (TextView) view.findViewById(R.id.clase);
            sede = (TextView) view.findViewById(R.id.sede);
            fecha = (TextView) view.findViewById(R.id.fecha);
            hora = (TextView) view.findViewById(R.id.hora);
            entrenador = (TextView) view.findViewById(R.id.entrenador);
            relative_layout = view.findViewById(R.id.relative_layout_reserve);
        }
    }

}

