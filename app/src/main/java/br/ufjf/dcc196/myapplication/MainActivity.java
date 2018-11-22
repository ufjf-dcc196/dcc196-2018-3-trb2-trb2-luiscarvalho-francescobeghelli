package br.ufjf.dcc196.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnCadPart;
    private Button btnCadEvent;

    private SQLiteDatabase db;

    public static final int REQUEST_CREATE_PERSON = 1;
    public static final int REQUEST_CREATE_EVENT = 2;

    private RecyclerView lstParticipantes;
    private RecyclerView lstEventos;

    private static ParticipanteAdapter adapterPart;
    private static EventoAdapter adapterEvent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new ParticipanteEventoDbHelper(MainActivity.this).getWritableDatabase();

        btnCadPart = (Button)findViewById(R.id.btnCadParticipante);
        btnCadEvent = (Button)findViewById(R.id.btnCadEvento);

        btnCadPart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,CadastroParticipanteActivity.class);
                startActivityForResult(i, REQUEST_CREATE_PERSON);
            }
        });

        btnCadEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,CadastroEventoActivity.class);
                startActivityForResult(i, REQUEST_CREATE_EVENT);
            }
        });

        lstParticipantes = (RecyclerView)findViewById(R.id.lstParticipantes);
        adapterPart = new ParticipanteAdapter(getParticipanteCursor());
        lstParticipantes.setAdapter(adapterPart);
        lstParticipantes.setLayoutManager(new LinearLayoutManager(this));

        adapterPart.setOnParticClickListener(new ParticipanteAdapter.OnParticClickListener() {
            @Override
            public void onParticClick(View particView, int position) {
                Intent i = new Intent(MainActivity.this,CadastrarPartEventActivity.class);
                startActivityForResult(i, REQUEST_CREATE_PERSON);
            }
        });

        lstEventos = (RecyclerView)findViewById(R.id.lstEventos);
        adapterEvent = new EventoAdapter(getEventoCursor());
        lstEventos.setAdapter(adapterEvent);
        lstEventos.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CREATE_PERSON && resultCode == Activity.RESULT_OK)
            handleParticipanteCad();
        else if(requestCode == REQUEST_CREATE_EVENT && resultCode == Activity.RESULT_OK )
            handleEventoCad();
    }

    private void handleEventoCad() {
        lstEventos.swapAdapter(new ParticipanteAdapter(getEventoCursor()), false);
    }

    private void handleParticipanteCad() {
        lstParticipantes.swapAdapter(new ParticipanteAdapter(getParticipanteCursor()), false);
    }

    private Cursor getParticipanteCursor()
    {
        return db.query(ParticipanteContract.Participante.TABLE_NAME, new String[] {ParticipanteContract.Participante.COLUMN_NAME_NOME, ParticipanteContract.Participante.COLUMN_NAME_CPF,
                ParticipanteContract.Participante.COLUMN_NAME_EMAIL }, null,null,null,null,null,null);
    }

    private Cursor getEventoCursor()
    {
        return db.query(EventoContract.Evento.TABLE_NAME, new String[] {EventoContract.Evento.COLUMN_NAME_TITULO, EventoContract.Evento.COLUMN_NAME_HORARIO,
                EventoContract.Evento.COLUMN_NAME_DESC, EventoContract.Evento.COLUMN_NAME_FACILIT, EventoContract.Evento.COLUMN_NAME_DIA }, null,null,null,null,null,null);
    }
}
