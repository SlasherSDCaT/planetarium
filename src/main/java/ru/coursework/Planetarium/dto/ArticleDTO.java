package ru.coursework.Planetarium.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArticleDTO {
    private long id;
    private String title;
    private String description;
}