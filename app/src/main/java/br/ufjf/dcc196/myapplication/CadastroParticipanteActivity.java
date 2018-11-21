package br.ufjf.dcc196.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroParticipanteActivity extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtEmail;
    private EditText edtCpf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_participante);

        edtNome = (EditText)findViewById(R.id.edtNomeCompleto);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtCpf = (EditText)findViewById(R.id.edtCpf);

        Button btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    if(validateEntry())
                    {
                        saveParticipantes();

                        setResult(Activity.RESULT_OK);
                        Toast.makeText(CadastroParticipanteActivity.this,"Participante salvo com sucesso", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else
                        Toast.makeText(CadastroParticipanteActivity.this,"Por favor preencha todos os campos", Toast.LENGTH_LONG).show();
                }
                catch(Exception er)
                {
                    Toast.makeText(CadastroParticipanteActivity.this,"Ocorreu um erro ao salvar", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void saveParticipantes() {
        SQLiteDatabase db = new ParticipanteEventoDbHelper(CadastroParticipanteActivity.this).getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(ParticipanteContract.Participante.COLUMN_NAME_CPF, edtCpf.getText().toString());
        cv.put(ParticipanteContract.Participante.COLUMN_NAME_EMAIL, edtEmail.getText().toString());
        cv.put(ParticipanteContract.Participante.COLUMN_NAME_NOME, edtNome.getText().toString());

        db.insert(ParticipanteContract.Participante.TABLE_NAME,null, cv);
    }

    private boolean validateEntry()
    {
        return !(edtNome.getText().toString().isEmpty() || edtEmail.getText().toString().isEmpty() || edtCpf.getText().toString().isEmpty());
    }
}
