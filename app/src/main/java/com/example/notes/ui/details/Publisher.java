package com.example.notes.ui.details;

import com.example.notes.domain.Note;
import com.example.notes.ui.Observer;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    private List<Observer> observers = new ArrayList<>();

    public void subscribe(Observer observer){
        observers.add(observer);
    }

    public void unsubscribe(Observer observer){
        observers.remove(observer);
    }

    public void notify(Note note){
        for(Observer observer: observers){
            observer.updateNote(note);
        }
    }
}
