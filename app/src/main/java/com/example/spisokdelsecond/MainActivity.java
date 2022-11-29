package com.example.spisokdelsecond;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycleViewNotes;
    private FloatingActionButton buttonAdd;
    private Database database = Database.getInstance();
    private NotesAdapter notesAdapter;

    private  void initViews()
    {
        recycleViewNotes = findViewById(R.id.recycleViewNotes);
        buttonAdd = findViewById(R.id.buttonAddNode);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        notesAdapter = new NotesAdapter();
        notesAdapter.setOnNoteClickListener(new NotesAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClick(Note note) {

            }
        });
        recycleViewNotes.setAdapter(notesAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                 ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Note note = notesAdapter.getNotes().get(position);
                database.remove(note.getId());
                showNotes();
            }
        });
        itemTouchHelper.attachToRecyclerView(recycleViewNotes);

    }

    @Override
    protected void onResume() {
        super.onResume();
        showNotes();
    }

    private  void showNotes()
    {
      notesAdapter.setNotes(database.getNotes());
    }

    public void add_note(View view) {

        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivity(intent);
    }
}