package com.example.lyrics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;

public class EditTextActivity extends AppCompatActivity {
    EditText editText;
    Button saveButton;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        editText = findViewById(R.id.editText);
        saveButton = findViewById(R.id.saveButton);

        // Get the text from the intent
        Intent intent = getIntent();
        String text = intent.getStringExtra("text");
        editText.setText(text);

        // Set the save button click listener
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editedText = editText.getText().toString();
                Intent resultIntent = new Intent();
                resultIntent.putExtra("editedText", editedText);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }




}
