package ru.kirill.android_notes_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(savedInstanceState == null) {
            Note_fragment note_fragment = new Note_fragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_note, note_fragment).commit();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Fragment backStackFragment = (Fragment)getSupportFragmentManager()
                .findFragmentById(R.id.frame_note);
        if(backStackFragment!=null&&backStackFragment instanceof Note_content_fragment){
            onBackPressed();
        }
    }
}