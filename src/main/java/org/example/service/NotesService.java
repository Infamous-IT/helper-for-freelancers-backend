package org.example.service;

import org.example.dto.NoteDTO;

import java.util.List;
import java.util.UUID;

public interface NotesService {

    List<NoteDTO> getAll();

    NoteDTO update(NoteDTO noteDTO);

    NoteDTO create(NoteDTO noteDTO);

    NoteDTO getById(UUID uuid);

    void delete(UUID uuid);
}
