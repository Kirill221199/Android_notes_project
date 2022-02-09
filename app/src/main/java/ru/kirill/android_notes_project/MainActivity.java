package ru.kirill.android_notes_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(savedInstanceState == null){
            Note_fragment note_fragment = new Note_fragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_note, note_fragment).commit();

            if(getResources().getConfiguration().orientation == Configuration
            .ORIENTATION_LANDSCAPE){
                Note_content_fragment note_content_fragment = new Note_content_fragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_note_content,
                        note_content_fragment).commit();
            }
        }

    }
}