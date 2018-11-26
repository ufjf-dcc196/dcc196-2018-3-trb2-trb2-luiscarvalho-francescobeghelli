package br.ufjf.dcc196.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.ViewHolder> {

    private Cursor cursor;
    private OnEventoClickListener listener;
    private OnEventoLongClickListener longListener;

    public EventoAdapter(Cursor c) {
        cursor = c;
    }
    
    public interface OnEventoClickListener {
        void onEventoClick(View eventoView, int itemId);
    }

    public interface OnEventoLongClickListener {
        void onEventoLongClick(View eventoView, int itemId);
    }

    public void setOnEventoClickListener(EventoAdapter.OnEventoClickListener listener) {
        this.listener = listener;
    }

    public void setOnEventoLongClickListener(EventoAdapter.OnEventoLongClickListener longListener){
        this.longListener = longListener;
    }

    @NonNull
    @Override
    public EventoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context ctx = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(ctx);

        View itemView = inflater.inflate(R.layout.item_evento, viewGroup, false); //Precisa arrumar
        EventoAdapter.ViewHolder vwHolder = new EventoAdapter.ViewHolder(itemView);
        return vwHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventoAdapter.ViewHolder viewHolder, int i) {
        int idxTitulo = cursor.getColumnIndexOrThrow(EventoContract.Evento.COLUMN_NAME_TITULO);
        int idxId = cursor.getColumnIndexOrThrow(EventoContract.Evento._ID);
        cursor.moveToPosition(i);
        viewHolder.txtTituloEvento.setText(cursor.getString(idxTitulo));
        final int id = cursor.getInt(idxId);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null) {
                    listener.onEventoClick(v, id);
                }
                }
            });
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                if(longListener != null) {
                    longListener.onEventoLongClick(v, id);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTituloEvento;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            txtTituloEvento = (TextView) itemView.findViewById(R.id.txtNomeEvento);
        }
    }
}

