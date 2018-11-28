package br.ufjf.dcc196.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
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

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_participante);

        edtNome = (EditText)findViewById(R.id.edtNomeCompleto);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtCpf = (EditText)findViewById(R.id.edtCpf);

        Button btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

        Bundle bundle = null;
        try {
            bundle = this.getIntent().getExtras();
            id = bundle.getInt("id", -1);
        }
        catch (Exception err)
        {
            id = -1;
        }

        if(id != -1) //é pra atualizar, preenche com os dados do participante
        {
            edtNome.setText(bundle.getString("nome"));
            edtEmail.setText(bundle.getString("email"));
            edtCpf.setText(bundle.getString("cpf"));
            btnCadastrar.setText("Atualizar Participante");
        }

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    if(validateEntry())
                    {
                        if(id != -1)
                        {
                            ParticipanteContract.updateParticipante(new ParticipanteEventoDbHelper(CadastroParticipanteActivity.this).getWritableDatabase(), id,
                                    edtCpf.getText().toString(), edtEmail.getText().toString(), edtNome.getText().toString());
                            Intent i = new Intent();
                            i.putExtra("nome", edtNome.getText().toString());
                            i.putExtra("email", edtEmail.getText().toString());
                            i.putExtra("cpf", edtCpf.getText().toString());
                            setResult(Activity.RESULT_OK, i);
                        }
                        else
                        {
                            ParticipanteContract.saveParticipantes(new ParticipanteEventoDbHelper(CadastroParticipanteActivity.this).getWritableDatabase(),
                                    edtCpf.getText().toString(), edtEmail.getText().toString(), edtNome.getText().toString());
                            setResult(Activity.RESULT_OK);
                        }

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

    private boolean validateEntry()
    {
        return !(edtNome.getText().toString().isEmpty() || edtEmail.getText().toString().isEmpty() || edtCpf.getText().toString().isEmpty());
    }
}
