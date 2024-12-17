package sk.ukf.Zaverecna_praca.dto;

import sk.ukf.Zaverecna_praca.entity.Conference;

import java.time.LocalDateTime;

public class NotesDTO {
    private Long id;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Conference conference;
}
