package br.ufjf.dcc196.myapplication;

import android.app.Activity;
import android.content.Intent;
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


    private static ArrayList<Participante> participanteList = new ArrayList<Participante>();
    private static ArrayList<Evento> eventoList = new ArrayList<Evento>();

    public static final int REQUEST_CREATE_PERSON = 1;
    public static final int REQUEST_CREATE_EVENT = 2;

    private RecyclerView lstParticipantes;

    private static ParticipanteAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        adapter = new ParticipanteAdapter(participanteList);
        lstParticipantes.setAdapter(adapter);
        lstParticipantes.setLayoutManager(new LinearLayoutManager(this));

        adapter.setOnParticClickListener(new ParticipanteAdapter.OnParticClickListener() {
            @Override
            public void onParticClick(View particView, int position) {
                Intent i = new Intent(MainActivity.this,CadastrarPartEventActivity.class);
                startActivityForResult(i, REQUEST_CREATE_PERSON);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CREATE_PERSON && resultCode == Activity.RESULT_OK && data != null)
            handleParticipanteCad(data);
        else if(requestCode == REQUEST_CREATE_EVENT && resultCode == Activity.RESULT_OK && data != null)
            handleEventoCad(data);
    }

    private void handleEventoCad(Intent data) {
        eventoList.add(new Evento(data.getStringExtra("titulo"),data.getStringExtra("dia"),data.getStringExtra("horario"),
                data.getStringExtra("facilitador"),data.getStringExtra("descricao")));
    }

    private void handleParticipanteCad(Intent data) {
        participanteList.add(new Participante(data.getStringExtra("nome"),data.getStringExtra("cpf"),data.getStringExtra("email")));
        lstParticipantes.swapAdapter(new ParticipanteAdapter(participanteList), false);
    }
}
