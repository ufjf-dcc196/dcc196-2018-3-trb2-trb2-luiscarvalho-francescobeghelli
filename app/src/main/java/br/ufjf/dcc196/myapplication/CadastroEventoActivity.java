package br.ufjf.dcc196.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastroEventoActivity extends AppCompatActivity {

    private EditText edtTitulo;
    private EditText edtDia;
    private EditText edtHorario;
    private EditText edtFacilit;
    private EditText edtDesc;
    private Button btnCadastrarEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);

        edtTitulo = (EditText)findViewById(R.id.edtTituloCadEvento);
        edtDia = (EditText)findViewById(R.id.edtDiaCadEvento);
        edtHorario = (EditText)findViewById(R.id.edtHorarioCadEvento);
        edtFacilit = (EditText)findViewById(R.id.edtFacilitadorCadEvento);
        edtHorario = (EditText)findViewById(R.id.edtDescricaoCadEvento);

        btnCadastrarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }
}
