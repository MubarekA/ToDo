package com.example.todo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {
    EditText etItem;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etItem = findViewById((R.id.etItem));
        btnSave = findViewById((R.id.btnSave));
        getSupportActionBar().setTitle("Edit item");
        etItem.setText(getIntent().getStringExtra(MainActivity.KEY_ITEM_TEXT));
        //used when the user is done editing, they clock save
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create intenet containg the results
                Intent intent = new Intent();

                //pass results of the edit
                intent.putExtra(MainActivity.KEY_ITEM_TEXT, etItem.getText().toString());
                intent.putExtra(MainActivity.KEY_ITEM_POSITION,getIntent().getExtras().getInt(MainActivity.KEY_ITEM_POSITION));
                // set the result!
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}