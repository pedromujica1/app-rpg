package net.hanshq.hello;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends Activity {

    private EditText editNome, editClasse, editAntecedente, editAlinhamento, editIdade;
    private TextView txtContagem, txtResultadoDado;
    private Button btnSalvar, btnGirarDado;

    private int contador = 0;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNome = findViewById(R.id.editNome);
        editClasse = findViewById(R.id.editClasse);
        editAntecedente = findViewById(R.id.editAntecedente);
        editAlinhamento = findViewById(R.id.editAlinhamento);
        editIdade = findViewById(R.id.editIdade);
        txtContagem = findViewById(R.id.txtContagem);
        txtResultadoDado = findViewById(R.id.txtResultadoDado);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnGirarDado = findViewById(R.id.btnGirarDado);

        // Botão Salvar Personagem
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contador++;
                txtContagem.setText("Personagens cadastrados: " + contador);

                // Limpar campos após salvar
                editNome.setText("");
                editClasse.setText("");
                editAntecedente.setText("");
                editAlinhamento.setText("");
                editIdade.setText("");
            }
        });

        // Botão Girar Dado
        btnGirarDado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int resultado = random.nextInt(21); // 0 a 20
                txtResultadoDado.setText("Resultado do dado: " + resultado);
            }
        });
    }
}
