package br.ufjf.dcc196.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

public class CadastrarPartEventActivity extends AppCompatActivity {

    private static final int REQUEST_UPDATE_EVENT = 1;
    private TextView txtTitulo;
    private TextView txtDia;
    private TextView txtHorario;
    private TextView txtFacilitador;
    private TextView txtDescricao;
    private Button btnEditarEvento;

    private RecyclerView lstPartCadastradosEvento;
    private SQLiteDatabase db;
    private static ParticipanteAdapter adapterPart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_part_event);

        db = new ParticipanteEventoDbHelper(this).getWritableDatabase();

        Bundle bundle = getIntent().getExtras();
        final int id = bundle.getInt("id");

        txtTitulo = (TextView)findViewById(R.id.txtTituloCadPartEvent);
        txtDia = (TextView)findViewById(R.id.txtDiaCadPartEvent);
        txtHorario = (TextView)findViewById(R.id.txtHorarioCadPartEvent);
        txtFacilitador = (TextView)findViewById(R.id.txtFacilitadorCadPartEvent);
        txtDescricao = (TextView)findViewById(R.id.txtDescricaoCadPartEvent);

        lstPartCadastradosEvento = (RecyclerView)findViewById(R.id.lstPartCadastradosEvento);
        adapterPart = new ParticipanteAdapter(InscricaoContract.getParticipantesEventoCursor(db, id));
        adapterPart.setOnParticClickListener(new ParticipanteAdapter.OnParticClickListener() {
            @Override
            public void onParticClick(View particView, int position) {
                // não faz nada
            }
        });
        adapterPart.setOnParticLongClickListener(new ParticipanteAdapter.OnParticLongClickListener() {
            @Override
            public void onParticLongClick(View particView, int itemId) {
                db.delete(InscricaoContract.Inscricao.TABLE_NAME,InscricaoContract.Inscricao.COLUMN_NAME_ID_PARTICIPANTE  + " = ? AND "
                        + InscricaoContract.Inscricao.COLUMN_NAME_ID_EVENTO + " = ?",new String[] { Integer.toString(itemId), Integer.toString(id) });
                Toast.makeText( CadastrarPartEventActivity.this,"Inscrição no evento removida", Toast.LENGTH_SHORT).show();
                CadastrarPartEventActivity.this.recreate();
            }
        });
        lstPartCadastradosEvento.setAdapter(adapterPart);
        lstPartCadastradosEvento.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor = EventoContract.getEventoCursor(db,EventoContract.Evento._ID+" = ?",new String[] { Integer.toString(id) });
        int idxTitulo = cursor.getColumnIndexOrThrow(EventoContract.Evento.COLUMN_NAME_TITULO);
        int idxDia = cursor.getColumnIndexOrThrow(EventoContract.Evento.COLUMN_NAME_DIA);
        int idxHorario = cursor.getColumnIndexOrThrow(EventoContract.Evento.COLUMN_NAME_HORARIO);
        int idxFacilitador = cursor.getColumnIndexOrThrow(EventoContract.Evento.COLUMN_NAME_FACILIT);
        int idxDescricao = cursor.getColumnIndexOrThrow(EventoContract.Evento.COLUMN_NAME_DESC);
        cursor.moveToNext();
        txtTitulo.setText(cursor.getString(idxTitulo));
        txtDia.setText(cursor.getString(idxDia));
        txtHorario.setText(cursor.getString(idxHorario));
        txtFacilitador.setText(cursor.getString(idxFacilitador));
        txtDescricao.setText(cursor.getString(idxDescricao));

        btnEditarEvento = (Button)findViewById(R.id.btnEditarEvento);
        btnEditarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CadastrarPartEventActivity.this, CadastroEventoActivity.class);
                i.putExtra("id", id);
                i.putExtra("titulo", txtTitulo.getText().toString());
                i.putExtra("dia", txtDia.getText().toString());
                i.putExtra("facilitador", txtFacilitador.getText().toString());
                i.putExtra("descricao", txtDescricao.getText().toString());
                i.putExtra("horario", txtHorario.getText().toString());
                startActivityForResult(i, REQUEST_UPDATE_EVENT);
            }
        });
        btnEditarEvento.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_UPDATE_EVENT && resultCode == Activity.RESULT_OK)
        {
            Bundle bundle = data.getExtras();
            txtTitulo.setText(bundle.getString("titulo"));
            txtDia.setText(bundle.getString("dia"));
            txtHorario.setText(bundle.getString("horario"));
            txtFacilitador.setText(bundle.getString("facilitador"));
            txtDescricao.setText(bundle.getString("descricao"));
        }
    }
}
