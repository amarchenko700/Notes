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
import com.example.notes.ui.PublisherHolder;
import com.example.notes.ui.details.Publisher;

import java.util.List;

public class NoteListFragment extends Fragment {

    private NotesRepository notesRepository;
    private OnNoteCliked onNoteCliked;
    private Publisher publisher;

    public interface OnNoteCliked{
        void onNoteCliked(Note note);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnNoteCliked){
            onNoteCliked = (OnNoteCliked) context;
        }

        if (context instanceof PublisherHolder){
            publisher = ((PublisherHolder) context).getPublisher();
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
        // 01:05:57 для создания списка устанавливаем фрагменту View
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // 01:10:53 после предоставления вьюхи в методе onCreateView вызывается данный метод
        super.onViewCreated(view, savedInstanceState);

        LinearLayout notesList = view.findViewById(R.id.notes_list_container);
        // 01:14:05
        List<Note> notes = notesRepository.getNotes();

        for (Note note: notes){
            View itemView = LayoutInflater.from(requireContext()).inflate(R.layout.item_notes, notesList, false);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onNoteCliked != null){
                        onNoteCliked.onNoteCliked(note);
                    }
                    if (publisher != null){
                        publisher.notify(note);
                    }
                }
            });

            TextView noteName = itemView.findViewById(R.id.notes_name);
            noteName.setText(note.getName());
            notesList.addView(itemView);
        }

    }
}
