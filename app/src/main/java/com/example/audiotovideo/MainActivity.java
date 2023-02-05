package com.example.audiotovideo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CardView card1, card2;
    TextView select, process;

    ImageView play, pause;
    Uri Video;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        select = findViewById(R.id.VideoTxt);
        process = findViewById(R.id.Audiotxt);
        play = findViewById(R.id.play);
        pause = findViewById(R.id.pause);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("video/*");
                startActivityForResult(intent, 1);
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Video != null){
                    play();
                }
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player != null) {
                    player.pause();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (data == null){
                return;
            }
            Video = data.getData();
            select.setText(getName(data.getData().getPath()));
            process.setText("Done Press to play");
        }
    }

    public String getName(String name) {
        String cutName = "";
        for (int i = name.length() - 1; i > 0; i--) {
            if (name.charAt(i) == '/') {
                break;
            }
            cutName = name.substring(i);
        }
        return cutName;
    }

    public void play(){
        player = MediaPlayer.create(getApplicationContext(), Video);
        player.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (player != null) {
            player.pause();
        }
    }
}