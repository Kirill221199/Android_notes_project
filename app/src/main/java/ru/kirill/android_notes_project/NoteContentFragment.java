package ru.kirill.android_notes_project;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class NoteContentFragment extends Fragment {

    public static final String ARG_NOTE = "note";
    protected Note note;
    protected TextView data;

    public static NoteContentFragment newInstance(Note note) {
        NoteContentFragment fragment = new NoteContentFragment();
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
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_menu, menu);
        menu.findItem(R.id.action_settings).setVisible(false);
        menu.findItem(R.id.action_about_developer).setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case (R.id.action_settings_fragment) : {
                // TODO реализовать настройки
                return true;
            }
            case (R.id.action_copy_fragment) : {
                Toast toastCopy =
                        Toast.makeText(requireContext().getApplicationContext(),
                                "Copy is done", Toast.LENGTH_SHORT);
                toastCopy.show();
                return true;
            }
            case (R.id.action_send_fragment) : {
                // TODO реализовать отправку заметки
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        note = getArguments().getParcelable(ARG_NOTE);
        String[] notes = getResources().getStringArray(R.array.notesContent);
        TextView tv = view.findViewById(R.id.tv_content);
        tv.setText(notes[note.getIndex()]);

        data = view.findViewById(R.id.tv_data);
        data.setText("click to set the date");
        data.setGravity(Gravity.CENTER);
        data.setBackgroundColor(Color.rgb(192,192,192));
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int yy = calendar.get(Calendar.YEAR);
                int mm = calendar.get(Calendar.MONTH);
                int dd = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePicker = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(
                            DatePicker view, int year, int monthOfYear,
                            int dayOfMonth) {
                        String date =
                                "Date: "+ String.valueOf(year) +"-"+ String.valueOf(monthOfYear)
                                +"-"+ String.valueOf(dayOfMonth);
                        data.setText(date);
                    }
                }, yy, mm, dd);
                datePicker.show();
            }
        });
    }

}