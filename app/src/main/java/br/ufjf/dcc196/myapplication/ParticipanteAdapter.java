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

    private ArrayList<Participante> itens;
    private Cursor cursor;
    private OnParticClickListener listener;

    public ParticipanteAdapter(Cursor c){
        cursor = c;
    }

    public void setCursor(Cursor c){
        cursor = c;
        notifyDataSetChanged();
    }

    public interface OnParticClickListener {
        void onParticClick(View particView, int position);
    }

    public void setOnParticClickListener(OnParticClickListener listener){
        this.listener = listener;
    }


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
        TextView itemEmail = viewHolder.txtEmailParticipante;
        itemEmail.setText(p.getEmail());
        TextView itemCpf = viewHolder.txtCpfParticipante;
        itemCpf.setText(p.getCpf());

        int idxTitulo = cursor.getColumnIndexOrThrow(CadastroPartContract.CadastroParticipante.COLUMN_NAME_NOME);
        int idxAutor = cursor.getColumnIndexOrThrow(CadastroPartContract.CadastroParticipante.COLUMN_NAME_EMAIL);
        int idxAno = cursor.getColumnIndexOrThrow(CadastroPartContract.CadastroParticipante.COLUMN_NAME_CPF);
        cursor.moveToPosition(i);
        viewHolder.txtNomeParticipante.setText(cursor.getString(idxTitulo));
        viewHolder.txtEmailParticipante.setText(cursor.getString(idxAutor));
        viewHolder.txtCpfParticipante.setText(cursor.getString(idxAno));
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtNomeParticipante;
        public TextView txtEmailParticipante;
        public TextView txtCpfParticipante;



        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            txtNomeParticipante = (TextView)itemView.findViewById(R.id.txtNomeParticipante);
            txtEmailParticipante = (TextView)itemView.findViewById(R.id.txtNomeParticipante);
            txtCpfParticipante = (TextView)itemView.findViewById(R.id.txtNomeParticipante);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onParticClick(itemView, position);
                        }
                    }
                }
            });

        }
    }
}
