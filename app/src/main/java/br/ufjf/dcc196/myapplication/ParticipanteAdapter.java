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

    public ParticipanteAdapter(Cursor c){
        cursor = c;
    }

    public interface OnParticClickListener {
        void onParticClick(View particView, int position);
    }

    public void setOnParticClickListener(OnParticClickListener listener){
        this.listener = listener;
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
                listener.onParticClick(v, id);
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
