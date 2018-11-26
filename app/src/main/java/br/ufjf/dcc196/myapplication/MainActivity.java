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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnCadPart;
    private Button btnCadEvent;

    private SQLiteDatabase db;

    public static final int REQUEST_CREATE_PERSON = 1;
    public static final int REQUEST_CREATE_EVENT = 2;
    public static final int REQUEST_DETAILS_PERSON = 3;
    public static final int REQUEST_DETAILS_EVENT = 4;


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

        btnCadPart.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });

        btnCadEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,CadastroEventoActivity.class);
                startActivityForResult(i, REQUEST_CREATE_EVENT);
            }
        });

        btnCadEvent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });

        lstParticipantes = (RecyclerView)findViewById(R.id.lstParticipantes);
        adapterPart = new ParticipanteAdapter(ParticipanteContract.getParticipanteCursor(db, null, null));
        lstParticipantes.setAdapter(adapterPart);
        lstParticipantes.setLayoutManager(new LinearLayoutManager(this));

        adapterPart.setOnParticClickListener(new ParticipanteAdapter.OnParticClickListener() {
            @Override
            public void onParticClick(View particView, int itemId) {
                Intent i = new Intent(MainActivity.this, ParticipanteDetailsActivity.class);
                i.putExtra("id", itemId);
                startActivityForResult(i, REQUEST_DETAILS_PERSON);
            }
        });

        adapterPart.setOnParticLongClickListener(new ParticipanteAdapter.OnParticLongClickListener() {
            @Override
            public void onParticLongClick(View particView, int itemId) {
                db.delete(ParticipanteContract.Participante.TABLE_NAME,ParticipanteContract.Participante._ID  + " = ? "
                        ,new String[] { Integer.toString(itemId)});
                Toast.makeText( MainActivity.this,"Inscrição no evento removida", Toast.LENGTH_SHORT).show();
                MainActivity.this.recreate();
            }
        });

        lstEventos = (RecyclerView)findViewById(R.id.lstEventos);
        adapterEvent = new EventoAdapter(EventoContract.getEventoCursor(db, null, null));
        lstEventos.setAdapter(adapterEvent);
        lstEventos.setLayoutManager(new LinearLayoutManager(this));
        adapterEvent.setOnEventoClickListener(new EventoAdapter.OnEventoClickListener() {
            @Override
            public void onEventoClick(View eventoView, int itemId) {
                Intent i = new Intent(MainActivity.this, CadastrarPartEventActivity.class);
                i.putExtra("id", itemId);
                startActivityForResult(i, REQUEST_DETAILS_EVENT);
            }
        });
        adapterEvent.setOnEventoLongClickListener(new EventoAdapter.OnEventoLongClickListener() {
            @Override
            public void onEventoLongClick(View eventoView, int itemId) {
                Intent i = new Intent(MainActivity.this, ParticipanteDetailsActivity.class);
                i.putExtra("id", itemId);
                db.delete(EventoContract.Evento.TABLE_NAME,EventoContract.Evento._ID  + " = ? "
                        ,new String[] { Integer.toString(itemId)});
                Toast.makeText( MainActivity.this,"Inscrição no evento removida", Toast.LENGTH_SHORT).show();
                MainActivity.this.recreate();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CREATE_PERSON && resultCode == Activity.RESULT_OK)
            handleParticipanteCad();
        else if(requestCode == REQUEST_CREATE_EVENT && resultCode == Activity.RESULT_OK )
            handleEventoCad();
        else if(requestCode == REQUEST_DETAILS_PERSON)
            handleParticipanteCad();
        else if(requestCode == REQUEST_DETAILS_EVENT)
            handleEventoCad();
    }

    private void handleEventoCad() {
        adapterEvent = new EventoAdapter(EventoContract.getEventoCursor(db, null, null));
        adapterEvent.setOnEventoClickListener(new EventoAdapter.OnEventoClickListener() {
            @Override
            public void onEventoClick(View eventoView, int itemId) {
                Intent i = new Intent(MainActivity.this, CadastrarPartEventActivity.class);
                i.putExtra("id", itemId);
                startActivityForResult(i, REQUEST_DETAILS_EVENT);
            }
        });
        adapterEvent.setOnEventoLongClickListener(new EventoAdapter.OnEventoLongClickListener() {
            @Override
            public void onEventoLongClick(View eventoView, int itemId) {
                // não faz nada
            }
        });

        lstEventos.swapAdapter(adapterEvent, false);
    }

    private void handleParticipanteCad() {
        adapterPart = new ParticipanteAdapter(ParticipanteContract.getParticipanteCursor(db, null, null));
        adapterPart.setOnParticClickListener(new ParticipanteAdapter.OnParticClickListener() {
            @Override
            public void onParticClick(View particView, int itemId) {
                Intent i = new Intent(MainActivity.this, ParticipanteDetailsActivity.class);
                i.putExtra("id", itemId);
                startActivityForResult(i, REQUEST_DETAILS_PERSON);
            }
        });
        adapterPart.setOnParticLongClickListener(new ParticipanteAdapter.OnParticLongClickListener() {
            @Override
            public void onParticLongClick(View particView, int position) {
                // não faz nada
            }
        });

        lstParticipantes.swapAdapter(adapterPart, false);
    }
}