package ru.kirill.android_notes_project;

import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Bundle;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class Note_fragment extends Fragment {

    public static final String CURRENT_NOTE = "note_current";
    private Note currentNote;

    public static Note_fragment newInstance() {
        Note_fragment fragment = new Note_fragment();
        return fragment;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(CURRENT_NOTE,currentNote);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(savedInstanceState!=null){
            currentNote = savedInstanceState.getParcelable(CURRENT_NOTE);
        }else{
            currentNote = new Note(0);
        }
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            showLand();
        }
        initView(view);
    }

    private void initView(View view)  {
        String[] notes = getResources().getStringArray(R.array.notes);
        for (int i=0;i<notes.length;i++){
            String noteName = notes[i];
            TextView textView = new TextView(getContext());
            textView.setTextSize(30f);
            textView.setTextColor(Color.rgb(0,0,0));
            textView.setText(noteName);
            ((LinearLayout) view).addView(textView);
            final int finalI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentNote = new Note(finalI);
                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                        showLand();
                    }else{// портрет
                        showPort();
                    }
                }
            });
            textView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    PopupMenu popupMenu = new  PopupMenu(requireContext(),view, Gravity.CENTER);
                    requireActivity().getMenuInflater().inflate(R.menu.note_popup_menu,
                            popupMenu.getMenu());

                    popupMenu.setOnMenuItemClickListener (new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            switch (menuItem.getItemId()){
                                case (R.id.action_copy_fragment) : {
                                    Toast toast_copy =
                                            Toast.makeText(requireContext().getApplicationContext(),
                                            "Copy is done", Toast.LENGTH_SHORT);
                                    toast_copy.show();
                                    return true;
                                }
                                case (R.id.action_delete_fragment) : {
                                    // TODO реализовать настройки
                                    return true;
                                }
                                case (R.id.action_archive_fragment) : {
                                    Toast toast_archive =
                                            Toast.makeText(requireContext().getApplicationContext(),
                                            "Add to the archive", Toast.LENGTH_SHORT);
                                    toast_archive.show();
                                    return true;
                                }
                            }
                            return true;
                        }
                    });

                    popupMenu.show();
                    return true;
                }
            });

        }
    }


    protected void showLand(){
        Note_content_fragment note_content_fragment =Note_content_fragment.newInstance(currentNote);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_note_content,
                note_content_fragment).commit();
    }

    protected void showPort(){
        Note_content_fragment note_content_fragment =Note_content_fragment.newInstance(currentNote);
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.frame_note,
                note_content_fragment).addToBackStack("").commit();
    }
}