package ru.kirill.android_notes_project;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Note_content_fragment extends Fragment {

    public static final String ARG_NOTE = "note";
    protected Note note;

    public static Note_content_fragment newInstance(Note note) {
        Note_content_fragment fragment = new Note_content_fragment();
        Bundle bundle= new Bundle();
        bundle.putParcelable(ARG_NOTE,note);
        fragment.setArguments(bundle);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        note = getArguments().getParcelable(ARG_NOTE);
        String[] notes = getResources().getStringArray(R.array.note_content);
        TextView tv = view.findViewById(R.id.tv_content);
        tv.setText(notes[note.getIndex()]);
    }

}