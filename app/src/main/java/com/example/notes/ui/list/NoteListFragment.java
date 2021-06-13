package com.example.notes.ui.list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.notes.R;
import com.example.notes.domain.Note;
import com.example.notes.domain.NotesRepository;
import com.example.notes.domain.NotesRepositoryImpl;

import java.util.List;

public class NoteListFragment extends Fragment {

    private NotesRepository notesRepository;

    public interface OnNoteCliked{
        void onNoteCliked(Note note);
    }

    private OnNoteCliked onNoteCliked;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnNoteCliked){
            onNoteCliked = (OnNoteCliked) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onNoteCliked = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notesRepository = new NotesRepositoryImpl();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout notesList = view.findViewById(R.id.notes_list_container);
        List<Note> notes = notesRepository.getNotes();

        for (Note note: notes){
            View itemView = LayoutInflater.from(requireContext()).inflate(R.layout.item_notes, notesList, false);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onNoteCliked != null){
                        onNoteCliked.onNoteCliked(note);
                    }
                }
            });

            TextView noteName = itemView.findViewById(R.id.notes_name);
            noteName.setText(note.getName());
            notesList.addView(itemView);
        }

    }
}
