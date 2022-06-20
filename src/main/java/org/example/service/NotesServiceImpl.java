package org.example.service;

import org.example.dao.NoteDAOImpl;
import org.example.dto.NoteDTO;
import org.example.mapper.NoteMapper;
import org.example.model.Note;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Service
public class NotesServiceImpl implements NotesService {

    private final NoteDAOImpl noteDAO;
    private final NoteMapper noteMapper;

    public NotesServiceImpl(NoteDAOImpl noteDAO, NoteMapper noteMapper) {
        this.noteDAO = noteDAO;
        this.noteMapper = noteMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<NoteDTO> getAll() {
        return noteMapper.noteToNoteDTOList(noteDAO.getAll());
    }

    @Transactional
    @Override
    public NoteDTO update(@Valid NoteDTO noteDTO) {
        Note note = noteMapper.noteDTOToNote(noteDTO);
        noteDAO.update(note);
        return noteDTO;
    }

    @Transactional
    @Override
    public NoteDTO create(@Valid NoteDTO noteDTO) {
        Note note = noteMapper.noteDTOToNote(noteDTO);
        Note createdNote = noteDAO.create(note);
        return noteMapper.noteToNoteDTO(createdNote);
    }

    @Transactional(readOnly = true)
    @Override
    public NoteDTO getById(UUID uuid) {
        Note note = noteDAO.getById(uuid);

        if (note == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note with id " + uuid + " was not found.");
        }
        return noteMapper.noteToNoteDTO(note);
    }

    @Transactional
    @Override
    public void delete(UUID uuid) {
        noteDAO.delete(uuid);
    }
}
