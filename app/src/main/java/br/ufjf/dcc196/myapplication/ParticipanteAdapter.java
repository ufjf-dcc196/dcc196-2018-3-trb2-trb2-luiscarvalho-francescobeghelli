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

public class ParticipanteAdapter extends RecyclerView.Adapter<ParticipanteAdapter.ViewHolder> {

    private Cursor cursor;
    private OnParticClickListener listener;
    private OnParticLongClickListener longListener;

    public ParticipanteAdapter(Cursor c){
        cursor = c;
    }

    public interface OnParticClickListener {
        void onParticClick(View particView, int itemId);
    }

    public interface OnParticLongClickListener {
        void onParticLongClick(View particView, int itemId);
    }

    public void setOnParticClickListener(OnParticClickListener listener){
        this.listener = listener;
    }

    public void setOnParticLongClickListener(ParticipanteAdapter.OnParticLongClickListener longListener){
        this.longListener = longListener;
    }

    public void setCursor(Cursor c)
    {
        cursor = c;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context ctx = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(ctx);

        View itemView = inflater.inflate(R.layout.item_participante, viewGroup, false);
        ViewHolder vwHolder = new ViewHolder(itemView);
        return vwHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        int idxTitulo = cursor.getColumnIndexOrThrow(ParticipanteContract.Participante.COLUMN_NAME_NOME);
        int idxId = cursor.getColumnIndexOrThrow(ParticipanteContract.Participante._ID);
        cursor.moveToPosition(i);
        viewHolder.txtNomeParticipante.setText(cursor.getString(idxTitulo));

        final int id = cursor.getInt(idxId);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null) {
                    listener.onParticClick(v, id);
                }
            }
        });

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                if(longListener != null) {
                    longListener.onParticLongClick(v, id);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtNomeParticipante;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            txtNomeParticipante = (TextView)itemView.findViewById(R.id.txtNomeParticipante);
        }
    }
}
