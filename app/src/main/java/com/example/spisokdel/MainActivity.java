package com.example.spisokdel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayoutNotes;
    private FloatingActionButton buttonAdd;
    public static final ArrayList<Note> notes = new ArrayList<>();

    private  void initViews()
    {
        linearLayoutNotes = findViewById(R.id.linearLayoutNotes);
        buttonAdd = findViewById(R.id.buttonAddNode);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        Random random = new Random();
       for (int i=0; i<20; i++)
       {
           Note note = new Note(i, "Note "+i, random.nextInt(3));
           notes.add(note);
       }
       showNotes();
    }

    private  void showNotes()
    {
      for(Note note: notes)
      {
          View view = getLayoutInflater().inflate(R.layout.note_item,
                  linearLayoutNotes, false);
          
          TextView textView = view.findViewById(R.id.textViewNote);
          textView.setText(note.getText());
          int colorResId;
          switch (note.getPriority())
          {
              case 0:
                   colorResId = android.R.color.holo_green_light;
                   break;
              case 1:
                   colorResId = android.R.color.holo_orange_dark;
                   break;
              default:
                  colorResId = android.R.color.holo_red_dark;
                  break;
          }
          int color = ContextCompat.getColor(this, colorResId);
          textView.setBackgroundColor(color);
          linearLayoutNotes.addView(view);
      }
    }

    public void add_note(View view) {

        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivity(intent);
    }
}