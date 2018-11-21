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

    private ArrayList<Evento> itens;
    private Cursor cursor;
    private OnEventoClickListener listener;

    public EventoAdapter(Cursor c) {
        cursor = c;
    }

    public void setCursor(Cursor c) {
        cursor = c;
        notifyDataSetChanged();
    }

    public interface OnEventoClickListener {
        void onEventoClick(View eventoView, int position);
    }

    public void setOnEventoClickListener(EventoAdapter.OnEventoClickListener listener) {
        this.listener = listener;
    }


    public EventoAdapter(ArrayList<Evento> itens) {
        this.itens = itens;
    }

    @NonNull
    @Override
    public EventoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context ctx = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(ctx);

        View itemView = inflater.inflate(R.layout.item_participante, viewGroup, false); //Precisa arrumar
        EventoAdapter.ViewHolder vwHolder = new EventoAdapter.ViewHolder(itemView);
        return vwHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventoAdapter.ViewHolder viewHolder, int i) {
        Evento e = itens.get(i);
        TextView itemTitulo = viewHolder.txtTituloEvento;
        itemTitulo.setText(e.getTitulo());
        TextView itemDia = viewHolder.txtDiaEvento;
        itemDia.setText(e.getDia());
        TextView itemHorario = viewHolder.txtHorarioEvento;
        itemHorario.setText(e.getHorario());
        TextView itemFacilitador = viewHolder.txtFacilitadorEvento;
        itemFacilitador.setText(e.getFacilitador());
        TextView itemDescricao = viewHolder.txtDescricaoEvento;
        itemDescricao.setText(e.getDescricao());

        int idxTitulo = cursor.getColumnIndexOrThrow(EventoContract.Evento.COLUMN_NAME_TITULO);
        int idxDia = cursor.getColumnIndexOrThrow(EventoContract.Evento.COLUMN_NAME_DIA);
        int idxHorario = cursor.getColumnIndexOrThrow(EventoContract.Evento.COLUMN_NAME_HORARIO);
        int idxFacilitador = cursor.getColumnIndexOrThrow(EventoContract.Evento.COLUMN_NAME_FACILIT);
        int idxDescricao = cursor.getColumnIndexOrThrow(EventoContract.Evento.COLUMN_NAME_DESC);
        cursor.moveToPosition(i);
        viewHolder.txtTituloEvento.setText(cursor.getString(idxTitulo));
        viewHolder.txtDiaEvento.setText(cursor.getString(idxDia));
        viewHolder.txtHorarioEvento.setText(cursor.getString(idxHorario));
        viewHolder.txtFacilitadorEvento.setText(cursor.getString(idxFacilitador));
        viewHolder.txtDescricaoEvento.setText(cursor.getString(idxDescricao));
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTituloEvento;
        public TextView txtDiaEvento;
        public TextView txtHorarioEvento;
        public TextView txtFacilitadorEvento;
        public TextView txtDescricaoEvento;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            txtTituloEvento = (TextView) itemView.findViewById(R.id.txtNomeParticipante);
            txtDiaEvento = (TextView) itemView.findViewById(R.id.txtNomeParticipante);
            txtHorarioEvento = (TextView) itemView.findViewById(R.id.txtNomeParticipante);
            txtFacilitadorEvento = (TextView) itemView.findViewById(R.id.txtNomeParticipante);
            txtDescricaoEvento = (TextView) itemView.findViewById(R.id.txtNomeParticipante);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onEventoClick(itemView, position);
                        }
                    }
                }
            });

        }
    }
}

