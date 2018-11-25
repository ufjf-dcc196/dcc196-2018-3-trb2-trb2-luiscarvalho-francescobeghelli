package br.ufjf.dcc196.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class CadastrarPartEventActivity extends AppCompatActivity {

    private TextView txtNome;
    private TextView txtEmail;
    private TextView txtCpf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_part_event);

        Bundle bundle = getIntent().getExtras();
        //final int posicao = bundle.getInt("posicao");
        //final Participante participante = MainActivity.participantes.get(posicao);
        //txtNome.setText(participante.getNome());
        //txtEmail.setText(participante.getEmail());
        //txtCpf.setText(participante.getCpf());
    }
}
