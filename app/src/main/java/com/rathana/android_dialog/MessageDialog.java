package com.rathana.android_dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MessageDialog extends DialogFragment {

    private String mTitle;
    private String mMessage;

    public static final String ARG_TITLE = "title";
    public static final String ARG_MESSAGE = "message";

    private MessageDialog(){}
    static MessageDialog getInstance(String title, String message) {
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TITLE, title);
        bundle.putString(ARG_MESSAGE, message);
        MessageDialog messageDialog = new MessageDialog();
        messageDialog.setArguments(bundle);
        return messageDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Bundle bundle= this.getArguments();
        builder.setTitle(bundle.getString(ARG_TITLE));
        builder.setMessage(bundle.getString(ARG_MESSAGE));
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        return builder.create();

    }
}
