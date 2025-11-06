package com.example.rptmobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class CharaActivity extends AppCompatActivity {

    private Button Criar_Personagem;
    private ListView listChars;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> charsList;

    //private TextView tvCreatedCharacter;
    private ActivityResultLauncher<Intent> createCharaLauncher;
    private static final String PREFS_NAME = "rpt_prefs";
    private static final String KEY_CHARS_JSON = "chars_json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chara);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Criar_Personagem = findViewById(R.id.bt_chCreate);
        //tvCreatedCharacter = findViewById(R.id.tv_charName);
        listChars = findViewById(R.id.list_chars);
        charsList = loadChars();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, charsList);
        listChars.setAdapter(adapter);

        createCharaLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        String name = result.getData().getStringExtra("char_name");
                        if (name != null && !name.isEmpty()) {
                            name = name.trim(); //remove os espaços do inicio e fim
                            if(!name.isEmpty()) {
                                charsList.add(name);
                                adapter.notifyDataSetChanged();
                                saveChars(charsList);
                            }
                        }
                    }
                });

        Criar_Personagem.setOnClickListener(view ->{
            createCharaLauncher.launch(new Intent(this, chCreateActivity.class));
        });
    }

    private void saveChars(ArrayList<String> list) {
        JSONArray arr = new JSONArray();
        for(String s : list) arr.put(s);
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putString(KEY_CHARS_JSON, arr.toString()).apply();
    }

    private ArrayList<String> loadChars() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(KEY_CHARS_JSON, null);
        ArrayList<String> list = new ArrayList<>();

        if(json != null) {
            try {
                JSONArray arr = new JSONArray(json);

                for(int i = 0; i < arr.length(); i++) {
                    String name = arr.optString(i, null);
                    if(name != null) list.add(name);
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

}