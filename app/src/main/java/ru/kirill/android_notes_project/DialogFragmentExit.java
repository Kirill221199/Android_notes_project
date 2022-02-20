package ru.kirill.android_notes_project;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogFragmentExit extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return         new AlertDialog.Builder(requireContext())
                .setTitle("Are you sure to exit?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    showToast("See you!");
                    requireActivity().finish();
                })
                .setNegativeButton("No", (dialog, which) -> {
                    showToast("Thank you for staying");
                })
                .setNeutralButton("Don't know",(dialog, which) -> {
                    showToast("It would be necessary to decide already");
                })
                .show();

    }

    protected void showToast(String message){
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}
