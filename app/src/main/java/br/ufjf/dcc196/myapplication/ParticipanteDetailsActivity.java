package br.ufjf.dcc196.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ParticipanteDetailsActivity extends AppCompatActivity {

    private static final int REQUEST_UPDATE_PARTICIPANTE = 1;
    private static final int REQUEST_ADD_INSCRICAO = 2;
    private TextView txtNome;
    private TextView txtEmail;
    private TextView txtCpf;
    private Button btnEdtPart;
    private Button btnCadEvent;
    private int id;

    private RecyclerView listEventosPart;
    private SQLiteDatabase db;
    private static EventoAdapter adapterPart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participante_details);

        db = new ParticipanteEventoDbHelper(this).getWritableDatabase();

        txtNome = (TextView)findViewById(R.id.txtNomePartEvent);
        txtEmail = (TextView)findViewById(R.id.txtEmailPartEvent);
        txtCpf = (TextView)findViewById(R.id.txtCPFPartEvent);
        btnEdtPart = (Button)findViewById(R.id.btnEdtPart);
        btnCadEvent = (Button)findViewById(R.id.btnCadEvento);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("id");

        listEventosPart = (RecyclerView) findViewById(R.id.lstEventosParticipante);
        fillAdapter();

        Cursor cursor = ParticipanteContract.getParticipanteCursor(db,ParticipanteContract.Participante._ID+" = ?",new String[] {Integer.toString(id)});
        int idxNome = cursor.getColumnIndexOrThrow(ParticipanteContract.Participante.COLUMN_NAME_NOME);
        int idxEmail = cursor.getColumnIndexOrThrow(ParticipanteContract.Participante.COLUMN_NAME_EMAIL);
        int idxCPF = cursor.getColumnIndexOrThrow(ParticipanteContract.Participante.COLUMN_NAME_CPF);
        cursor.moveToNext();
        txtNome.setText(cursor.getString(idxNome));
        txtEmail.setText(cursor.getString(idxEmail));
        txtCpf.setText(cursor.getString(idxCPF));

        btnEdtPart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ParticipanteDetailsActivity.this, CadastroParticipanteActivity.class);
                i.putExtra("id", id);
                i.putExtra("nome", txtNome.getText().toString());
                i.putExtra("email", txtEmail.getText().toString());
                i.putExtra("cpf", txtCpf.getText().toString());
                startActivityForResult(i, REQUEST_UPDATE_PARTICIPANTE);
            }
        });

        btnCadEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ParticipanteDetailsActivity.this,InscricaoParticipanteActivity.class);
                i.putExtra("id", id);
                startActivityForResult(i, REQUEST_ADD_INSCRICAO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_UPDATE_PARTICIPANTE && resultCode == Activity.RESULT_OK)
        {
            Bundle bundle = data.getExtras();
            txtNome.setText(bundle.getString("nome"));
            txtCpf.setText(bundle.getString("cpf"));
            txtEmail.setText(bundle.getString("email"));
        }
        else if(requestCode == REQUEST_ADD_INSCRICAO && resultCode == Activity.RESULT_OK){
            fillAdapter();
        }
    }

    private void fillAdapter()
    {
        adapterPart = new EventoAdapter(InscricaoContract.getEventosQueParticipaCursor(db, id));
        adapterPart.setOnEventoLongClickListener(new EventoAdapter.OnEventoLongClickListener() {
            @Override
            public void onEventoLongClick(View eventoView, int itemId) {
                db.delete(InscricaoContract.Inscricao.TABLE_NAME,InscricaoContract.Inscricao.COLUMN_NAME_ID_PARTICIPANTE  + " = ? AND "
                        + InscricaoContract.Inscricao.COLUMN_NAME_ID_EVENTO + " = ?",new String[] { Integer.toString(id), Integer.toString(itemId) });
                Toast.makeText( ParticipanteDetailsActivity.this,"Inscrição no evento removida", Toast.LENGTH_SHORT).show();
                adapterPart.notifyItemRemoved(listEventosPart.getChildLayoutPosition(eventoView));
                adapterPart.setCursor(InscricaoContract.getEventosQueParticipaCursor(db, id));
            }
        });
        listEventosPart.setAdapter(adapterPart);
        listEventosPart.setLayoutManager(new LinearLayoutManager(this));
        listEventosPart.swapAdapter(adapterPart,false);
    }
}