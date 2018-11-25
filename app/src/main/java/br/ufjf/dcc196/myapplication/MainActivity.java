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
import java.util.List;

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

    public static List<Participante> participantes = new ArrayList<Participante>(){{

        Participante part1 = new Participante("Fulano da Silva", "66666666666", "fulano@hotmail.com");
        Participante part2 = new Participante("Cicrano da Silva", "77777777777", "cicrano@hotmail.com");
        Participante part3 = new Participante("Beltrano da Silva", "11111111111", "beltrano@hotmail.com");
        Participante part4 = new Participante("Outrano da Silva", "3333333333", "outrano@hotmail.com");

        add(part1);
        add(part2);
        add(part3);
        add(part4);
    }};

    public static List<Evento> eventos = new ArrayList<Evento>(){{
        Evento ev1 = new Evento("Curso de Aprendizagem 1", "04/02/2020", "16:20" , "Teacher Love" , "Curso de Aprendizagem 1.");
        Evento ev2 = new Evento("Curso de Aprendizagem 2", "04/02/2020", "16:20" , "Teacher Happiness" , "Curso de Aprendizagem 2.");
        Evento ev3 = new Evento("Curso de Aprendizagem 3", "04/02/2020", "16:20" , "Teacher A" , "Curso de Aprendizagem 3.");
        Evento ev4 = new Evento("Curso de Aprendizagem 4", "04/02/2020", "16:20" , "Teacher Newbie" , "Curso de Aprendizagem 4.");

        add(ev1);
        add(ev2);
        add(ev3);
        add(ev4);

        ev1.getInscritos().add(participantes.get(1));
        ev2.getInscritos().add(participantes.get(1));
        ev3.getInscritos().add(participantes.get(1));
        ev4.getInscritos().add(participantes.get(1));

        participantes.get(1).getEventos().add(ev1);
        participantes.get(1).getEventos().add(ev1);
        participantes.get(1).getEventos().add(ev1);
        participantes.get(1).getEventos().add(ev1);
    }};

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
                i.putExtra("posicao", position);
                startActivityForResult(i, REQUEST_CREATE_PERSON);
            }
        });

        lstEventos = (RecyclerView)findViewById(R.id.lstEventos);
        adapterEvent = new EventoAdapter(getEventoCursor());
        lstEventos.setAdapter(adapterEvent);
        lstEventos.setLayoutManager(new LinearLayoutManager(this));
        adapterEvent.setOnEventoClickListener(new EventoAdapter.OnEventoClickListener() {
            @Override
            public void onEventoClick(View eventoView, int position) {
                Intent i = new Intent(MainActivity.this,CadastrarPartEventActivity.class);
                startActivityForResult(i, REQUEST_CREATE_PERSON);
            }
        });
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
