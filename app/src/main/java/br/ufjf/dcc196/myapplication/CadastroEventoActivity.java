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

public class CadastroEventoActivity extends AppCompatActivity {

    private EditText edtTitulo;
    private EditText edtDia;
    private EditText edtHorario;
    private EditText edtFacilit;
    private EditText edtDesc;
    private Button btnCadastrarEvento;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);

        edtTitulo = (EditText)findViewById(R.id.edtTituloCadEvento);
        edtDia = (EditText)findViewById(R.id.edtDiaCadEvento);
        edtHorario = (EditText)findViewById(R.id.edtHorarioCadEvento);
        edtFacilit = (EditText)findViewById(R.id.edtFacilitadorCadEvento);
        edtDesc = (EditText)findViewById(R.id.edtDescricaoCadEvento);
        btnCadastrarEvento = (Button) findViewById(R.id.btnCadastrarEvento);

        Bundle bundle = null;
        try {
            bundle = this.getIntent().getExtras();
            id = bundle.getInt("id", -1);
        }
        catch (Exception err)
        {
            id = -1;
        }

        if(id != -1) //é pra atualizar, preenche com os dados do evento
        {
            edtTitulo.setText(bundle.getString("titulo"));
            edtDia.setText(bundle.getString("dia"));
            edtHorario.setText(bundle.getString("horario"));
            edtFacilit.setText(bundle.getString("facilitador"));
            edtDesc.setText(bundle.getString("descricao"));
            btnCadastrarEvento.setText("Atualizar Evento");
        }

        btnCadastrarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    if (validateEntry()) {
                        if(id != -1)
                        {
                            EventoContract.updateEvento(new ParticipanteEventoDbHelper(CadastroEventoActivity.this).getWritableDatabase(), id,
                                    edtTitulo.getText().toString(), edtDia.getText().toString(), edtHorario.getText().toString(), edtFacilit.getText().toString(), edtDesc.getText().toString());

                            Intent i = new Intent();
                            i.putExtra("titulo", edtTitulo.getText().toString());
                            i.putExtra("horario", edtHorario.getText().toString());
                            i.putExtra("dia", edtDia.getText().toString());
                            i.putExtra("facilitador", edtFacilit.getText().toString());
                            i.putExtra("descricao", edtDesc.getText().toString());
                            setResult(Activity.RESULT_OK, i);
                        }
                        else {
                            EventoContract.saveEvento(new ParticipanteEventoDbHelper(CadastroEventoActivity.this).getWritableDatabase(),
                                    edtTitulo.getText().toString(), edtDia.getText().toString(), edtHorario.getText().toString(), edtFacilit.getText().toString(), edtDesc.getText().toString());
                            setResult(Activity.RESULT_OK);
                        }
                        Toast.makeText(CadastroEventoActivity.this, "Evento salvo com sucesso", Toast.LENGTH_SHORT).show();
                        finish();
                    } else
                        Toast.makeText(CadastroEventoActivity.this, "Por favor preencha todos os campos", Toast.LENGTH_LONG).show();

                }
                catch(Exception err)
                {
                    Toast.makeText(CadastroEventoActivity.this, "Ocorreu um erro ao salvar o evento.", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCadastrarEvento.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
    }

    private boolean validateEntry()
    {
        return !(edtTitulo.getText().toString().isEmpty() || edtDia.getText().toString().isEmpty() ||
                edtHorario.getText().toString().isEmpty()|| edtFacilit.getText().toString().isEmpty() ||
                edtDesc.getText().toString().isEmpty());
    }
}
