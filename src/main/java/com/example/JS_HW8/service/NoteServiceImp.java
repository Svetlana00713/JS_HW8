package com.example.JS_HW8.service;

import com.example.JS_HW8.aspect.LoggedExecution;
import com.example.JS_HW8.entity.Note;
import com.example.JS_HW8.repository.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NoteServiceImp implements NoteService {
    private final NoteRepository noteRepository;

    @LoggedExecution
    @Override
    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    @LoggedExecution
    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @LoggedExecution
    @Override
    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElseThrow(null);
    }

    @LoggedExecution
    @Override
    public Note updateNote(Note note) {
        Note noteById = getNoteById(note.getId());
        noteById.setTitle(note.getTitle());
        noteById.setContent(note.getContent());
        noteById.setCreatedDate(note.getCreatedDate());
        return noteRepository.save(noteById);
    }

    @LoggedExecution
    @Override
    public void deleteNote(Long id) {
        Note noteByID = getNoteById(id);
        noteRepository.delete(noteByID);
    }
}
