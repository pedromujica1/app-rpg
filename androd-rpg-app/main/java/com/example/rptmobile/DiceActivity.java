package com.example.rptmobile;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class DiceActivity extends AppCompatActivity {

    private Button buttonDado;
    private TextView resultDado;
    private VideoView vvRick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_dados);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        vvRick = findViewById(R.id.videoView);
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ngg);
        vvRick.setVideoURI(videoUri);
        vvRick.setOnCompletionListener(mp -> {
            vvRick.setVisibility(View.GONE);
            vvRick.seekTo(0);
        });

        buttonDado = findViewById(R.id.bt_dado);
        resultDado = findViewById(R.id.dado_result);
        buttonDado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int result = random.nextInt(20) + 1;
                if(result == 1) {
                    playBadRollVideo();
                }

                String stresult = String.valueOf(result);
                resultDado.setText(stresult);
                resultDado.setVisibility(View.VISIBLE);
            }
        });
    }
    private void playBadRollVideo() {
        // Mostrar e iniciar o vídeo
        vvRick.setVisibility(View.VISIBLE);
        vvRick.start();
    }
    protected void onDestroy() {
        super.onDestroy();
        if (vvRick != null) {
            vvRick.stopPlayback();
        }
    }
}