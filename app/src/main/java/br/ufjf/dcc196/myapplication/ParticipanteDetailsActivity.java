package br.ufjf.dcc196.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ParticipanteDetailsActivity extends AppCompatActivity {

    private static final int REQUEST_UPDATE_PARTICIPANTE = 1;
    private TextView txtNome;
    private TextView txtEmail;
    private TextView txtCpf;
    private Button btnEdtPart;
    private Button btnCadEvent;

    private RecyclerView listEventosPart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participante_details);

        txtNome = (TextView)findViewById(R.id.txtNomePartEvent);
        txtEmail = (TextView)findViewById(R.id.txtEmailPartEvent);
        txtCpf = (TextView)findViewById(R.id.txtCPFPartEvent);
        btnEdtPart = (Button)findViewById(R.id.btnEdtPart);
        btnCadEvent = (Button)findViewById(R.id.btnCadEvento);

        Bundle bundle = getIntent().getExtras();
        final int id = bundle.getInt("id");

        SQLiteDatabase db = new ParticipanteEventoDbHelper(this).getWritableDatabase();

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
    }
}