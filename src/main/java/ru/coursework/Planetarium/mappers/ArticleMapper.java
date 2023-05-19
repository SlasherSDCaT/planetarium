package ru.coursework.Planetarium.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.coursework.Planetarium.dto.ArticleDTO;
import ru.coursework.Planetarium.entity.Article;

@Mapper
public interface ArticleMapper {
    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);
    ArticleDTO converArticleToArticleDTO(Article article);
}
