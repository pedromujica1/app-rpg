package com.example.rptmobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class chCreateActivity extends AppCompatActivity {

    private Button Done_bt;
    private EditText etName;
    private Button Cancel_bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ch_create);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Spinner race_selection = findViewById(R.id.race);
        List<String> races = new ArrayList<>();
        races.add("Anão");
        races.add("Elfo");
        races.add("Halfling");
        races.add("Humano");
        races.add("Meio-Elfo");
        races.add("Tiefling");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, races);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        race_selection.setAdapter(adapter);
        Spinner class_selection = findViewById(R.id.classe);

        List<String> classes = new ArrayList<>();
        classes.add("Bárbaro");
        classes.add("Bardo");
        classes.add("Bruxo");
        classes.add("Clérigo");
        classes.add("Druida");
        classes.add("Feiticeiro");
        classes.add("Guerreiro");
        classes.add("Ladino");
        classes.add("Mago");
        classes.add("Monge");
        classes.add("Paladino");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, classes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        class_selection.setAdapter(adapter);

        Spinner background_selection = findViewById(R.id.bground);
        List<String> antecedentes = new ArrayList<>();
        antecedentes.add("Artesão");
        antecedentes.add("Artista");
        antecedentes.add("Charlatão");
        antecedentes.add("Criminoso");
        antecedentes.add("Gladiador");
        antecedentes.add("Nobre");
        antecedentes.add("Órfão");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, antecedentes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        background_selection.setAdapter(adapter);


        etName = findViewById(R.id.et_Name);
        Done_bt = findViewById(R.id.bt_done);
        Done_bt.setOnClickListener(view -> {
            String name = etName.getText().toString().trim(); //pega o nome q foi botado la
            Intent result = new Intent();
            result.putExtra("char_name", name);
            setResult(RESULT_OK, result);
            finish();
        });

        Cancel_bt = findViewById(R.id.bt_cancel);
        Cancel_bt.setOnClickListener(view -> finish());
    }
}
