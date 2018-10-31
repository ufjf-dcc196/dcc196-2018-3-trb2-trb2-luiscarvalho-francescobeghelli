package br.ufjf.dcc196.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ParticipanteAdapter extends RecyclerView.Adapter<ParticipanteAdapter.ViewHolder> {

    private ArrayList<Participante> itens;

    public ParticipanteAdapter(ArrayList<Participante> itens) {
        this.itens = itens;
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
        Participante p = itens.get(i);
        TextView itemNome = viewHolder.txtNomeParticipante;
        itemNome.setText(p.getNome());
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtNomeParticipante;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNomeParticipante = (TextView)itemView.findViewById(R.id.txtNomeParticipante);
        }
    }
}
