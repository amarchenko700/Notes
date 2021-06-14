package com.example.notes.ui.details;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notes.R;
import com.example.notes.domain.Note;
import com.example.notes.ui.Observer;
import com.example.notes.ui.PublisherHolder;

public class NoteDetailsFragment extends Fragment implements Observer {

    private static final String ARG_NOTE = "ARG_NOTE";
    private Publisher publisher;

    public static NoteDetailsFragment newInstance(Note note){
        NoteDetailsFragment fragment = new NoteDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_NOTE, note);
        fragment.setArguments(bundle);
        return fragment;
    }

    public NoteDetailsFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView noteName = view.findViewById(R.id.note_name);
        TextView noteDescription = view.findViewById(R.id.note_detail);
        TextView noteDateCreated = view.findViewById(R.id.note_dateCreated);

        Note note = getArguments().getParcelable(ARG_NOTE);
        noteName.setText(note.getName());
        noteDescription.setText(note.getDescription());
        noteDateCreated.setText(note.getDateCreated().toString());
    }

    @Override
    public void updateNote(Note note) {
        Toast.makeText(requireContext(), note.getName(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof PublisherHolder){
            publisher = ((PublisherHolder) context).getPublisher();
            publisher.subscribe(this);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(publisher != null){
            publisher.unsubscribe(this);
        }
    }
}