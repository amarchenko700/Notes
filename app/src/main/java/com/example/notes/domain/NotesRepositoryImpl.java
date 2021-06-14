package com.example.notes.domain;

import android.content.Context;

import com.example.notes.R;

import java.util.ArrayList;
import java.util.List;

public class NotesRepositoryImpl implements NotesRepository {

    @Override
    public List<Note> getNotes(){

        ArrayList<Note> result = new ArrayList<>();
        result.add(new Note(R.string.Заметка1, R.string.НазваниеЗаметка1));
        result.add(new Note(R.string.Заметка2, R.string.НазваниеЗаметка2));
        result.add(new Note(R.string.Заметка3, R.string.НазваниеЗаметка3));
        result.add(new Note(R.string.Заметка4, R.string.НазваниеЗаметка4));
        result.add(new Note(R.string.Заметка5, R.string.НазваниеЗаметка5));
        return result;
    };

}
