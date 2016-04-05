package com.bleedev.mypkb;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = (EditText) findViewById(R.id.editText);

        GridView gridview = (GridView) findViewById(R.id.gridView);
        gridview.setAdapter(new TextAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    final int position, long id) {
                new AlertDialog.Builder(MainActivity.this)
                        .setMessage("Are you sure about the update?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                                AppUtil.updateKeyText(MainActivity.this, position, editText.getText().toString());
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

            }
        });

    AppUtil.saveAll(MainActivity.this);

    }
}
