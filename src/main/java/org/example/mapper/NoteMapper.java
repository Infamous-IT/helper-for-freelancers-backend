package org.example.mapper;

import org.example.dto.NoteDTO;
import org.example.model.Note;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    NoteDTO noteToNoteDTO(Note note);

    List<NoteDTO> noteToNoteDTOList(List<Note> noteList);

    Note noteDTOToNote(NoteDTO noteDTO);

}
