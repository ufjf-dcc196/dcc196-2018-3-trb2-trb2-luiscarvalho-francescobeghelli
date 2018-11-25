package br.ufjf.dcc196.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ParticipanteDetailsActivity extends AppCompatActivity {

    private TextView txtNome;
    private TextView txtEmail;
    private TextView txtCpf;

    Bundle bundle = getIntent().getExtras();
    final String id = Integer.toString(bundle.getInt("id"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participante_details);

        SQLiteDatabase db = new ParticipanteEventoDbHelper(this).getWritableDatabase();

        Cursor cursor = ParticipanteContract.getParticipanteCursor(db,ParticipanteContract.Participante._ID+" = ?",new String[] {id});
        int idxNome = cursor.getColumnIndexOrThrow(ParticipanteContract.Participante.COLUMN_NAME_NOME);
        int idxEmail = cursor.getColumnIndexOrThrow(ParticipanteContract.Participante.COLUMN_NAME_EMAIL);
        int idxCPF = cursor.getColumnIndexOrThrow(ParticipanteContract.Participante.COLUMN_NAME_CPF);
        txtNome.setText(cursor.getString(idxNome));
        txtEmail.setText(cursor.getString(idxEmail));
        txtCpf.setText(cursor.getString(idxCPF));

    }
}

//cv.getAsString();