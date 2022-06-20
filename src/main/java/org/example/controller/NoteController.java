package org.example.controller;

import org.example.dto.NoteDTO;
import org.example.service.NotesService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = NoteController.BASE_URL)
public class NoteController {

    public static final String BASE_URL = ServiceAPIUrl.VERSION_PATH + "/notes";

    private final NotesService notesService;

    public NoteController(NotesService notesService) {
        this.notesService = notesService;
    }

    @GetMapping
    public List<NoteDTO> getAll() {
        return notesService.getAll();
    }

    @GetMapping(value = "/{id}")
    public NoteDTO getById(@PathVariable UUID id) {
        return notesService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NoteDTO create(@Valid @RequestBody NoteDTO noteDTO) {
        notesService.create(noteDTO);
        return noteDTO;
    }

    @PutMapping(value = "/{id}")
    public NoteDTO update(@Valid @RequestBody NoteDTO noteDTO) {
        notesService.update(noteDTO);
        return noteDTO;
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable UUID id) {
        notesService.delete(id);
    }
}
