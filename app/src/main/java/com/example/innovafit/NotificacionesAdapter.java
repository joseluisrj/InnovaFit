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

public class NotificacionesAdapter extends RecyclerView.Adapter<NotificacionesAdapter.MyViewHolder> {

    private static final String TAG = "RVMembresiasAdapter";

    private List<Notificacion> mNotificacionesList;
    private Context mContext;
    private Activity mActivity;

    public NotificacionesAdapter(Context context, List<Notificacion> notificacionesList) {
        this.mNotificacionesList = notificacionesList;
        mContext = context;
    }

    @Override
    public NotificacionesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notificacion_fila, parent, false);

        return new NotificacionesAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NotificacionesAdapter.MyViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        final Notificacion notificacion = mNotificacionesList.get(position);
        holder.titulo.setText(notificacion.getTitulo());
        holder.fecha.setText(notificacion.getFecha());

        holder.relative_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + mNotificacionesList.get(position));

                Toast.makeText(mContext, String.valueOf(mNotificacionesList.get(position).getTitulo()) + " " + mNotificacionesList.get(position).getTitulo(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, DetalleNotifActivity.class);

                intent.putExtra("titulo", mNotificacionesList.get(position).getTitulo());
                intent.putExtra("detalle", mNotificacionesList.get(position).getDetalle());

                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mNotificacionesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView titulo, fecha;
        public RelativeLayout relative_layout;

        public MyViewHolder(View view) {
            super(view);
            titulo = (TextView) view.findViewById(R.id.not_titulo);
            fecha = (TextView) view.findViewById(R.id.not_fecha);
            relative_layout = view.findViewById(R.id.relative_layout_notification);
        }
    }

}

