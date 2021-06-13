package com.example.notes.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.notes.R;
import com.example.notes.domain.Note;
import com.example.notes.ui.details.NoteDetailsActivity;
import com.example.notes.ui.details.NoteDetailsFragment;
import com.example.notes.ui.list.NoteListFragment;

public class MainActivity extends AppCompatActivity implements NoteListFragment.OnNoteCliked {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onNoteCliked(Note note) {
        //Toast.makeText(this, note.getName(), Toast.LENGTH_SHORT).show();
        boolean isLandscape = getResources().getBoolean(R.bool.isLandscape);
        if (isLandscape) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.notes_details_fragment, NoteDetailsFragment.newInstance(note))
                    .commit();
        } else {
            Intent intent = new Intent(this, NoteDetailsActivity.class);
            intent.putExtra(NoteDetailsActivity.ARG_NOTE, note);
            startActivity(intent);
        }
    }
}