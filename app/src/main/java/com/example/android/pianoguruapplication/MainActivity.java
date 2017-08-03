package com.example.android.pianoguruapplication;

import android.content.Context;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.media.AudioManager.STREAM_MUSIC;

public class MainActivity extends AppCompatActivity{

    private String mNotes[] = new String[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText notes = (EditText) findViewById(R.id.notes);
        final Button play = (Button) findViewById(R.id.play);
        final Button pause = (Button) findViewById(R.id.pause);
        final SoundPool sp = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mNotes = notes.getText().toString().split(" ");
                try {
                    start(sp, mNotes);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread.interrupted();
            }
        });

    }


    public void start(SoundPool sp, String[] mNotes) throws InterruptedException {


        for (int i=0;i<mNotes.length;i++) {
            String sound = mNotes[i];
            if (mNotes[i].equals(".")) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                Resources res = getApplicationContext().getResources();
                int playId = res.getIdentifier(sound, "raw", getApplicationContext().getPackageName());
                int soundId = sp.load(MainActivity.this, playId, 1);
                sp.play(soundId, 1, 1, 1, 0, 1);
            }
        }

    }
}