package ru.kirill.android_notes_project;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogFragmentDelete extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setTitle("Sure you want to delete note?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    showToast("Note delete!");
                })
                .setNegativeButton("No", (dialog, which) -> {
                    showToast("Note not deleted.");
                })
                .show();

    }

    protected void showToast(String message){
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}
