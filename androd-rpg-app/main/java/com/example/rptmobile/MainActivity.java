package com.example.rptmobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button Tela_dado;
    private Button Tela_personagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Tela_dado = findViewById(R.id.bt_TelaDado);
        Tela_dado.setOnClickListener(view-> {
            startActivity(new Intent(this, DiceActivity.class));
        });

        Tela_personagem = findViewById(R.id.bt_TelaPesonagem);
        Tela_personagem.setOnClickListener(view ->{
            startActivity(new Intent(this, CharaActivity.class));
        });

    }
}