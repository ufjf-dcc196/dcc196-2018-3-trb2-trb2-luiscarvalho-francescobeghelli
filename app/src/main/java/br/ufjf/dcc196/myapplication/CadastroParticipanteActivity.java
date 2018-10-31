package br.ufjf.dcc196.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.provider.Telephony;
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
                if(validateEntry())
                {
                    Intent intent = new Intent();
                    intent.putExtra("nome",edtNome.getText().toString());
                    intent.putExtra("cpf",edtCpf.getText().toString());
                    intent.putExtra("email",edtEmail.getText().toString());
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
                else
                    Toast.makeText(CadastroParticipanteActivity.this,"Por favor preencha todos os campos", Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean validateEntry()
    {
        return !(edtNome.getText().toString().isEmpty() || edtEmail.getText().toString().isEmpty() || edtCpf.getText().toString().isEmpty());
    }
}
