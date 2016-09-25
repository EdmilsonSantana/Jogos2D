package br.com.projetos.jogodaforca;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class Main extends AppCompatActivity {

    private Button btJogar;
    private Button btPlay;
    private EditText etLetra;
    private ForcaView forcaView;
    private ForcaService forcaService;

    private String[] palavras = new String[]{"alura", "caelum"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.main);

        btJogar = (Button) findViewById(R.id.btJogar);
        btPlay = (Button) findViewById(R.id.btPlay);
        etLetra = (EditText) findViewById(R.id.etLetra);

        forcaView = (ForcaView) findViewById(R.id.fvJogo);

        this.init();
    }

    private void init() {
        btJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( etLetra.getText().toString().trim().length() == 0 ) {
                    return;
                }

                getForcaService().joga(etLetra.getText().toString().trim().charAt(0));
                forcaView.invalidate();
                etLetra.getText().clear();

                if( getForcaService().isTerminou() ) {
                    btJogar.setEnabled(Boolean.FALSE);
                    btPlay.setEnabled(Boolean.TRUE);
                    if ( getForcaService().isMorreu()) {
                        Toast.makeText(getApplicationContext(), "Ops! Você perdeu", Toast.LENGTH_SHORT).show();
                    } else if(getForcaService().isGanhou()) {
                        Toast.makeText(getApplicationContext(), "Parabéns, você ganhou!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        btPlay.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String palavra = palavras[new Random().nextInt(palavras.length) ];
                setForcaService( new ForcaService(palavra) );
                forcaView.setForcaService(getForcaService());

                etLetra.getText().clear();
                etLetra.setEnabled(Boolean.TRUE);
                btJogar.setEnabled(Boolean.TRUE);
                btPlay.setEnabled(Boolean.FALSE);
            }
        });
    }

    public ForcaService getForcaService() {
        return forcaService;
    }

    public void setForcaService(ForcaService forcaService) {
        this.forcaService = forcaService;
    }
}
