package com.example.spisokdelsecond;

import androidx.appcompat.app.AppCompatActivity;

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
    private RadioButton radioButtonLow;
    private RadioButton radioButtonMedium;
    private RadioButton radioButtonHigh;

    private Button buttonSaveNote;

    private Database database = Database.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);
        radioGroupPriority = findViewById(R.id.radioGroupPriority);
        radioButtonLow = findViewById(R.id.radioButtonLow);
        radioButtonMedium  =findViewById(R.id.radioButtonMedium);
        radioButtonHigh  =findViewById(R.id.radioButtonHigh);

        buttonSaveNote = findViewById(R.id.buttonSaveNote);
        buttonSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editTextTitle.getText().toString().trim();
                String description = editTextDescription.getText().toString().trim();
                int radioButtonId = radioGroupPriority.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(radioButtonId);
                int priority = getPriority();
                int id = database.getNotes().size();

                Note note = new Note(id,title,priority);
                database.add(note);

                finish();
            }
        });

    }

    private int getPriority()
    {
        int priority = 2;
        if (radioButtonLow.isChecked())
            priority = 0;
        else if (radioButtonMedium.isChecked())
            priority = 1;
        else if (radioButtonHigh.isChecked())
            priority = 2;
        return  priority;
    }

}