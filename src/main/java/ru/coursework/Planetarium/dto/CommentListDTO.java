package ru.coursework.Planetarium.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CommentListDTO {
    private Long id;
    private String comment;
    private LocalDate dateCreation;
    private String authorName;
}
