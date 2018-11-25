package br.ufjf.dcc196.myapplication;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class InscricaoParticipanteActivity extends AppCompatActivity {

    private RecyclerView lstInscricaoParticipante;

    private SQLiteDatabase db;

    private static EventoAdapter adapterPart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscricao_participante);

        Bundle bundle = getIntent().getExtras();
        final int id = bundle.getInt("id");

        db = new ParticipanteEventoDbHelper(this).getWritableDatabase();

        lstInscricaoParticipante = (RecyclerView)findViewById(R.id.lstInscricaoParticipante);
        adapterPart = new EventoAdapter(InscricaoContract.getEventosQueNaoParticipaCursor(db, id));
        lstInscricaoParticipante.setAdapter(adapterPart);
        lstInscricaoParticipante.setLayoutManager(new LinearLayoutManager(this));
        }
}
