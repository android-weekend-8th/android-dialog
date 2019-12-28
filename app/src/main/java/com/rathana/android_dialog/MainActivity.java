package com.rathana.android_dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class MainActivity extends AppCompatActivity {

    Button buttonMessageDialog;
    Button buttonSingleChoice;
    Button buttonRadioButtonList;
    Button buttonCheckboxList;
    Button buttonDialogFragment;
    Button buttonCustomDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonMessageDialog = findViewById(R.id.buttonMessageDialog);
        buttonSingleChoice = findViewById(R.id.buttonSingleShoice);
        buttonRadioButtonList = findViewById(R.id.buttonRadioListDialog);
        buttonCheckboxList = findViewById(R.id.buttonCheckboxList);
        buttonDialogFragment = findViewById(R.id.buttonDialogFragment);
        buttonCustomDialog = findViewById(R.id.buttonCustomDialog);

        buttonMessageDialog.setOnClickListener(v -> {
            showMessageDialog();
        });

        buttonSingleChoice.setOnClickListener(view -> showSingleChoice());

        buttonRadioButtonList.setOnClickListener(view -> showRadioButtonList());

        buttonCheckboxList.setOnClickListener(view -> showCheckboxList());

        buttonDialogFragment.setOnClickListener(view -> {
            DialogFragment dialog = MessageDialog.getInstance(
                    "Dialog Fragment",
                    "create dialog in dialog fragment"
            );

            dialog.show(getSupportFragmentManager(), "dialog message");
        });

        buttonCustomDialog.setOnClickListener(view ->
        {
            showCustomDialog();
        });
    }

    private void showMessageDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Notification");
        builder.setMessage("new information!!!!");
        builder.setIcon(R.drawable.ic_message_black_24dp);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //todo
                dialogInterface.dismiss();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setNeutralButton("Later", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //todo
            }
        });

        Dialog dialog = builder.create();
        dialog.show();

    }

    private String[] items = {"food", "drink", "beer", "wine", "..."};

    private void showSingleChoice() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Category");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(
                        MainActivity.this,
                        "" + items[i],
                        Toast.LENGTH_SHORT).show();
            }

        });

        builder.create().show();
    }

    static int chooseItem = 0;

    private void showRadioButtonList() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Category");
        builder.setSingleChoiceItems(items, chooseItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                chooseItem = i;
                Toast.makeText(MainActivity.this,
                        items[i],
                        Toast.LENGTH_SHORT).show();
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

    String[] products = {"Samsung", "iPhone 11 pro", "OOPO Reno2", "Laptop", "game Desktop"};
    boolean[] checked = {true, false, false, false, false};

    private void showCheckboxList() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("product list");
        builder.setMultiChoiceItems(products, checked,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean checked) {
                        //MainActivity.this.checked[i] = checked;
                        if (checked) {
                            Toast.makeText(MainActivity.this,
                                    products[i] + " choose", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this,
                                    products[i] + "is not choose", Toast.LENGTH_SHORT).show();
                        }

                    }
                }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.create().show();
    }

    private void showCustomDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Custom dialog");
        View view = LayoutInflater.from(this)
                .inflate(R.layout.layout_custom_dialog,
                        null);

        EditText userName = view.findViewById(R.id.username);
        EditText password = view.findViewById(R.id.password);

        builder.setView(view);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String name = userName.getText().toString();
                String pass = password.getText().toString();
                Toast.makeText(MainActivity.this,
                        "username =" + name + " password =" + pass,
                        Toast.LENGTH_SHORT).show();
            }
        });

        builder.create().show();

    }
}
