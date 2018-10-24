package br.ufjf.dcc196.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {

    private Button btnCadPart;
    private Button btnCadEvent;
    private ScrollView scrView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadPart = (Button)findViewById(R.id.btnCadParticipante);
        btnCadEvent = (Button)findViewById(R.id.btnCadEvento);

        btnCadPart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,CadastroParticipanteActivity.class);
                startActivity(i);
            }
        });

        btnCadEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,CadastroEventoActivity.class);
                startActivity(i);
            }
        });
    }
}
