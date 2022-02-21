package ru.kirill.android_notes_project;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class NoteFragment extends Fragment {

    public static final String CURRENT_NOTE = "note_current";
    private Note currentNote;

    public static NoteFragment newInstance() {
        NoteFragment fragment = new NoteFragment();
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
                                    Toast toastCopy =
                                            Toast.makeText(requireContext().getApplicationContext(),
                                            "Copy is done", Toast.LENGTH_SHORT);
                                    toastCopy.show();
                                    return true;
                                }
                                case (R.id.action_delete_fragment) : {
                                    new DialogFragmentDelete().show(getActivity().getSupportFragmentManager(),"tag");
                                    return true;
                                }
                                case (R.id.action_archive_fragment) : {
                                    Toast toastArchive =
                                            Toast.makeText(requireContext().getApplicationContext(),
                                            "Add to the archive", Toast.LENGTH_SHORT);
                                    toastArchive.show();
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
        NoteContentFragment note_contentFragment = NoteContentFragment.newInstance(currentNote);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_note_content,
                note_contentFragment).commit();
    }

    protected void showPort(){
        NoteContentFragment note_contentFragment = NoteContentFragment.newInstance(currentNote);
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.frame_note,
                note_contentFragment).addToBackStack("").commit();
    }



}