package org.example.service;

import org.example.dao.NoteDAOImpl;
import org.example.dto.NoteDTO;
import org.example.mapper.NoteMapper;
import org.example.model.Note;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotesServiceImplTest {

    @InjectMocks
    NotesServiceImpl notesService;

    @Mock
    NoteDAOImpl noteDAO;

    @Autowired
    private NoteMapper noteMapper;

    @Test
    void getAll() {
        List<Note> noteList = new ArrayList<>();
        Note note1 = new Note("This is title 1", "This is description for title 1");
        Note note2 = new Note("This is title 2", "This is description for title 2");
        Note note3 = new Note("This is title 3", "This is description for title 3");

        noteList.add(note1);
        noteList.add(note2);
        noteList.add(note3);

        when(noteDAO.getAll()).thenReturn(noteList);

        List<NoteDTO> noteDTOList = notesService.getAll();

        assertEquals(3, noteDTOList.size());
        verify(noteDAO, times(1)).getAll();
    }

    @Test
    void update() {
        Note note = new Note("This is title", "This is description for title");
        notesService.update(noteMapper.noteToNoteDTO(note));

        assertEquals("This is title", note.getTitle());
        assertEquals("This is description for title", note.getDescription());

        verify(noteDAO, times(1)).update(note);
    }

    @Test
    void create() {
        Note note = new Note("This is title", "This is description for title");
        notesService.create(noteMapper.noteToNoteDTO(note));

        assertEquals("This is title", note.getTitle());
        assertEquals("This is description for title", note.getDescription());

        verify(noteDAO, times(1)).create(note);
    }

    @Test
    void getById() {
        when(noteDAO.getById(UUID.fromString("65fe885a-35ef-4105-9d06-2373759b2c13")))
                .thenReturn(new Note("Title 1", "Description 1"));

        NoteDTO noteDTO = notesService.getById(UUID.fromString("65fe885a-35ef-4105-9d06-2373759b2c13"));

        assertEquals("Title 1", noteDTO.getTitle());
        assertEquals("Description 1", noteDTO.getDescription());
    }


    @Test
    void delete() {
        UUID id = UUID.fromString("65fe885a-35ef-4105-9d06-2373759b2c15");

        willDoNothing().given(noteDAO).delete(id);

        notesService.delete(id);

        verify(noteDAO, times(1)).delete(id);
    }
}