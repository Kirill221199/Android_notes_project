package ru.kirill.android_notes_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(savedInstanceState == null) {
            Note_fragment note_fragment = new Note_fragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_note, note_fragment).commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case (R.id.action_about_developer) : {
                Developer_info developer_info = new Developer_info();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_note,
                        developer_info).addToBackStack(" ").commit();
            }
            case (R.id.action_settings) : {
                // TODO реализовать настройки
            }
        }
        return super.onOptionsItemSelected(item);
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