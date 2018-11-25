package br.ufjf.dcc196.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CadastrarPartEventActivity extends AppCompatActivity {

    private static final int REQUEST_UPDATE_EVENT = 1;
    private TextView txtTitulo;
    private TextView txtDia;
    private TextView txtHorario;
    private TextView txtFacilitador;
    private TextView txtDescricao;
    private Button btnEditarEvento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_part_event);

        txtTitulo = (TextView)findViewById(R.id.txtTituloCadPartEvent);
        txtDia = (TextView)findViewById(R.id.txtDiaCadPartEvent);
        txtHorario = (TextView)findViewById(R.id.txtHorarioCadPartEvent);
        txtFacilitador = (TextView)findViewById(R.id.txtFacilitadorCadPartEvent);
        txtDescricao = (TextView)findViewById(R.id.txtDescricaoCadPartEvent);

        Bundle bundle = getIntent().getExtras();
        final int id = bundle.getInt("id");

        SQLiteDatabase db = new ParticipanteEventoDbHelper(this).getWritableDatabase();

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
