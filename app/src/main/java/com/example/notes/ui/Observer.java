package com.example.notes.ui;

import com.example.notes.domain.Note;

public interface Observer {
    void updateNote(Note note);
}
