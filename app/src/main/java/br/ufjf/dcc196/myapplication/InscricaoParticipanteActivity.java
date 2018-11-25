package br.ufjf.dcc196.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

public class InscricaoParticipanteActivity extends AppCompatActivity {

    private RecyclerView lstInscricaoParticipante;

    private SQLiteDatabase db;

    private static EventoAdapter adapterEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscricao_participante);

        Bundle bundle = getIntent().getExtras();
        final int id = bundle.getInt("id");

        db = new ParticipanteEventoDbHelper(this).getWritableDatabase();

        lstInscricaoParticipante = (RecyclerView)findViewById(R.id.lstInscricaoParticipante);
        adapterEvent = new EventoAdapter(InscricaoContract.getEventosQueNaoParticipaCursor(db, id));
        lstInscricaoParticipante.setAdapter(adapterEvent);
        lstInscricaoParticipante.setLayoutManager(new LinearLayoutManager(this));

        adapterEvent.setOnEventoClickListener(new EventoAdapter.OnEventoClickListener() {
            @Override
            public void onEventoClick(View eventoView, int itemId) {
                InscricaoContract.saveInscricao(db,itemId,id);
                Toast.makeText(InscricaoParticipanteActivity.this,"Inscrição feita com sucesso",Toast.LENGTH_SHORT).show();
                setResult(Activity.RESULT_OK);
                finish();
            }
        });
        }
}
