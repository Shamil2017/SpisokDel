package com.example.spisokdel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AddNoteActivity extends AppCompatActivity {
    private EditText editTextTitle;
    private EditText editTextDescription;
    private RadioGroup radioGroupPriority;
    private Button buttonSaveNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);
        radioGroupPriority = findViewById(R.id.radioGroupPriority);
        buttonSaveNote = findViewById(R.id.buttonSaveNote);
        buttonSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editTextTitle.getText().toString().trim();
                String description = editTextDescription.getText().toString().trim();
                int radioButtonId = radioGroupPriority.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(radioButtonId);
                int priority = Integer.parseInt(radioButton.getText().toString());
                Note note = new Note(1,title,priority);
                MainActivity.notes.add(note);
                Intent intent = new Intent(getBaseContext(),
                        MainActivity.class);
                startActivity(intent);
            }
        });

    }

}