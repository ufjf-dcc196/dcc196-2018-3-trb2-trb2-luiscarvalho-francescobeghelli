package br.ufjf.dcc196.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class InscricaoParticipanteActivity extends AppCompatActivity {

    private RecyclerView lstInscricaoParticipante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscricao_participante);

        lstInscricaoParticipante = (RecyclerView)findViewById(R.id.lstInscricaoParticipante);
        adapterInscricao = new EventoAdapter(InscricaoContract.getInscricaoCursor(db, null, null));
        lstInscricaoParticipante.setAdapter(adapterInscricao);
        lstInscricaoParticipante.setLayoutManager(new LinearLayoutManager(this));
        }
}
