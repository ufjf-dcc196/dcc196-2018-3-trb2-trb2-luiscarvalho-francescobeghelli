package br.ufjf.dcc196.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class CadastrarPartEventActivity extends AppCompatActivity {

    private TextView txtTitulo;
    private TextView txtDia;
    private TextView txtHorario;
    private TextView txtFacilitador;
    private TextView txtDescricao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_part_event);

        txtTitulo = (TextView)findViewById(R.id.txtNomePartEvent);
        txtDia = (TextView)findViewById(R.id.txtEmailPartEvent);
        txtHorario = (TextView)findViewById(R.id.txtCPFPartEvent);
        txtFacilitador = (TextView)findViewById(R.id.txtCPFPartEvent);
        txtDescricao = (TextView)findViewById(R.id.txtCPFPartEvent);

        Bundle bundle = getIntent().getExtras();
        final String id = Integer.toString(bundle.getInt("id"));

        SQLiteDatabase db = new ParticipanteEventoDbHelper(this).getWritableDatabase();

        Cursor cursor = ParticipanteContract.getParticipanteCursor(db,EventoContract.Evento._ID+" = ?",new String[] {id});
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

    }
}
